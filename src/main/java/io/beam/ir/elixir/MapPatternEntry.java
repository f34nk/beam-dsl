package io.beam.ir.elixir;

import java.util.List;

public record MapPatternEntry(Expression key, Pattern value, SourceSpan source) implements Node {

  public static MapPatternEntry of(Expression key, Pattern value) {
    return new MapPatternEntry(key, value, null);
  }
}
