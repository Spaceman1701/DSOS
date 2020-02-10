package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.ast.{AST, ArrayIndex, AssignStmt, Binop, BranchStmt, BreakStmt, ContinueStmt, DestructerAssignStmt, EmbeddedExpr, Expr, ExprStmt, FloatLit, FunCall, IntLit, ListComp, ListenExpr, Lit, Literal, LoopStmt, Parens, ReturnStmt, SendExpr, SpawnStmt, Stmt, StringLit, Ternary, TextPart, Unop, Var}
import io.github.spaceman1701.oxidizer.compiler.util._

import scala.collection.mutable.ListBuffer

class BytecodeGenerator {

  val constantStrings = ListBuffer[String]()
  var reverseStringMap = Map[String, U32]()

  val bytecodeBuffer = ListBuffer[Instruction]()

  def generate(block: List[Stmt]): Unit = {
    val insBuffer = ListBuffer[Instruction]()

    for (stmt <- block) {
      stmt match {
        case ExprStmt(expr) => convertExpr(expr)
        case AssignStmt(ident, expr) =>
          convertExpr(expr)
        case DestructerAssignStmt(idents, expr) =>
          convertExpr(expr)
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
              addIns(LoadConstStr(addConstString(text)))
            case EmbeddedExpr(expr) =>
              convertExpr(expr)
          }
        }

        for (_ <- 0 until parts.size - 1) { //generate concats
          addIns(Concat)
        }
    }
  }

  def addConstString(text: String): U32 = {
    if (reverseStringMap.contains(text)) {
      reverseStringMap(text)
    } else {
      constantStrings.addOne(text)
      val partIndex = new U32(constantStrings.size - 1)
      reverseStringMap = reverseStringMap + (text -> partIndex)
      partIndex
    }
  }

  def addIns(ins: Instruction): Unit = {
    bytecodeBuffer.addOne(ins)
  }
}
