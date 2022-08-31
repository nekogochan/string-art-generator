package nekogochan.stringart.math.point;

import lombok.ToString;
import nekogochan.stringart.math.SAShape;
import nekogochan.stringart.math.SAShapeVisitor;
import nekogochan.stringart.util.Unchecked;

@ToString
public class IntPoint implements SAShape, Cloneable {
  public int x, y;

  public IntPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public IntPoint() {
  }

  @Override
  public IntPoint clone() {
    return (IntPoint) Unchecked.call(super::clone);
  }

  @Override
  public void acceptVisitor(SAShapeVisitor visitor) {
    visitor.visit(this);
  }
}
