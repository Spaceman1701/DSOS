package io.github.spaceman1701.oxidizer.compiler

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

import io.github.spaceman1701.oxidizer.parser.{OxidizerLexer, OxidizerParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import org.junit.Test
import org.junit.Assert._

import io.github.spaceman1701.oxidizer.compiler.ast._

class ParseTreeConverterTest {
  @Test
  def testParseSimpleModule(): Unit = {
    val input =
      "import foobar; \n" +
      "class TestClass {}"
    val stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
    val lexer = new OxidizerLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8))
    val parser = new OxidizerParser(new CommonTokenStream(lexer))
    val module = new ParseTreeConverter().visitProgram(parser.program())
    assertNotNull(module)

    module match {
      case ModuleDef(imports, decls) => {
        assertEquals("incorrect number of imports", 1, imports.length)
        assertEquals("wrong import name", "foobar", imports(0).Name)

        assertEquals("wrong number of decls", 1, decls.length)

        decls(0) match {
          case ClassDecl(name, methods) => {
            assertEquals("wrong class name", "TestClass", name)
            assertEquals("wrong number of methods",0, methods.length)
          }
          case _ => fail("incorrect AST")
        }
      }
    }
  }
}
