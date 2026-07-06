package io.beam.ir.elixir;

public record PinPattern(String name, SourceSpan source) implements Pattern {

  public static PinPattern of(String name) {
    return new PinPattern(name, null);
  }
}
