package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.ast
import io.github.spaceman1701.oxidizer.compiler.ast.{AST, ArrayIndex, AssignStmt, BinaryOperator, Binop, BitwiseAnd, BitwiseOr, BranchStmt, BreakStmt, CompareEq, CompareGE, CompareGT, CompareLE, CompareLT, CompareNE, Compliment, ContinueStmt, Decrement, DestructerAssignStmt, Divide, Elif, EmbeddedExpr, Expr, ExprStmt, FloatLit, ForLoop, FunCall, FunctionDef, IfBranch, Increment, IntLit, ListComp, ListenExpr, Lit, Literal, LogicalAnd, LogicalOr, LoopStmt, Minus, Multiply, Negate, ObjConstructor, Parens, Plus, Power, ReturnStmt, SendExpr, SpawnStmt, Stmt, StringLit, SwitchBranch, Ternary, TextPart, UnaryOperator, Unop, UnsignedRightShift, Var, WhileLoop}
import io.github.spaceman1701.oxidizer.compiler.util._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class BytecodeGenerator {

  val stringConstants = new NameBuffer()
  val localVariables: mutable.Stack[NameBuffer] = mutable.Stack[NameBuffer]()

  val bytecodeBuffer: ListBuffer[Instruction] = ListBuffer[Instruction]()

  var loopCtxStack: mutable.Stack[LoopContext] = mutable.Stack[LoopContext]()
  private val loopWatcherStack = mutable.Stack[LoopWatchers]()

  def isHeapIdent(ident: String): Boolean = {
    return ident.contains(".")
  }

  def generateFunctionBody(function: FunctionDef): Int = {
    val block = function.body
    val functionStart = bytecodeBuffer.size
    println("function " + function.name + " starts at " + functionStart)

    newLocalCtx()
    function.params.foreach{p =>
      localVar(p)
    }
    generateCurrentCtx(block)
    Ret >>: this
    popLocalCtx()

    functionStart
  }

  def generate(block: List[Stmt]): Unit = {
    newLocalCtx()
    generateCurrentCtx(block)
    popLocalCtx()
  }

  def generateCurrentCtx(block: List[Stmt]): Unit = {
    for (stmt <- block) {
      stmt match {
        case ExprStmt(expr) => emitExpr(expr)
        case AssignStmt(ident, expr) =>
          val index = localVar(ident)
          emitExpr(expr) //leaves one on the stack

          if (isHeapIdent(ident)) {
            generateMemberLoadForHeapStore(ident) //load the ref to the member we will store to
            StoreMember >>: this
          } else {
            Store(new U16(index)) >>: this //store as local var
          }

        case DestructerAssignStmt(idents, expr) => ???
        case LoopStmt(loop) =>
          loopWatcherStack.push(new LoopWatchers())
          loop match {
            case ForLoop(ident, inExpr, body) =>
              emitFunctionCall("__iterator", List(inExpr)) //generate iterator onto stack
              Dup >>: this
              val loopStart = bytecodeBuffer.size
              emitIteratorValue()
              val identVar = new U16(localVar(ident))
              Store(identVar) >>: this
              generate(body)
              emitNextIterator()
              Dup >>: this
              //emitIteratorOver()
              val iteratorOverJump = NoOp >>: this
              Jump(new U32(loopStart))
              val endOfLoop = bytecodeBuffer.size
              bytecodeBuffer(iteratorOverJump) = IfFalse(new U32(endOfLoop))
              Consume >>: this

              loopWatcherStack.top.breaks.foreach(ins => {
                bytecodeBuffer(ins) = Jump(new U32(endOfLoop))
              })

              loopWatcherStack.top.continues.foreach(ins => {
                bytecodeBuffer(ins) = Jump(new U32(loopStart))
              })
            case WhileLoop(cond, body) =>
              val loopStart = bytecodeBuffer.size
              println("loop starts at " + loopStart)
              emitExpr(cond)
              val jumpIns = NoOp >>: this
              generate(body)
              Jump(new U32(loopStart)) >>: this
              val loopEnd = bytecodeBuffer.size
              bytecodeBuffer(jumpIns) = IfFalse(new U32(loopEnd))

              loopWatcherStack.top.breaks.foreach(ins => {
                bytecodeBuffer(ins) = Jump(new U32(loopEnd))
              })

              loopWatcherStack.top.continues.foreach(ins => {
                bytecodeBuffer(ins) = Jump(new U32(loopStart))
              })

          }
          loopWatcherStack.pop()
        case BranchStmt(branch) =>
          branch match {
            case IfBranch(cond, ifBody, elifs, elseBody) => emitIfElse(cond, ifBody, elifs, elseBody)
            case SwitchBranch(cond, cases) => ???
          }
        case ReturnStmt(expr) =>
          emitExpr(expr)
          Ret >>: this
        case BreakStmt =>
          loopWatcherStack.top.breaks.addOne(NoOp >>: this)
        case ContinueStmt =>
          loopWatcherStack.top.continues.addOne(NoOp >>: this)
        case SpawnStmt(expr) => {
          expr match {
            case Var(ident) => {
              val ptr = stringConstants.add(ident)
              LoadConstStr(new U32(ptr.toInt)) >>: this
              SpawnCoroutine >>: this
            }
            case _ => ???
          }
        }
      }
    }
  }

  def newLocalCtx(): Unit = {
    if (localVariables.isEmpty) {
      localVariables.addOne(new NameBuffer())
    } else {
      localVariables.addOne(localVariables.top.clone())
    }
  }

  def localVar(name: String): Long = localVariables.top.add(name)

  def popLocalCtx(): NameBuffer = {
    localVariables.pop()
  }

  def emitNextIterator() = {
    LoadConstInt(1) >>: this
    val ptr = stringConstants.add("__iterator_next") //nextify the iterator
    LoadConstStr(new U32(ptr)) >>: this
    Call >>: this
  }

  def emitIteratorOver() = {
    LoadConstInt(1) >>: this
    val ptr = stringConstants.add("__is_iterator_over") //nextify the iterator
    LoadConstStr(new U32(ptr)) >>: this
    Call >>: this
  }

  def emitIteratorValue() = {
    LoadConstInt(1) >>: this
    val ptr = stringConstants.add("__iterator_value")
    LoadConstStr(new U32(ptr))
    Call >>: this
  }

  def emitIfElse(cond: Expr, ifBody: List[Stmt], elifs: List[Elif], elseBody: Option[List[Stmt]]) = {
    val skipIndicies = ListBuffer[Int]()
    emitExpr(cond) //leaves bool on the stack
    val jumpIns = NoOp >>: this
    generate(ifBody)
    skipIndicies.addOne(NoOp >>: this) //jump past rest of branch
    val afterIfIns = bytecodeBuffer.size - 1 //index of the next instruction generated
    bytecodeBuffer(jumpIns) = IfFalse(new U32(afterIfIns))
    for (elif <- elifs) {
      emitExpr(elif.cond)
      val jumpIns = NoOp >>: this
      generate(elif.body)
      val afterIfIns = bytecodeBuffer.size - 1 //index of the next instruction generated
      bytecodeBuffer(jumpIns) = IfFalse(new U32(afterIfIns))
      skipIndicies.addOne(NoOp >>: this)
    }
    elseBody.foreach(generate)
    val endOfBranch = bytecodeBuffer.size - 1
    skipIndicies.foreach(bytecodeBuffer(_) = Jump(new U32(endOfBranch)))
  }

  def emitExpr(expr: Expr): Unit = {
    expr match {
      case Var(ident) =>
        if (isHeapIdent(ident)) {
          generateMemberLoad(ident)
        } else {
          LoadVar(new U16(localVar(ident))) >>: this
        }
      case ArrayIndex(arrayExpr, start, end, step) => ???
      case Lit(literal) => convertLiteral(literal)
      case FunCall(ident, params) => emitFunctionCall(ident, params)
      case ObjConstructor(ident, params) => emitConstructor(ident, params)
      case Parens(expr) => emitExpr(expr)
      case ListComp(comp) => ???
      case Unop(expr, op) => emitUnOp(expr, op)
      case Binop(first, second, op) => emitBinOp(first, second, op)
      case Ternary(cond, ifExpr, elseExpr) =>
        emitIfElse(cond, List(ExprStmt(ifExpr)), List(), Some(List(ExprStmt(elseExpr))))
      case SendExpr(expr) => {
        emitExpr(expr)
        SendAsync >>: this
      }
      case ListenExpr(expr) => {
        emitExpr(expr)
        ListenAsync >>: this
      }
    }
  }

  def emitConstructor(ident: String, params: List[Expr]): Unit = {
    val objName = stringConstants.add(ident)
    LoadConstStr(new U32(objName)) >>: this
    CreateObject >>: this
    Dup >>: this //the constructor call will consume one reference

    for (p <- params.reverse) {
      emitExpr(p)
    }

    LoadConstInt(params.length + 1) >>: this //params length + newly created object

    val ctrStr = mangleName(ident, "constructor")
    val ptr = stringConstants.add(ctrStr)
    LoadConstStr(new U32(ptr)) >>: this
    Call >>: this
  }

  private def mangleName(className: String, memberName: String) = className + "__" + memberName

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
    for (p <- params.reverse) {
      emitExpr(p)
    }
    if (isHeapIdent(ident)) {
      LoadConstInt(params.length + 1) >>: this
      generateMemberFunctionLoad(ident) //BOS -> [param_count, self, function_handle] -> TOS
      SwapTOS2WithTOS3 >>: this //[self, param_count, function_handle]
    } else {
      LoadConstInt(params.length) >>: this
      val ptr = stringConstants.add(ident)
      LoadConstStr(new U32(ptr)) >>: this
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
    LoadVar(new U16(localVar(pieces(0)))) >>: this//base of name must be a local variable objref
    for (piece <- pieces.slice(1, pieces.size)) {
      stringConstants.add(piece)
      LoadConstStr(new U32(stringConstants.add(piece))) >>: this
      LoadMember >>: this //each member piece is a new objref on the stack
    }
    //result is one objref added to the stack
  }

  def generateMemberLoadForHeapStore(ident: String): Unit = {
    val pieces = ident.split("\\.")
    LoadVar(new U16(localVar(pieces(0)))) >>: this//base of name must be a local variable objref
    for ((piece, index) <- pieces.slice(1, pieces.size).zipWithIndex) {
      stringConstants.add(piece)
      LoadConstStr(new U32(stringConstants.add(piece))) >>: this
      if (index < pieces.size - 2) {
        LoadMember >>: this //each member piece is a new objref on the stack
      }
    }
  }

  def generateMemberFunctionLoad(ident: String): Unit = {
    val pieces = ident.split("\\.")
    LoadVar(new U16(localVar(pieces(0)))) >>: this//base of name must be a local variable objref
    if (pieces.length == 2) {
      Dup >>: this
    }
    for ((piece, index) <- pieces.slice(1, pieces.size).zipWithIndex) {
      stringConstants.add(piece)
      LoadConstStr(new U32(stringConstants.add(piece))) >>: this
      LoadMember >>: this //each member piece is a new objref on the stack
      if (index == pieces.size - 2 && pieces.length != 2) {
        Dup >>: this
      }
    }
    //result is one objref added to the stack
  }

  def >>: (ins: Instruction): Int = {
    bytecodeBuffer.addOne(ins)
    bytecodeBuffer.size - 1
  }
}
