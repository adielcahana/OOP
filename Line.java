package ass2;

public class Line {
    private Point start;
    private Point end;
    private Point middle;
       // constructors
        public Line(Point start, Point end) {
           this.start = start;
           this.end = end;
           this.middle = middle();
	   }
	   public Line(double x1, double y1, double x2, double y2) {
		   this.start.x = x1;
		   this.start.y = y1;
		   this.end.getX();
		   this.end.getY();
	   }

	   // Return the length of the line
	   public double length() {
           double length = this.start.distance(end);
           return length;
	   }

	   // Returns the middle point of the line
	public Point middle() {
           this.middle.x = end.x - start.x;
           this.middle.y = end.y - start.y;
		return middle;
	   }

	   // Returns the start point of the line
	   public Point start() { 
		   this.start.getX();
		   this.start.getY();
		   return start;
	   }

	   // Returns the end point of the line
	   public Point end() {
		   this.end.getX();
		   this.end.getY();
		   return end;
	   }

	   // Returns true if the lines intersect, false otherwise
	   public boolean isIntersecting(Line other) {
	   }

	   // Returns the intersection point if the lines intersect,
	   // and null otherwise.
	   public Point intersectionWith(Line other) {
	   }

	   // equals -- return true is the lines are equal, false otherwise
	   public boolean equals(Line other) { }

	}