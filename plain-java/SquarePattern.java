import java.awt.*;
import java.util.*;

/**
 * This class is used to draw many squares with alternating colors.
 * @author Mingyu Sun
 */
public class SquarePattern {

    /**
     * The X represents the x position of the innermost square is 300.
     */
    public static final int X = 300;

    /**
     * The Y represents the y position of the innermost square is 300.
     */
    public static final int Y = 300;

    /**
    * WIDTH means that each square should be 50 pixels smaller.
     */
    public static final int WIDTH = 50;

    /**
     * HWIDTH means that each side will decrease 25 per time.
     */
    public static final int HWIDTH = 25;

    /**
     * PIXEL means the drawing panel has 650 pixels.
     */
    public static final int PIXEL = 650;

    /**
     * MAXNUM means the maximum number for RGB value is 255.
     */
    public static final int MAXNUM = 255;

    /**
     * MAXSQ means the maximum number of square is 12.
     */
    public static final int MAXSQ = 12;


    /**
     * This is the main method, which is the main part of this project.
     * @param args  This is required for main to run.
     */
    public static void main(String[] args) {


        DrawingPanel panel = new DrawingPanel(PIXEL, PIXEL);

        Graphics g = panel.getGraphics();

        Scanner console = new Scanner(System.in);

        System.out.print("Number of Squares(1-12): ");

        int numberOfSquare = console.nextInt();

        System.out.println();

        System.out.print("Red value (0-255): ");

        int red = console.nextInt();

        System.out.println();

        System.out.print("Green value (0-255): ");

        int green = console.nextInt();

        System.out.println();

        System.out.print("Blue value (0-255): ");

        int blue = console.nextInt();

        System.out.println();

        int realNumberOfSquare = Math.min(Math.max(numberOfSquare, 1), MAXSQ);

        int realRed = Math.min(Math.max(red, 0), MAXNUM);

        int realGreen = Math.min(Math.max(green, 0), MAXNUM);

        int realBlue = Math.min(Math.max(blue, 0), MAXNUM);

        Color cusColor = new Color(realRed, realGreen, realBlue);

        Color atiColor = new Color(MAXNUM - realRed, MAXNUM - realGreen, MAXNUM - realBlue);

        for (int i = realNumberOfSquare; i >= 1; i -= 2) {

            int reWidth = i * WIDTH;

            int width = (i - 1) * WIDTH;

            if ((realNumberOfSquare % 2) == 0) {

                g.setColor(atiColor);

                drawSquare(g, atiColor, X - HWIDTH * (i - 1), Y - HWIDTH * (i - 1), reWidth);

                g.setColor(cusColor);

                drawSquare(g, cusColor, X - HWIDTH * (i - 2), Y - HWIDTH * (i - 2), width);

            } else {

                g.setColor(cusColor);

                drawSquare(g, cusColor, X - HWIDTH * (i - 1), Y - HWIDTH * (i - 1), reWidth);

                g.setColor(atiColor);

                drawSquare(g, atiColor, X - HWIDTH * (i - 2), Y - HWIDTH * (i - 2), width);
            }
        }

        for (int j = realNumberOfSquare; j >= 1; j -= 2) {

            int reWidth = j * WIDTH;

            int width = (j - 1) * WIDTH;

            if ((realNumberOfSquare % 2) == 0) {

                g.setColor(cusColor);

                drawOutlineSquare(g, cusColor, X - HWIDTH * (j - 1), Y - HWIDTH * (j - 1), reWidth);

                g.setColor(atiColor);

                drawOutlineSquare(g, atiColor, X - HWIDTH * (j - 2), Y - HWIDTH * (j - 2), width);

            } else {

                g.setColor(atiColor);

                drawOutlineSquare(g, atiColor, X - HWIDTH * (j - 1), Y - HWIDTH * (j - 1), reWidth);

                g.setColor(cusColor);

                drawOutlineSquare(g, cusColor, X - HWIDTH * (j - 2), Y - HWIDTH * (j - 2), width);
            }
        }
    }

    /**
     * This method is used to draw a square.
     * @param g This is the panel that we draw on.
     * @param color This is the color that we use to draw the outline of the square.
     * @param x is the top left position of x-axis.
     * @param y is the top left position of y-axis.
     * @param width This is the width of the square.
     */
    public static void drawSquare(Graphics g, Color color, int x, int y, int width) {

        g.setColor(color);
        g.fillRect(x, y, width, width);
    }

    /**
    * This method is used to draw an outline of a square.
    * @param g This is the panel that we draw on.
    * @param color This is the color that we use to draw the outline of the square.
    * @param x is the top left position of x-axis.
    * @param y is the top left position of y-axis.
    * @param width This is the width of the square.
    */
    public static void drawOutlineSquare(Graphics g, Color color, int x, int y, int width) {

        g.setColor(color);
        g.drawRect(x, y, width, width);
    }
}
