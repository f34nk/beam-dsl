package io.beam.ir.elixir;

import java.util.List;

public record AnonFun(List<AnonFunClause> clauses, SourceSpan source) implements Expression {}
