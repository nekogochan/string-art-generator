package nekogochan.stringart.graphics;

import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.math.point.Point;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;

class GraphicsHelper {
  private final Graphics2D gx;
  private final int width, height;

  GraphicsHelper(Graphics2D gx, int width, int height) {
    this.gx = gx;
    this.width = width;
    this.height = height;
  }

  public void drawPoint(Point point, Color color) {
    drawWithColor(color, () -> drawPoint(point));
  }

  public void drawCircle(Circle circle, Color color) {
    drawWithColor(color, () -> drawCircle(circle));
  }

  public void drawLineSegment(LineSegment lineSegment, Color color) {
    drawWithColor(color, () -> drawLineSegment(lineSegment));
  }

  public void drawPoint(Point point) {
    drawCircle(new Circle(point, 1.0));
  }

  public void drawCircle(Circle circle) {
    var _2r = (int) circle.r * 2;
    gx.drawOval((int) (circle.center.x - circle.r),
                (int) (circle.center.y - circle.r),
                _2r, _2r);
  }

  public void drawLineSegment(LineSegment lineSegment) {
    var a = lineSegment.a;
    var b = lineSegment.b;
    gx.drawLine((int) a.x, (int) a.y,
                (int) b.x, (int) b.y);
  }

  public void clear() {
    gx.clearRect(0, 0, width, height);
  }

  public void fill(Color color) {
    drawWithColor(color, () -> gx.fillRect(0, 0, width, height));
  }

  public void setStroke(float width) {
    setStroke(width, CAP_ROUND, JOIN_ROUND);
  }

  public void setStroke(float width, int cap, int join) {
    gx.setStroke(new BasicStroke(width, cap, join));
  }

  private void drawWithColor(Color color, Runnable fn) {
    var prevColor = gx.getColor();
    gx.setColor(color);
    fn.run();
    gx.setColor(prevColor);
  }
}
