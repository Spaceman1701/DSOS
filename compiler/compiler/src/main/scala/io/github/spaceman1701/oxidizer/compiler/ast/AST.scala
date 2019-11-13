package io.github.spaceman1701.oxidizer.compiler.ast

sealed trait AST


sealed trait Module extends AST
case class ModuleDef(imports: List[ImportDecl], decls: List[Decl]) extends Module


sealed trait Decl extends AST
case class ClassDecl(name: String, members: List[ClassItem]) extends Decl
case class FunctionDecl(functionDef: FunctionDef) extends Decl


sealed trait Stmt extends AST
case class ExprStmt(expr: Expr) extends Stmt
case class AssignStmt(ident: String, expr: Expr) extends Stmt
case class LoopStmt(loop: Loop) extends Stmt
case class BranchStmt(branch: Branch) extends Stmt
case class ReturnStmt(expr: Expr) extends Stmt


sealed trait Loop extends AST
case class ForLoop(ident: String, inExpr: Expr, body: List[Stmt]) extends Loop
case class WhileLoop(cond: Expr, body: List[Stmt]) extends Loop


sealed trait Branch extends AST
case class IfBranch(cond: Expr, ifBody: List[Stmt], elifs: List[Elif], elseBody: Option[List[Stmt]]) extends Branch
case class SwitchBranch(cond: Expr, cases: List[SwitchCase]) extends Branch

case class SwitchCase(switchCase: Either[String, Literal], body: List[Stmt]) extends AST
case class Elif(cond: Expr, body: List[Stmt]) extends AST


sealed trait StringPart extends AST
case class TextPart(text: String) extends StringPart
case class EmbeddedExpr(expr: Expr) extends StringPart


sealed trait Literal extends AST
case class IntLit(value: Long) extends Literal
case class FloatLit(value: Double) extends Literal
case class StringLit(parts: List[StringPart]) extends Literal


sealed trait Expr extends AST
case class Var(ident: String) extends Expr
case class Lit(literal: Literal) extends Expr
case class FunCall(ident: String, params: List[Expr]) extends Expr
case class Parens(expr: Expr) extends Expr
case class ListComp(comp: Comprehension) extends Expr
case class Unop(expr: Expr, Op: UnaryOperator) extends Expr
case class Binop(first: Expr, second: Expr, Op: BinaryOperator) extends Expr
case class Ternary(cond: Expr, ifExpr: Expr, elseExpr: Expr) extends Expr


sealed trait Comprehension extends AST
case class EmptyList() extends Comprehension
case class LiteralList(elements: List[Expr]) extends Comprehension
case class ForComp(ele: Expr, iterator: String, inExpr: Expr, cond: Option[Expr]) extends Comprehension
case class ListSlice(start: Option[Expr], end: Option[Expr], step: Option[Expr]) extends Comprehension


sealed trait ClassItem extends AST
case class ClassMethod(func: FunctionDef) extends ClassItem
case class ClassField(isPrivate: Boolean, name: String, initExpr: Option[Expr]) extends ClassItem


case class FunctionDef(isPrivate: Boolean, name: String, params: List[String], body: List[Stmt]) extends AST


case class ImportDecl(Name: String) extends AST


sealed trait BinaryOperator extends AST
case object Plus extends BinaryOperator
case object Minus extends BinaryOperator
case object Concat extends BinaryOperator
case object Multiply extends BinaryOperator
case object Divide extends BinaryOperator
case object Power extends BinaryOperator
case object LogicalAnd extends BinaryOperator
case object LogicalOr extends BinaryOperator
case object XOr extends BinaryOperator
case object BitwiseAnd extends BinaryOperator
case object BitwiseOr extends BinaryOperator
case object LeftShift extends BinaryOperator
case object RightShift extends BinaryOperator
case object Modulo extends BinaryOperator

case object CompareEq extends BinaryOperator
case object CompareGT extends BinaryOperator
case object CompareGE extends BinaryOperator
case object CompareLT extends BinaryOperator
case object CompareLE extends BinaryOperator
case object CompareNE extends BinaryOperator


sealed trait UnaryOperator extends AST
case object Not extends UnaryOperator
case object Compliment extends UnaryOperator
case object Negate extends UnaryOperator
case object Increment extends UnaryOperator
case object Decrement extends UnaryOperator
