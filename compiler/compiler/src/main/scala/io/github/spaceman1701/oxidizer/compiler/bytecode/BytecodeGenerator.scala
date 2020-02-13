package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.ast
import io.github.spaceman1701.oxidizer.compiler.ast.{AST, ArrayIndex, AssignStmt, BinaryOperator, Binop, BitwiseAnd, BitwiseOr, BranchStmt, BreakStmt, CompareEq, CompareGE, CompareGT, CompareLE, CompareLT, CompareNE, Compliment, ContinueStmt, Decrement, DestructerAssignStmt, Divide, Elif, EmbeddedExpr, Expr, ExprStmt, FloatLit, ForLoop, FunCall, IfBranch, Increment, IntLit, LeftShift, ListComp, ListenExpr, Lit, Literal, LogicalAnd, LogicalOr, LoopStmt, Minus, Modulo, Multiply, Negate, Parens, Plus, Power, ReturnStmt, RightShift, SendExpr, SpawnStmt, Stmt, StringLit, SwitchBranch, Ternary, TextPart, UnaryOperator, Unop, UnsignedRightShift, Var, WhileLoop, XOr}
import io.github.spaceman1701.oxidizer.compiler.util._

import scala.collection.mutable.ListBuffer

class BytecodeGenerator {

  val stringConstants = new NameBuffer()
  val localVariables = new NameBuffer()

  val bytecodeBuffer: ListBuffer[Instruction] = ListBuffer[Instruction]()

  var currentLoopCtx: LoopContext = null

  def isHeapIdent(ident: String): Boolean = {
    return ident.contains(".")
  }

  def generate(block: List[Stmt]): Unit = {
    for (stmt <- block) {
      stmt match {
        case ExprStmt(expr) => emitExpr(expr)
        case AssignStmt(ident, expr) =>
          val index = localVariables.add(ident)
          emitExpr(expr) //leaves one on the stack

          if (isHeapIdent(ident)) {
            generateMemberLoad(ident) //load the ref to the member we will store to
            StoreMember >>: this
          } else {
            Store(new U16(index)) >>: this //store as local var
          }

        case DestructerAssignStmt(idents, expr) => ???
        case LoopStmt(loop) =>
          loop match {
            case ForLoop(ident, inExpr, body) => ???
            case WhileLoop(cond, body) => ???
          }
        case BranchStmt(branch) =>
          branch match {
            case IfBranch(cond, ifBody, elifs, elseBody) => emitIfElse(cond, ifBody, elifs, elseBody)
            case SwitchBranch(cond, cases) => ???
          }
        case ReturnStmt(expr) =>
          emitExpr(expr)
          Ret >>: this
        case BreakStmt =>
        case ContinueStmt => ???
        case SpawnStmt(expr) => ???
      }
    }
  }

  def emitIfElse(cond: Expr, ifBody: List[Stmt], elifs: List[Elif], elseBody: Option[List[Stmt]]) = {
    val skipIndicies = ListBuffer[Int]()
    emitExpr(cond) //leaves bool on the stack
    val jumpIns = NoOp >>: this
    generate(ifBody)
    skipIndicies.addOne(NoOp >>: this) //jump past rest of branch
    val afterIfIns = bytecodeBuffer.size //index of the next instruction generated
    bytecodeBuffer(jumpIns) = IfFalse(new U32(afterIfIns))
    for (elif <- elifs) {
      emitExpr(elif.cond)
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

  def emitExpr(expr: Expr): Unit = {
    expr match {
      case Var(ident) =>
        if (isHeapIdent(ident)) {
          generateMemberLoad(ident)
        } else {
          LoadVar(new U16(localVariables.add(ident))) >>: this
        }
      case ArrayIndex(arrayExpr, start, end, step) => ???
      case Lit(literal) => convertLiteral(literal)
      case FunCall(ident, params) => emitFunctionCall(ident, params)
      case Parens(expr) => emitExpr(expr)
      case ListComp(comp) => ???
      case Unop(expr, op) => emitUnOp(expr, op)
      case Binop(first, second, op) => emitBinOp(first, second, op)
      case Ternary(cond, ifExpr, elseExpr) =>
        emitIfElse(cond, List(ExprStmt(ifExpr)), List(), Some(List(ExprStmt(elseExpr))))
      case SendExpr(expr) => ???
      case ListenExpr(expr) => ???
    }
  }

  def emitUnOp(expr: Expr, op: UnaryOperator): Unit = {
    op match {
      case ast.Not =>
        emitExpr(expr)
        Not >>: this
      case Compliment =>
        emitExpr(expr)
        BCompliment >>: this
      case Negate =>
        LoadConstInt(0) >>: this
        emitExpr(expr)
        Sub >>: this
      case Increment =>
        emitExpr(expr)
        LoadConstInt(1) >>: this
        Add >>: this
      case Decrement =>
        emitExpr(expr)
        LoadConstInt(1) >>: this
        Sub >>: this
    }
  }

  def emitBinOp(first: Expr, second: Expr, op: BinaryOperator): Unit = {
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
      case CompareNE =>
        emitSimpleBinOp(first, second, CompEq)
        Not >>: this

      case ast.XOr => emitSimpleBinOp(first, second, XOr)
      case BitwiseAnd => emitSimpleBinOp(first, second, BAnd)
      case BitwiseOr => emitSimpleBinOp(first, second, BOr)
      case UnsignedRightShift => emitSimpleBinOp(first, second, URightShift)
      case ast.LeftShift => emitSimpleBinOp(first, second, LeftShift)
      case ast.RightShift => emitSimpleBinOp(first, second, RightShift)
      case ast.Modulo => emitSimpleBinOp(first, second, Modulo)
    }
  }

  def emitFunctionCall(ident: String, params: List[Expr]): Unit = {
    for (p <- params) {
      emitExpr(p)
    }
    LoadConstInt(params.length) >>: this
    if (isHeapIdent(ident)) {
      generateMemberLoad(ident)
    }
    Call >>: this
  }

  def emitSimpleBinOp(first: Expr, second: Expr, ins: Instruction): Unit = {
    emitExpr(first)
    emitExpr(second)
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
              emitExpr(expr)
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
