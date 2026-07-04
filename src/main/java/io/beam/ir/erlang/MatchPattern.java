package io.beam.ir.erlang;

public record MatchPattern(Pattern left, Pattern right, SourceSpan source) implements Pattern {

  public static MatchPattern of(Pattern left, Pattern right) {
    return new MatchPattern(left, right, null);
  }

  public static MatchPattern of(Pattern left, Pattern right, SourceSpan source) {
    return new MatchPattern(left, right, source);
  }
}
