package io.beam.ir.elixir;

public record VariablePattern(String name, SourceSpan source) implements Pattern {

  public static VariablePattern of(String name) {
    return new VariablePattern(name, null);
  }
}
