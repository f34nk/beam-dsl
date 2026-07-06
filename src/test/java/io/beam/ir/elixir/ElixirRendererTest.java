package io.beam.ir.elixir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ElixirRendererTest {

  @Test
  void printWidthMatchesMixFormat() {
    assertEquals(98, DefaultElixirRenderer.printWidthForTests());
  }
}
