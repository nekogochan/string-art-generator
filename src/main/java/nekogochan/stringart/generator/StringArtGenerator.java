package nekogochan.stringart.generator;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.generator.nail.connections.connections.Connections;
import nekogochan.stringart.generator.processor.Processor;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.linesegment.LineSegment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nekogochan.stringart.math.circle.Circle.TangentSides.From;

public class StringArtGenerator {

  private final Processor processor;
  private final Connections connections;
  private final List<Nail> nails;
  private final Map<Nail, Integer> nailsIndexes;

  private int nextIterIdx = 0;
  private From nextIterSide = From.LEFT;
  private int lastNailIdx = -1;

  public StringArtGenerator(Processor processor, Connections connections) {
    this.processor = processor;
    this.connections = connections;

    nails = new ArrayList<>();
    connections.getUsedNails().forEach(nails::add);

    nailsIndexes = new HashMap<>();
    for (int i = 0; i < nails.size(); i++) {
      nailsIndexes.put(nails.get(i), i);
    }
  }

  public void setData(double[][] data) {
    processor.setData(data);
  }

  public double[][] getData() {
    return processor.getData();
  }

  public int getLastNailIdx() {
    if (lastNailIdx == -1) {
      throw new IllegalStateException("there is no last nail idx, call proceed()");
    }
    return lastNailIdx;
  }

  public Iterable<Nail> getNails() {
    return nails;
  }

  public LineSegment proceed() {
    var iterIdx = nextIterIdx;
    var fromSide = nextIterSide;
    var root = nails.get(iterIdx);

    Nail bestNail = null;
    double bestValue = Double.NEGATIVE_INFINITY;
    LineSegment bestSegment = null;
    Circle.TangentSides bestSides = null;

    for (var sides : Circle.TangentSides.byFromSide(fromSide)) {
      for (var nail : connections.getConnections(root, sides)) {
        var tangent = root.tangent(nail, sides);
        var value = processor.calcSegmentValue(tangent);
        if (value > bestValue) {
          bestNail = nail;
          bestValue = value;
          bestSegment = tangent;
          bestSides = sides;
        }
      }
    }

    processor.removeSegment(bestSegment);
    nextIterIdx = nailsIndexes.get(bestNail);
    nextIterSide = bestSides.to.convertToFromSide();
    lastNailIdx = iterIdx;
    return bestSegment;
  }
}
