package io.beam.ir.elixir;

public record WildcardPattern(SourceSpan source) implements Pattern {

  public static WildcardPattern of() {
    return new WildcardPattern(null);
  }
}
