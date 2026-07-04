package io.beam.ir.erlang;

import java.util.List;

public record Module(
    String name,
    List<Function> functions,
    List<String> headerComments,
    Moduledoc moduledoc,
    List<String> includeHeaders,
    List<TypeAlias> typeAliases,
    List<String> exports,
    String verbatimOrNull) {

  public Module(String name, List<Function> functions) {
    this(name, functions, null, null, null, null, null, null);
  }

  public static Module of(String name, List<Function> functions) {
    return new Module(name, functions);
  }

  public static Module of(
      String name,
      List<Function> functions,
      List<String> headerComments,
      Moduledoc moduledoc,
      String includeHeader) {
    return new Module(
        name,
        functions,
        headerComments,
        moduledoc,
        includeHeader == null ? null : List.of(includeHeader),
        null,
        null,
        null);
  }

  public static Module of(
      String name,
      List<Function> functions,
      List<String> headerComments,
      Moduledoc moduledoc,
      String includeHeader,
      List<TypeAlias> typeAliases) {
    return new Module(
        name,
        functions,
        headerComments,
        moduledoc,
        includeHeader == null ? null : List.of(includeHeader),
        typeAliases,
        null,
        null);
  }

  public static Module of(
      String name,
      List<Function> functions,
      List<String> headerComments,
      Moduledoc moduledoc,
      List<String> includeHeaders,
      List<TypeAlias> typeAliases,
      List<String> exports) {
    return new Module(
        name, functions, headerComments, moduledoc, includeHeaders, typeAliases, exports, null);
  }

  public static Module verbatim(String content) {
    return new Module("", List.of(), null, null, null, null, null, content);
  }
}
