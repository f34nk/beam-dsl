package io.beam.ir.elixir;

public record WildcardPattern() implements Pattern {

  public static WildcardPattern of() {
    return new WildcardPattern();
  }
}
