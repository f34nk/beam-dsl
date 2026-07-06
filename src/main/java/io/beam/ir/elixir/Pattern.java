package io.beam.ir.elixir;

public sealed interface Pattern extends Node permits StructPattern, VariablePattern {}
