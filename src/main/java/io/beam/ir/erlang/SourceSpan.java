package io.beam.ir.erlang;

public record SourceSpan(String file, int startLine, int startColumn, int endLine, int endColumn) {

  public static SourceSpan of(
      String file, int startLine, int startColumn, int endLine, int endColumn) {
    return new SourceSpan(file, startLine, startColumn, endLine, endColumn);
  }
}
