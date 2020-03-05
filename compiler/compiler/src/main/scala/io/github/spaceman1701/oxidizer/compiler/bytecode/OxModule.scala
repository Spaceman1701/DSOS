package io.github.spaceman1701.oxidizer.compiler.bytecode

import io.github.spaceman1701.oxidizer.compiler.ast.{ArrayIndex, Binop, ClassDecl, ClassField, ClassMethod, FloatLit, FunCall, FunctionDecl, IntLit, ListComp, ListenExpr, Lit, ModuleDef, Parens, SendExpr, StringLit, Ternary, Unop, Var}
import io.github.spaceman1701.oxidizer.compiler.util.{LiteralFloatValue, LiteralIntValue, LiteralStringValue, LiteralValue}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class OxModule(val classes: List[ClassDescriptor], val functions: Map[String, Long], val bytecode: List[Instruction]) {
  val functionByIndex: Map[Long, String] = functions.map{case (name, index) => (index, name)}
}

object OxModule {

  private def mangleName(className: String, memberName: String) = className + "__" + memberName

  def fromModuleDef(program: ModuleDef): OxModule = {

    val classes = ListBuffer[ClassDescriptor]()
    val functions = mutable.Map[String, Long]()

    val bytecodeGenerator = new BytecodeGenerator()


    for (decl <- program.decls) {
      decl match {
        case ClassDecl(name, members) =>
          val fieldMap = mutable.Map[String, Option[LiteralValue]]()
          for (member <- members) {

            member match {
              case ClassMethod(func) =>
                //Class methods should store a string which references the true function name in the runtime
                //object data.
                val startIndex = bytecodeGenerator.generateFunctionBody(func)
                val compiledName = mangleName(name, func.name)
                functions += (compiledName -> startIndex)

                fieldMap += (func.name -> Some(LiteralStringValue(compiledName)))
              case ClassField(isPrivate, name, initExpr) =>
                initExpr match {
                  case None => fieldMap += (name -> None)
                  case Some(value) => value match {
                    case Lit(literal) =>

                      val litVal: LiteralValue = literal match {
                        case IntLit(value) => LiteralIntValue(value)
                        case FloatLit(value) => LiteralFloatValue(value)
                        case StringLit(parts) => ???
                      }

                      fieldMap += (name -> Some(litVal))
                    case _ => ???
                  }
                }
            }
          }
          classes += new ClassDescriptor(name, fieldMap.toMap)
        case FunctionDecl(functionDef) =>
          val startIndex = bytecodeGenerator.generateFunctionBody(functionDef)
          functions += (functionDef.name -> startIndex)
      }
    }
    new OxModule(classes.toList, functions.toMap, bytecodeGenerator.bytecodeBuffer.toList)
  }
}
