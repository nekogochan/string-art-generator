package nekogochan.stringart.generator.nail;

import lombok.ToString;
import nekogochan.stringart.math.circle.Circle;
import nekogochan.stringart.math.point.Point;

@ToString
public class Nail extends Circle {
  /**
   * used in connections, should have values = 0, 1, 2... etc
   */
  private final int idx;

  public Nail(int idx, Point center, double r) {
    super(center, r);
    this.idx = idx;
  }

  public Nail(int idx, double r) {
    super(r);
    this.idx = idx;
  }

  public Nail(int idx) {
    this.idx = idx;
  }

  public int getIdx() {
    return idx;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Nail nail = (Nail) o;
    return idx == nail.idx;
  }

  @Override
  public int hashCode() {
    return idx;
  }
}
