package nekogochan.stringart.graphics;

import nekogochan.stringart.math.SAShapeVisitor;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.math.point.IntPoint;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.math.point.PolarPoint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class SAShapeGraphicsVisitor implements SAShapeVisitor {

  private final Graphics2D gx;

  public SAShapeGraphicsVisitor(Graphics2D gx) {this.gx = gx;}

  @Override
  public void visit(Circle circle) {
    var _2r = (int) (circle.r * 2.0);
    gx.drawOval((int) (circle.center.x - circle.r),
                (int) (circle.center.y - circle.r),
                _2r, _2r);
  }

  @Override
  public void visit(LineSegment lineSegment) {
    var a = lineSegment.a;
    var b = lineSegment.b;
    gx.drawLine((int) a.x, (int) a.y,
                (int) b.x, (int) b.y);
  }

  @Override
  public void visit(IntPoint intPoint) {
    var d = 1;
    var stroke = gx.getStroke();
    if (stroke instanceof BasicStroke) {
      d = (int) ((BasicStroke) stroke).getLineWidth();
    }
    var d_2 = d / 2;
    gx.drawOval(intPoint.x - d_2, intPoint.y - d_2, d, d);
  }

  @Override
  public void visit(Point point) {
    visit(point.toInt());
  }

  @Override
  public void visit(PolarPoint polarPoint) {
    visit(polarPoint.toCartesian().toInt());
  }
}
