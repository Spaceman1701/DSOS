package io.github.spaceman1701.oxidizer.compiler

import io.github.spaceman1701.oxidizer.compiler.ast._
import io.github.spaceman1701.oxidizer.parser.OxidizerParser.ExprContext
import io.github.spaceman1701.oxidizer.parser.{OxidizerParser, OxidizerParserBaseVisitor}

import scala.jdk.CollectionConverters._

class ParseTreeConverter extends OxidizerParserBaseVisitor[AST] {

  override def visitModuleDef(ctx: OxidizerParser.ModuleDefContext): Module = {
    val importDecls = ctx.importdecl().asScala.toList.map(visitImportdecl)
    val otherDecls =  ctx.decl().asScala.toList.map(visit).asInstanceOf[List[Decl]]
    ModuleDef(importDecls, otherDecls)
  }

  override def visitTypeDecl(ctx: OxidizerParser.TypeDeclContext): ClassDecl = {
    ClassDecl(ctx.NAME().getSymbol.getText, visitInnerclass(ctx.innerclass()))
  }

  override def visitFuncDecl(ctx: OxidizerParser.FuncDeclContext): FunctionDecl = {
    FunctionDecl(visitFuncdecl(ctx.funcdecl()))
  }

  override def visitBlock(ctx: OxidizerParser.BlockContext): List[Stmt] = {
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
    val body = ctx.block().stmt().asScala.toList.map(visit).asInstanceOf[List[Stmt]]
    WhileLoop(loopExpr, body)
  }

  override def visitIfBranch(ctx: OxidizerParser.IfBranchContext): IfBranch = {
    val cond = visit(ctx.expr()).asInstanceOf[Expr]
    val ifBody = ctx.ifBody.stmt().asScala.toList.map(visit).asInstanceOf[List[Stmt]]
    val elifs = ctx.elif().asScala.toList.map(visitElif)
    val elseBody = if (ctx.elseBody != null) {
      Option.apply(ctx.elseBody.stmt().asScala.toList.map(visit).asInstanceOf[List[Stmt]])
    } else {
      Option.empty
    }

    IfBranch(cond, ifBody, elifs, elseBody)
  }

  override def visitSwitchBranch(ctx: OxidizerParser.SwitchBranchContext): SwitchBranch = ???

  override def visitSwitch_case(ctx: OxidizerParser.Switch_caseContext): SwitchCase = ???

  override def visitElif(ctx: OxidizerParser.ElifContext): Elif = ???

  override def visitCompare_op(ctx: OxidizerParser.Compare_opContext): Binop = ???

  override def visitString(ctx: OxidizerParser.StringContext): StringLit = ???

  override def visitText(ctx: OxidizerParser.TextContext): TextPart = ???

  override def visitEmbeddedExpr(ctx: OxidizerParser.EmbeddedExprContext): EmbeddedExpr = ???

  override def visitIntLit(ctx: OxidizerParser.IntLitContext): IntLit = ???

  override def visitFloatLit(ctx: OxidizerParser.FloatLitContext): FloatLit = ???

  override def visitStringLit(ctx: OxidizerParser.StringLitContext): StringLit = ???

  override def visitShift(ctx: OxidizerParser.ShiftContext): Binop = ???

  override def visitDec(ctx: OxidizerParser.DecContext): Unop = ???

  override def visitBor(ctx: OxidizerParser.BorContext): Binop = ???

  override def visitTernary(ctx: OxidizerParser.TernaryContext): Ternary = ???

  override def visitAddSub(ctx: OxidizerParser.AddSubContext): Binop = ???

  override def visitEQTest(ctx: OxidizerParser.EQTestContext): Binop = ???

  override def visitVar(ctx: OxidizerParser.VarContext): Var = ???

  override def visitParens(ctx: OxidizerParser.ParensContext): Parens = ???

  override def visitLAnd(ctx: OxidizerParser.LAndContext): Binop = ???

  override def visitListComp(ctx: OxidizerParser.ListCompContext): Comprehension = ???

  override def visitBAnd(ctx: OxidizerParser.BAndContext): Binop = ???

  override def visitLor(ctx: OxidizerParser.LorContext): Binop = ???

  override def visitConcat(ctx: OxidizerParser.ConcatContext): Binop = ???

  override def visitNeg(ctx: OxidizerParser.NegContext): Unop = ???

  override def visitNot(ctx: OxidizerParser.NotContext): Unop = ???

  override def visitFunCall(ctx: OxidizerParser.FunCallContext): FunCall = ???

  override def visitLit(ctx: OxidizerParser.LitContext): Lit = ???

  override def visitPow(ctx: OxidizerParser.PowContext): Binop = ???

  override def visitCompare(ctx: OxidizerParser.CompareContext): Binop = ???

  override def visitXor(ctx: OxidizerParser.XorContext): Binop = ???

  override def visitMulModDiv(ctx: OxidizerParser.MulModDivContext): Binop = ???

  override def visitInc(ctx: OxidizerParser.IncContext): Unop = ???

  override def visitEmptyList(ctx: OxidizerParser.EmptyListContext): EmptyList = ???

  override def visitLiteralList(ctx: OxidizerParser.LiteralListContext): LiteralList = ???

  override def visitForComp(ctx: OxidizerParser.ForCompContext): ForComp = ???

  override def visitListSlice(ctx: OxidizerParser.ListSliceContext): ListSlice = ???

  override def visitIdent(ctx: OxidizerParser.IdentContext): String = ???

  override def visitFuncdecl(ctx: OxidizerParser.FuncdeclContext): FunctionDef = ???

  override def visitInnerclass(ctx: OxidizerParser.InnerclassContext): List[FunctionDecl] = ???

  override def visitImportdecl(ctx: OxidizerParser.ImportdeclContext): ImportDecl = ???
}