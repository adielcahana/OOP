package ass2;

import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
* @author Ori Engelberg <turht50@gmail.com>
* @version 1.0
* @since 2016-03-24 */
public class MultipleBouncingBallsAnimation {

    public static int[] stringsToInts(String[] numbers) {
        int size = numbers.length;
        int[] intNums = new int[size];
        int i = 0;
        while (i < size) {
            intNums[i] = Integer.parseInt(numbers[i]);
            i++;
        }
        return intNums;
    }

    public static Ball [] getBallsArray(String[] args, Point upperEdge, Point lowerEdge) {
        Random rand = new Random();
        int i;
        int[] sizes = stringsToInts(args);
        Ball [] balls = new Ball[sizes.length];
        for (i = 0; i < sizes.length; i++) {
            balls[i] = new Ball(new Point(rand.nextInt(200), rand.nextInt(200)),
            sizes[i], java.awt.Color.BLACK, lowerEdge);
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextDouble(), setSpeedBySize(sizes[i]));
            balls[i].setVelocity(v);
            }
        return balls;
        }

    public static double setSpeedBySize(int size) {
    	if (size > 50){
    		size = 50;
    	}
    	return 15 - size /4;
    }

    public static void drawBallArray(Ball [] balls, DrawSurface surface){
        for (int i = 0; i < balls.length; i++) {
          	balls[i].moveOneStep();
            balls[i].drawOn(surface);
            }
        }
    
    public static void main(String[] args) {
        GUI gui = new GUI("title", 400, 400);
        Sleeper sleeper = new Sleeper();
        Point upperEdge = new Point(0,0);
        Point lowerEdge = new Point (400, 400);
        Ball[] balls = getBallsArray(args, upperEdge, lowerEdge);
    	while (true) {
        DrawSurface surface = gui.getDrawSurface();
    	drawBallArray(balls, surface);
        gui.show(surface);
        sleeper.sleepFor(50);  // wait for 50 milliseconds.
    	}
    }
}