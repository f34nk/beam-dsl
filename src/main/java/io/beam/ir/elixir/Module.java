package io.beam.ir.elixir;

import java.util.List;

public record Module(
    String name,
    Moduledoc moduledocOrNull,
    List<UseDirective> uses,
    List<Alias> aliases,
    List<Function> functions,
    List<String> moduleAttributes,
    String verbatimOrNull)
    implements Node {

  public static Module of(String name, List<Function> functions) {
    return new Module(name, null, List.of(), List.of(), functions, List.of(), null);
  }

  public static Module verbatim(String content) {
    return new Module("", null, List.of(), List.of(), List.of(), List.of(), content);
  }

  @Override
  public SourceSpan source() {
    return null;
  }
}
