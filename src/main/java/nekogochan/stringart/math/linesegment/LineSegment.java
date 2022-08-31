package nekogochan.stringart.math.linesegment;

import lombok.ToString;
import nekogochan.stringart.math.SAShape;
import nekogochan.stringart.math.SAShapeVisitor;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.Unchecked;

import java.util.Iterator;
import java.util.stream.Stream;

import static java.lang.Math.hypot;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;

@ToString
public class LineSegment implements SAShape, Cloneable {
  public final Point a, b;

  public LineSegment(Point a, Point b) {
    this.a = a;
    this.b = b;
  }

  public LineSegment() {
    this.a = new Point();
    this.b = new Point();
  }

  private double sqr(double d) {
    return pow(d, 2.0);
  }

  private double dist2(Point a, Point b) {
    return sqr(a.x - b.x) + sqr(a.y - b.y);
  }

  private double distSquared(Point c) {
    var l2 = dist2(a, b);
    if (l2 == 0) return dist2(c, a);
    var t = (
      (c.x - a.x) * (b.x - a.x)
        + (a.y - c.y) * (b.y - a.y)
    ) / l2;
    t = max(0, min(1, t));
    return dist2(c, new Point(a.x + t * (b.x - a.x),
                              a.y + t * (b.y - a.y)));
  }

  public double distanceTo(Point p) {
    return pDistance(p.x, p.y,
                     a.x, a.y,
                     b.x, b.y);
  }

  public double distanceTo(Circle c) {
    return distanceTo(c.center) - c.r;
  }

  private static double pDistance(double x, double y,
                                  double x1, double y1,
                                  double x2, double y2) {
    var A = x - x1;
    var B = y - y1;
    var C = x2 - x1;
    var D = y2 - y1;

    var dot = A * C + B * D;
    var len_sq = C * C + D * D;
    var param = -1.0;
    if (len_sq != 0) {
      param = dot / len_sq;
    }

    double xx, yy;

    if (param < 0.0) {
      xx = x1;
      yy = y1;
    } else if (param > 1.0) {
      xx = x2;
      yy = y2;
    } else {
      xx = x1 + param * C;
      yy = y1 + param * D;
    }

    var dx = x - xx;
    var dy = y - yy;
    return hypot(dx, dy);
  }

  public Stream<Point> asPointStream(double step) {
    var len = a.distanceTo(b);
    var stepsCount = (int) (len / step) + 1;
    var dx = (b.x - a.x) / stepsCount;
    var dy = (b.y - a.y) / stepsCount;
    var p = a.clone();
    var iter = new Iterator<Point>() {
      int step = 0;

      @Override
      public boolean hasNext() {
        return step <= stepsCount;
      }

      @Override
      public Point next() {
        step++;
        p.x += dx;
        p.y += dy;
        return p;
      }
    };
    return Stream.iterate(p, __ -> iter.hasNext(), __ -> iter.next());
  }

  @Override
  public LineSegment clone() {
    return (LineSegment) Unchecked.call(super::clone);
  }

  @Override
  public void acceptVisitor(SAShapeVisitor visitor) {
    visitor.visit(this);
  }
}
