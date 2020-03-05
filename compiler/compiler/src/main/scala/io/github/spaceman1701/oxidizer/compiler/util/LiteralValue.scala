package io.github.spaceman1701.oxidizer.compiler.util

sealed trait LiteralValue

case class LiteralStringValue(v: String) extends LiteralValue
case class LiteralIntValue(v: Long) extends LiteralValue
case class LiteralFloatValue(v: Double) extends LiteralValue