package nekogochan.stringart.math.circle;

import lombok.ToString;
import nekogochan.stringart.math.SAShape;
import nekogochan.stringart.math.SAShapeVisitor;
import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.Unchecked;

import java.util.Arrays;

import static java.lang.Math.*;

@ToString
public class Circle implements SAShape, Cloneable {
  public final Point center;
  public double r;

  public Circle(Point center, double r) {
    this.center = center;
    this.r = r;
  }

  public Circle(double r) {
    this(new Point(), r);
  }

  public Circle() {
    this(0.0);
  }

  public double distanceTo(Point point) {
    return center.distanceTo(point) - r;
  }

  @Override
  public void acceptVisitor(SAShapeVisitor visitor) {
    visitor.visit(this);
  }

  // https://math.stackexchange.com/a/4048944
  public LineSegment tangent(Circle that, TangentSides sides) {
    switch (sides) {
      case FROM_LEFT_TO_LEFT: return tangentInner(that, true);
      case FROM_LEFT_TO_RIGHT: return tangentOuter(that, true);
      case FROM_RIGHT_TO_LEFT: return tangentOuter(that, false);
      case FROM_RIGHT_TO_RIGHT: return tangentInner(that, false);
    };
    throw new IllegalStateException();
  }

  private LineSegment tangentInner(Circle that, boolean fromLeft) {
    var _1 = this;
    var _2 = that;

    if (_1.r > _2.r) {
      var tmp = _2;
      _2 = _1;
      _1 = tmp;
    }

    var c1 = _1.center;
    var c2 = _2.center;
    var r1 = _1.r;
    var r2 = _2.r;

    var H = hypot(c1.x - c2.x,
                  c1.y - c2.y);

    var O = r1 + r2;

    var phi = (PI / 2) - asin(O / H);

    if (fromLeft) {
      phi = -phi;
    }

    var dra = c1.clone()
                .subtract(c2)
                .toPolar();
    dra.a += phi;

    dra.r = r1;
    var dxy1 = dra.toCartesian();

    dra.r = r2;
    var dxy2 = dra.toCartesian();

    var p1 = c1.clone().subtract(dxy1);
    var p2 = c2.clone().add(dxy2);

    return new LineSegment(p1, p2);
  }

  private LineSegment tangentOuter(Circle that, boolean fromLeft) {
    var _1 = this;
    var _2 = that;

    if (_2.r > _1.r) {
      var tmp = _2;
      _2 = _1;
      _1 = tmp;
    }

    var c1 = _1.center;
    var c2 = _2.center;
    var r1 = _1.r;
    var r2 = _2.r;

    var H = hypot(c1.x - c2.x,
                  c1.y - c2.y);

    var A = r1 - r2;

    var phi = acos(A / H);

    if (fromLeft) {
      phi = -phi;
    }

    var dra = c1.clone()
                .subtract(c2)
                .toPolar();
    dra.a += phi;

    dra.r = r1;
    var dxy1 = dra.toCartesian();

    dra.r = r2;
    var dxy2 = dra.toCartesian();

    var p1 = c1.clone().subtract(dxy1);
    var p2 = c2.clone().subtract(dxy2);

    return new LineSegment(p1, p2);
  }

  @Override
  public Circle clone() {
    return (Circle) Unchecked.call(super::clone);
  }

  public enum TangentSides {
    FROM_LEFT_TO_LEFT(From.LEFT, To.LEFT),
    FROM_LEFT_TO_RIGHT(From.LEFT, To.RIGHT),
    FROM_RIGHT_TO_LEFT(From.RIGHT, To.LEFT),
    FROM_RIGHT_TO_RIGHT(From.RIGHT, To.RIGHT);

    private static TangentSides[] filterByFromSide(From from) {
      return Arrays.stream(values())
                   .filter(s -> from == null || from == s.from)
                   .toArray(TangentSides[]::new);
    }

    private static final TangentSides[]
      FROM_LEFT = filterByFromSide(From.LEFT),
      FROM_RIGHT = filterByFromSide(From.RIGHT);

    public static TangentSides[] byFromSide(From from) {
      switch (from) {
        case LEFT: return FROM_LEFT;
        case RIGHT: return FROM_RIGHT;
      }
      throw new IllegalStateException();
    }

    public final From from;
    public final To to;

    TangentSides(From from, To to) {
      this.from = from;
      this.to = to;
    }

    public enum From {
      LEFT, RIGHT;
    }

    public enum To {
      LEFT, RIGHT;

      public From convertToFromSide() {
        switch (this) {
          case LEFT: return From.RIGHT;
          case RIGHT: return From.LEFT;
        }
        throw new IllegalStateException();
      }
    }
  }
}
