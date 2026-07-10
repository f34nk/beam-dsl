package io.beam.ir.elixir;

public record Variable(String name) implements Expression {

  public static Variable of(String name) {
    return new Variable(name);
  }
}
