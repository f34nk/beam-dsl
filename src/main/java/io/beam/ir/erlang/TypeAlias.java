package io.beam.ir.erlang;

import java.util.List;

public record TypeAlias(
    String name,
    String definition,
    List<String> preambleCommentsOrNull,
    List<String> variantsOrNull,
    SourceSpan source) {

  public static TypeAlias of(String name, String definition) {
    return new TypeAlias(name, definition, null, null, null);
  }

  public static TypeAlias of(String name, String definition, SourceSpan source) {
    return new TypeAlias(name, definition, null, null, source);
  }

  public static TypeAlias of(String name, String definition, List<String> preambleComments) {
    return new TypeAlias(name, definition, preambleComments, null, null);
  }

  public static TypeAlias union(String name, List<String> variants) {
    return new TypeAlias(name, String.join(" | ", variants), null, variants, null);
  }
}
