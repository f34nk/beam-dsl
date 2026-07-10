package io.beam.ir.elixir;

import java.util.List;

public record InterpolatedStringExpr(List<InterpolatedSegment> segments)
    implements Expression {}
