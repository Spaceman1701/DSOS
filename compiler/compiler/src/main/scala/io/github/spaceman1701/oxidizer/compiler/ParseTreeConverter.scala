package io.github.spaceman1701.oxidizer.compiler

import io.github.spaceman1701.oxidizer.compiler.ast.{AST, ImportDecl, ModuleDef}
import io.github.spaceman1701.oxidizer.parser.{OxidizerParser, OxidizerParserBaseVisitor}

import scala.jdk.CollectionConverters._

class ParseTreeConverter extends OxidizerParserBaseVisitor[AST] {
  /**
   * Visit a parse tree produced by {@link OxidizerParser#module}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitModule(ctx: OxidizerParser.ModuleContext): AST = {
    val imports = ctx.importdecl().asScala.toList.map(visitImportdecl)
    val decls = ctx.decl().asScala.toList.map(visitDecl)
    ModuleDef(imports, decls)
  }

  /**
   * Visit a parse tree produced by {@link OxidizerParser#decl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitDecl(ctx: OxidizerParser.DeclContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#block}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitBlock(ctx: OxidizerParser.BlockContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#stmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitStmt(ctx: OxidizerParser.StmtContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#loop}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitLoop(ctx: OxidizerParser.LoopContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#branch}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitBranch(ctx: OxidizerParser.BranchContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#compare_op}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitCompare_op(ctx: OxidizerParser.Compare_opContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#string}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitString(ctx: OxidizerParser.StringContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Text}
   * labeled alternative in {@link OxidizerParser#stringPart}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitText(ctx: OxidizerParser.TextContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code EmbeddedExpr}
   * labeled alternative in {@link OxidizerParser#stringPart}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitEmbeddedExpr(ctx: OxidizerParser.EmbeddedExprContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#literall}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitLiterall(ctx: OxidizerParser.LiterallContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Shift}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitShift(ctx: OxidizerParser.ShiftContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Dec}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitDec(ctx: OxidizerParser.DecContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Bor}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitBor(ctx: OxidizerParser.BorContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Ternary}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitTernary(ctx: OxidizerParser.TernaryContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code AddSub}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitAddSub(ctx: OxidizerParser.AddSubContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code EQTest}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitEQTest(ctx: OxidizerParser.EQTestContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Var}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitVar(ctx: OxidizerParser.VarContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Parens}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitParens(ctx: OxidizerParser.ParensContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code LAnd}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitLAnd(ctx: OxidizerParser.LAndContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code ListComp}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitListComp(ctx: OxidizerParser.ListCompContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code BAnd}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitBAnd(ctx: OxidizerParser.BAndContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Lor}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitLor(ctx: OxidizerParser.LorContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Concat}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitConcat(ctx: OxidizerParser.ConcatContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Neg}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitNeg(ctx: OxidizerParser.NegContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Not}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitNot(ctx: OxidizerParser.NotContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code FunCall}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitFunCall(ctx: OxidizerParser.FunCallContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Lit}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitLit(ctx: OxidizerParser.LitContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Pow}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitPow(ctx: OxidizerParser.PowContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Compare}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitCompare(ctx: OxidizerParser.CompareContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Xor}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitXor(ctx: OxidizerParser.XorContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code MulModDiv}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitMulModDiv(ctx: OxidizerParser.MulModDivContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code Inc}
   * labeled alternative in {@link OxidizerParser#expr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitInc(ctx: OxidizerParser.IncContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code EmptyList}
   * labeled alternative in {@link OxidizerParser#comprehension}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitEmptyList(ctx: OxidizerParser.EmptyListContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code LiteralList}
   * labeled alternative in {@link OxidizerParser#comprehension}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitLiteralList(ctx: OxidizerParser.LiteralListContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code ForComp}
   * labeled alternative in {@link OxidizerParser#comprehension}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitForComp(ctx: OxidizerParser.ForCompContext): AST = ???

  /**
   * Visit a parse tree produced by the {@code ListSlice}
   * labeled alternative in {@link OxidizerParser#comprehension}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitListSlice(ctx: OxidizerParser.ListSliceContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#ident}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitIdent(ctx: OxidizerParser.IdentContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#typedecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitTypedecl(ctx: OxidizerParser.TypedeclContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#innerclass}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitInnerclass(ctx: OxidizerParser.InnerclassContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#funcdecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitFuncdecl(ctx: OxidizerParser.FuncdeclContext): AST = ???

  /**
   * Visit a parse tree produced by {@link OxidizerParser#importdecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  override def visitImportdecl(ctx: OxidizerParser.ImportdeclContext): AST = ???
}
