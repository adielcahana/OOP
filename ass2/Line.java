package ass2;

public class Line {
    private Point start;
    private Point end;
    private double slope;
       // constructors
        public Line(Point start, Point end) {
           this.start = start;
           this.end = end;
           this.slope = slope();
           }
       public Line(double x1, double y1, double x2, double y2) {
		   this.start = new Point(x1,y1);
		   this.end = new Point(x2,y2);
		   
		   this.slope = slope();
	   }

	   // Return the length of the line
	   public double length() {
           double length = this.start.distance(end);
           return length;
	   }

	   // Returns the middle point of the line
	public Point middle() {
		double x1, x2 ,y1 ,y2;
		x1 = this.start.getX();
		x2 = this.end.getX();
		y1 = this.start.getY();
		y2 = this.end.getY();
		Point middle = new Point((x2+x1)/2,(y2+y1)/2);
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

	   public double slope(){
		   double dx, dy;
		   dx = this.start.getX() - this.end.getX();
		   dy = this.start.getY() - this.end.getY();
		   return (dy / dx);
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
			   double x, y, m1, m2, b1, b2;
			   m1 = this.getSlope();
			   m2 = other.getSlope();
			   b1 = this.start.getY() - m1 * this.start.getX();
			   b2 = other.start.getY() - m2 * other.start.getX();
			   
			   if (Double.isInfinite(m1)) {
					   x = this.start.getX();
					   y = m2 * x + b2;
					   if(!IsInTheDomain(y, this.start.getY(), this.end.getY())){
						   return null;
				   }
				}
			   else if (Double.isInfinite(m2)) {
					   x = other.start.getX();
					   y = m1 * x + b1;
					   if(!IsInTheDomain(y, other.start.getY(), other.end.getY())){
						   return null;
					   }
				}
			   else if(m1 == m2){
			      if ((this.start.equals(other.start)) || (this.start.equals(other.end))){
			    	  y = this.start.getY();
			    	  x = this.start.getX();
			      }
			      else if (this.end.equals(other.start) || (this.end.equals(other.end))){
			    	  y = this.end.getY();
			    	  x = this.end.getX();
			   }
			      else{
			    	  return null;
			      }
			   }
			   
			   else{
               x = (b2 - b1) / (m1 - m2);
               y = m1 * x + b1;
               if (!(x > this.start.getX() && x < this.end.getX() || x < this.start.getX() && x > this.end.getX())){
            	   return null;
			       }
               }
			Point intersection = new Point(x, y);
			return intersection;
	}

	   public boolean IsItEqual(double y, double y2) {
			if (y == y2){
				return true;
			}
			return false;
		}
	boolean IsInTheDomain(double x1, double x2Start, double x2End) {
		   if ((x1 > x2Start && x1 < x2End) || (x1 < x2Start && x1 > x2End)) {
			   return true;
		   }
			   return false;
	   }

	   // equals -- return true is the lines are equal, false otherwise
	   public boolean equals(Line other) {
		   if (other == null) {
			   return false;
		   }
		   if (this.start.equals(other.start) && this.end.equals(other.end)) {
			   return true;
		   }
		   return false;
	   }
	public double getSlope() {
		return slope;
	}
	}