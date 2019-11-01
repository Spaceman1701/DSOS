package io.github.spaceman1701.oxidizer.compiler.ast

sealed trait AST


sealed trait Module extends AST
case class ModuleDef(imports: List[ImportDecl], decls: List[Decl]) extends Module


sealed trait Decl extends AST
case class ClassDecl(name: String, methods: List[FunctionDecl]) extends Decl
case class FunctionDecl(functionDef: FunctionDecl) extends Decl


sealed trait Stmt extends AST
case class ExprStmt(expr: Expr) extends Stmt
case class AssignStmt(ident: String, expr: Expr) extends Stmt
case class LoopStmt(loop: Loop) extends Stmt
case class BranchStmt(branch: Branch) extends Stmt
case class ReturnStmt(expr: Expr) extends Stmt


sealed trait Loop extends AST
case class ForLoop(ident: String, inExpr: Expr, body: List[Stmt]) extends Loop
case class WhileLoop(cond: Expr, body: List[Stmt]) extends Loop


sealed trait Branch extends Branch
case class IfBranch(cond: Expr, ifBody: List[Stmt], elifs: List[Elif], elseBody: Option[List[Stmt]]) extends Branch
case class SwitchBranch(cond: Expr, cases: List[SwitchCase]) extends Branch

case class SwitchCase(switchCase: Either[String, Literal], body: List[Stmt]) extends AST
case class Elif(cond: Expr, body: List[Stmt]) extends AST


sealed trait StringPart
case class TextPart(text: String) extends StringPart
case class EmbeddedExpr(expr: Expr) extends StringPart


sealed trait Literal
case class IntLit(value: Long) extends Literal
case class FloatLit(value: Double) extends Literal
case class StringLit(parts: List[StringPart]) extends Literal


sealed trait Expr extends AST
case class Var(ident: String) extends Expr
case class Lit(literal: Literal) extends Expr
case class FunCall(ident: String, params: List[Expr]) extends Expr
case class Parens(expr: Expr) extends Expr
case class ListComp(comp: Comprehension) extends Expr
case class Unop(expr: Expr, Op: String) extends Expr
case class Binop(first: Expr, second: Expr, Op: String) extends Expr
case class Ternary(cond: Expr, ifExpr: Expr, elseExpr: Expr) extends Expr


sealed trait Comprehension
case object EmptyList extends Comprehension
case class LiteralList(elements: List[Expr]) extends Comprehension
case class ForComp(ele: Expr, iterator: String, inExpr: Expr, cond: Option[Expr]) extends Comprehension
case class ListSlice(start: Option[Expr], end: Option[Expr], step: Option[Expr]) extends Comprehension


case class FunctionDef(isPrivate: Boolean, name: String, params: List[String], body: List[Stmt]) extends AST


case class ImportDecl(Name: String) extends AST

