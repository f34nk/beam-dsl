package io.beam.ir.erlang;

public record BinarySegmentPattern(
    Pattern pattern, String literal, Integer size, String type, Integer unit, SourceSpan source) {

  public static BinarySegmentPattern literal(String literal) {
    return new BinarySegmentPattern(null, literal, null, null, null, null);
  }

  public static BinarySegmentPattern of(Pattern pattern) {
    return new BinarySegmentPattern(pattern, null, null, null, null, null);
  }

  public static BinarySegmentPattern of(Pattern pattern, String type) {
    return new BinarySegmentPattern(pattern, null, null, type, null, null);
  }

  public static BinarySegmentPattern of(Pattern pattern, Integer size, String type) {
    return new BinarySegmentPattern(pattern, null, size, type, null, null);
  }

  public static BinarySegmentPattern of(Pattern pattern, Integer size, String type, Integer unit) {
    return new BinarySegmentPattern(pattern, null, size, type, unit, null);
  }
}
