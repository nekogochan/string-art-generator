package nekogochan.stringart.graphics;

import nekogochan.stringart.math.SAShape;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_ROUND;

public class StringArtGraphicsExtension extends Graphics2D {
  private final BufferedImage source;
  private final Graphics2D gx;
  private final SAShapeGraphicsVisitor saShapeGraphicsVisitor;

  public StringArtGraphicsExtension(BufferedImage source) {
    this.source = source;
    this.gx = source.createGraphics();
    this.saShapeGraphicsVisitor = new SAShapeGraphicsVisitor(gx);
  }

  public BufferedImage getSource() {
    return source;
  }

  public void draw(SAShape saShape) {
    saShapeGraphicsVisitor.visit(saShape);
  }

  public void draw(SAShape saShape, Color color) {
    var prevColor = gx.getColor();
    gx.setColor(color);
    draw(saShape);
    gx.setColor(prevColor);
  }

  public void clear() {
    gx.clearRect(0, 0, source.getWidth(), source.getHeight());
  }

  public void fill(Color color) {
    var prevColor = gx.getColor();
    gx.setColor(color);
    gx.fillRect(0, 0, source.getWidth(), source.getHeight());
    gx.setColor(prevColor);
  }

  public void setStroke(float width) {
    setStroke(width, CAP_ROUND, JOIN_ROUND);
  }

  public void setStroke(float width, int cap, int join) {
    gx.setStroke(new BasicStroke(width, cap, join));
  }

  @Override
  public void draw3DRect(int x, int y, int width, int height, boolean raised) {gx.draw3DRect(x, y, width, height, raised);}

  @Override
  public void fill3DRect(int x, int y, int width, int height, boolean raised) {gx.fill3DRect(x, y, width, height, raised);}

  @Override
  public void draw(Shape s) {gx.draw(s);}

  @Override
  public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {return gx.drawImage(img, xform, obs);}

  @Override
  public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {gx.drawImage(img, op, x, y);}

  @Override
  public void drawRenderedImage(RenderedImage img, AffineTransform xform) {gx.drawRenderedImage(img, xform);}

  @Override
  public void drawRenderableImage(RenderableImage img, AffineTransform xform) {gx.drawRenderableImage(img, xform);}

  @Override
  public void drawString(String str, int x, int y) {gx.drawString(str, x, y);}

  @Override
  public void drawString(String str, float x, float y) {gx.drawString(str, x, y);}

  @Override
  public void drawString(AttributedCharacterIterator iterator, int x, int y) {gx.drawString(iterator, x, y);}

  @Override
  public void drawString(AttributedCharacterIterator iterator, float x, float y) {gx.drawString(iterator, x, y);}

  @Override
  public void drawGlyphVector(GlyphVector g, float x, float y) {gx.drawGlyphVector(g, x, y);}

  @Override
  public void fill(Shape s) {gx.fill(s);}

  @Override
  public boolean hit(Rectangle rect, Shape s, boolean onStroke) {return gx.hit(rect, s, onStroke);}

  @Override
  public GraphicsConfiguration getDeviceConfiguration() {return gx.getDeviceConfiguration();}

  @Override
  public void setComposite(Composite comp) {gx.setComposite(comp);}

  @Override
  public void setPaint(Paint paint) {gx.setPaint(paint);}

  @Override
  public void setStroke(Stroke s) {gx.setStroke(s);}

  @Override
  public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {gx.setRenderingHint(hintKey, hintValue);}

  @Override
  public Object getRenderingHint(RenderingHints.Key hintKey) {return gx.getRenderingHint(hintKey);}

  @Override
  public void setRenderingHints(Map<?, ?> hints) {gx.setRenderingHints(hints);}

  @Override
  public void addRenderingHints(Map<?, ?> hints) {gx.addRenderingHints(hints);}

  @Override
  public RenderingHints getRenderingHints() {return gx.getRenderingHints();}

  @Override
  public void translate(int x, int y) {gx.translate(x, y);}

  @Override
  public void translate(double tx, double ty) {gx.translate(tx, ty);}

  @Override
  public void rotate(double theta) {gx.rotate(theta);}

  @Override
  public void rotate(double theta, double x, double y) {gx.rotate(theta, x, y);}

  @Override
  public void scale(double sx, double sy) {gx.scale(sx, sy);}

  @Override
  public void shear(double shx, double shy) {gx.shear(shx, shy);}

  @Override
  public void transform(AffineTransform Tx) {gx.transform(Tx);}

  @Override
  public void setTransform(AffineTransform Tx) {gx.setTransform(Tx);}

  @Override
  public AffineTransform getTransform() {return gx.getTransform();}

