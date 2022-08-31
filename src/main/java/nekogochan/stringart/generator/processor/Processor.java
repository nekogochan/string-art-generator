package nekogochan.stringart.generator.processor;

import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.math.point.Point;

public class Processor {
  private final double weightCalcStep;
  private final double removeStep;
  private final double removeValue;
  private double[][] data;

  public Processor(double weightCalcStep, double removeStep, double removeValue) {
    this.weightCalcStep = weightCalcStep;
    this.removeStep = removeStep;
    this.removeValue = removeValue;
  }

  public double[][] getData() {
    return data;
  }

  public void setData(double[][] data) {
    this.data = data;
  }

  public void removeSegment(LineSegment segment) {
    segment.asPointStream(removeStep)
           .map(Point::toInt)
           .forEach(p -> data[p.x][p.y] -= removeValue);
  }

  public double calcSegmentValue(LineSegment segment) {
    return segment.asPointStream(weightCalcStep)
                  .map(Point::toInt)
                  .mapToDouble(p -> data[p.x][p.y])
                  .average()
                  .orElse(0.0);
  }
}
