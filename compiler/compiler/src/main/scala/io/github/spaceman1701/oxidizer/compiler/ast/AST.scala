package io.github.spaceman1701.oxidizer.compiler.ast

sealed trait AST


sealed trait Module extends AST
case class ModuleDef(imports: List[ImportDecl], decls: List[Decl]) extends Module


sealed trait Decl extends AST
case class ClassDecl(name: String) extends Decl
case class FunctionDecl(functionDef: FunctionDecl) extends Decl


case class FunctionDef(isPrivate: Boolean, name: String, params: List[String]) extends AST


case class ImportDecl(Name: String) extends AST


sealed trait Expr extends AST