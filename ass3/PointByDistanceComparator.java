
import java.util.Comparator;

public class PointByDistanceComparator implements Comparator  {
    private Point origin;

    public PointByDistanceComparator(Point origin) {
		this.origin = origin;
	}
	@Override
	public int compare(Object obj1, Object obj2) {
        if (obj1 instanceof Point && obj2 instanceof Point) {
        	Point p1 = (Point) obj1;
        	Point p2 = (Point) obj2;

        	double dist1 = p1.distance(origin);
        	double dist2 = p2.distance(origin);
        	if (dist1 < dist2) {
                return -1;
            } else if (dist1 > dist2) {
                return 1;
            } else {
                return 0;
            }
        } else {
        	return -1;
        }
	}
}
