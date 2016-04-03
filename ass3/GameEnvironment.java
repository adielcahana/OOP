import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biuoop.DrawSurface;

public class GameEnvironment {
    private List collidables;
    private Point lowerFrameEdge;
    private Point upperFrameEdge;
    private DrawSurface surface;

    public GameEnvironment(Point lowerFrameEdge, Point upperFrameEdge) {
        this.collidables = new ArrayList();
        this.lowerFrameEdge = lowerFrameEdge;
        this.upperFrameEdge = upperFrameEdge;
        this.setSurface(null);
    }
    public Point getLowerFrameEdge() {
		return lowerFrameEdge;
	}
	public Point getUpperFrameEdge() {
		return upperFrameEdge;
	}
	// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    public Collidable getCollidable(int index) {
        if (this.collidables.size() > index) {
            return (Collidable) this.collidables.get(index);
        }
    	return null;
    }
    
    public void removeCollidable(int index) {
        this.collidables.remove(index);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        int i = 0;
        List collisionsPoints = new ArrayList();
        List sortedCollisionsPoints = new ArrayList();
        List blocks = new ArrayList();
        while (i < this.collidables.size()) {
            Collidable c = (Collidable) this.collidables.get(i);
            Rectangle rect = c.getCollisionRectangle();
            Point temp = trajectory.closestIntersectionToStartOfLine(rect);
            if (temp != null) {
            	blocks.add(c);
                collisionsPoints.add(temp);
            }
            i++;
        }
        if (collisionsPoints.isEmpty() || blocks.isEmpty()) {
        	System.out.println("Error: no CollisionInfo");
        	return null;
        }
        sortedCollisionsPoints.addAll(collisionsPoints);
        Collections.sort(sortedCollisionsPoints, new PointByDistanceComparator(trajectory.start()));
        i = collisionsPoints.indexOf(sortedCollisionsPoints.get(0));
        return new CollisionInfo((Point) collisionsPoints.get(i), (Collidable) blocks.get(i));
    }
	public DrawSurface getSurface() {
		return surface;
	}
	public void setSurface(DrawSurface surface) {
		this.surface = surface;
	}
}