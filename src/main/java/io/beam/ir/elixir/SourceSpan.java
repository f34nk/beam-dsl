package io.beam.ir.elixir;

public record SourceSpan(int line, int column) {

  public static SourceSpan of(int line, int column) {
    return new SourceSpan(line, column);
  }
}
