import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;

public class GeometryTest {
	public void testMyLines() {
	    GUI gui = new GUI("MyLines", 500, 500);
        DrawSurface d = gui.getDrawSurface();
	    Block b = new Block(new Point(234, 106), 120, 40, 3);
	    Line[] lines = b.getCollisionRectangle().myLines();
	    String[] direction = {"UP","RIGHT","DOWN","LEFT"};
	    int i = 0;
	    while (i < 4) {
	    	System.out.println(i);
	    	lines[i].drawOn(d, Color.BLACK);
	    	d.drawText((int) lines[i].middle().getX(), (int) lines[i].middle().getY(), direction[i], 20);
	    	i++;
	    }
        gui.show(d);
	}

	public void testUpperBlock() {
	    GUI gui = new GUI("ClosetintersectionToStartOfLine", 500, 500);
        DrawSurface d = gui.getDrawSurface();
        Block b = new Block(new Point(0, 0), 600, 10, -1, Color.WHITE);
	    Line line = new Line(new Point(250, 250), new Point(250, -600));
	    line.drawOn(d, Color.BLACK);
	    b.drawOn(d);
	    List intersections = b.getCollisionRectangle().intersectionPoints(line);
	    int j = 0;
	    while (j < intersections.size()) {
	        ((Point) intersections.get(j)).drawOn(d, Color.GREEN);
	       	j++;
	    }
	    line.closestIntersectionToStartOfLine(b.getCollisionRectangle()).drawOn(d, Color.BLUE);
	    d.drawText(10, 450, "green - intersection Points" , 10);
	    d.drawText(10, 460, "blue - closest Intersection To Start Of Line" , 10);
        gui.show(d);
	}

	public void testClosetintersectionToStartOfLine() {
	    GUI gui = new GUI("Closet intersection To Start Of Line", 500, 500);
        DrawSurface d = gui.getDrawSurface();
	    Block b = new Block(new Point(150, 160), 120, 40, 3, Color.YELLOW);
	    Line[] lines = new Line[4];
	    lines[0] = new Line(new Point(10, 10), new Point(400, 400));
	    lines[1] = new Line(new Point(10, 400), new Point(400, 10));
	    lines[2] = new Line(new Point(400, 200), new Point(10, 200));
	    lines[3] = new Line(new Point(210, 400), new Point(210, 10));
	    b.drawOn(d);
	    d.drawText(10, 450, "green - intersection Points" , 10);
	    d.drawText(10, 460, "blue - closest Intersection To Start Of Line" , 10);
	    d.drawText(10, 470, "red - start of line" , 10);
	    int i = 0;
	    while (i < 4) {
	    	lines[i].drawOn(d, Color.BLACK);
	    	lines[i].start().drawOn(d, Color.RED);
	    	List intersections = b.getCollisionRectangle().intersectionPoints(lines[i]);
	    	int j = 0;
	    	while (j < intersections.size()) {
	    		((Point) intersections.get(j)).drawOn(d, Color.GREEN);
	    		j++;
	    	}
	    	lines[i].closestIntersectionToStartOfLine(b.getCollisionRectangle()).drawOn(d, Color.BLUE);
	    	i++;
	    }
        gui.show(d);
	}
	
	public void testPointByDistanceCompartor() {
	    GUI gui = new GUI("Distance", 500, 500);
        DrawSurface d = gui.getDrawSurface();
        List points = new ArrayList();
	    Point p = new Point(250, 250);
	    points.add(new Point(10, 10));
	    points.add(new Point(400, 200));
	    points.add(new Point(210, 370));
	    points.add(new Point(234, 423));
	    Collections.sort(points, new PointByDistanceComparator(p));
	    d.drawText(10, 450, "green - Origin" , 10);
	    d.drawText(10, 470, "red - distant points" , 10);
	    Integer i = 0;
	    while (i < 4) {
	    	Point temp = (Point) points.get(i);
	    	Line line = new Line(temp, p);
	    	line.drawOn(d, Color.BLACK);
	    	Double dist = temp.distance(p);
	    	d.drawText((int) line.middle().getX(), (int) line.middle().getY(), dist.toString(), 15);
	    	temp.drawOn(d, Color.RED);
	    	d.setColor(Color.BLACK);
	    	d.drawText((int) temp.getX(), (int) temp.getY() - 3, i.toString() , 15);
	    	i++;
	    }
	    p.drawOn(d, Color.GREEN);
        gui.show(d);
	}

	public void testDistance() {
	    GUI gui = new GUI("PointByDistanceCompartor", 500, 500);
        DrawSurface d = gui.getDrawSurface();
	    Point p1 = new Point(35, 10);
	    Point p2 = new Point(250, 34);
	    Point p = new Point(250, 250);
	    Line line1 = new Line(p1, p);
	    Line line2 = new Line(p2, p);
	    line1.drawOn(d, Color.BLACK);
	    line2.drawOn(d, Color.BLACK);
	    Double dist1 = p1.distance(p);
	    Double dist2 = p2.distance(p);
	    d.drawText((int) line1.middle().getX(), (int) line1.middle().getY(), dist1.toString(), 15);
	    d.drawText((int) line2.middle().getX(), (int) line2.middle().getY(), dist2.toString(), 15);
	    d.drawText(10, 450, "green - Origin" , 10);
	    d.drawText(10, 470, "red - distant points" , 10);
	    p1.drawOn(d, Color.RED);
	    p2.drawOn(d, Color.RED);
	    p.drawOn(d, Color.GREEN);
        gui.show(d);
	}

    public static void main(String[] args) {
		GeometryTest test = new GeometryTest();
		//test.testMyLines();
		test.testClosetintersectionToStartOfLine();
		//test.testDistance();
		//test.testPointByDistanceCompartor();
		//test.testUpperBlock();
	}
}
