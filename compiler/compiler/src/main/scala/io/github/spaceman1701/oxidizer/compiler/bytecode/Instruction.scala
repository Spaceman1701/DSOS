package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.util.{U32, U64, U16}

sealed trait Instruction {
  val opcode: Byte
  val size: Byte = 1
}

/*
Basic Load/Store instructions. For simplicity only wide insturctions.
 */
case class Store(loc: U16) extends Instruction { //objref | value -> [] (convert to objref and place on data stack)
  val opcode = 0
}
case class LoadConstInt(value: Long) extends Instruction {
  val opcode = 1
}
case class LoadConstFloat(value: Double) extends Instruction {
  val opcode = 2
}
case class LoadConstStr(ptr: U32) extends Instruction { //load a string from the constant string areas
  val opcode = 3
}
case class LoadVar(loc: U16) extends Instruction {
  val opcode = 4
}

case object CreateObject extends Instruction { // objref(name) -> objref
  val opcode = 5
}

/*
Native list slice. Allows complex slices to be done in one instruction
 */
case object SliceList extends Instruction { //objref, objref -> objref
  val opcode = 6
}

/*
Math instructions. Short circuit to native impl when available
 */
//objref, objref -> value
case object Add extends Instruction {
  val opcode = 7
}
case object Sub extends Instruction {
  val opcode = 8
}
case object Mul extends Instruction{
  val opcode = 9
}
case object Div extends Instruction{
  val opcode = 10
}
case object Mod extends Instruction{
  val opcode = 11
}
case object Concat extends Instruction{
  val opcode = 12
}
case object Pow extends Instruction{
  val opcode = 13
}

case object LAnd extends Instruction {
  val opcode = 14
}

case object LOr extends Instruction {
  val opcode = 15
}

/*
Compare instructions. For when result of a compare is stored
 */
case object CompG extends Instruction { //objref, objref -> value
  val opcode = 16
}
case object CompL extends Instruction {
  val opcode = 17
}
case object CompEq extends Instruction {
  val opcode = 18
}

/*
Load class member (function or field)
 */
case object LoadMember extends Instruction { //objref, name -> objref
  val opcode = 19
}

/*
Store top into pointer at top-1.

i.e
LoadVar(0) //objref
LoadConstString(foo) //objref, name
LoadMember //objref
LoadConstInt(0) //objref, value
StoreMember //[] (the objref now points to value)

 */
case object StoreMember extends Instruction { //objref, objref -> []
  val opcode = 20
}

/*
Call a function. Creates a new data stack and a new execution stack
The tos should be a callable, the next value should be a param count, and the next values
should be n params
 */
//objref(callable), objref(int), objref... -> []
case object Call extends Instruction {
  val opcode = 21
}


//unconditional jump
case class Jump(target: U32) extends Instruction {
  val opcode = 22
}

/*
Branch instrictions. Normal and wide as most branches are very small. Jumps can only occur within a single module
so wide instructions are safe as unsigned Int
 */
//objref, objref -> []
//jumps can only happen inside a file
//thus, hard limit on 4,294,967,295 per file
case class IfFalse(target: U32) extends Instruction {
  val opcode = 23
}


case object Ret extends Instruction {
  val opcode = 24
}


case object Throw extends Instruction { //objref(exeception) -> [] //clears the execution stack
  val opcode = 25
}

/*
Async IO instructions.
 */
case object PostEvent extends Instruction { //objref(event) -> objref-promise
  val opcode = 26
}
case object WaitEvent extends Instruction { //objref-promise | objref -> objref
  val opcode = 27
}

/*
Spawn new coroutine with a function
 */
case object SpawnCoroutine extends Instruction { //objref(callable)
  val opcode = 28
}

/*
IPC instructions. Write suspends current thread. Read suspends until object is placed on channel
 */
//TODO: Maybe these should use PostEvent and WaitEvent
case object WriteChannel extends Instruction { //objref -> []
  val opcode = 29
}
case object ReadChannel extends Instruction { //objref(channel) -> objref
  val opcode = 30
}

case object Not extends Instruction {
  val opcode = 31
}

case object  XOr extends Instruction {
  val opcode = 32
}

case object BAnd extends Instruction {
  val opcode = 33
}

case object  BOr extends Instruction {
  val opcode = 34
}

case object LeftShift extends Instruction {
  val opcode = 35
}

case object RightShift extends Instruction {
  val opcode = 36
}

case object URightShift extends Instruction {
  val opcode = 37
}

case object Modulo extends Instruction {
  val opcode = 38
}

case object BCompliment extends Instruction {
  val opcode = 39
}

case object Dup extends Instruction { //objref -> objref, objref
  val opcode = 40
}
/*
Following instructions:
Used for the bytecode generator. Cannot be produced in real code.
 */
case object NoOp extends Instruction {
  val opcode: Byte = -1
}

case class Break(loopContext: LoopContext) extends Instruction {
  val opcode: Byte = -2
}

case class Continue(loopContext: LoopContext) extends Instruction {
  val opcode: Byte = -3
}
