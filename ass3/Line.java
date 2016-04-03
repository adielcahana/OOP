import java.awt.Color;
import java.util.Collections;
import java.util.List;

import biuoop.DrawSurface;

/**
* @author Ori Engelberg <turht50@gmail.com>
* @version 1.0
* @since 2016-03-21 */
public class Line {
    private Point start;
    private Point end;
    private double slope;

    /**
    * Contractor - Create a start point an end point and the line slope.
    * @param start - the first point in the line.
    * @param end - the last point in the line. */
        public Line(Point start, Point end) {
           this.start = start;
           this.end = end;
           this.slope = slope();
           }

        /**
         * Contractor - Create a start point an end point and the line slope.
         * @param x1 - the x value of the start point.
         * @param y1 - the y value of the start point.
         * @param x2 - the x value of the end point.
         * @param y2 - the y value of the end point. */
       public Line(double x1, double y1, double x2, double y2) {
           this.start = new Point(x1, y1);
           this.end = new Point(x2, y2);
           this.slope = slope();
           }

       /**
        * Calculate the length of the line.
        * @return the distance between the first point to the last. */
       public double length() {
           double length = this.start.distance(end);
           return length;
           }

       /**
        * Calculate the middle point.
        * Sum the x values of the start and the end points and their y values and create a new point.
        * @return the middle point of the line. */
       public Point middle() {
           double x1, x2 , y1 , y2;
           x1 = this.start.getX();
           x2 = this.end.getX();
           y1 = this.start.getY();
           y2 = this.end.getY();
           Point middle = new Point((x2 + x1) / 2, (y2 + y1) / 2);
           return middle;
           }

       /**
        * Initialize the start point.
        * @return the start point. */
       public Point start() {
           return start;
       }

       /**
        * Initialize the end point.
        * @return the end point. */
       public Point end() {
           return end;
       }

       /**
        * Initialize the line slope.
        * Do slope = (y1-y2) / (x1-x2).
        * @return the line slope. */
        public double slope() {
           double dx, dy;
           dx = this.start.getX() - this.end.getX();
           dy = this.start.getY() - this.end.getY();
           return (dy / dx);
        }

        /**
         * Check intersection between 2 lines.
         * @param other - another line to check intersection.
         * @return true if the lines intersected, else return false.  */
        public boolean isIntersecting(Line other) {
            Point intersection = intersectionWith(other);
            if (intersection != null) {
                return true;
                }
            return false;
    }

        /**
         * Create the intersection point between the lines.
         * Create 2 equations y = m * x - b and check the where the y values are equal.
         * @param other - another line to check the intersection.
         * @return the intersection point, if the lines don't intersected returns null.  */
        public Point intersectionWith(Line other) {
            double x, y;
            // find the m and b to create the equation y = m * x -b.
            Double m1 = this.getSlope();
            Double m2 = other.getSlope();
            double b1 = this.start.getY() - m1 * this.start.getX();
            double b2 = other.start.getY() - m2 * other.start.getX();
            Point intersection = null;
            // If the slopes equal there isn't intersection.
            if (m1.equals(m2)) { 
                return null;
            } else if (m1.isInfinite()) {
                // If one of the slopes is Infinity then the x value is known and we find the y value.
                x = this.start.getX();
                y = m2 * x + b2;
                intersection = new Point(x, y);
                // If no intersection return null.
                if (!(intersection.isInLineSegment(this) && intersection.isInLineSegment(other))) {
                    return null;
                }
            } else if ( m2.isInfinite()) {
                x = other.start.getX();
                y = m1 * x + b1;
                intersection = new Point(x, y);
                if (!(intersection.isInLineSegment(this) && intersection.isInLineSegment(other))) {
                    return null;
                }
            } else {
               // In other cases find the x and y of the intersection and check if this is in the both lines.
               x = (b2 - b1) / (m1 - m2);
               y = m1 * x + b1;
               intersection = new Point(x, y);
               if (!(intersection.isInLineSegment(this) && intersection.isInLineSegment(other))) {
                   return null;
               }
            }
            return intersection;
        }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
        	System.out.println("Error: no intersections");
            return null;
        }
        Collections.sort(intersections, new PointByDistanceComparator(this.start()));
        System.out.println("there is intersections");
        return (Point) intersections.get(0);
    }
    /**
     * Check if 2 lines are equal.
     * @param other - the other line.
     * @return true if the lines equal, else return false.  */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
            }
        // Check if the x values and the y values are equal in the start and the of the lines.
        return (this.start.equals(other.start) && this.end.equals(other.end));
        }

    /**
     * Create a slope to the line.
     * @return the slope. */
    public double getSlope() {
        return slope;
    }

    public void drawOn(DrawSurface d, Color color) {
        Point start = this.start();
        Point end = this.end();
        d.setColor(color);
        d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
}