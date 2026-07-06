package io.beam.ir.elixir;

import java.util.List;

public record UseDirective(String module, List<UseOption> options, SourceSpan source)
    implements Node {

  @Override
  public SourceSpan source() {
    return source;
  }
}
