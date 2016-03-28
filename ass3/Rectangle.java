import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List intersectionPoints(Line line) {
        List intersectionPoints = new ArrayList();
        Line[] lines = this.madeOfLines();
        for (int i = 0, j = 0; (i < 4) && (j < 2); i++) {
            intersectionPoints.add(line.intersectionWith(lines[i]));
            if (intersectionPoints.get(j) != null) {
                j++;
            } else {
                intersectionPoints.remove(j);
            }
        }
        return intersectionPoints;
    }

  //returns line array when the index 0 is the top line,
  //and so on (moving clockwise on the rectangle edges)
    public int pointPlace(Point p) {
        Line pointLine = new Line(p, p);
        Line[] lines = this.madeOfLines();
        int i = 0;
        while (!pointLine.isIntersecting(lines[i])) {
            i++;
        }
        return i+1;
    }

    public Line[] madeOfLines() {
        Line[] lines = new Line[4];
        //up
        lines[0] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                           this.upperLeft.getX() + width, this.upperLeft.getY());
        //left
        lines[1] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                             this.upperLeft.getX(), this.upperLeft.getY() + height);
        //right
        lines[2] = new Line(this.upperLeft.getX() + width, this.upperLeft.getY(),
                              this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        //down
        lines[3] = new Line(this.upperLeft.getX(), this.upperLeft.getY() + height,
                             this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        return lines;
    }
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                              (int) this.width, (int) this.height);
    }

}
