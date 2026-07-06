package io.beam.ir.elixir;

public record DefstructField(String nameOrNil, SourceSpan source) implements Node {

  public static DefstructField field(String name) {
    return new DefstructField(name, null);
  }

  public static DefstructField nilField() {
    return new DefstructField(null, null);
  }

  @Override
  public SourceSpan source() {
    return source;
  }
}
