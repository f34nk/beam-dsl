package io.beam.ir.elixir;

public record InfixExpr(Expression left, String op, Expression right)
    implements Expression {}