  @Override
  public Paint getPaint() {return gx.getPaint();}

  @Override
  public Composite getComposite() {return gx.getComposite();}

  @Override
  public void setBackground(Color color) {gx.setBackground(color);}

  @Override
  public Color getBackground() {return gx.getBackground();}

  @Override
  public Stroke getStroke() {return gx.getStroke();}

  @Override
  public void clip(Shape s) {gx.clip(s);}

  @Override
  public FontRenderContext getFontRenderContext() {return gx.getFontRenderContext();}

  @Override
  public Graphics create() {return gx.create();}

  @Override
  public Graphics create(int x, int y, int width, int height) {return gx.create(x, y, width, height);}

  @Override
  public Color getColor() {return gx.getColor();}

  @Override
  public void setColor(Color c) {gx.setColor(c);}

  @Override
  public void setPaintMode() {gx.setPaintMode();}

  @Override
  public void setXORMode(Color c1) {gx.setXORMode(c1);}

  @Override
  public Font getFont() {return gx.getFont();}

  @Override
  public void setFont(Font font) {gx.setFont(font);}

  @Override
  public FontMetrics getFontMetrics() {return gx.getFontMetrics();}

  @Override
  public FontMetrics getFontMetrics(Font f) {return gx.getFontMetrics(f);}

  @Override
  public Rectangle getClipBounds() {return gx.getClipBounds();}

  @Override
  public void clipRect(int x, int y, int width, int height) {gx.clipRect(x, y, width, height);}

  @Override
  public void setClip(int x, int y, int width, int height) {gx.setClip(x, y, width, height);}

  @Override
  public Shape getClip() {return gx.getClip();}

  @Override
  public void setClip(Shape clip) {gx.setClip(clip);}

  @Override
  public void copyArea(int x, int y, int width, int height, int dx, int dy) {gx.copyArea(x, y, width, height, dx, dy);}

  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {gx.drawLine(x1, y1, x2, y2);}

  @Override
  public void fillRect(int x, int y, int width, int height) {gx.fillRect(x, y, width, height);}

  @Override
  public void drawRect(int x, int y, int width, int height) {gx.drawRect(x, y, width, height);}

  @Override
  public void clearRect(int x, int y, int width, int height) {gx.clearRect(x, y, width, height);}

  @Override
  public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {gx.drawRoundRect(x, y, width, height, arcWidth, arcHeight);}

  @Override
  public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {gx.fillRoundRect(x, y, width, height, arcWidth, arcHeight);}

  @Override
  public void drawOval(int x, int y, int width, int height) {gx.drawOval(x, y, width, height);}

  @Override
  public void fillOval(int x, int y, int width, int height) {gx.fillOval(x, y, width, height);}

  @Override
  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {gx.drawArc(x, y, width, height, startAngle, arcAngle);}

  @Override
  public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {gx.fillArc(x, y, width, height, startAngle, arcAngle);}

  @Override
  public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {gx.drawPolyline(xPoints, yPoints, nPoints);}

  @Override
  public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {gx.drawPolygon(xPoints, yPoints, nPoints);}

  @Override
  public void drawPolygon(Polygon p) {gx.drawPolygon(p);}

  @Override
  public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {gx.fillPolygon(xPoints, yPoints, nPoints);}

  @Override
  public void fillPolygon(Polygon p) {gx.fillPolygon(p);}

  @Override
  public void drawChars(char[] data, int offset, int length, int x, int y) {gx.drawChars(data, offset, length, x, y);}

  @Override
  public void drawBytes(byte[] data, int offset, int length, int x, int y) {gx.drawBytes(data, offset, length, x, y);}

  @Override
  public boolean drawImage(Image img, int x, int y, ImageObserver observer) {return gx.drawImage(img, x, y, observer);}

  @Override
  public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {return gx.drawImage(img, x, y, width, height, observer);}

  @Override
  public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {return gx.drawImage(img, x, y, bgcolor, observer);}

  @Override
  public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {return gx.drawImage(img, x, y, width, height, bgcolor, observer);}

  @Override
  public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {return gx.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);}

  @Override
  public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {return gx.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);}

  @Override
  public void dispose() {gx.dispose();}

  @Override
  @Deprecated(since = "9")
  public void finalize() {gx.finalize();}

  @Override
  public String toString() {return gx.toString();}

  @Override
  @Deprecated
  public Rectangle getClipRect() {return gx.getClipRect();}

  @Override
  public boolean hitClip(int x, int y, int width, int height) {return gx.hitClip(x, y, width, height);}

  @Override
  public Rectangle getClipBounds(Rectangle r) {return gx.getClipBounds(r);}
}
