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

    /**
     * Cast a string type to integer type.
     * <p>
     * Gets a string array and returns its elements as integer array
     * <p>
     * @param numbers - the string of numbers.
     * @return array of integer. */
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

    /**
     * cast a string type to integer type.
     * <p>
     * gets a string array and returns its elements as integer array
     * <p>
     * @param numbers - the string of numbers.
     * @param upperEdge - the upper left point of the frame.
     * @param lowerEdge - the lower right point of the frame.
     * @return array of Balls. */
    public static Ball [] getBallsArray(String[] numbers, Point upperEdge, Point lowerEdge) {
        Random rand = new Random();
        // Cast the string numbers to integers.
        int[] sizes = stringsToInts(numbers);
        Ball [] balls = new Ball[sizes.length];
        // The frame size.
        int dx = (int) lowerEdge.getX() - (int) upperEdge.getX();
        int dy = (int) lowerEdge.getY() - (int) upperEdge.getY();
        // Create an balls array by the size.
        for (int i = 0; i < sizes.length; i++) {
            Point center = new Point(upperEdge.getX() + 1 + rand.nextInt(dx), upperEdge.getY() + 1 + rand.nextInt(dy));
            balls[i] = new Ball(center, sizes[i], java.awt.Color.BLACK, lowerEdge, upperEdge);
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextDouble(), setSpeedBySize(sizes[i]));
            balls[i].setVelocity(v);
            }
        return balls;
        }

    /**
     * Give the ball speed that depend by his size.
     * <p>
     * As much the ball is bigger, then his speed smaller.
     * <p>
     * @param size - the ball radius.
     * @return the speed of the ball. */
    public static double setSpeedBySize(int size) {
        if (size > 50) {
            size = 50;
        }
        return 15 - size / 4;
    }

    /**
     * Get an array of balls and draw them.
     * <p>
     * gets a string array and returns its elements as integer array
     * <p>
     * @param balls - array of balls.
     * @param surface - the surface of the ball to draw. */
     public static void drawBallArray(Ball [] balls, DrawSurface surface) {
        for (int i = 0; i < balls.length; i++) {
            // Move the ball and draw it.
        	balls[i].moveOneStep();
            balls[i].drawOn(surface);
            }
        }

     /**
      * The main method of the class.
      * <p>
      * Get a string of numbers make it array of balls and draw it.
      * <p>
      * @param args - the string of numbers (the size of the balls).*/
    public static void main(String[] args) {
        GUI gui = new GUI("title", 400, 400);
        Sleeper sleeper = new Sleeper();
        // Create the frame boundaries.
        Point upperEdge = new Point(0, 0);
        Point lowerEdge = new Point(400, 400);
        Ball[] balls = getBallsArray(args, upperEdge, lowerEdge);
        // Make the balls array move.
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            drawBallArray(balls, surface);
            gui.show(surface);
         // Wait for 50 milliseconds.
            sleeper.sleepFor(50);  
        }
    }
}