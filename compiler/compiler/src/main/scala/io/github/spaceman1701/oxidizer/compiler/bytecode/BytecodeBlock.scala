package io.github.spaceman1701.oxidizer.compiler.bytecode

import scala.collection.mutable.ListBuffer

class BytecodeBlock {
  val instructions: ListBuffer[Instruction] = ListBuffer()

  def append(instruction: Instruction): Unit = {
    instructions.addOne(instruction)
  }

  def toBytes(): Array[Byte] = {
    return null
  }
}
