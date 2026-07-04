package io.beam.ir.erlang;

public record OpaquePattern(String text, SourceSpan source) implements Pattern {

  public static OpaquePattern of(String text) {
    return new OpaquePattern(text, null);
  }
}
