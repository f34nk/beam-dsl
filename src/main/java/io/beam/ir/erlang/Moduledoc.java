package io.beam.ir.erlang;

public record Moduledoc(String text) {

  public static Moduledoc of(String text) {
    return new Moduledoc(text);
  }
}
