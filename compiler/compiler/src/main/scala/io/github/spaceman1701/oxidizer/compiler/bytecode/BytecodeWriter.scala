package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.io.OutputStream
import java.nio.charset.StandardCharsets

object BytecodeWriter {

  def write(instructions: List[Instruction], output: OutputStream) = {
    for (ins <- instructions) {
      val line = f"${ins.opcode}%-2s -- ${ins}\n"
      output.write(line.getBytes(StandardCharsets.UTF_8))
    }
  }
}
