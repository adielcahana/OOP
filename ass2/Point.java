package ass2;
/**
* @author Ori Engelberg <turht50@gmail.com>
* @version 1.0
* @since 2016-03-02 */
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
        double x1, x2, y1, y2;
        x1 = this.getX();
        x2 = other.getX();
        y1 = this.getY();
        y2 = other.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public boolean equals(Point other) { 
    	if (other == null){
            return false;
        }
        double x1, x2, y1, y2;
        x1 = this.getX();
        x2 = other.getX();
        y1 = this.getY();
        y2 = other.getY();
        if (x1 == x2 && y1 == y2){
        return true;
        }
            return false;

    }

    public double getX() {
        return this.x;
        }
    public double getY() {
        return this.y;
        }
}
