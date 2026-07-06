package io.beam.ir.elixir;

import java.util.List;

public record MapPattern(List<MapPatternEntry> entries, SourceSpan source) implements Pattern {

  public static MapPattern of(List<MapPatternEntry> entries) {
    return new MapPattern(entries, null);
  }
}
