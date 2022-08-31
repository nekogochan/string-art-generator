package nekogochan.stringart.generator.nail.connections;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.generator.nail.connections.connections.ConnectionUtils;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.TestFrames;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;

class ConnectionUtilsTest {

  @Test
  @Disabled
  void show() {
    var random = new Random();
    TestFrames.withRedrawOnClick((frame, gx) -> {
      var anyNotFree = false;
      while (!anyNotFree) {
        var nails = new ArrayList<Nail>();
        while (nails.size() < 3) {
          var p = new Point(random.nextDouble(150.0, 450.0), random.nextDouble(50.0, 450.0));
          if (nails.stream()
                   .anyMatch(n -> n.distanceTo(p) < 100.0)) {
            continue;
          }
          nails.add(new Nail(nails.size(), p, 50.0));
        }
        var root = nails.get(0);
        gx.clear();
        gx.setStroke(3.0f);
        gx.draw(root, YELLOW);
        for (int i = 0; i < nails.size(); i++) {
          Nail n = nails.get(i);
          gx.drawString(String.valueOf(n.getIdx()), (int) n.center.x, (int) n.center.y);
          if (n.equals(root)) continue;
          gx.setStroke(2.0f);
          gx.draw(n);
          for (var sides : Circle.TangentSides.values()) {
            var free = ConnectionUtils.isFree(nails, 0, i, sides, 3.0f);
            if (!free) {
              anyNotFree = true;
            }
            var tangent = root.tangent(n, sides);
            gx.setStroke(1.0f);
            gx.draw(tangent, free ? GREEN : RED);
          }
        }
      }
    });
  }
}