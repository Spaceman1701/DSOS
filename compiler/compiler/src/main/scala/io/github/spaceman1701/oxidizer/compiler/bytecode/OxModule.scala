package io.github.spaceman1701.oxidizer.compiler.bytecode

import java.nio.{ByteBuffer, ByteOrder}

import io.github.spaceman1701.oxidizer.compiler.ast.{ArrayIndex, Binop, ClassDecl, ClassField, ClassMethod, FloatLit, FunCall, FunctionDecl, IntLit, ListComp, ListenExpr, Lit, ModuleDef, Parens, SendExpr, StringLit, Ternary, Unop, Var}
import io.github.spaceman1701.oxidizer.compiler.util.{LiteralFloatValue, LiteralIntValue, LiteralStringValue, LiteralValue, U32}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class OxModule(val classes: List[ClassDescriptor], val functions: Map[String, Long], val stringsBuffer: NameBuffer, val bytecode: List[Instruction]) {
  val functionByIndex: Map[Long, String] = functions.map{case (name, index) => (index, name)}

  def compile(): CompiledModule = {
    var (bytecodePts, _) = bytecode.foldLeft((List[Int](), 0)){case ((sizes, totalSize), ins) =>
      val newTotal = totalSize + ins.size
      (newTotal :: sizes, newTotal)
    }

    bytecodePts = bytecodePts.reverse

    var (startPtr, stringSegment, _) = stringsBuffer.finalizeList().foldLeft((List[Int](), Array[Byte](), 0)){case ((offsets, segment, totalBytes), string) =>
      val bytes = string.getBytes.concat(Array[Byte](0))
      val size = bytes.length //number of bytes for the string plus terminator
      (totalBytes :: offsets, segment.concat(bytes),totalBytes + size)
    }

    startPtr = startPtr.reverse

    println(startPtr)

    val processedIns = bytecode.map {
      case Jump(target) => Jump(new U32(bytecodePts(target.value.toInt)))
      case IfFalse(target) => IfFalse(new U32(target.value.toInt))
      case LoadConstStr(ptr) => {
        println("load str at ", startPtr(ptr.value.toInt))
        LoadConstStr(new U32(startPtr(ptr.value.toInt)))
      }
      case NoOp => throw new IllegalStateException("NoOp was not processed out during initial pre-compile")
      case Break(loopContext) => throw new IllegalStateException("Break was not processed out during initial pre-compile")
      case Continue(loopContext) => throw new IllegalStateException("Continue was not processed out during initial pre-compile")
      case ins => ins
    }

    val processedFunctions = functions.map{case (name, index) => (name -> bytecodePts(index.toInt))}

    val bytes = processedIns.foldLeft(Array[Byte]()){case (array, ins) =>
      val insBytes = emitBytes(ins)
      array.concat(insBytes)
    }

    val headerFields = ListBuffer[HeaderField]()

    processedFunctions.foreach{case (name, ptr) =>
        val header = FunctionField(stringsBuffer.add(name).toInt, ptr)
        headerFields.addOne(header)
    }

    classes.foreach{desc =>
      val classNameIndex = stringsBuffer.add(desc.name).toInt

      for ((name, value) <- desc.members) {
        val nameIndex = stringsBuffer.add(name).toInt
        val data = value match {
          case None => Array[Byte](0)
          case Some(value) => value match {
            case LiteralStringValue(v) =>
              val ptr = stringsBuffer.add(v)
              ByteBuffer.allocate(8)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putLong(ptr).array()
            case LiteralIntValue(v) =>
              ByteBuffer.allocate(8)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putLong(v).array()
            case LiteralFloatValue(v) =>
              ByteBuffer.allocate(8)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putDouble(v).array()
          }
        }

        val header = ClassHeaderField(classNameIndex, nameIndex, data)
        headerFields.addOne(header)
      }
    }

    val headerBytes = headerFields.flatMap{field => field.toBytes}.toArray

    new CompiledModule(headerBytes, stringSegment, bytes)
  }

  private def emitBytes(ins: Instruction): Array[Byte] = {
    ins match {
      case Store(loc) =>
        val param = loc.value.toByteArray
        Array(ins.opcode).concat(param)
      case LoadConstInt(value) =>
        val param = ByteBuffer.allocate(8)
          .order(ByteOrder.LITTLE_ENDIAN)
          .putLong(value).array()
        Array(ins.opcode).concat(param)
      case LoadConstFloat(value) =>
        val param = ByteBuffer.allocate(8)
          .order(ByteOrder.LITTLE_ENDIAN)
          .putDouble(value).array()
        Array(ins.opcode).concat(param)
      case LoadConstStr(ptr) =>
        val param = ptr.toByteArray
        println(param.length)
        Array(ins.opcode).concat(param)
      case LoadVar(loc) =>
        val param = loc.toByteArray
        Array(ins.opcode).concat(param)
      case Jump(target) =>
        val param = target.toByteArray
        Array(ins.opcode).concat(param)
      case IfFalse(target) =>
        val param = target.toByteArray
        Array(ins.opcode).concat(param)
      case other => Array(other.opcode)
    }
  }
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
          bytecodeGenerator.stringConstants.add(name)
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
                bytecodeGenerator.stringConstants.add(compiledName)
              case ClassField(isPrivate, name, initExpr) =>
                bytecodeGenerator.stringConstants.add(name)
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
          bytecodeGenerator.stringConstants.add(functionDef.name)
      }
    }
    new OxModule(classes.toList, functions.toMap, bytecodeGenerator.stringConstants, bytecodeGenerator.bytecodeBuffer.toList)
  }
}
