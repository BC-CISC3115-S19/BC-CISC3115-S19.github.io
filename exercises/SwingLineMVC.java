import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;

/**
 * Note that SwingLineMVC implements a new interface: Observer. Look at the code to see how this
 * class works with the idea of a 'model'.
 */
public class SwingLineMVC extends JFrame implements Observer {

  JScrollBar lengthAdjuster;
  JTextField lengthDisplay;
  JTextField thicknessDisplay;

  JButton thicker;
  JButton thinner;
  JButton lucky;

  PaintCanvas paintArea;

  /* note that these two instance variables no longer exist;
   * instead, they are part of the 'model'
   *
  int length = 100;
  int thickness = 1;
  */

  LineModel model;

  public SwingLineMVC(String name) {
    super(name);
    model = new LineModel(100, 1); // initialize line 'model' object
    model.addObserver(this); // this object is 'listening' for changes to model

    // set up groovy components

    lengthAdjuster = new JScrollBar(JScrollBar.HORIZONTAL, model.getLength(), 20, 0, 300);

    lengthDisplay = new JTextField(3);
    lengthDisplay.setEditable(true);
    lengthDisplay.setText(model.getLength() + "");

    thicknessDisplay = new JTextField(3);
    thicknessDisplay.setEditable(true);
    thicknessDisplay.setText(model.getThickness() + "");

    JButton thicker = new JButton("Thicker");
    JButton thinner = new JButton("Thinner");
    JButton lucky = new JButton("Feeling Lucky?");

    thicker.addActionListener(new ThickListener());
    thinner.addActionListener(new ThinListener());
    lucky.addActionListener(new LuckyListener());

    thicknessDisplay.addActionListener(new ThicknessListener());
    lengthDisplay.addActionListener(new LengthListener());

    lengthAdjuster.addAdjustmentListener(new ScrollbarListener());

    paintArea = new PaintCanvas();

    // do slightly complex layout

    add(paintArea, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(0, 1));
    buttonPanel.add(thinner);
    buttonPanel.add(thicknessDisplay);
    buttonPanel.add(thicker);
    buttonPanel.add(lucky);

    add(buttonPanel, BorderLayout.EAST);

    JPanel scrollPanel = new JPanel();
    scrollPanel.add(lengthAdjuster);
    scrollPanel.add(lengthDisplay);

    add(scrollPanel, BorderLayout.SOUTH);

    // display application window

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 300);
    setVisible(true);
  }

  public static void main(String[] args) {
    createAndShowGUI();
  }

  private static void createAndShowGUI() {
    SwingLineMVC sl = new SwingLineMVC("Swing Line");
  }

  /**
   * This methd is the "event handler" for when the model changes. In our case, it's simple: repaint
   * the canvas. But remember that the textfields and scrollbar 'thumb' position are also part of
   * the 'view', so we also need to be sure the value they show matches the model.
   */
  public void update(Observable o, Object arg) {
    paintArea.repaint();
    thicknessDisplay.setText(model.getThickness() + "");
    lengthDisplay.setText(model.getLength() + "");
    lengthAdjuster.setValue(model.getLength());
  }

  class PaintCanvas extends JPanel {
    int centerX;
    int centerY;

    public void paintComponent(Graphics g) {
      int length = model.getLength();
      int thickness = model.getThickness();
      int centerX = getWidth() / 2;
      int centerY = getHeight() / 2;
      for (int i = 0; i < thickness; i++) { // Draw 'thickness' number of lines
        g.drawLine(centerX - (length / 2), centerY + i, centerX + (length / 2), centerY + i);
      }
    }
  }

  /**
   * LineModel contains the "application data" for this line-manipulating app. It allows outside
   * classes to change the contents of the model--in this case, it can be changed arbitrarily, but
   * the class silently corrects negative values to 0. By implmemeting Observable, the class allows
   * other objects to register as 'listeners' who want to be informed when the values in the model
   * have changed.
   */
  class LineModel extends Observable {
    int length;
    int thickness;

    LineModel(int length, int thickness) {
      this.length = length;
      this.thickness = thickness;
    }

    LineModel() {
      this(100, 1);
    }

    void setLength(int length) {
      if (this.length != length) {
        if (length < 0) {
          this.length = 0;
        } else {
          this.length = length;
        }
        setChanged(); // Observable method--indicate model has changed
        notifyObservers(); // Observable method--tell others about change
      }
    }

    void setThickness(int thickness) {
      if (this.thickness != thickness) {
        if (thickness < 0) {
          this.thickness = 0;
        } else {
          this.thickness = thickness;
        }
        setChanged();
        notifyObservers();
      }
    }

    int getLength() {
      return length;
    }

    int getThickness() {
      return thickness;
    }
  }

  /* note that these listeners no longer are responsible for repainting--
   * their only job is to update the model. It's somebody else's problem
   * to decide whether that change is significant.
   */

  /* the first two listeners would be a little shorter if the model offered
   * methods to increment and decrement the thickness.
   */

  class ThickListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int thickness = model.getThickness();
      thickness += 1;
      model.setThickness(thickness);
    }
  }

  class ThinListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int thickness = model.getThickness();
      thickness -= 1;
      model.setThickness(thickness);
    }
  }

  class LuckyListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Random random = new Random();
      int thickness = random.nextInt(20) + 1;
      model.setThickness(thickness);
    }
  }

  class LengthListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String text = lengthDisplay.getText();
      int length = Integer.parseInt(text);
      model.setLength(length);
    }
  }

  class ThicknessListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String text = thicknessDisplay.getText();
      int thickness = Integer.parseInt(text);
      model.setThickness(thickness);
    }
  }

  class ScrollbarListener implements AdjustmentListener {
    public void adjustmentValueChanged(AdjustmentEvent e) {
      int length =
          lengthAdjuster.getValue(); // Only one scrollbar, no need for getSource() invocation
      model.setLength(length);
    }
  }
}
