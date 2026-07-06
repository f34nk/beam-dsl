package io.beam.ir.elixir;

import java.util.List;

public record MapExpr(Expression baseOrNull, List<MapEntry> entries, SourceSpan source)
    implements Expression {

  public static MapExpr of(List<MapEntry> entries) {
    return new MapExpr(null, entries, null);
  }

  public static MapExpr of(Expression base, List<MapEntry> entries) {
    return new MapExpr(base, entries, null);
  }
}
