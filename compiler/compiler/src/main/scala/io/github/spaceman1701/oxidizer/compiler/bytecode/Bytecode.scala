package io.github.spaceman1701.oxidizer.compiler.bytecode

sealed trait Instruction {

  /*
  Basic Load/Store instructions. For simplicity only wide insturctions.
   */
  case class Store(loc: Int) extends Instruction //objref | value -> [] (convert to objref and place on data stack)
  case class LoadConstInt(value: Long) extends Instruction
  case class LoadConstFloat(value: Double) extends Instruction
  case class LoadConstStr(ptr: Long) extends Instruction //load a string from the constant string areas
  case class LoadVar(loc: Int) extends Instruction

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
  Call a function. Creates a new data stack and a new execution stack
   */
  case object Call extends Instruction//objref(callable) -> []

  /*
  Branch instrictions. Normal and wide as most branches are very small. Jumps can only occur within a single module
  so wide instructions are safe as signed Int
   */
  case class IfG(target: Byte) extends Instruction//objref, objref -> []
  case class IfL(target: Byte) extends Instruction
  case class IfEq(target: Byte) extends Instruction
  case class IfGWide(target: Int) extends Instruction //jumps can only happen inside a file
  case class IfLWide(target: Int) extends Instruction
  case class IfEqWide(target: Int) extends Instruction

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
  case object CreateChannel extends Instruction // [] -> objref(channel)
  case object WriteChannel extends Instruction //objref -> []
  case object ReadChannel extends Instruction //objref(channel) -> objref

}

