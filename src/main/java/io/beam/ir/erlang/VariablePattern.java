package io.beam.ir.erlang;

public record VariablePattern(String name) implements Pattern {

  public static VariablePattern of(String name) {
    return new VariablePattern(name);
  }
}
