package io.github.spaceman1701.oxidizer.compiler.bytecode

class CompiledModule(val module: OxModule) {
  private val functionMap: Map[FunctionDescriptor, BytecodeBlock] = Map()
  private val classesMap: Map[ClassDescriptor, ClassLayout] = Map()
}
