package io.beam.ir.elixir;

public record UseOption(String key, Expression value, SourceSpan source) implements Node {

  @Override
  public SourceSpan source() {
    return source;
  }
}
