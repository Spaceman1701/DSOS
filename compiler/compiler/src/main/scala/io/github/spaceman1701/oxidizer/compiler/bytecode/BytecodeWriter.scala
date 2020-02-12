package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.io.OutputStream
import java.nio.charset.StandardCharsets

object BytecodeWriter {

  def write(instructions: List[Instruction], output: OutputStream) = {
    val header = "index -- op -- name\n"
    output.write(header.getBytes(StandardCharsets.UTF_8))

    for ((ins, index) <- instructions.zipWithIndex) {
      val line = f"${index}%-5s -- ${ins.opcode}%-2s -- ${ins}\n"
      output.write(line.getBytes(StandardCharsets.UTF_8))
    }
  }
}
