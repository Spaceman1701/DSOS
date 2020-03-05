package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.util.LiteralValue

class ClassDescriptor(val name: String, val members: Map[String, Option[LiteralValue]])
