package nekogochan.stringart.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class MouseClickListener implements MouseListener {

  private final Consumer<MouseEvent> onClick;

  public MouseClickListener(Consumer<MouseEvent> onClick) {this.onClick = onClick;}

  @Override
  public void mouseClicked(MouseEvent e) {
    onClick.accept(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
