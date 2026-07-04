package io.beam.ir.erlang;

public record MapPatternEntry(
    Expression key, Pattern value, boolean updateOnly, SourceSpan source) {

  public static MapPatternEntry of(Expression key, Pattern value) {
    return new MapPatternEntry(key, value, false, null);
  }

  public static MapPatternEntry of(Expression key, Pattern value, boolean updateOnly) {
    return new MapPatternEntry(key, value, updateOnly, null);
  }
}
