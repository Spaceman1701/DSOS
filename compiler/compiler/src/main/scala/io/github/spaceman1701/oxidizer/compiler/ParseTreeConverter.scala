package io.github.spaceman1701.oxidizer.compiler

import io.github.spaceman1701.oxidizer.compiler.ast._
import io.github.spaceman1701.oxidizer.parser.OxidizerParser.ExprContext
import io.github.spaceman1701.oxidizer.parser.{OxidizerParser, OxidizerParserBaseVisitor}

import scala.jdk.CollectionConverters._

class ParseTreeConverter extends OxidizerParserBaseVisitor[AST] {

  override def visitProgram(ctx: OxidizerParser.ProgramContext): Module = {
    visit(ctx.module()).asInstanceOf[Module]
  }

  override def visitModuleDef(ctx: OxidizerParser.ModuleDefContext): Module = {
    val importDecls = ctx.importdecl().asScala.toList.map(visitImportdecl)
    val otherDecls =  ctx.decl().asScala.toList.map(visit).asInstanceOf[List[Decl]]
    ModuleDef(importDecls, otherDecls)
  }

  override def visitTypeDecl(ctx: OxidizerParser.TypeDeclContext): ClassDecl = {
    def items = ctx.classitem().asScala.toList.map(visit).asInstanceOf[List[ClassItem]]
    ClassDecl(ctx.NAME().getSymbol.getText, items)
  }

  override def visitClassMethod(ctx: OxidizerParser.ClassMethodContext): ClassMethod = {
    ClassMethod(visitFuncdef(ctx.funcdef()))
  }

  override def visitClassField(ctx: OxidizerParser.ClassFieldContext): ClassField = {
    val isPrivate = ctx.KW_PRIVATE() != null
    val name = ctx.NAME().getText
    val initExpr = if (ctx.expr() != null) {
      Option.apply(visitExpr(ctx.expr()))
    } else {
      Option.empty
    }
    ClassField(isPrivate, name, initExpr)
  }

  override def visitBreakStmt(ctx: OxidizerParser.BreakStmtContext): Stmt = BreakStmt

  override def visitContinueStmt(ctx: OxidizerParser.ContinueStmtContext): Stmt = ContinueStmt

  override def visitFuncDecl(ctx: OxidizerParser.FuncDeclContext): FunctionDecl = {
    val f = ctx.funcdef()
    FunctionDecl(visitFuncdef(f))
  }

  private def visitStmtBlock(ctx: OxidizerParser.BlockContext): List[Stmt] = {
    ctx.stmt().asScala.toList.map(visit).asInstanceOf[List[Stmt]]
  }

  override def visitExprStmt(ctx: OxidizerParser.ExprStmtContext): ExprStmt = {
    ExprStmt(visit(ctx.expr()).asInstanceOf[Expr])
  }

  override def visitAssignStmt(ctx: OxidizerParser.AssignStmtContext): AssignStmt = {
    AssignStmt(ctx.ident().NAME().getSymbol.getText, visit(ctx.expr()).asInstanceOf[Expr])
  }

  override def visitLoopStmt(ctx: OxidizerParser.LoopStmtContext): LoopStmt = {
    LoopStmt(visit(ctx.loop()).asInstanceOf[Loop])
  }

  override def visitBranchStmt(ctx: OxidizerParser.BranchStmtContext): BranchStmt = {
    BranchStmt(visit(ctx.branch()).asInstanceOf[Branch])
  }

  override def visitReturnStmt(ctx: OxidizerParser.ReturnStmtContext): ReturnStmt = {
    ReturnStmt(visit(ctx.expr()).asInstanceOf[Expr])
  }

  override def visitForLoop(ctx: OxidizerParser.ForLoopContext): ForLoop = {
    val iterator = ctx.ident().NAME().getText
    val loopExpr = visit(ctx.expr()).asInstanceOf[Expr]
    val body = ctx.stmt().asScala.toList.map(visit).asInstanceOf[List[Stmt]]
    ForLoop(iterator, loopExpr, body)
  }

  override def visitWhileLoop(ctx: OxidizerParser.WhileLoopContext): WhileLoop = {
    val loopExpr = visit(ctx.expr()).asInstanceOf[Expr]
    val body = visitStmtBlock(ctx.block())
    WhileLoop(loopExpr, body)
  }

