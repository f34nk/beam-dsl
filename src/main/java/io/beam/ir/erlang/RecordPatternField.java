package io.beam.ir.erlang;

public record RecordPatternField(String name, Pattern pattern, SourceSpan source) {

  public static RecordPatternField of(String name, Pattern pattern) {
    return new RecordPatternField(name, pattern, null);
  }

  public static RecordPatternField of(String name, Pattern pattern, SourceSpan source) {
    return new RecordPatternField(name, pattern, source);
  }
}
