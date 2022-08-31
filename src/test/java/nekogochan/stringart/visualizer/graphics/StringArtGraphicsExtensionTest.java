package nekogochan.stringart.visualizer.graphics;

import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.util.TestFrames;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

class StringArtGraphicsExtensionTest {

  /**
   * all circles center must be posed in one point
   */
  @Test
  @Disabled
  void drawCircleByCenter() {
    TestFrames.withCloseOnClick((frame, gx) -> {
      var circle = new Circle();
      circle.center.x = circle.center.y = 250;

      gx.fill(BLACK);

      circle.r = 5;
      gx.draw(circle, GREEN);

      circle.r = 100;
      gx.draw(circle, RED);

      circle.r = 200;
      gx.draw(circle, BLUE);
    });
  }
}