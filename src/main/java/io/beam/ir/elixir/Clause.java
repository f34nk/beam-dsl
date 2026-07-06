package io.beam.ir.elixir;

public record Clause(Pattern pattern, Expression body, SourceSpan source) implements Node {

  public static Clause of(Pattern pattern, Expression body) {
    return new Clause(pattern, body, null);
  }
}
