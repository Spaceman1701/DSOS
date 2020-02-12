package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.io.OutputStream
import java.nio.charset.StandardCharsets

object BytecodeWriter {

  def write(instructions: List[Instruction], functions: Map[Int, String], output: OutputStream) = {
    val header = "index -- op -- name\n"
    output.write(header.getBytes(StandardCharsets.UTF_8))

    for ((ins, index) <- instructions.zipWithIndex) {
      if (functions.get(index).isDefined) {
        val functionLine = f"function ${functions(index)}:\n"
        output.write(functionLine.getBytes(StandardCharsets.UTF_8))
      }
      val line = f"${index}%-5s -- ${ins.opcode}%-2s -- ${ins}\n"
      output.write(line.getBytes(StandardCharsets.UTF_8))
    }
  }
}
