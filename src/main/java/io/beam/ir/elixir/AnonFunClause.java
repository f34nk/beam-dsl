package io.beam.ir.elixir;

import java.util.List;

public record AnonFunClause(List<Pattern> params, Expression body, SourceSpan source)
    implements Node {}
