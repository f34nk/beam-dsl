package io.beam.ir.elixir;

public record AssignPattern(Pattern left, Pattern right, SourceSpan source) implements Pattern {

  public static AssignPattern of(String name, Pattern pattern) {
    return new AssignPattern(VariablePattern.of(name), pattern, null);
  }

  public static AssignPattern of(Pattern left, Pattern right) {
    return new AssignPattern(left, right, null);
  }
}
