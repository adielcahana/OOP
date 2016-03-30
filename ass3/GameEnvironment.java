import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class GameEnvironment {
    private ArrayList collidables;
    private Point lowerFrameEdge;
    private Point upperFrameEdge;
    public DrawSurface d;

    public GameEnvironment(Point lowerFrameEdge, Point upperFrameEdge) {
        this.collidables = new ArrayList();
        this.lowerFrameEdge = lowerFrameEdge;
        this.upperFrameEdge = upperFrameEdge;
        d = null;
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public static int min(ArrayList numbers) {
        double min = (double) numbers.get(0);
        int index = 0;
        int i = 1;
        while (i < numbers.size()) {
            if (min > (double) numbers.get(i)) {
                min =  (double) numbers.get(i);
                index = i;
            }
            i++;
        }
        return index;
    }

    public Line getTrajectory(Point start, Velocity v) {
        double width = this.lowerFrameEdge.getX() - this.upperFrameEdge.getX();
        double height = this.lowerFrameEdge.getY() - this.upperFrameEdge.getY();
        double dx = v.getDx();
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
        ArrayList blocks = new ArrayList();
        while (i < this.collidables.size()) {
            Collidable c = (Collidable) this.collidables.get(i);
            Rectangle rect = c.getCollisionRectangle();
            Point temp = trajectory.closestIntersectionToStartOfLine(rect);
            if (temp != null) {
            	blocks.add(c);
                collisionsPoints.add(temp);
                distances.add(temp.distance(trajectory.start()));
            }
            i++;
        }
        return new CollisionInfo((Point) collisionsPoints.get(min(distances)), (Collidable) blocks.get(min(distances)));
    }

    public static void main(String[] args) {
        //create an array for half of the sizes
    	Random rand = new Random();
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("GAME", 600, 600);
        Block upFrame = new Block(new Point(0, 0), 600, 10, 0);
        Block lowFrame = new Block(new Point(0, 590), 600, 10, 0);
        Block rFrame = new Block(new Point(0, 0), 10, 600, 0);
        Block lFrame = new Block(new Point(590, 0), 10, 600, 0);
        GameEnvironment enviroment = new GameEnvironment(new Point(600, 600) , new Point(0, 0));
        for (int i = 0; i< 20 ; i++)
        {
        	enviroment.addCollidable(new Block(new Point(rand.nextInt(600) + 1, rand.nextInt(600) +1), 60, 20, 3));
        }
        enviroment.addCollidable(lowFrame);
        enviroment.addCollidable(upFrame);
        enviroment.addCollidable(rFrame);
        enviroment.addCollidable(lFrame);
        Ball ball = new Ball(new Point(300, 300), 5 , Color.RED, new Point(600, 600), new Point(0, 0), enviroment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360) +1, 10));
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            enviroment.d = surface;
            ball.drawOn(surface);
            ball.moveOneStep();
            for (int i = 0; i < enviroment.collidables.size() ; i++)
            {
            	Block b = (Block) enviroment.collidables.get(i);
            	b.drawOn(surface, Color.YELLOW);
            }
            gui.show(surface);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            }
    }
}