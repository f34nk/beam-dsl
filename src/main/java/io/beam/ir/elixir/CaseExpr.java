package io.beam.ir.elixir;

import java.util.List;

public record CaseExpr(Expression subject, List<Clause> clauses, SourceSpan source)
    implements Expression {}
