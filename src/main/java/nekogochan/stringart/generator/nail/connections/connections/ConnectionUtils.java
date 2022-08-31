package nekogochan.stringart.generator.nail.connections.connections;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.generator.nail.connections.connections.base.AdjacencyMatrix;
import nekogochan.stringart.math.circle.Circle;

import java.util.List;

import static java.lang.Math.abs;

public class ConnectionUtils {

  static public boolean isFree(List<Nail> nails, int fromIdx, int toIdx, Circle.TangentSides sides, double distanceToNails) {
    return isFree(nails, fromIdx, toIdx, sides, distanceToNails, 0, 0);
  }

  /**
   * check if all point in tangent between 'a' and 'b' are at a distance to all nails (expect 'a', 'b', and neighbours of 'a')
   */
  static public boolean isFree(List<Nail> nails, int fromIdx, int toIdx, Circle.TangentSides sides,
                               double distanceToNails, int neighboursFalseCount, int neighboursSkipCheckCount) {
    if (abs(toIdx - fromIdx) <= neighboursFalseCount) {
      return false;
    }
    var t = nails.get(fromIdx).tangent(nails.get(toIdx), sides);
    for (int i = 0; i < nails.size(); i++) {
      if (i == fromIdx || i == toIdx) {
        continue;
      }
      var n = nails.get(i);
      if (abs(fromIdx - i) <= neighboursSkipCheckCount) {
        // mean go 'thought'
        if (t.distanceTo(n) < 0.0) {
          return false;
        }
      } else {
        if (t.distanceTo(n) < distanceToNails) {
          return false;
        }
      }
    }
    return true;
  }

  static public void bindAllFree(AdjacencyMatrix matrix, List<Nail> nails, Circle.TangentSides sides,
                                 double distanceToNails, int neighboursFalseCount, int neighboursRangeCheckSkipCount) {
    matrix.fill(false);
    for (int i = 0; i < nails.size(); i++) {
      for (int j = 0; j < nails.size(); j++) {
        if (ConnectionUtils.isFree(nails, i, j, sides, distanceToNails, neighboursFalseCount, neighboursRangeCheckSkipCount)) {
          matrix.addEdge(i, j);
        }
      }
    }
    matrix.fillNeighbours(false, neighboursFalseCount);
  }
}
