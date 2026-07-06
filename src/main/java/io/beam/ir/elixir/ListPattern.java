package io.beam.ir.elixir;

import java.util.List;

public record ListPattern(List<Pattern> elements, SourceSpan source) implements Pattern {

  public static ListPattern of(List<Pattern> elements) {
    return new ListPattern(elements, null);
  }
}
