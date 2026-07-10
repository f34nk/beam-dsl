package io.beam.ir.elixir;

public record AtomPattern(String value) implements Pattern {

  public static AtomPattern of(String value) {
    return new AtomPattern(value);
  }
}
