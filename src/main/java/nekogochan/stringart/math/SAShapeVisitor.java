package nekogochan.stringart.math;

import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.math.point.IntPoint;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.math.point.PolarPoint;

public interface SAShapeVisitor {

  default void visit(SAShape saShape) {
    saShape.acceptVisitor(this);
  }

  void visit(Circle circle);

  void visit(LineSegment lineSegment);

  void visit(IntPoint intPoint);
  void visit(Point point);
  void visit(PolarPoint polarPoint);
}
