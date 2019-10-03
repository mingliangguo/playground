import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRect extends JPanel {

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(new Color(212, 0, 212));
    g2d.fillRect(10, 15, 120, 90);

    g2d.setColor(new Color(31, 255, 1));
    g2d.fillRect(25, 30, 75, 45);

  }

  public static void main(String[] args) {
    DrawRect rects = new DrawRect();
    JFrame frame = new JFrame("Rectangles");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(rects);
    frame.setSize(360, 300);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

