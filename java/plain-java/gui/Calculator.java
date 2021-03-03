package gui;

import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {
  public Calculator() {
    initUI();
  }

  private void initUI() {

    // createGroupLayout();
    createGridLayout();

    setTitle("Calculator");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void createGridLayout() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    panel.setLayout(new GridLayout(5, 4, 5, 5));
    String[] buttons = {
      "Cls", "Bck", "", "Close", "7", "8", "9", "/",
      "4", "5", "6", "*", "1", "2", "3", "-",
      "0", ".", "=", "+"
    };

    for (int i = 0; i < buttons.length; i++) {
      if (i == 2) {
        panel.add(new JLabel(buttons[i]));
      } else {
        panel.add(new JButton(buttons[i]));
      }
    }
    add(panel);

  }

  private void createGroupLayout() {
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(e -> System.exit(0));

    Container pane = getContentPane();
    GroupLayout gl = new GroupLayout(pane);
    pane.setLayout(gl);

    gl.setAutoCreateContainerGaps(true);

    gl.setHorizontalGroup(
        gl.createSequentialGroup()
        .addComponent(quitButton)
        );
    gl.setVerticalGroup(
        gl.createSequentialGroup()
        .addComponent(quitButton)
        );
  }
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      Calculator calc = new Calculator();
      calc.setVisible(true);
    });
  }
}
