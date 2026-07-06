package io.beam.ir.elixir;

import java.util.List;

public record AndGuard(List<Guard> guards, SourceSpan source) implements Guard {

  public static AndGuard of(List<Guard> guards) {
    return new AndGuard(guards, null);
  }
}
