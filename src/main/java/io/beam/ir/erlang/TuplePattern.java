package io.beam.ir.erlang;

import java.util.List;

public record TuplePattern(List<Pattern> elements, SourceSpan source) implements Pattern {

  public static TuplePattern of(List<Pattern> elements) {
    return new TuplePattern(elements, null);
  }

  public static TuplePattern of(List<Pattern> elements, SourceSpan source) {
    return new TuplePattern(elements, source);
  }
}
