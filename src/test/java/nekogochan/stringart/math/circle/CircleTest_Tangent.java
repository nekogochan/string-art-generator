package nekogochan.stringart.math.circle;

import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.TestFrames;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static java.awt.Color.*;
import static java.lang.Math.min;

class CircleTest_Tangent {

  @Test
  @Disabled
  void randomTests() {
    TestFrames.withRedrawOnClick((frame, gx) -> {
      var successDraw = false;
      while (!successDraw) {
        var sides = Circle.TangentSides.values()[(int) (Math.random() * 4.0)];
        var a = randCircle();
        var b = randCircle();

        if (a.center.distanceTo(b.center) < (a.r + b.r)) {
          continue;
        }

        gx.clear();
        gx.draw(a, RED);
        gx.draw(b, BLUE);
        gx.draw(a.tangent(b, sides), GREEN);
        gx.drawString(sides.name(), 10, 490);
        frame.repaint();

        successDraw = true;
      }
    });
  }

  Circle randCircle() {
    var rnd = new Random();
    var x = rnd.nextDouble(400.0) + 50.0;
    var y = rnd.nextDouble(400.0) + 50.0;
    var r = rnd.nextDouble(25.0,
                           min(
                             min(x, 500.0 - x),
                             min(y, 500.0 - y)
                           ));
    return new Circle(new Point(x, y), r);
  }
}