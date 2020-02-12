package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.ast
import io.github.spaceman1701.oxidizer.compiler.ast.{AST, ArrayIndex, AssignStmt, BinaryOperator, Binop, BitwiseAnd, BitwiseOr, BranchStmt, BreakStmt, CompareEq, CompareGE, CompareGT, CompareLE, CompareLT, CompareNE, ContinueStmt, DestructerAssignStmt, Divide, Elif, EmbeddedExpr, Expr, ExprStmt, FloatLit, FunCall, IfBranch, IntLit, LeftShift, ListComp, ListenExpr, Lit, Literal, LogicalAnd, LogicalOr, LoopStmt, Minus, Modulo, Multiply, Parens, Plus, Power, ReturnStmt, RightShift, SendExpr, SpawnStmt, Stmt, StringLit, SwitchBranch, Ternary, TextPart, Unop, UnsignedRightShift, Var, XOr}
import io.github.spaceman1701.oxidizer.compiler.util._

import scala.collection.mutable.ListBuffer

class BytecodeGenerator {

  val stringConstants = new NameBuffer()
  val localVariables = new NameBuffer()

  val bytecodeBuffer = ListBuffer[Instruction]()


  def isHeapIdent(ident: String): Boolean = {
    return ident.contains(".")
  }

  def generate(block: List[Stmt]): Unit = {
    for (stmt <- block) {
      stmt match {
        case ExprStmt(expr) => convertExpr(expr)
        case AssignStmt(ident, expr) =>
          val index = localVariables.add(ident)
          convertExpr(expr) //leaves one on the stack

          if (isHeapIdent(ident)) {
            generateMemberLoad(ident) //load the ref to the member we will store to
            StoreMember >>: this
          } else {
            Store(new U16(index)) >>: this //store as local var
          }

        case DestructerAssignStmt(idents, expr) => ???
        case LoopStmt(loop) =>
        case BranchStmt(branch) =>
          branch match {
            case IfBranch(cond, ifBody, elifs, elseBody) => emitIfElse(cond, ifBody, elifs, elseBody)
            case SwitchBranch(cond, cases) => ???
          }
        case ReturnStmt(expr) => ???
        case BreakStmt => ???
        case ContinueStmt => ???
        case SpawnStmt(expr) => ???
      }
    }
  }

  def emitIfElse(cond: Expr, ifBody: List[Stmt], elifs: List[Elif], elseBody: Option[List[Stmt]]) = {
    val skipIndicies = ListBuffer[Int]()
    convertExpr(cond) //leaves bool on the stack
    val jumpIns = NoOp >>: this
    generate(ifBody)
    skipIndicies.addOne(NoOp >>: this) //jump past rest of branch
    val afterIfIns = bytecodeBuffer.size //index of the next instruction generated
    bytecodeBuffer(jumpIns) = IfFalse(new U32(afterIfIns))
    for (elif <- elifs) {
      convertExpr(elif.cond)
      val jumpIns = NoOp >>: this
      generate(elif.body)
      val afterIfIns = bytecodeBuffer.size //index of the next instruction generated
      bytecodeBuffer(jumpIns) = IfFalse(new U32(afterIfIns))
      skipIndicies.addOne(NoOp >>: this)
    }
    elseBody.foreach(generate)
    val endOfBranch = bytecodeBuffer.size
    skipIndicies.foreach(bytecodeBuffer(_) = Jump(new U32(endOfBranch)))
  }

  def convertExpr(expr: Expr): Unit = {
    expr match {
      case Var(ident) =>
        if (isHeapIdent(ident)) {
          generateMemberLoad(ident)
        } else {
          LoadVar(new U16(localVariables.add(ident))) >>: this
        }
      case ArrayIndex(arrayExpr, start, end, step) => ???
      case Lit(literal) => convertLiteral(literal)
      case FunCall(ident, params) =>
        for (p <- params) {
          convertExpr(p)
        }
        LoadConstInt(params.length) >>: this
        if (isHeapIdent(ident)) {
          generateMemberLoad(ident)
        }
        Call >>: this
      case Parens(expr) => convertExpr(expr)
      case ListComp(comp) => ???
      case Unop(expr, op) => ???
      case Binop(first, second, op) => pickBinOp(first, second, op)
      case Ternary(cond, ifExpr, elseExpr) => ???
      case SendExpr(expr) => ???
      case ListenExpr(expr) => ???
    }
  }

  def pickBinOp(first: Expr, second: Expr, op: BinaryOperator): Unit = {
    op match {
      case Plus => emitSimpleBinOp(first, second, Add)
      case Minus => emitSimpleBinOp(first, second, Sub)
      case ast.Concat => emitSimpleBinOp(first, second, Concat)
      case Multiply => emitSimpleBinOp(first, second, Mul)
      case Divide => emitSimpleBinOp(first, second, Div)
      case Power => emitSimpleBinOp(first, second, Pow)
      case LogicalAnd => emitSimpleBinOp(first, second, LAnd)
      case LogicalOr => emitSimpleBinOp(first, second, LOr)
      case CompareEq => emitSimpleBinOp(first, second, CompEq)
      case CompareGT => emitSimpleBinOp(first, second, CompG)
      case CompareLT => emitSimpleBinOp(first, second, CompL)

      case CompareLE => emitSimpleBinOp(second, first, CompG) //a <= b == not (b > a)
      case CompareGE => emitSimpleBinOp(second, first, CompL) //a >= b == not (b < a)

      case _ => ??? //the rest should be implemented as functions
    }
  }

  def emitSimpleBinOp(first: Expr, second: Expr, ins: Instruction): Unit = {
    convertExpr(first)
    convertExpr(second)
    ins >>: this
  }

  def convertLiteral(lit: Literal): Unit = {
    lit match {
      case IntLit(value) => LoadConstInt(value) >>: this
      case FloatLit(value) => LoadConstFloat(value) >>: this
      case StringLit(parts) =>
        for (part <- parts) {
          part match {
            case TextPart(text) =>
              val ptr = stringConstants.add(text)
              LoadConstStr(new U32(ptr)) >>: this
            case EmbeddedExpr(expr) =>
              convertExpr(expr)
          }
        }

        for (_ <- 0 until parts.size - 1) { //generate concats
          Concat >>: this
        }
    }
  }

  def generateMemberLoad(ident: String): Unit = {
    val pieces = ident.split("\\.")
    LoadVar(new U16(localVariables.add(pieces(0)))) >>: this//base of name must be a local variable objref
    for (piece <- pieces.slice(1, pieces.size)) {
      LoadMember >>: this //each member piece is a new objref on the stack
    }
    //result is one objref added to the stack
  }

  def >>: (ins: Instruction): Int = {
    bytecodeBuffer.addOne(ins)
    bytecodeBuffer.size - 1
  }
}
