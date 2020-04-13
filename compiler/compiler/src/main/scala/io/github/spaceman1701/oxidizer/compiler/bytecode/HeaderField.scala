package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.nio.{ByteBuffer, ByteOrder}
import java.util

sealed trait HeaderField {
  val fieldCode: Byte

  def toBytes: Array[Byte]
}

case class FunctionField(funcNameIndex: Int, byteOffset: Int) extends HeaderField {
  override val fieldCode = 1

  override def toBytes: Array[Byte] =
    ByteBuffer.allocate(9)
      .order(ByteOrder.BIG_ENDIAN)
      .put(fieldCode)
      .putInt(funcNameIndex)
      .putInt(byteOffset)
      .array()
}

case class ClassHeaderField(classNameIndex: Int, fieldType: Byte, fieldNameIndex: Int, fieldValue: Array[Byte]) extends HeaderField {
  override val fieldCode: Byte = 2

  override def toBytes: Array[Byte] =
    ByteBuffer.allocate(18)
      .order(ByteOrder.BIG_ENDIAN)
      .put(fieldCode)
      .put(fieldType)
      .putInt(classNameIndex)
      .putInt(fieldNameIndex)
      .put(fieldValue)
      .array()
}