package nekogochan.stringart.visualizer.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
  public static Frame showImage(BufferedImage image) {
    var frame = new JFrame();
    frame.setLayout(new FlowLayout());
    frame.setSize(image.getWidth(), image.getHeight() + 44);
    var label = new JLabel();
    label.setIcon(new ImageIcon(image));
    frame.add(label);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    return frame;
  }
}
