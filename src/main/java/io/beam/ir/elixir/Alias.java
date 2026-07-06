package io.beam.ir.elixir;

public record Alias(String module, String asOrNull, SourceSpan source) implements Node {

  public static Alias of(String module) {
    return new Alias(module, null, null);
  }

  public static Alias of(String module, String as) {
    return new Alias(module, as, null);
  }

  @Override
  public SourceSpan source() {
    return source;
  }
}
