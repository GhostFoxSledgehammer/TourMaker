// License: GPL. For details, see LICENSE file.
package TourMaker.data;

/**
 *
 * @author Kishan Tripathi
 */
public class Point {

  public final double x;
  public final double y;

  public Point(double xx, double yy) {
    x = xx;
    y = yy;
  }

  @Override
  public String toString() {
    return "x = " + String.format("%.2f", x) + ", y = " + String.format("%.2f", y);
  }
}
