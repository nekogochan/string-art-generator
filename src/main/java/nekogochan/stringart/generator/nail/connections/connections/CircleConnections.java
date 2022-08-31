package nekogochan.stringart.generator.nail.connections.connections;

import nekogochan.stringart.generator.nail.connections.connections.base.AdjacencyMatrix;
import nekogochan.stringart.generator.nail.connections.connections.base.AdjacencyMatrixConnections;
import nekogochan.stringart.generator.nail.connections.layout.CircleLayout;
import nekogochan.stringart.math.circle.Circle;

import java.util.HashMap;
import java.util.Map;

public class CircleConnections {

  private final double distanceToNails;

  public CircleConnections(double distanceToNails) {
    this.distanceToNails = distanceToNails;
  }

  public AdjacencyMatrixConnections connect(CircleLayout layout) {
    var nails = layout.createNails();
    Map<Circle.TangentSides, AdjacencyMatrix> map = new HashMap<>();
    for (var sides : Circle.TangentSides.values()) {
      var matrix = new AdjacencyMatrix(nails.size());
      ConnectionUtils.bindAllFree(matrix, nails, sides, distanceToNails, 1, 0);
      map.put(sides, matrix);
    }
    return new AdjacencyMatrixConnections(nails, map);
  }
}
