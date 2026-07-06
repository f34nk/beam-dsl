package io.beam.ir.elixir;

public record AtomPattern(String value, SourceSpan source) implements Pattern {

  public static AtomPattern of(String value) {
    return new AtomPattern(value, null);
  }
}
