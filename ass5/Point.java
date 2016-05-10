import java.awt.Color;

import biuoop.DrawSurface;

/**
* @author Ori Engelberg <turht50@gmail.com>
* @version 1.0
* @since 2016-03-21 */
public class Point {

    private double x;
    private double y;

    /**
    * Contractor - Give the point a x and y values.
    * @param x - the point coordinate in x
    * @param y - the point coordinate in y. */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between 2 points.
     * Take (x1-x2)^2 + (y1-y2)^2 and calculate the sqrt.
     * @param other - other point to calculate the distance.
     * @return the distance between the 2 points. */
    public double distance(Point other) {
        if (other == null) {
           return 0;
        }
        double x1, x2, y1, y2;
        x1 = this.getX();
        x2 = other.getX();
        y1 = this.getY();
        y2 = other.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }
    public boolean isInLine(Line line) {
    	double slope = line.getSlope();
    	if (Double.isInfinite(slope) && Math.abs(this.getX() - line.start().getX()) < 0.001) {
    		return this.isInLineSegment(line);
    	}
    	double b = line.start().getY() - slope * line.start().getX();
    	if (Math.abs(this.getY() - slope * this.getX() - b) < 0.001) {
    		return this.isInLineSegment(line);
    	}
        return false;
    }

    public boolean isInLineSegment(Line line) {
    	return (((this.x >= line.start().getX() && x <= line.end().getX()) 
    			|| (this.x <= line.start().getX() && x >= line.end().getX())) 
    			&& ((this.y >= line.end().getY() && this.y <= line.start().getY()) 
                || (this.y <= line.end().getY() && this.y >= line.start().getY())));
    }
    /**
     * Compare between 2 points.
     * Check if the x values equal and the y values equal.
     * @param other - other point to compare her values.
     * @return true if the points equal and false if not. */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

    /**
     * Initialize the point x value.
     * @return the point x value. */
    public double getX() {
        return this.x;
        }

    /**
     * Initialize the point y value.
     * @return the point y value. */
    public double getY() {
        return this.y;
        }
    
    public void drawOn(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillCircle((int) this.getX(), (int) this.getY(), 3);
    }
}