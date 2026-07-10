package io.beam.ir.elixir;

public record FunctionArityGuard(String variable, int arity) implements Guard {

  public static FunctionArityGuard of(String variable, int arity) {
    return new FunctionArityGuard(variable, arity);
  }
}
