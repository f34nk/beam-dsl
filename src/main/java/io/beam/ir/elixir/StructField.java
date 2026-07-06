package io.beam.ir.elixir;

public record StructField(String name, Expression value, SourceSpan source) implements Node {

  public static StructField of(String name, Expression value) {
    return new StructField(name, value, null);
  }
}
