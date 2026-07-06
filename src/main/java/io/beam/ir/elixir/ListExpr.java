package io.beam.ir.elixir;

import java.util.List;

public record ListExpr(List<Expression> elements, SourceSpan source) implements Expression {

  public static ListExpr of(List<Expression> elements) {
    return new ListExpr(elements, null);
  }
}
