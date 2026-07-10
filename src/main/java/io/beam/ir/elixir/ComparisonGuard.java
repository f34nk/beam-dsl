package io.beam.ir.elixir;

public record ComparisonGuard(Expression left, String op, Expression right) implements Guard {}
