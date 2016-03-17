package ass2;
import biuoop.GUI; 
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstraectArtDrawing {
	
	static void drawLine(Line l, DrawSurface d){
		Point start = l.start();
		Point end = l.end();
		d.setColor(Color.BLACK);
		d.drawLine((int) start.getX(),(int) start.getY(),(int) end.getX(),(int) end.getY());
	}
	
	static void drawPoint(Point p, DrawSurface d){
		d.setColor(Color.BLUE);
		d.fillCircle((int)p.getX(), (int)p.getY(), 3);
	}
	
	static Line generateRandomLine(){
		Point start = generateRandomPoint();
		Point end = generateRandomPoint();
		Line randline = new Line(start,end);
		return randline;
	}
	
	static Point generateRandomPoint(){
		Random rand = new Random();
		double x = rand.nextInt((int)width) +1;
		double y = rand.nextInt((int)height) +1;
		Point randPoint = new Point(x,y);
		return randPoint;
	}
	
	static Line[] lineList(int numOfLines){
		Line[] listOfLines = new Line[numOfLines];
		for (int i=0; i < numOfLines; i++){
			listOfLines[i] = generateRandomLine();
		}
		return listOfLines;
	}    
	
	public void DrawGraph(int numOfLines, int width, int height){
		Line[] listOfLines = lineList(numOfLines);
		Point intersectPoint = null;
		GUI gui = new GUI("Random Abstraect Art", width, height);
	    DrawSurface d = gui.getDrawSurface();
	    for (int i=0; i < numOfLines ; i++){
	    	drawLine(listOfLines[i], d);
	    	drawPoint(listOfLines[i].middle(), d);
	    }
	    	for (int j=i+1; j < NUM_OF_LINES; j++){
	    		 if (listOfLines[i].isIntersecting(listOfLines[j]) == true){
	    			drawPoint(listOfLines[i].intersectionWith(listOfLines[j]), d, "RED");
	    		}
	    gui.show(d);
	}
	
	public static void main(String[] args) {
		AbstraectArtDrawing art = new AbstraectArtDrawing();
		art.DrawGraph(10, 500, 500);
		}
}