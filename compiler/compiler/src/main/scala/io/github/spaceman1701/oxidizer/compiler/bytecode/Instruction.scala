package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.util.{U32, U64, U16}

sealed trait Instruction

/*
Basic Load/Store instructions. For simplicity only wide insturctions.
 */
case class Store(loc: U16) extends Instruction //objref | value -> [] (convert to objref and place on data stack)
case class LoadConstInt(value: Long) extends Instruction
case class LoadConstFloat(value: Double) extends Instruction
case class LoadConstStr(ptr: U32) extends Instruction //load a string from the constant string areas
case class LoadVar(loc: U16) extends Instruction

case object CreateObject extends Instruction // objref(name) -> objref

/*
Native list slice. Allows complex slices to be done in one instruction
 */
case object SliceList extends Instruction //objref, objref -> objref

/*
Math instructions. Short circuit to native impl when available
 */
case object Add extends Instruction //objref, objref -> value
case object Sub extends Instruction
case object Mul extends Instruction
case object Div extends Instruction
case object Mod extends Instruction
case object Concat extends Instruction
case object Pow extends Instruction

/*
Compare instructions. For when result of a compare is stored
 */
case object CompG extends Instruction //objref, objref -> value
case object CompL extends Instruction
case object CompEq extends Instruction

/*
Load class member (function or field)
 */
case object LoadMember extends Instruction//objref, name -> objref

/*
Store top into pointer at top-1.

i.e
LoadVar(0) //objref
LoadConstString(foo) //objref, name
LoadMember //objref
LoadConstInt(0) //objref, value
StoreMember //[] (the objref now points to value)

 */
case object StoreMember extends Instruction //objref, objref -> []

/*
Call a function. Creates a new data stack and a new execution stack
 */
case object Call extends Instruction//objref(callable) -> []

/*
Branch instrictions. Normal and wide as most branches are very small. Jumps can only occur within a single module
so wide instructions are safe as unsigned Int
 */
case class IfG(target: Byte) extends Instruction//objref, objref -> []
case class IfL(target: Byte) extends Instruction
case class IfEq(target: Byte) extends Instruction
case class IfGWide(target: U32) extends Instruction //jumps can only happen inside a file
case class IfLWide(target: U32) extends Instruction //thus, hard limit on 4,294,967,295 per file
case class IfEqWide(target: U32) extends Instruction


case object Throw extends Instruction //objref(exeception) -> [] //clears the execution stack

/*
Async IO instructions.
 */
case object PostEvent extends Instruction //objref(event) -> objref-promise
case object WaitEvent extends Instruction //objref-promise | objref -> objref

/*
Spawn new coroutine with a function
 */
case object SpawnCoroutine extends Instruction //objref(callable)

/*
IPC instructions. Write suspends current thread. Read suspends until object is placed on channel
 */
//TODO: Maybe these should use PostEvent and WaitEvent
case object WriteChannel extends Instruction //objref -> []
case object ReadChannel extends Instruction //objref(channel) -> objref
