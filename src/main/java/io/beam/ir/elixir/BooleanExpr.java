package io.beam.ir.elixir;

public record BooleanExpr(boolean value) implements Expression {

  public static BooleanExpr of(boolean value) {
    return new BooleanExpr(value);
  }
}
