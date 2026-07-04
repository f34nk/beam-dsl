package io.beam.ir.erlang;

import java.util.List;

public record FunctionClause(
    List<Pattern> patterns, Guard guard, Expression body, SourceSpan source) implements Node {

  public static FunctionClause of(List<Pattern> patterns, Expression body) {
    return new FunctionClause(patterns, null, body, null);
  }

  public static FunctionClause of(List<Pattern> patterns, Guard guard, Expression body) {
    return new FunctionClause(patterns, guard, body, null);
  }

  public static FunctionClause of(
      List<Pattern> patterns, Guard guard, Expression body, SourceSpan source) {
    return new FunctionClause(patterns, guard, body, source);
  }
}
