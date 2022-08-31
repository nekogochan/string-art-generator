package nekogochan.stringart.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nekogochan.stringart.graphics.StringArtGraphicsExtension;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Font.BOLD;
import static java.awt.Font.MONOSPACED;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static nekogochan.stringart.visualizer.util.ImageUtil.showImage;

public class TestFrames {

  @AllArgsConstructor
  @Getter
  public static final class TestFrame {
    private final Frame frame;
    private final StringArtGraphicsExtension gx;
  }

  public static TestFrame withCloseOnClick(int width, int height, BiConsumer<Frame, StringArtGraphicsExtension> drawFn) {
    var image = new BufferedImage(width, height, TYPE_INT_RGB);
    var gx = new StringArtGraphicsExtension(image);
    gx.setStroke(3.0f);
    gx.setBackground(BLACK);
    gx.setColor(WHITE);
    gx.setFont(new Font(MONOSPACED, BOLD, 25));
    gx.clear();
    var frame = showImage(image);

    var frameClosed = new Object() {
      boolean $ = false;
    };

    frame.addMouseListener(
      new MouseClickListener(
        ev -> {
          frame.dispose();
          frameClosed.$ = true;
        }));

    frame.addWindowListener(
      new WindowCloseListener(
        ev -> frameClosed.$ = true));

    drawFn.accept(frame, gx);
    frame.repaint();
    while (!frameClosed.$) {
      Unchecked.run(() -> Thread.sleep(10));
    }

    return new TestFrame(frame, gx);

  }

  public static TestFrame withCloseOnClick(BiConsumer<Frame, StringArtGraphicsExtension> drawFn) {
    return withCloseOnClick(500, 500, drawFn);
  }

  public static TestFrame withRedrawOnClick(BiConsumer<Frame, StringArtGraphicsExtension> redrawFn) {
    return withRedrawOnClick(500, 500, redrawFn);
  }

  public static TestFrame withRedrawOnClick(int w, int h, BiConsumer<Frame, StringArtGraphicsExtension> __redrawFn) {
    var image = new BufferedImage(w, h, TYPE_INT_RGB);
    var gx = new StringArtGraphicsExtension(image);
    gx.setStroke(3.0f);
    gx.setBackground(BLACK);
    gx.setColor(WHITE);
    gx.setFont(new Font(MONOSPACED, BOLD, 25));
    var frame = showImage(image);

    var inDraw = new Object() {
      boolean $ = false;
    };

    var _redrawFn = __redrawFn;
    __redrawFn = (__frame, __gx) -> {
      __gx.clear();
      inDraw.$ = true;
      _redrawFn.accept(__frame, __gx);
      inDraw.$ = false;
      frame.repaint();
    };
    final BiConsumer<Frame, StringArtGraphicsExtension> redrawFn = __redrawFn;

    frame.addMouseListener(
      new MouseClickListener(
        ev -> {
          if (inDraw.$) {
            return;
          }
          redrawFn.accept(frame, gx);
          frame.repaint();
        }));

    var frameClosed = new Object() {
      boolean $ = false;
    };
    frame.addWindowListener(
      new WindowCloseListener(
        ev -> frameClosed.$ = true));

    __redrawFn.accept(frame, gx);
    while (!frameClosed.$) {
      Unchecked.run(() -> Thread.sleep(10));
    }

    return new TestFrame(frame, gx);
  }
}
