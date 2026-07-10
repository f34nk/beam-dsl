package io.beam.ir.elixir;

import java.util.List;

public record BlockExpr(List<Expression> statements) implements Expression {}
