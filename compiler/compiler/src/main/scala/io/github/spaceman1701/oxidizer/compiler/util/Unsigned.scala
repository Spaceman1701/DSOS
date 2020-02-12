package io.github.spaceman1701.oxidizer.compiler.util

import scala.math.{ScalaNumber, ScalaNumericConversions}

class Unsigned(private val bits: Int, val value: BigInt) {
  require(value <= maxValue, f"${value} can't fit in an ${bits} bit number")
  require(value >= 0)

  def maxValue: BigInt = BigInt(1) << bits
  def minValue = 0

  def toBytes = value.toByteArray

  override def toString: String = f"0x${value.toString(16)}"
}

class U8(value: Short) extends Unsigned(8, BigInt(value))

class U16(value: Long) extends Unsigned(16, BigInt(value))

class U32(value: Long) extends Unsigned(32, BigInt(value))

class U64(override val value: BigInt) extends Unsigned(64, value)