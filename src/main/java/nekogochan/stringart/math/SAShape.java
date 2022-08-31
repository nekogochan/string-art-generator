package nekogochan.stringart.math;

public interface SAShape {
  void acceptVisitor(SAShapeVisitor visitor);
}
