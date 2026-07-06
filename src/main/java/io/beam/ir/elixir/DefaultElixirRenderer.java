package io.beam.ir.elixir;

import java.util.List;

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

  private void render(Expression expression, StringBuilder out, String indent) {
    if (expression instanceof AtomExpr atom) {
      out.append(':').append(atom.value());
    } else if (expression instanceof IntegerExpr integer) {
      out.append(integer.value());
    } else if (expression instanceof Variable variable) {
      out.append(variable.name());
    } else if (expression instanceof StringExpr string) {
      out.append('"').append(escapeString(string.value())).append('"');
    } else if (expression instanceof NilExpr) {
      out.append("nil");
    } else if (expression instanceof BooleanExpr bool) {
      out.append(bool.value());
    } else if (expression instanceof OpaqueExpr opaque) {
      out.append(opaque.text());
    } else if (expression instanceof TupleExpr tuple) {
      render(tuple, out, indent);
    } else if (expression instanceof ListExpr list) {
      render(list, out, indent);
    } else if (expression instanceof MapExpr map) {
      render(map, out, indent);
    } else {
      throw new IllegalArgumentException("Unsupported expression: " + expression);
    }
  }

  private void render(TupleExpr tuple, StringBuilder out, String indent) {
    if (!collectionExceedsPrintWidth(tuple.elements(), '{', '}')) {
      out.append('{');
      renderCommaSeparated(tuple.elements(), out, indent);
      out.append('}');
      return;
    }
    renderCollectionVertical(tuple.elements(), out, indent, '{', '}');
  }

  private void render(ListExpr list, StringBuilder out, String indent) {
    if (!collectionExceedsPrintWidth(list.elements(), '[', ']')) {
      out.append('[');
      renderCommaSeparated(list.elements(), out, indent);
      out.append(']');
      return;
    }
    renderCollectionVertical(list.elements(), out, indent, '[', ']');
  }

  private void render(MapExpr map, StringBuilder out, String indent) {
    if (map.baseOrNull() != null) {
      render(map.baseOrNull(), out, indent);
      out.append(' ');
    }
    out.append('%');
    if (!mapExceedsPrintWidth(map)) {
      out.append('{');
      renderMapEntries(map.entries(), out, indent);
      out.append('}');
      return;
    }
    out.append("{\n");
    String entryIndent = indent + INDENT;
    for (int i = 0; i < map.entries().size(); i++) {
      if (i > 0) {
        out.append(",\n");
      }
      out.append(entryIndent);
      renderMapEntry(map.entries().get(i), out, entryIndent);
    }
    out.append('\n').append(indent).append('}');
  }

  private void renderMapEntries(List<MapEntry> entries, StringBuilder out, String indent) {
    for (int i = 0; i < entries.size(); i++) {
      if (i > 0) {
        out.append(", ");
      }
      renderMapEntry(entries.get(i), out, indent);
    }
  }

  private void renderMapEntry(MapEntry entry, StringBuilder out, String indent) {
    if (!entry.arrowSyntax() && entry.key() instanceof AtomExpr atom) {
      out.append(atom.value()).append(": ");
    } else {
      render(entry.key(), out, indent);
      if (entry.arrowSyntax()) {
        out.append(" => ");
      } else {
        out.append(": ");
      }
    }
    render(entry.value(), out, indent);
  }

  private void renderCommaSeparated(List<Expression> elements, StringBuilder out, String indent) {
    for (int i = 0; i < elements.size(); i++) {
      if (i > 0) {
        out.append(", ");
      }
      render(elements.get(i), out, indent);
    }
  }

  private void renderCollectionVertical(
      List<Expression> elements, StringBuilder out, String indent, char open, char close) {
    out.append(open).append('\n');
    String elementIndent = indent + INDENT;
    for (int i = 0; i < elements.size(); i++) {
      if (i > 0) {
        out.append(",\n");
      }
      out.append(elementIndent);
      render(elements.get(i), out, elementIndent);
    }
    out.append('\n').append(indent).append(close);
  }

  private boolean collectionExceedsPrintWidth(
      List<Expression> elements, char open, char close) {
    return exceedsPrintWidth(
        scratch -> {
          scratch.append(open);
          renderCommaSeparated(elements, scratch, "");
          scratch.append(close);
        });
  }

  private boolean mapExceedsPrintWidth(MapExpr map) {
    return exceedsPrintWidth(
        scratch -> {
          if (map.baseOrNull() != null) {
            render(map.baseOrNull(), scratch, "");
            scratch.append(' ');
          }
          scratch.append('%');
          scratch.append('{');
          renderMapEntries(map.entries(), scratch, "");
          scratch.append('}');
        });
  }

  private static String escapeString(String value) {
    return value.replace("\\", "\\\\").replace("\"", "\\\"");
  }

  @Override
  public String renderExpression(Expression expression) {
    StringBuilder out = new StringBuilder();
    render(expression, out, "");
    return out.toString();
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
  public String renderPattern(Pattern pattern) {
    throw new UnsupportedOperationException("Pattern rendering not implemented");
  }
}
