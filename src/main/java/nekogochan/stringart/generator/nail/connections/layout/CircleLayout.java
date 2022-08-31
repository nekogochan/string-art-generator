package nekogochan.stringart.generator.nail.connections.layout;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.point.PolarPoint;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

public class CircleLayout implements Layout {

  private Circle base;
  private int count;
  private double nailsRadius;

  public CircleLayout(Circle base, int count, double nailsRadius) {
    this.base = base;
    this.count = count;
    this.nailsRadius = nailsRadius;
  }

  @Override
  public List<Nail> createNails() {
    var nails = new ArrayList<Nail>(count);
    for (int i = 0; i < count; i++) {
      double factor = ((double) i) / ((double) count);
      var nailCenter = new PolarPoint(base.r, factor * PI * 2.0).toCartesian();
      nailCenter.add(base.center);
      nails.add(new Nail(i, nailCenter, nailsRadius));
    }
    return nails;
  }
}
