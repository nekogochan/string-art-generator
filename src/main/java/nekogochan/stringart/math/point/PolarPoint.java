package nekogochan.stringart.math.point;

import lombok.ToString;
import nekogochan.stringart.math.SAShape;
import nekogochan.stringart.math.SAShapeVisitor;
import nekogochan.stringart.util.Unchecked;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@ToString
public class PolarPoint implements SAShape, Cloneable {
  public double r, a;

  public PolarPoint(double r, double a) {
    this.r = r;
    this.a = a;
  }

  public PolarPoint() {
  }

  public Point toCartesian() {
    return new Point(
      cos(a) * r,
      sin(a) * r
    );
  }

  @Override
  public PolarPoint clone() {
    return (PolarPoint) Unchecked.call(super::clone);
  }

  @Override
  public void acceptVisitor(SAShapeVisitor visitor) {
    visitor.visit(this);
  }
}
