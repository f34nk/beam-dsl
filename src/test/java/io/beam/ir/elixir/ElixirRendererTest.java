package io.beam.ir.elixir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class ElixirRendererTest {

  @Test
  void printWidthMatchesMixFormat() {
    assertEquals(98, DefaultElixirRenderer.printWidthForTests());
  }

  @Test
  void rendersAtomExpr() {
    assertEquals(":foo", ElixirRenderer.renderExpression(AtomExpr.of("foo")));
  }

  @Test
  void rendersNilAndBoolean() {
    assertEquals("nil", ElixirRenderer.renderExpression(NilExpr.of()));
    assertEquals("true", ElixirRenderer.renderExpression(BooleanExpr.of(true)));
  }

  @Test
  void rendersStringExpr() {
    assertEquals("\"hello\"", ElixirRenderer.renderExpression(StringExpr.of("hello")));
  }

  @Test
  void rendersTupleExpr() {
    assertEquals(
        "{:ok, resp}",
        ElixirRenderer.renderExpression(
            TupleExpr.of(List.of(AtomExpr.of("ok"), Variable.of("resp")))));
  }

  @Test
  void rendersListExpr() {
    assertEquals(
        "[1, 2, 3]",
        ElixirRenderer.renderExpression(
            ListExpr.of(
                List.of(IntegerExpr.of(1), IntegerExpr.of(2), IntegerExpr.of(3)))));
  }

  @Test
  void rendersMapExpr() {
    assertEquals("%{}", ElixirRenderer.renderExpression(MapExpr.of(List.of())));
    assertEquals(
        "%{key: val}",
        ElixirRenderer.renderExpression(
            MapExpr.of(List.of(MapEntry.atomKey("key", Variable.of("val"))))));
    assertEquals(
        "%{\"k\" => v}",
        ElixirRenderer.renderExpression(
            MapExpr.of(List.of(MapEntry.stringKey("k", Variable.of("v"))))));
    assertEquals(
        "acc %{key: val}",
        ElixirRenderer.renderExpression(
            MapExpr.of(
                Variable.of("acc"), List.of(MapEntry.atomKey("key", Variable.of("val"))))));
  }
}
