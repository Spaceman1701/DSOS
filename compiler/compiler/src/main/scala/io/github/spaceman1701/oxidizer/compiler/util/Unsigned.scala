package io.github.spaceman1701.oxidizer.compiler.util

import scala.math.{ScalaNumber, ScalaNumericConversions}

class Unsigned(private val bytes: Int, val value: BigInt) {
  require(value <= maxValue)
  require(value >= 0)

  def maxValue: BigInt = BigInt(1) << bytes
  def minValue = 0

  def toBytes = value.toByteArray
}

class U8(value: Short) extends Unsigned(1, BigInt(value))

class U16(value: Int) extends Unsigned(2, BigInt(value))

class U32(value: Long) extends Unsigned(4, BigInt(value))

class U64(override val value: BigInt) extends Unsigned(8, value)