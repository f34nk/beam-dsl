package io.beam.ir.erlang;

import java.util.List;

public record Function(
    String name, List<FunctionClause> clauses, Spec spec, FunctionDoc doc, SourceSpan source)
    implements Node {

  public static Function of(String name, List<FunctionClause> clauses) {
    return new Function(name, clauses, null, null, null);
  }

  public static Function of(
      String name, List<FunctionClause> clauses, Spec spec, FunctionDoc doc, SourceSpan source) {
    return new Function(name, clauses, spec, doc, source);
  }

  public int arity() {
    if (clauses.isEmpty()) {
      return 0;
    }
    return clauses.get(0).patterns().size();
  }
}
