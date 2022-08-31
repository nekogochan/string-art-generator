package nekogochan.stringart.generator.nail.connections.connections.base;

import java.util.StringJoiner;

import static java.lang.Math.abs;
import static java.lang.System.arraycopy;

public class AdjacencyMatrix {
  private final boolean[][] edges;

  public AdjacencyMatrix(int size) {
    edges = new boolean[size][size];
  }

  public void put(int x, int y, boolean value) {
    edges[x][y] = value;
  }

  public void addEdge(int x, int y) {
    put(x, y, true);
  }

  public void removeEdge(int x, int y) {
    put(x, y, false);
  }

  public boolean hasEdge(int x, int y) {
    return edges[x][y];
  }

  public boolean[] getConnections(int x) {
    return edges[x];
  }

  public int size() {
    return edges.length;
  }

  public void fillDiagonal(boolean value) {
    for (int i = 0; i < size(); i++) {
      edges[i][i] = value;
    }
  }

  public void fillNeighbours(boolean value, int width) {
    var s = size();
    for (int i = 0; i < s; i++) {
      for (int j = 1; j <= width; j++) {
        put((i + j) % s, i, value);
        put((i - j + s) % s, i, value);
      }
    }
  }

  public void fillBefore(boolean value, int count) {
    var s = size();
    for (int i = 0; i < s; i++) {
      for (int j = 1; j <= count; j++) {
        put((i + j) % s, i, value);
      }
    }
  }

  public void fillAfter(boolean value, int count) {
    var s = size();
    for (int i = 0; i < s; i++) {
      for (int j = 1; j <= count; j++) {
        put((i - j + s) % s, i, value);
      }
    }
  }

  public void mirrorByIndexes() {
    var s = size();
    var newEdges = new boolean[s][s];
    for (int i = 0; i < s; i++) {
      for (int j = 0; j < s; j++) {
        var dm = abs(j - i);
        newEdges[i][j] = edges[i][(j + dm * 2) % s];
      }
    }
    for (int i = 0; i < size(); i++) {
      if (size() >= 0) {
        arraycopy(newEdges[i], 0, this.edges[i], 0, size());
      }
    }
  }

  public void fill(boolean value) {
    for (int i = 0; i < size(); i++) {
      for (int j = 0; j < size(); j++) {
        edges[i][j] = value;
      }
    }
  }

  @Override
  public String toString() {
    var str = new StringJoiner("\n");
    var cellSize = String.valueOf(size()).length();

    var firstRowStr = new StringJoiner(" ");
    firstRowStr.add(" ".repeat(cellSize));
    for (int i = 0; i < size(); i++) {
      var is = String.valueOf(i);
      firstRowStr.add(is + " ".repeat(cellSize - is.length()));
    }
    str.add(firstRowStr.toString());


    for (int i = 0; i < size(); i++) {
      var rowStr = new StringJoiner(" ");
      var is = String.valueOf(i);
      rowStr.add(is + " ".repeat(cellSize - is.length()));
      for (int j = 0; j < size(); j++) {
        rowStr.add((edges[i][j] ? "+" : "-") + " ".repeat(cellSize - 1));
      }
      str.add(rowStr.toString());
    }

    return str.toString();
  }
}
