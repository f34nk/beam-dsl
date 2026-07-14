package io.beam.ir.elixir;

public record ExpressionGuard(Expression expression) implements Guard {

  public static ExpressionGuard of(Expression expression) {
    return new ExpressionGuard(expression);
  }
}
