package io.beam.ir.erlang;

public record Clause(Pattern pattern, Guard guard, Expression body, SourceSpan source)
    implements Node {

  public static Clause of(Pattern pattern, Expression body) {
    return new Clause(pattern, null, body, null);
  }

  public static Clause of(Pattern pattern, Guard guard, Expression body) {
    return new Clause(pattern, guard, body, null);
  }

  public static Clause of(Pattern pattern, Expression body, SourceSpan source) {
    return new Clause(pattern, null, body, source);
  }

  public static Clause of(Pattern pattern, Guard guard, Expression body, SourceSpan source) {
    return new Clause(pattern, guard, body, source);
  }
}
