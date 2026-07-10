package io.beam.ir.elixir;

public record CaptureExpr(String function, int arity) implements Expression {

  public static CaptureExpr of(String function, int arity) {
    return new CaptureExpr(function, arity);
  }
}
