package io.beam.ir.erlang;

import java.util.List;

public record RecordPattern(
    String name, String alias, List<RecordPatternField> fields, SourceSpan source)
    implements Pattern {

  public static RecordPattern of(String name, List<RecordPatternField> fields) {
    return new RecordPattern(name, null, fields, null);
  }

  public static RecordPattern of(String name, List<RecordPatternField> fields, SourceSpan source) {
    return new RecordPattern(name, null, fields, source);
  }

  public static RecordPattern bind(String alias, String name, List<RecordPatternField> fields) {
    return new RecordPattern(name, alias, fields, null);
  }
}
