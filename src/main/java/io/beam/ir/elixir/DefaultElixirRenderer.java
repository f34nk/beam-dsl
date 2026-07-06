package io.beam.ir.elixir;

final class DefaultElixirRenderer implements Renderer {

  private static final String INDENT = "  ";
  private static final int PRINT_WIDTH = 98;

  private int compactLength(java.util.function.Consumer<StringBuilder> renderFn) {
    StringBuilder scratch = new StringBuilder();
    renderFn.accept(scratch);
    return scratch.length();
  }

  private boolean exceedsPrintWidth(java.util.function.Consumer<StringBuilder> renderFn) {
    return compactLength(renderFn) >= PRINT_WIDTH;
  }

  private boolean exceedsPrintWidthWithLinePrefix(
      String linePrefix, java.util.function.Consumer<StringBuilder> renderFn) {
    return linePrefix.length() + compactLength(renderFn) >= PRINT_WIDTH;
  }

  static int printWidthForTests() {
    return PRINT_WIDTH;
  }

  @Override
  public String render(Module module) {
    throw new UnsupportedOperationException("Module rendering not implemented");
  }

  @Override
  public String render(TypesModule typesModule) {
    throw new UnsupportedOperationException("TypesModule rendering not implemented");
  }

  @Override
  public String renderFunction(Function function) {
    throw new UnsupportedOperationException("Function rendering not implemented");
  }

  @Override
  public String renderExpression(Expression expression) {
    throw new UnsupportedOperationException("Expression rendering not implemented");
  }

  @Override
  public String renderPattern(Pattern pattern) {
    throw new UnsupportedOperationException("Pattern rendering not implemented");
  }
}
