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
      "class TestClass {\n" +
      "  private aField;\n" +
      "  aPublicField = 72;\n" +
        "def aMethod() {}\n" +
      "}"
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
          case ClassDecl(name, members) => {
            assertEquals("wrong class name", "TestClass", name)
            assertEquals("wrong number of class members", 3, members.length)

            var methodCount = 0
            var fieldCount = 0
            for (member <- members) {
              member match {
                case ClassMethod(_) => methodCount += 1
                case ClassField(_, _, _) => fieldCount += 1
              }
            }

            assertEquals("wrong number of class methods", 1, methodCount)
            assertEquals("wrong number of class fields", 2, fieldCount)
          }
          case _ => fail("incorrect AST")
        }
      }
    }
  }

  @Test
  def testTupleAssignment(): Unit = {
    val input =
      "def function() {\n" +
      "  a, b, c = [12, 8 * 8, foobar()[32]];\n" +
      "}"
    val stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
    val lexer = new OxidizerLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8))
    val parser = new OxidizerParser(new CommonTokenStream(lexer))
    val module = new ParseTreeConverter().visitProgram(parser.program())
    assertNotNull(module)

    module match {
      case ModuleDef(_, decls) => {
        decls(0) match {
          case FunctionDecl(FunctionDef(_, _, _, body)) => {
            val stmt = body(0)
            stmt match {
              case DestructerAssignStmt(idents, expr) => {
                assertEquals("there are three variables", 3, idents.length)
                assertEquals("the first variable is a", "a", idents(0))
                expr match {
                  case ListComp(LiteralList(exprs)) => {
                    assertEquals("three list elements", 3, exprs.length)
                  }
                  case _ => fail("expr wrong type")
                }
              }
              case _ => fail("statement wrong type")
            }
          }
          case _ => fail("no function decl")
        }
      }
    }
  }
}
