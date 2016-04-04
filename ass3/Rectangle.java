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
        Line[] lines = this.myLines();
        for (int i = 0; i < 4; i++) {
        	if (line.isIntersecting(lines[i])) {
                intersectionPoints.add(line.intersectionWith(lines[i]));
        	}
        }
        return intersectionPoints;
    }

    
    public int divideRectangle(Point p){
    	double x = upperLeft.getX();
    	double y = upperLeft.getY();
    	Line[] lines = new Line[5];
    	int i;
    	double divide = width/5;
    	for(i=0; i < 5; i++){
    		lines[i] = new Line(new Point(x + (divide*i),y),(new Point(x + (divide*(i + 1)),y)));
    	}
    	i = 0;
        while (i < 5) {
        	if (p.isInLine(lines[i])) {
        		break;
        	}
            i++;
        }
        return i;    	
    }
    
  //returns line array when the index 0 is the top line,
  //and so on (moving clockwise on the rectangle edges)
    public int placeInsideMe(Point p) {
        Line[] lines = this.myLines();
        int i = 0;
        while (i < 4) {
        	if (p.isInLine(lines[i])) {
        		break;
        	}
            i++;
        }
        System.out.println("i is " + i);
        return i;
    }

    public Line[] myLines() {
        Line[] lines = new Line[4];
        //up
        lines[0] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                           this.upperLeft.getX() + width, this.upperLeft.getY());
        //right
        lines[1] = new Line(this.upperLeft.getX() + width, this.upperLeft.getY(),
                           this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        //down
        lines[2] = new Line(this.upperLeft.getX(), this.upperLeft.getY() + height,
                           this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        //left
        lines[3] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                           this.upperLeft.getX(), this.upperLeft.getY() + height);
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
    public void drawOn(DrawSurface surface) {
    	surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                              (int) this.width, (int) this.height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }
}
