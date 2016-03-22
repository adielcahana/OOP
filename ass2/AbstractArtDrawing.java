package ass2;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {
    private int width;
    private int height;
    private int numOfLines;

	AbstractArtDrawing(int width, int height, int numOfLines) {
		this.width = width;
		this.height = height;
		this.numOfLines = numOfLines;
	}
	void drawLine(Line l, DrawSurface d) {
		Point start = l.start();
		Point end = l.end();
		d.setColor(Color.BLACK);
		d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
	}

	void drawPoint(Point p, DrawSurface d) {
		//d.setColor(Color.BLUE);
		d.fillCircle((int) p.getX(), (int) p.getY(), 3);
	}

    Line generateRandomLine() {
		Point start = generateRandomPoint();
		Point end = generateRandomPoint();
		Line randline = new Line(start, end);
		return randline;
	}

	Point generateRandomPoint() {
		Random rand = new Random();
		double x = rand.nextInt(this.width) + 1;
		double y = rand.nextInt(this.height) + 1;
		Point randPoint = new Point(x, y);
		return randPoint;
	}

	Line[] lineList() {
		Line[] listOfLines = new Line[this.numOfLines];
		Line newLine = null;
		int i = 0 ,j;
		while(i < this.numOfLines) {
			newLine = generateRandomLine();
			j=0;
			while (j <= i) {
				if (newLine.equals(listOfLines[i])) break;
				j++;
			}
			if (j > i){
				listOfLines[i] = newLine;
				i++;
			}
		}
		return listOfLines;
    }

	void drawGraph() {
		Line[] listOfLines = lineList();
		Point intersectPoint = null;
		GUI gui = new GUI("Random Abstraect Art", width, height);
	    DrawSurface d = gui.getDrawSurface();
	    for (int i = 0; i < this.numOfLines; i++) {
	    	drawLine(listOfLines[i], d);
	    	d.setColor(Color.BLUE);
	    	drawPoint(listOfLines[i].middle(), d);
	    	for (int j = i + 1; j < this.numOfLines; j++) {
	    		intersectPoint = listOfLines[i].intersectionWith(listOfLines[j]);
	    		 //if (listOfLines[i].isIntersecting(listOfLines[j]) == true){
	    		if (intersectPoint != null) {
	    			d.setColor(Color.RED);
	    			drawPoint(intersectPoint, d);
	    		}
	    	}
	    }
	    gui.show(d);
	}

	public static void main(String[] args) {
		//AbstractArtDrawing art = new AbstractArtDrawing(500, 500, 10);
		//art.drawGraph();
	      
		}
}