  override def visitIfBranch(ctx: OxidizerParser.IfBranchContext): IfBranch = {
    val cond = visit(ctx.expr()).asInstanceOf[Expr]
    val ifBody = visitStmtBlock(ctx.ifBody)
    val elifs = ctx.elif().asScala.toList.map(visitElif)
    val elseBody = if (ctx.elseBody != null) {
      Option.apply(visitStmtBlock(ctx.elseBody))
    } else {
      Option.empty
    }

    IfBranch(cond, ifBody, elifs, elseBody)
  }

  override def visitSwitchBranch(ctx: OxidizerParser.SwitchBranchContext): SwitchBranch = {
    val expr = visit(ctx.expr()).asInstanceOf[Expr]
    val cases = ctx.switch_case().asScala.toList.map(visitSwitch_case)
    SwitchBranch(expr, cases)
  }

  override def visitSwitch_case(ctx: OxidizerParser.Switch_caseContext): SwitchCase = {
    val caseCond = if (ctx.ident() != null) {
      Left(ctx.ident().NAME().getText)
    } else {
      Right(visit(ctx.literall()).asInstanceOf[Literal])
    }
    val body = visitStmtBlock(ctx.block())
    SwitchCase(caseCond, body)
  }

  override def visitElif(ctx: OxidizerParser.ElifContext): Elif = {
    val cond = visit(ctx.expr()).asInstanceOf[Expr]
    val body = visitStmtBlock(ctx.block())
    Elif(cond, body)
  }

  override def visitCompare_op(ctx: OxidizerParser.Compare_opContext): BinaryOperator = {
    ctx match {
      case _ if ctx.COMP_G() != null => CompareGT
      case _ if ctx.COMP_GE() != null => CompareGE
      case _ if ctx.COMP_L() != null => CompareLT
      case _ if ctx.COMP_LE() != null => CompareLE
    }
    throw new IllegalStateException("unreachable")
  }

  override def visitString(ctx: OxidizerParser.StringContext): StringLit = {
    StringLit(ctx.stringPart().asScala.toList.map(visit).asInstanceOf[List[StringPart]])
  }

  override def visitText(ctx: OxidizerParser.TextContext): TextPart = {
    TextPart(ctx.TEXT().getText)
  }

  override def visitEmbeddedExpr(ctx: OxidizerParser.EmbeddedExprContext): EmbeddedExpr = {
    EmbeddedExpr(visit(ctx.expr()).asInstanceOf[Expr])
  }

  override def visitIntLit(ctx: OxidizerParser.IntLitContext): IntLit = {
    IntLit(ctx.INTEGER().toString.toLong)
  }

  override def visitFloatLit(ctx: OxidizerParser.FloatLitContext): FloatLit = {
    FloatLit(ctx.FLOAT().toString.toDouble)
  }

  override def visitStringLit(ctx: OxidizerParser.StringLitContext): StringLit = {
    visitString(ctx.string())
  }

  private def visitBinop(left: OxidizerParser.ExprContext, right: OxidizerParser.ExprContext, op: BinaryOperator) = {
    val first = visitExpr(left)
    val second = visitExpr(right)
    Binop(first, second, op)
  }

  override def visitShift(ctx: OxidizerParser.ShiftContext): Binop = {
    val operator = if (ctx.OP_LSHIFT() != null) {
      LeftShift
    } else if (ctx.OP_RSHIFT() != null) {
      RightShift
    } else {
      UnsignedRightShift
    }
    visitBinop(ctx.expr(0), ctx.expr(1), operator)
  }

  override def visitDec(ctx: OxidizerParser.DecContext): Unop = {
    Unop(visitExpr(ctx.expr()), Decrement)
  }

