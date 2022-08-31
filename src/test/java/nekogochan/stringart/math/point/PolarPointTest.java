package nekogochan.stringart.math.point;

import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PolarPointTest {

  @Test
  void toRect() {
    var polar = new PolarPoint(2.0, PI / 4.0);

    var cartesian = polar.toCartesian();

    assertEquals(sqrt(2.0), cartesian.x, 0.001);
    assertEquals(sqrt(2.0), cartesian.y, 0.001);
  }
}