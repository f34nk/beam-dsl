package io.beam.ir.erlang;

public record TypedField(String name, String type, SourceSpan source) {

  public static TypedField of(String name, String type) {
    return new TypedField(name, type, null);
  }

  public static TypedField of(String name, String type, SourceSpan source) {
    return new TypedField(name, type, source);
  }
}
