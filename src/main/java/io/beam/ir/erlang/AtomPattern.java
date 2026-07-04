package io.beam.ir.erlang;

public record AtomPattern(String value, SourceSpan source) implements Pattern {

  public static AtomPattern of(String value) {
    return new AtomPattern(value, null);
  }

  public static AtomPattern of(String value, SourceSpan source) {
    return new AtomPattern(value, source);
  }
}
