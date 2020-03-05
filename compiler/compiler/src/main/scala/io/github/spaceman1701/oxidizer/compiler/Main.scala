package io.github.spaceman1701.oxidizer.compiler

import java.io.{ByteArrayInputStream, File, FileOutputStream, OutputStream}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import io.github.spaceman1701.oxidizer.compiler.ast.{ClassDecl, FunctionDecl}
import io.github.spaceman1701.oxidizer.compiler.bytecode.{BytecodeGenerator, BytecodeWriter, OxModule}
import io.github.spaceman1701.oxidizer.parser.{OxidizerLexer, OxidizerParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Main extends App {

  if (args.length < 1) {
    println("error: no input file")
    System.exit(-1)
  }




  println(s"compiling ${args(0)}")

  val inputBytes = Files.readAllBytes(Paths.get(args(0)))

  val stream = new ByteArrayInputStream(inputBytes)
  val lexer = new OxidizerLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8))
  val parser = new OxidizerParser(new CommonTokenStream(lexer))


  val program = new ParseTreeConverter().visitProgram(parser.program())

  val preCompiledModule = OxModule.fromModuleDef(program)

  val output: OutputStream =
    if (args.length == 3) {
      new FileOutputStream(new File(args(2)))
    } else {
      System.out
    }

  BytecodeWriter.write(preCompiledModule, output)
}
