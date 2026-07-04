package io.beam.ir.erlang;

import java.util.List;

public record Header(List<String> comments, List<RecordDef> records, List<TypeAlias> typeAliases) {

  public static Header of(
      List<String> comments, List<RecordDef> records, List<TypeAlias> typeAliases) {
    return new Header(comments, records, typeAliases);
  }
}
