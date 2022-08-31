package nekogochan.stringart.math.point;

import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

  @Test
  void toPolar() {
    var cartesian = new Point(sqrt(3.0), 1.0);

    var polar = cartesian.toPolar();

    assertEquals(2.0, polar.r, 0.001);
    assertEquals(PI / 6.0, polar.a, 0.001);
  }

  @Test
  void toInt() {
    var p = new Point(0.2, 0.8);

    var ip = p.toInt();

    assertEquals(0, ip.x);
    assertEquals(1, ip.y);

    p.x = 0.5;

    ip = p.toInt();

    assertEquals(1, ip.x);
  }
}