package io.beam.ir.elixir;

public record TypeDef(String name, String body, SourceSpan source) implements Node {

  @Override
  public SourceSpan source() {
    return source;
  }
}
