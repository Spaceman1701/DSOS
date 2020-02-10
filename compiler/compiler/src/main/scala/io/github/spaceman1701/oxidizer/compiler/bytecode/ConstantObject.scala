package io.github.spaceman1701.oxidizer.compiler.bytecode

sealed trait ConstantObject

case class IntConst(const: Long) extends ConstantObject
case class FloatConst(const: Float) extends ConstantObject
case class StringConst(const: String) extends ConstantObject