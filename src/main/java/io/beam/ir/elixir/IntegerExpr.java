package io.beam.ir.elixir;

public record IntegerExpr(long value) implements Expression {

  public static IntegerExpr of(long value) {
    return new IntegerExpr(value);
  }
}
