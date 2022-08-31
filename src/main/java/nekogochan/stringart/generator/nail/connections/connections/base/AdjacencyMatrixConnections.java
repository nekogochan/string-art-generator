package nekogochan.stringart.generator.nail.connections.connections.base;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.generator.nail.connections.connections.Connections;
import nekogochan.stringart.math.circle.Circle;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

public class AdjacencyMatrixConnections implements Connections {

  private final List<Nail> nails;
  private final EnumMap<Circle.TangentSides, AdjacencyMatrix> matrixMap;

  public AdjacencyMatrixConnections(List<Nail> nails, Map<Circle.TangentSides, AdjacencyMatrix> matrixMap) {
    this.nails = nails;
    this.matrixMap = new EnumMap<>(matrixMap);
  }

  @Override
  public Iterable<Nail> getConnections(Nail nail, Circle.TangentSides sides) {
    var matrix = matrixMap.get(sides);
    var connections = matrix.getConnections(nail.getIdx());

    return () -> new Iterator<>() {
      int j = 0;

      @Override
      public boolean hasNext() {
        if (j >= matrix.size()) {
          return false;
        }
        for (; j < matrix.size(); j++) {
          if (connections[j]) {
            return true;
          }
        }
        return false;
      }

      @Override
      public Nail next() {
        if (hasNext()) {
          return nails.get(j++);
        } else {
          throw new NoSuchElementException();
        }
      }
    };
  }

  public MemoizedConnections memoize() {
    var memoziedMap = new EnumMap<Circle.TangentSides, List<List<Nail>>>(Circle.TangentSides.class);
    matrixMap.forEach((sides, matrix) -> {
      memoziedMap.put(sides, nails.stream().map(nail -> {
                                    var connections = new ArrayList<Nail>();
                                    getConnections(nail, sides).forEach(connections::add);
                                    connections.trimToSize();
                                    return connections;
                                  })
                                  .collect(toList()));
    });
    return new MemoizedConnections(nails, memoziedMap);
  }

  @Override
  public Iterable<Nail> getUsedNails() {
    return nails;
  }
}
