package io.beam.ir.elixir;

public record MapEntry(Expression key, Expression value, boolean arrowSyntax, SourceSpan source)
    implements Node {

  public static MapEntry atomKey(String key, Expression value) {
    return new MapEntry(AtomExpr.of(key), value, false, null);
  }

  public static MapEntry stringKey(String key, Expression value) {
    return new MapEntry(StringExpr.of(key), value, true, null);
  }

  public static MapEntry pair(Expression key, Expression value) {
    return new MapEntry(key, value, true, null);
  }
}
