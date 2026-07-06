package io.beam.ir.elixir;

import java.util.List;

public record StructPattern(
    String moduleNameOrNull, List<StructPatternField> fields, SourceSpan source)
    implements Pattern {}
