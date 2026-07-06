package io.beam.ir.elixir;

public record CatchClause(Pattern kind, Pattern reason, Expression body, SourceSpan source)
    implements Node {}
