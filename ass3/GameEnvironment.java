import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class GameEnvironment {
    private ArrayList collidables;
    private Point lowerFrameEdge;
    private Point upperFrameEdge;

    public GameEnvironment(Point lowerFrameEdge, Point upperFrameEdge) {
        this.collidables = new ArrayList();
        this.lowerFrameEdge = lowerFrameEdge;
        this.upperFrameEdge = upperFrameEdge;
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public static int min(ArrayList numbers) {
        int min = (int) numbers.get(0);
        int index = 0;
        int i = 1;
        while (i < numbers.size()) {
            if (min > (int) numbers.get(i)) {
                min =  (int) numbers.get(i);
                index = i;
            }
            i++;
        }
        return index;
    }

    public Line getTrajectory(Point start, Velocity v) {
        double width = this.lowerFrameEdge.getX() - this.upperFrameEdge.getX();
        double height = this.lowerFrameEdge.getY() - this.upperFrameEdge.getY();
        double  dx = v.getDx();
        double dy = v.getDy();
        double diagonal = Math.sqrt((width * width) + (height * height)) + 1;
        double endX = start.getX() + diagonal * dx;
        double endY = start.getY() + diagonal * dy;
        return new Line(start, new Point(endX, endY));
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        int i = 0;
        ArrayList collisionsPoints = new ArrayList();
        ArrayList distances = new ArrayList();
        while (i < this.collidables.size()) {
            Collidable c = (Collidable) this.collidables.get(i);
            Rectangle rect = c.getCollisionRectangle();
            Point temp = trajectory.closestIntersectionToStartOfLine(rect);
            if (temp != null) {
                collisionsPoints.add(temp);
                distances.add(temp.distance(trajectory.start()));
            }
        }
        i = 0;
        while (i < this.collidables.size()) {
            Collidable c = (Collidable) this.collidables.get(i);
            if (c.getCollisionRectangle().pointPlace((Point) collisionsPoints.get(this.min(distances))) != 5) {
                break;
            }
            i++;
        }
        if (distances.size() == 0 || this.collidables.size() == 0) {
            return null;
        }
        return new CollisionInfo((Point) collisionsPoints.get(min(distances)), (Collidable) this.collidables.get(i));
    }

    public static void main(String[] args) {
        //create an array for half of the sizes
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("GAME", 600, 600);
        Block upFrame = new Block(new Point(0, 0), 600, 10, 0);
        Block lowFrame = new Block(new Point(0, 590), 600, 10, 0);
        Block rFrame = new Block(new Point(0, 0), 10, 600, 0);
        Block lFrame = new Block(new Point(590, 0), 10, 600, 0);
        GameEnvironment enviroment = new GameEnvironment(new Point(600, 600) , new Point(0, 0));
        enviroment.addCollidable(lowFrame);
        enviroment.addCollidable(upFrame);
        enviroment.addCollidable(rFrame);
        enviroment.addCollidable(lFrame);
        Ball ball = new Ball(new Point(300, 300), 10 , Color.RED, new Point(600, 600), new Point(0, 0), enviroment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(35, 7));
        while (true) {
            //DrawSurface surface = gui.getDrawSurface();
            //draw 2 frames
            DrawSurface surface = gui.getDrawSurface();
            ball.drawOn(surface);
            //ball.moveOneStep();
            upFrame.drawOn(surface, Color.BLACK);
            lowFrame.drawOn(surface, Color.BLACK);
            rFrame.drawOn(surface, Color.BLACK);
            lFrame.drawOn(surface, Color.BLACK);
            gui.show(surface);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            }
    }
}