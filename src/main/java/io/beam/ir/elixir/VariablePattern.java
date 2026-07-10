package io.beam.ir.elixir;

public record VariablePattern(String name) implements Pattern {

  public static VariablePattern of(String name) {
    return new VariablePattern(name);
  }
}
