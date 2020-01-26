package io.github.spaceman1701.oxidizer.compiler.bytecode

class FunctionDescriptor(val name: String, val args: Array[String]) {
  private val code: BytecodeBlock = new BytecodeBlock()
}
