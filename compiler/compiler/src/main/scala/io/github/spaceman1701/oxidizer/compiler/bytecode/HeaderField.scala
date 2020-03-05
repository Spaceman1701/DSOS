package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.nio.ByteBuffer

sealed trait HeaderField {
  val fieldCode: Byte

  def toBytes: Array[Byte]
}

case class FunctionField(funcNameIndex: Int, byteOffset: Int) extends HeaderField {
  override val fieldCode = 1

  override def toBytes: Array[Byte] =
    ByteBuffer.allocate(9)
      .put(fieldCode)
      .putInt(funcNameIndex)
      .putInt(byteOffset)
      .array()
}

case class ClassHeaderField(classNameIndex: Int, fieldNameIndex: Int, fieldValue: Array[Byte]) extends HeaderField {
  override val fieldCode: Byte = 2

  override def toBytes: Array[Byte] =
    ByteBuffer.allocate(9)
      .put(fieldCode)
      .putInt(classNameIndex)
      .putInt(fieldNameIndex)
      .put(fieldValue)
      .array()
}