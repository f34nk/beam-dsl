package io.beam.ir.erlang;

public record WildcardPattern(String name, SourceSpan source) implements Pattern {

  public static WildcardPattern of() {
    return new WildcardPattern(null, null);
  }

  public static WildcardPattern of(String name) {
    return new WildcardPattern(name, null);
  }

  public static WildcardPattern of(SourceSpan source) {
    return new WildcardPattern(null, source);
  }
}
