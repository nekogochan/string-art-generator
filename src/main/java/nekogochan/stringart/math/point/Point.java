package nekogochan.stringart.math.point;

import lombok.ToString;
import nekogochan.stringart.math.SAShape;
import nekogochan.stringart.math.SAShapeVisitor;
import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.util.Unchecked;

import static java.lang.Math.*;

@ToString
public class Point implements SAShape, Cloneable {
  public double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Point() {
  }

  @Override
  public Point clone() {
    return (Point) Unchecked.call(super::clone);
  }

  public PolarPoint toPolar() {
    return new PolarPoint(
      hypot(x, y),
      atan2(y, x)
    );
  }

  public IntPoint toInt() {
    return new IntPoint(
      (int) round(x),
      (int) round(y)
    );
  }

  public LineSegment segmentWith(Point that) {
    return new LineSegment(this, that);
  }

  public Point subtract(Point that) {
    x -= that.x;
    y -= that.y;
    return this;
  }

  public Point add(Point that) {
    x += that.x;
    y += that.y;
    return this;
  }

  public Point multiply(double value) {
    x *= value;
    y *= value;
    return this;
  }

  public double distanceTo(Point that) {
    return hypot(that.x - x,
                 that.y - y);
  }

  @Override
  public void acceptVisitor(SAShapeVisitor visitor) {
    visitor.visit(this);
  }
}

