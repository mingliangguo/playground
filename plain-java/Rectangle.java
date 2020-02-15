public class Rectangle {
  private Point upperLeft;
  private Point uppperRight;
  private Point lowerLeft;
  private Point lowerRight;
  public Rectangle(Point a, Point b, Point c, Point d) {
    this.upperLeft = a;
    this.uppperRight = b;
    this.lowerLeft = c;
    this.lowerRight = d;
  }

  public Rectangle(Point upperLeft, Point lowerRight) {
    this.uppperLeft  = upperLeft;
    this.uppperRight = new Point(lowerRight.x, uppperLeft.y);
    this.lowerLeft = new Point(upperLeft.x, lowerRight.y);
    this.lowerRight = lowerRight;
  }
}
