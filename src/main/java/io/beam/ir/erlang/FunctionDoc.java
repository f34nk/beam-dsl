package io.beam.ir.erlang;

public sealed interface FunctionDoc permits Doc, Edoc {
  String text();
}
