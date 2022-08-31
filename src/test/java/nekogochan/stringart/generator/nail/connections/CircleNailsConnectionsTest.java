package nekogochan.stringart.generator.nail.connections;

import nekogochan.stringart.generator.nail.Nail;
import nekogochan.stringart.generator.nail.connections.connections.CircleConnections;
import nekogochan.stringart.generator.nail.connections.layout.CircleLayout;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.TestFrames;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static nekogochan.stringart.math.circle.Circle.TangentSides.FROM_LEFT_TO_LEFT;
import static nekogochan.stringart.math.circle.Circle.TangentSides.FROM_LEFT_TO_RIGHT;
import static nekogochan.stringart.math.circle.Circle.TangentSides.FROM_RIGHT_TO_LEFT;
import static nekogochan.stringart.math.circle.Circle.TangentSides.FROM_RIGHT_TO_RIGHT;

class CircleNailsConnectionsTest {

  @Test
  @Disabled
  void layoutTest() {
    var base = new Circle(
      new Point(250, 250),
      200
    );
    var layout = new CircleLayout(
      base,
      10,
      7.5
    );
    var nails = layout.createNails();
    TestFrames.withCloseOnClick((frame, gx) -> {
      gx.setStroke(1.0f);
      gx.draw(base, RED);
      gx.setStroke(1.5f);
      nails.forEach(gx::draw);
    });
  }

  @Test
  @Disabled
  void connectionsTest() {
    var nr = 12.0;
    var nd = 12.0;
    var layout = new CircleLayout(
      new Circle(
        new Point(500.0, 500.0),
        490.0
      ),
      50,
      nr
    );
    var connections = new CircleConnections(nd).connect(layout).memoize();
    var nails = new ArrayList<Nail>();
    connections.getUsedNails().forEach(nails::add);
    var activeEnumCode = new Object() {
      int $ = -1;

      int next() {
        $++;
        if ($ == 4) $ = -1;
        return $;
      }
    };
    var colorMap = Map.of(
      FROM_LEFT_TO_LEFT, new Color(0, 255, 0),
      FROM_RIGHT_TO_LEFT, new Color(200, 100, 0),
      FROM_LEFT_TO_RIGHT, new Color(75, 75, 200),
      FROM_RIGHT_TO_RIGHT, new Color(0, 150, 155)
    );
    var i = new Object() {
      int $ = 0;
    };
    TestFrames.withRedrawOnClick(1000, 1000, (frame, gx) -> {
      gx.setStroke(1.0f);
      for (var n : nails) {
        gx.draw(n);
        gx.draw(new Circle(n.center, nr + nd), RED);
      }
      var root = nails.get((i.$++) % nails.size());
      gx.setStroke(3.0f);
      gx.draw(root);
      gx.setStroke(1.0f);
      Consumer<Circle.TangentSides> drawSides = (sides) -> {
        var color = colorMap.get(sides);
        var connectionsForRoot = connections.getConnections(root, sides);
        connectionsForRoot.forEach(conn -> {
          var tangent = root.tangent(conn, sides);
          gx.draw(tangent, color);
        });
      };
      if (activeEnumCode.next() == -1) {
        colorMap.keySet().forEach(drawSides);
      } else {
        drawSides.accept(Circle.TangentSides.values()[activeEnumCode.$]);
      }
    });
  }
}