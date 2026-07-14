package io.beam.ir.elixir;

public record CharlistExpr(String value) implements Expression {

  public static CharlistExpr of(String value) {
    return new CharlistExpr(value);
  }
}
