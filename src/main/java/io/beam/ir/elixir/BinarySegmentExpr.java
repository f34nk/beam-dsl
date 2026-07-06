package io.beam.ir.elixir;

public record BinarySegmentExpr(Expression value, String typeOrNull, SourceSpan source)
    implements Node {}
