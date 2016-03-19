package ass2;

public class Line {
    private Point start;
    private Point end;
    private Point middle;
       // constructors
        public Line(Point start, Point end) {
           this.start = start;
           this.end = end;
	   }
	   public Line(double x1, double y1, double x2, double y2) {
		   this.start = new Point(x1,y1);
		   this.end = new Point(x2,y2);
		   this.middle = new Point((x2+x1)/2,(y2+y1)/2);
	   }

	   // Return the length of the line
	   public double length() {
           double length = this.start.distance(end);
           return length;
	   }

	   // Returns the middle point of the line
	public Point middle() {
		return middle;
	   }

	   // Returns the start point of the line
	   public Point start() {
		   return start;
	   }

	   // Returns the end point of the line
	   public Point end() {
		   return end;
	   }

	   // Returns true if the lines intersect, false otherwise
	   public boolean isIntersecting(Line other) {
		   Point intersection = intersectionWith(other);
		   if (intersection != null){
			   return true;
		   }
		   else{
		   return false;
	       }
    }

	   // Returns the intersection point if the lines intersect,
	   // and null otherwise.
	   public Point intersectionWith(Line other) {
			   double m1, m2, X, Y;
			   m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
			   m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
			   X = (other.start.getY() - this.start.getY() - m2 * other.start.getX() + m1 * this.start.getX()) / (m1-m2);
			   Y = m1*X + this.start.getY()- m1 * this.start.getX();
			   Point intersection = new Point(X,Y);
		if (intersection.getX()< this.start.getX() && intersection.getX() > this.end.getX() || intersection.getX() > this.start.getX() && intersection.getX() < this.end.getX()){
			return intersection;
		}
		   else{
		   return null;
	   }
	}

	   // equals -- return true is the lines are equal, false otherwise
	   public boolean equals(Line other) {
		   return false;
	   }

	}