package io.beam.ir.elixir;

import java.util.List;

public record TuplePattern(List<Pattern> elements, SourceSpan source) implements Pattern {

  public static TuplePattern of(List<Pattern> elements) {
    return new TuplePattern(elements, null);
  }
}
