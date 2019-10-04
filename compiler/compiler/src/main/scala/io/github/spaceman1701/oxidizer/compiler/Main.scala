package io.github.spaceman1701.oxidizer.compiler

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

import io.github.spaceman1701.oxidizer.{OxidizerLexer, OxidizerParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Main extends App {
  val input = "hello world;"
  val stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
  val lexer = new OxidizerLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8))

  val parser = new OxidizerParser(new CommonTokenStream(lexer))

}
