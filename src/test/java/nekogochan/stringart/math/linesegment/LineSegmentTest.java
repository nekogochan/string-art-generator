package nekogochan.stringart.math.linesegment;

import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.TestFrames;
import nekogochan.stringart.util.Unchecked;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.MAGENTA;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.lang.Math.hypot;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LineSegmentTest_AsIntPointStream {

  static Random rand = new Random();

  static double randCoordValue() {
    return rand.nextDouble(25.0, 475.0);
  }

  static Point randPoint() {
    return new Point(randCoordValue(), randCoordValue());
  }

  @Test
  @Disabled
  void distanceTo() {
    TestFrames.withRedrawOnClick((frame, gx) -> {
      var p1 = randPoint();
      var p2 = randPoint();
      var p3 = randPoint();
      var s = p1.segmentWith(p2);
      var d = s.distanceTo(p3);

      gx.fill(BLACK);
      gx.setColor(WHITE);
      gx.setStroke(5.0f);
      gx.draw(p1);
      gx.drawString("1", (int) p1.x + 20, (int) p1.y);
      gx.draw(p2);
      gx.drawString("2", (int) p2.x + 20, (int) p2.y);
      gx.draw(p3);
      gx.drawString("3", (int) p3.x + 20, (int) p3.y);

      gx.setStroke(1.0f);
      gx.draw(s);

      gx.drawString("distance: " + d, 25, 25);
    });
  }

  @Test
  void distanceTo_inStraight() {
    var p1 = new Point(0.0, 0.0);
    var p2 = new Point(0.0, 100.0);
    var p3 = new Point(0.0, 200.0);

    var s = p1.segmentWith(p2);
    var d = s.distanceTo(p3);

    assertEquals(100.0, d, 0.001);
  }

  @Test
  void distanceTo_perpendicular() {
    var p1 = new Point(0.0, 0.0);
    var p2 = new Point(0.0, 200.0);
    var p3 = new Point(100.0, 0.0);

    var s = p1.segmentWith(p2);
    var d = s.distanceTo(p3);

    assertEquals(100.0, d, 0.001);
  }

  @Test
  void distanceTo_endOfSegment() {
    var p1 = new Point(0.0, 0.0);
    var p2 = new Point(0.0, 100.0);
    var p3 = new Point(100.0, 200.0);

    var s = p1.segmentWith(p2);
    var d = s.distanceTo(p3);

    assertEquals(hypot(100.0, 100.0), d, 0.001);
  }

  @Test
  @Disabled
  void visualTests() {
    var points = new ArrayList<Point>();

    while (points.size() < 4) {
      var newPoint = randPoint();
      if (points.stream().noneMatch(p -> p.distanceTo(newPoint) < 25.0)) {
        points.add(newPoint);
      }
    }

    var colors = new Color[]{
      RED,
      GREEN,
      BLUE,
      MAGENTA
    };


    var threads = new ArrayList<Thread>();

    for (int $i = 0; $i < 4; $i++) {
      final var i = $i;
      threads.add(
        new Thread(() -> {
          TestFrames.withCloseOnClick((fr, gx) -> {
            gx.fill(BLACK);
            for (int $j = 0; $j < 4; $j++) {
              final var j = $j;
              if (i == j) continue;
              var a = points.get(i).clone();
              var b = points.get(j).clone();
              gx.setStroke(3.0f);
              gx.draw(new Circle(a, 10.0), colors[i]);
              gx.draw(new Circle(b, 10.0), colors[j]);
              gx.setStroke(1.0f);
              a.segmentWith(b)
               .asPointStream(20.0)
               .forEach(p -> {
                 gx.draw(new Circle(p.clone(), 2.0), WHITE);
                 fr.repaint();
               });
            }
          });
        })
      );
    }
    threads.forEach(Thread::start);
    threads.forEach(Unchecked.accept(Thread::join));
  }
}