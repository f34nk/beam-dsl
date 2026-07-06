package io.beam.ir.elixir;

public record StructPatternField(String nameOrNull, Pattern pattern, SourceSpan source)
    implements Node {}
