package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.util.{U32, Unsigned}

import scala.collection.mutable.ListBuffer

class NameBuffer() {
  private var ptrMap: Map[String, Long] = Map[String, Long]()
  private var strList = ListBuffer[String]()

  override def clone(): NameBuffer = {
    val clonedBuffer = new NameBuffer()
    clonedBuffer.ptrMap = ptrMap.map{case (key, value) => (key, value)}
    clonedBuffer.strList = strList.clone()
    clonedBuffer
  }

  def addAndCheck(text: String): (Long, Boolean) = {
    if (ptrMap.contains(text)) {
      (ptrMap(text), false)
    } else {
      strList.addOne(text)
      val partIndex = strList.size - 1
      ptrMap = ptrMap + (text -> partIndex)
      (partIndex, true)
    }
  }

  def add(text: String): Long = addAndCheck(text)._1

  def get(ptr: Long): String = {
    if (ptr != ptr.toInt) {
      throw new IllegalStateException("pointer to large for Scala's badness")
    }
    return strList(ptr.toInt)
  }

  def finalizeList(): List[String] = strList.toList
}
