package nekogochan.stringart.generator.nail.connections.connections.base;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.generator.nail.connections.connections.Connections;
import nekogochan.stringart.math.circle.Circle;

import java.util.EnumMap;
import java.util.List;

public class MemoizedConnections implements Connections {

  private final List<Nail> nails;
  private final EnumMap<Circle.TangentSides, List<List<Nail>>> connections;

  public MemoizedConnections(List<Nail> nails, EnumMap<Circle.TangentSides, List<List<Nail>>> connections) {
    this.nails = nails;
    this.connections = connections;
  }

  @Override
  public Iterable<Nail> getConnections(Nail nail, Circle.TangentSides sides) {
    return connections.get(sides).get(nail.getIdx());
  }

  @Override
  public Iterable<Nail> getUsedNails() {
    return nails;
  }
}
