package io.beam.ir.erlang;

import java.util.List;

public record Header(
    List<String> comments,
    List<RecordDef> records,
    List<TypeAlias> typeAliases,
    List<HeaderEntry> entriesOrNull,
    boolean separateEntries,
    String verbatimOrNull) {

  public static Header of(
      List<String> comments, List<RecordDef> records, List<TypeAlias> typeAliases) {
    return new Header(comments, records, typeAliases, null, true, null);
  }

  public static Header ofEntries(List<HeaderEntry> entries) {
    return ofEntries(entries, true);
  }

  public static Header ofEntries(List<HeaderEntry> entries, boolean separateEntries) {
    return new Header(null, null, null, entries, separateEntries, null);
  }

  public static Header verbatim(String content) {
    return new Header(null, null, null, null, true, content);
  }
}
