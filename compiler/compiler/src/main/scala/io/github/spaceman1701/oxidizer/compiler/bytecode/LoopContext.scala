package io.github.spaceman1701.oxidizer.compiler.bytecode

import scala.collection.mutable.ListBuffer

class LoopContext(val loopStartIndex: Int, var loopEndIndex: Int)

class LoopWatchers() {
  val breaks: ListBuffer[Int] = ListBuffer[Int]()
  val continues: ListBuffer[Int] = ListBuffer[Int]()
}