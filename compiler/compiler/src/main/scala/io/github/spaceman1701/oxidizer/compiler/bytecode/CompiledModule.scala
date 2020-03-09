package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.io.File
import java.nio.file.{Files, StandardOpenOption}

class CompiledModule(val headerSegment: Array[Byte], val stringSegment: Array[Byte], val textSegment: Array[Byte]) {

  def writeToFile(outputFile: File): Unit = {
    val path = outputFile.toPath

    val headerStart = 4
    val stringStart = headerStart + headerSegment.length
    val textStart = stringStart + stringSegment.length


    val fileData = Array[Byte](1, 2, 3, 4) ++ headerSegment ++ stringSegment ++ textSegment

    if (!outputFile.exists()) {
      outputFile.getAbsoluteFile.getParentFile.mkdir
      outputFile.createNewFile()
    }

    Files.write(path, fileData, StandardOpenOption.WRITE)
  }

}
