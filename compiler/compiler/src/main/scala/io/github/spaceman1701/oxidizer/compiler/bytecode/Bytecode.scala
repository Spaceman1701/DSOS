package io.github.spaceman1701.oxidizer.compiler.bytecode

sealed trait Instruction {
  case class Store(loc: Long) extends Instruction
  case class LoadConst(value: Long) extends Instruction
  case class LoadVar(loc: Long) extends Instruction

  case object LoadArray extends Instruction //objref, index -> objref

  case object Add extends Instruction
  case object Sub extends Instruction
  case object Mul extends Instruction
  case object Div extends Instruction
  case object Mod extends Instruction
  case object Concat extends Instruction
  case object Pow extends Instruction

  case object CompG extends Instruction
  case object CompL extends Instruction
  case object CompEq extends Instruction

  case object GetMember extends Instruction//objref, name -> objref

  case object Call extends Instruction//objref -> []

  case class IfG(target: Byte) extends Instruction
  case class IfL(target: Byte) extends Instruction
  case class IfEq(target: Byte) extends Instruction
  case class IfGWide(target: Long) extends Instruction
  case class IfLWide(target: Long) extends Instruction
  case class IfEqWide(target: Long) extends Instruction

}

