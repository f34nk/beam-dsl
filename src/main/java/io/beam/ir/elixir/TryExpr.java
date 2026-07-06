package io.beam.ir.elixir;

import java.util.List;

public record TryExpr(Expression body, List<CatchClause> catchClauses, SourceSpan source)
    implements Expression {}
