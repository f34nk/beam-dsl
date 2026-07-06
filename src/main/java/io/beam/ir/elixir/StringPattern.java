package io.beam.ir.elixir;

public record StringPattern(String value, SourceSpan source) implements Pattern {

  public static StringPattern of(String value) {
    return new StringPattern(value, null);
  }
}
