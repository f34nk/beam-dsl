package io.beam.ir.elixir;

public record IfExpr(
    Expression condition, Expression thenBranch, Expression elseBranchOrNull, boolean inline)
    implements Expression {}
