package io.beam.ir.erlang;

import java.util.List;

public record TypedField(
    String name,
    String type,
    String defaultValueOrNull,
    List<String> fieldCommentsOrNull,
    SourceSpan source) {

  public static TypedField of(String name, String type) {
    return new TypedField(name, type, null, null, null);
  }

  public static TypedField of(String name, String type, SourceSpan source) {
    return new TypedField(name, type, null, null, source);
  }

  public static TypedField of(String name, String type, String defaultValue) {
    return new TypedField(name, type, defaultValue, null, null);
  }

  public static TypedField of(
      String name, String type, String defaultValue, List<String> fieldComments) {
    return new TypedField(name, type, defaultValue, fieldComments, null);
  }
}
