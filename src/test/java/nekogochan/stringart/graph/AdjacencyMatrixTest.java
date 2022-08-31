package nekogochan.stringart.graph;

import nekogochan.stringart.generator.nail.connections.connections.base.AdjacencyMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixTest {

  @Test
  void fillDiagonal() {
    var m = new AdjacencyMatrix(5);
    m.fillDiagonal(true);

    assertEquals(
      "  0 1 2 3 4\n" +
      "0 + - - - -\n" +
      "1 - + - - -\n" +
      "2 - - + - -\n" +
      "3 - - - + -\n" +
      "4 - - - - +\n",
      m.toString()
    );
  }

  @Test
  void fillNeighbours() {
    var m = new AdjacencyMatrix(5);
    m.fillNeighbours(true, 1);

    assertEquals(
      "  0 1 2 3 4\n" +
      "0 - + - - +\n" +
      "1 + - + - -\n" +
      "2 - + - + -\n" +
      "3 - - + - +\n" +
      "4 + - - + -\n",
      m.toString()
    );
  }

  @Test
  void verticalMirror() {
    var m = new AdjacencyMatrix(4);
    m.fillDiagonal(true);
    m.addEdge(0, 3);
    m.addEdge(2, 1);

    assertEquals(
      "  0 1 2 3\n" +
      "0 + - - +\n" +
      "1 - + - -\n" +
      "2 - + + -\n" +
      "3 - - - +\n",
      m.toString()
    );

    m.mirrorByIndexes();

    assertEquals(
      "  0 1 2 3\n" +
      "0 + + - -\n" +
      "1 - + - -\n" +
      "2 - - + +\n" +
      "3 - - - +\n",
      m.toString()
    );
  }

  @Test
  void fillBefore() {
    var m = new AdjacencyMatrix(5);
    m.fillBefore(true, 1);

    assertEquals(
      "  0 1 2 3 4\n" +
      "0 - - - - +\n" +
      "1 + - - - -\n" +
      "2 - + - - -\n" +
      "3 - - + - -\n" +
      "4 - - - + -",
      m.toString()
    );
  }

  @Test
  void fillAfter() {
    var m = new AdjacencyMatrix(5);
    m.fillAfter(true, 1);

    assertEquals(
      "  0 1 2 3 4\n" +
      "0 - + - - -\n" +
      "1 - - + - -\n" +
      "2 - - - + -\n" +
      "3 - - - - +\n" +
      "4 + - - - -\n",
      m.toString()
    );
  }
}