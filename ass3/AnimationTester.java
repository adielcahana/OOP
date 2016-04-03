
import java.awt.Color;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class AnimationTester {
	public static void main(String[] args) {
    	Random rand = new Random();
    	Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.PINK};
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("GAME", 800, 600);
        Block upFrame = new Block(new Point(0, 0), 800, 20, -1, Color.GRAY);
        Block lowFrame = new Block(new Point(0, 580), 800, 20, -1, Color.GRAY);
        Block lFrame = new Block(new Point(0, 20), 20, 580, -1, Color.GRAY);
        Block rFrame = new Block(new Point(780, 20), 20, 580, -1, Color.GRAY);
        GameEnvironment enviroment = new GameEnvironment(new Point(800, 600) , new Point(0, 0));
        BlockFactory blockFactory = new BlockFactory(new Point(800, 600) , new Point(0, 0));
        Point start = new Point(230, 150);
        Velocity velocity = new Velocity(50, 20);
        for (int i = 0; i < 6; i++) {
        	List blockList = null;
        	if (i == 0) {
        	    blockList = blockFactory.createBlockRaw(start, 2, colors[i % 4]);
        	} else {
        	    blockList = blockFactory.createBlockRaw(start, 1, colors[i % 4]);
        	}
        	for (int j = 0; j < blockList.size(); j++) {
        	    enviroment.addCollidable((Collidable) blockList.get(j));
        	}
        	start = velocity.applyToPoint(start);
        }
        enviroment.addCollidable(lowFrame);
        enviroment.addCollidable(upFrame);
        enviroment.addCollidable(rFrame);
        enviroment.addCollidable(lFrame);
        Ball ball = new Ball(new Point(500, 450), 5 , Color.RED, enviroment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(48 , 15));
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            enviroment.setSurface(surface);
            ball.drawOn(surface);
            ball.moveOneStep();
            for (int i = 0; enviroment.getCollidable(i) != null; i++) {
            	Block b = (Block) enviroment.getCollidable(i);
//            	b.drawOn(surface);
            	if (b.getMaxHits() == 0) {
            		enviroment.removeCollidable(i);
            		i--;
            	} else {
                    b.drawOn(surface);
            	}
            }
            gui.show(surface);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            }
    }
//	public static void main(String[] args) {
//        //create an array for half of the sizes
//    	Random rand = new Random();
//        Sleeper sleeper = new Sleeper();
//        GUI gui = new GUI("GAME", 600, 600);
//        Block upFrame = new Block(new Point(0, 0), 600, 10, -1);
//        Block lowFrame = new Block(new Point(0, 590), 600, 10, -1);
//        Block lFrame = new Block(new Point(0, 10), 10, 580, -1);
//        Block rFrame = new Block(new Point(590, 10), 10, 580, -1);
//        GameEnvironment enviroment = new GameEnvironment(new Point(600, 600) , new Point(0, 0));
//        for (int i = 0; i < 20; i++) {
//        	enviroment.addCollidable(new Block(new Point(rand.nextInt(600) + 1, rand.nextInt(600) + 1), 60, 20, 3));
//        }
//        enviroment.addCollidable(lowFrame);
//        enviroment.addCollidable(upFrame);
//        enviroment.addCollidable(rFrame);
//        enviroment.addCollidable(lFrame);
//        Ball ball = new Ball(new Point(300, 300), 5 , Color.RED, enviroment);
//        ball.setVelocity(Velocity.fromAngleAndSpeed(26, 15));
//        while (true) {
//            Line trajectory = enviroment.getTrajectory(ball.getCenter(), ball.getVelocity());
//            Line trajUpdate = new Line(ball.getCenter(), trajectory.end());
//            CollisionInfo info = enviroment.getClosestCollision(trajectory);
//         	   //if a collision will occcure in the next step, change the ball velocity
//                while (info.collisionPoint().distance(ball.getCenter()) - ball.getVelocity().getSpeed() > ball.getSize()) {
//                	DrawSurface surface = gui.getDrawSurface();
//                	enviroment.setSurface(surface);
//                	int i = 0;
//                    while (enviroment.getCollidable(i) != null) {
//                    	Block b = (Block) enviroment.getCollidable(i);
//                    	b.drawOn(surface, Color.YELLOW);
//                        i++;
//                    }
//                	if (info != null) {
//                	   System.out.println("info isn't null");
//                  	   info.collisionPoint().drawOn(surface, Color.RED);
//                	}
//                	ball.moveOneStep();
//                	trajUpdate = new Line(ball.getCenter(), trajectory.end());
//                	ball.drawOn(surface);
//                	trajUpdate.drawOn(surface, Color.BLACK);
//                    gui.show(surface);
//                    sleeper.sleepFor(50);  // wait for 50 milliseconds.
//                }
//                ball.setVelocity(info.collisionObject().hit(info.collisionPoint(), ball.getVelocity()));
//         }
//    }
}
