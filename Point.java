package ass2;

public class Point {

	private double x;
	private double y;

	public Point(double x, double y) {
		
		this.x = x;
		this.y = y;
    }
	
	public double distance(Point other) {
		if (other == null){
			return 0;
		}
        return Math.sqrt(((this.x - other.x) *(this.x - other.x)) + ((this.y - other.y)*(this.y - other.y)));
	}
	
	public boolean equals(Point other) { 
		if (other == null){
			return false;
		}
		double distance = distance(other);
		if (distance == 0){
		return true;
		}
		else{
			return false;
		}
		
	}
	
	 public double getX() {
	      return this.x;
	   }
	 public double getY() {
	      return this.y;
	   }
}
