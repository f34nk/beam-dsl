package io.beam.ir.erlang;

public record VariablePattern(String name, SourceSpan source) implements Pattern {

  public static VariablePattern of(String name) {
    return new VariablePattern(name, null);
  }

  public static VariablePattern of(String name, SourceSpan source) {
    return new VariablePattern(name, source);
  }
}