  override def visitBor(ctx: OxidizerParser.BorContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), BitwiseOr)
  }

  override def visitTernary(ctx: OxidizerParser.TernaryContext): Ternary = {
    val cond = visitExpr(ctx.expr(0))
    val ifBody = visitExpr(ctx.expr(1))
    val elseBody = visitExpr(ctx.expr(2))
    Ternary(cond, ifBody, elseBody)
  }

  override def visitAddSub(ctx: OxidizerParser.AddSubContext): Binop = {
    val op = if (ctx.OP_MINUS() != null) {
      Minus
    } else {
      Plus
    }
    visitBinop(ctx.expr(0), ctx.expr(1), op)
  }

  override def visitEQTest(ctx: OxidizerParser.EQTestContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), CompareEq)
  }

  override def visitVar(ctx: OxidizerParser.VarContext): Var = {
    Var(ctx.ident().NAME().getText)
  }

  override def visitParens(ctx: OxidizerParser.ParensContext): Parens = {
    Parens(visitExpr(ctx.expr()))
  }

  override def visitLAnd(ctx: OxidizerParser.LAndContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), LogicalAnd)
  }

  override def visitListComp(ctx: OxidizerParser.ListCompContext): ListComp = {
    ListComp(visit(ctx.comprehension()).asInstanceOf[Comprehension])
  }

  override def visitBAnd(ctx: OxidizerParser.BAndContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), BitwiseAnd)
  }

  override def visitLor(ctx: OxidizerParser.LorContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), LogicalOr)
  }

  override def visitConcat(ctx: OxidizerParser.ConcatContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), Concat)
  }

  override def visitNeg(ctx: OxidizerParser.NegContext): Unop = {
    Unop(visitExpr(ctx.expr()), Negate)
  }

  override def visitNot(ctx: OxidizerParser.NotContext): Unop = {
    Unop(visitExpr(ctx.expr()), Not)
  }

  override def visitFunCall(ctx: OxidizerParser.FunCallContext): FunCall = {
    val name = ctx.ident().NAME().getText
    val params = ctx.expr().asScala.toList.map(visitExpr)
    FunCall(name, params)
  }

  override def visitLit(ctx: OxidizerParser.LitContext): Lit = {
    Lit(visit(ctx.literall()).asInstanceOf[Literal])
  }

  override def visitPow(ctx: OxidizerParser.PowContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), Power)
  }

  override def visitCompare(ctx: OxidizerParser.CompareContext): Binop = {
    val op = visitCompare_op(ctx.compare_op())
    visitBinop(ctx.expr(0), ctx.expr(1), op)
  }

  override def visitXor(ctx: OxidizerParser.XorContext): Binop = {
    visitBinop(ctx.expr(0), ctx.expr(1), XOr)
  }

  override def visitMulModDiv(ctx: OxidizerParser.MulModDivContext): Binop = {
    val op = if (ctx.OP_DIV() != null) {
      Divide
    } else if (ctx.OP_MOD() != null) {
      Modulo
    } else {
      Multiply
    }
    visitBinop(ctx.expr(0), ctx.expr(1), op)
  }

  override def visitInc(ctx: OxidizerParser.IncContext): Unop = {
    Unop(visitExpr(ctx.expr()), Increment)
  }

  override def visitEmptyList(ctx: OxidizerParser.EmptyListContext): EmptyList = {
    EmptyList()
  }

  override def visitLiteralList(ctx: OxidizerParser.LiteralListContext): LiteralList = {
    LiteralList(ctx.expr().asScala.toList.map(visitExpr))
  }

  override def visitForComp(ctx: OxidizerParser.ForCompContext): ForComp = {
    val ele = visitExpr(ctx.ele)
    val iterator = ctx.ident().NAME().getText
    val inExpr = visitExpr(ctx.inExpr)
    val cond = if (ctx.cond != null) {
      Option.apply(visitExpr(ctx.cond))
    } else {
      Option.empty
    }

    ForComp(ele, iterator, inExpr, cond)
  }

  override def visitListSlice(ctx: OxidizerParser.ListSliceContext): ListSlice = {
    val start = if (ctx.start != null) Option.apply(visitExpr(ctx.start)) else Option.empty
    val end = if (ctx.end != null) Option.apply(visitExpr(ctx.end)) else Option.empty
    val step = if (ctx.step != null) Option.apply(visitExpr(ctx.step)) else Option.empty
    ListSlice(start, end, step)
  }

  override def visitFuncdef(ctx: OxidizerParser.FuncdefContext): FunctionDef = {
    val isPrivate = ctx.KW_PRIVATE() != null
    val name = ctx.NAME(0).getText
    val params = ctx.NAME().asScala.toList.takeRight(1).map(_.getText)
    val body = visitStmtBlock(ctx.block())
    FunctionDef(isPrivate, name, params, body)
  }

  override def visitImportdecl(ctx: OxidizerParser.ImportdeclContext): ImportDecl = {
    println("visiting import decl")
    ImportDecl(ctx.NAME().getText)
  }

  private def visitExpr(ctx: OxidizerParser.ExprContext): Expr = {
    visit(ctx).asInstanceOf[Expr]
  }

  private def visitStmt(ctx: OxidizerParser.StmtContext): Stmt = {
    visit(ctx).asInstanceOf[Stmt]
  }
}