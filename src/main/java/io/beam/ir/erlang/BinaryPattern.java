package io.beam.ir.erlang;

import java.util.List;

public record BinaryPattern(List<BinarySegmentPattern> segments, SourceSpan source)
    implements Pattern {

  public static BinaryPattern of(String value) {
    if (value.isEmpty()) {
      return new BinaryPattern(List.of(), null);
    }
    return new BinaryPattern(List.of(BinarySegmentPattern.literal(value)), null);
  }

  public static BinaryPattern of(String value, SourceSpan source) {
    if (value.isEmpty()) {
      return new BinaryPattern(List.of(), source);
    }
    return new BinaryPattern(List.of(BinarySegmentPattern.literal(value)), source);
  }

  public static BinaryPattern of(List<BinarySegmentPattern> segments) {
    return new BinaryPattern(segments, null);
  }

  public static BinaryPattern of(List<BinarySegmentPattern> segments, SourceSpan source) {
    return new BinaryPattern(segments, source);
  }
}
