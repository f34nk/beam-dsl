package io.beam.ir.elixir;

public record DefstructField(String nameOrNil, Expression defaultOrNull, SourceSpan source)
    implements Node {

  public static DefstructField field(String name) {
    return new DefstructField(name, null, null);
  }

  public static DefstructField field(String name, Expression defaultValue) {
    return new DefstructField(name, defaultValue, null);
  }

  public static DefstructField nilField() {
    return new DefstructField(null, null, null);
  }

  @Override
  public SourceSpan source() {
    return source;
  }
}
