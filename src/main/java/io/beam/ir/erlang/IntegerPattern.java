package io.beam.ir.erlang;

public record IntegerPattern(long value, SourceSpan source) implements Pattern {

  public static IntegerPattern of(long value) {
    return new IntegerPattern(value, null);
  }

  public static IntegerPattern of(long value, SourceSpan source) {
    return new IntegerPattern(value, source);
  }
}
