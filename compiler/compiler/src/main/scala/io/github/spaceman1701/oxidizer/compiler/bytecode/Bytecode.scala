package io.github.spaceman1701.oxidizer.compiler.bytecode

sealed trait Bytecode

case object Pop
case object LoadConst
case object LoadVar

case object Add
case object Sub
case object Mul
case object Div
case object Mod
case object Concat
case object Pow

case object CompGT
case object CompGE
case object CompLT
case object CompEq