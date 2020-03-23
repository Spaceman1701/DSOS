package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.io.File
import java.nio.ByteBuffer
import java.nio.file.{Files, StandardOpenOption}

class CompiledModule(val headerSegment: Array[Byte], val stringSegment: Array[Byte], val textSegment: Array[Byte]) {

  private final val magicBytes = Array[Byte](0xF, 0xE, 0x0)


  def writeToFile(outputFile: File): Unit = {
    val path = outputFile.toPath

    val segmentMap = ByteBuffer.allocate(12).putInt(headerStartOffset).putInt(stringStartOffset).putInt(textStartOffset).array()
    val fileData = magicBytes ++ segmentMap ++ headerSegment ++ stringSegment ++ textSegment

    if (!outputFile.exists()) {
      outputFile.getAbsoluteFile.getParentFile.mkdir
      outputFile.createNewFile()
    }

    println(textSegment(0), textSegment(1), textSegment(2), textSegment(3), textSegment(4))

    Files.write(path, fileData, StandardOpenOption.WRITE)
  }

  def headerStartOffset: Int = magicBytes.length + 12
  def stringStartOffset: Int = headerStartOffset + headerSegment.length
  def textStartOffset: Int = stringStartOffset + stringSegment.length

}
