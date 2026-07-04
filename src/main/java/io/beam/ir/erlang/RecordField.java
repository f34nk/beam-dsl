package io.beam.ir.erlang;

public record RecordField(String name, Expression value, SourceSpan source) {

  public static RecordField of(String name, Expression value) {
    return new RecordField(name, value, null);
  }

  public static RecordField of(String name, Expression value, SourceSpan source) {
    return new RecordField(name, value, source);
  }
}
