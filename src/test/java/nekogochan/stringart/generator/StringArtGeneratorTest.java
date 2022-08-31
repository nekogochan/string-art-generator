package nekogochan.stringart.generator;

import nekogochan.stringart.generator.nail.connections.connections.CircleConnections;
import nekogochan.stringart.generator.nail.connections.layout.CircleLayout;
import nekogochan.stringart.generator.processor.Processor;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.linesegment.LineSegment;
import nekogochan.stringart.math.point.Point;
import nekogochan.stringart.util.TestFrames;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

class StringArtGeneratorTest {

  @Test
  @Disabled
  void simpleTest() {
    var generator = new StringArtGenerator(
      new Processor(1.0, 1.0, 0.1),
      new CircleConnections(0.0).connect(
        new CircleLayout(
          new Circle(
            new Point(500.0, 500.0),
            475.0
          ),
          200,
          3.0
        )
      )
    );
    var data = cross();
    generator.setData(data);

    var segments = new ArrayList<LineSegment>();

    TestFrames.withCloseOnClick(1000, 1000, (frame, gx) -> {
      gx.fill(WHITE);
      var img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < data.length; x++) {
        for (int y = 0; y < data[x].length; y++) {
          if (data[x][y] > 0.5) {
            img.setRGB(x, y, 0xffffff);
          }
        }
      }
      gx.drawImage(img, 0, 0, null);
    });

    TestFrames.withRedrawOnClick(1000, 1000, (frame, gx) -> {
      for (int i = 0; i < 250; i++) {
        segments.add(generator.proceed());
      }
      gx.fill(WHITE);
      gx.setStroke(1.0f);
      for (var n : generator.getNails()) {
        gx.draw(n, BLACK);
      }
      for (var s : new ArrayList<>(segments)) {
        gx.draw(s, new Color(0.0f, 0.0f, 0.0f, 0.25f));
      }
      frame.repaint();
    });
  }

  private double[][] cross() {
    var data = new double[1000][1000];
    for (int x = 0; x < data.length; x++) {
      for (int y = 0; y < data.length; y++) {
        data[x][y] = (x > 350 && x < 650) || (y > 350 && y < 650) ? 1.0 : 0.0;
      }
    }
    return data;
  }
}