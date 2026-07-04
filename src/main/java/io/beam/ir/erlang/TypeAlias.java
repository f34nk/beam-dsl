package io.beam.ir.erlang;

public record TypeAlias(String name, String definition, SourceSpan source) {

  public static TypeAlias of(String name, String definition) {
    return new TypeAlias(name, definition, null);
  }

  public static TypeAlias of(String name, String definition, SourceSpan source) {
    return new TypeAlias(name, definition, source);
  }
}
