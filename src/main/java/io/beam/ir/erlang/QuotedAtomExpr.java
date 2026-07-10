package io.beam.ir.erlang;

public record QuotedAtomExpr(String value) implements Expression {

  public static QuotedAtomExpr of(String value) {
    return new QuotedAtomExpr(value);
  }
}
