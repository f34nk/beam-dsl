package io.beam.ir.elixir;

public record NilPattern() implements Pattern {

  public static NilPattern of() {
    return new NilPattern();
  }
}
