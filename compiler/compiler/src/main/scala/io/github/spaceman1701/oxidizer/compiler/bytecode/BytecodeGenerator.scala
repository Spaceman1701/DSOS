package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.ast.{AST, ArrayIndex, AssignStmt, Binop, BranchStmt, BreakStmt, ContinueStmt, DestructerAssignStmt, EmbeddedExpr, Expr, ExprStmt, FloatLit, FunCall, IntLit, ListComp, ListenExpr, Lit, Literal, LoopStmt, Parens, ReturnStmt, SendExpr, SpawnStmt, Stmt, StringLit, Ternary, TextPart, Unop, Var}
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
    val insBuffer = ListBuffer[Instruction]()

    for (stmt <- block) {
      stmt match {
        case ExprStmt(expr) => convertExpr(expr)
        case AssignStmt(ident, expr) =>
          val (index, added) = localVariables.add(ident)
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
        case ReturnStmt(expr) =>
        case BreakStmt =>
        case ContinueStmt =>
        case SpawnStmt(expr) =>
      }
    }
  }

  def convertExpr(expr: Expr): Unit = {
    expr match {
      case Var(ident) =>
      case ArrayIndex(arrayExpr, start, end, step) =>
      case Lit(literal) => convertLiteral(literal)
      case FunCall(ident, params) =>
      case Parens(expr) =>
      case ListComp(comp) =>
      case Unop(expr, op) =>
      case Binop(first, second, op) =>
      case Ternary(cond, ifExpr, elseExpr) =>
      case SendExpr(expr) =>
      case ListenExpr(expr) =>
    }
  }

  def convertLiteral(lit: Literal): Unit = {
    lit match {
      case IntLit(value) => addIns(LoadConstInt(value))
      case FloatLit(value) => addIns(LoadConstFloat(value))
      case StringLit(parts) =>
        for (part <- parts) {
          part match {
            case TextPart(text) =>
              val (ptr, _) = stringConstants.add(text)
              addIns(LoadConstStr(new U32(ptr)))
            case EmbeddedExpr(expr) =>
              convertExpr(expr)
          }
        }

        for (_ <- 0 until parts.size - 1) { //generate concats
          addIns(Concat)
        }
    }
  }

  def generateMemberLoad(ident: String): Unit = {
    val pieces = ident.split(".")
    LoadVar(new U16(localVariables.add(pieces(0))._1)) >>: this//base of name must be a local variable objref
    for (piece <- pieces.slice(1, pieces.size)) {
      LoadMember >>: this //each member piece is a new objref on the stack
    }
    //result is one objref added to the stack
  }

  def addIns(ins: Instruction): Unit = {
    bytecodeBuffer.addOne(ins)
  }

  def >>: (ins: Instruction): Unit = {
    bytecodeBuffer.addOne(ins)
  }
}
