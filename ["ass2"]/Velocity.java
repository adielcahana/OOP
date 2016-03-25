package ass2;

public class Velocity {
    private double dx;
	private double dy;
	   // constructor
	   public Velocity(double dx, double dy) {
		   this.dx = dx;
		   this.dy = dy;
	   }

	   public double getDx(){
		   return this.dx;
	   }

	   public double getDy(){
		   return this.dy;
	   }

	   // Take a point with position (x,y) and return a new point
	   // with position (x + dx, y + dy)
	   public Point applyToPoint(Point p) {
		   return new Point(p.getX() + this.dx, p.getY() + this.dy);
	   }

	   public static Velocity fromAngleAndSpeed(double angle, double speed) {
		      double dx = speed * Math.cos(Math.toDegrees(angle));
		      double dy = speed * Math.sin(Math.toDegrees(angle));
		      return new Velocity(dx, dy);
		   }


}
