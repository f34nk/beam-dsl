package io.beam.ir.elixir;

import java.util.List;

public record BinaryExpr(List<BinarySegmentExpr> segments) implements Expression {}
