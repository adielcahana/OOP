package ass2;

import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

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
	
	public static void main(String[] args){
		Random rand = new Random();
		Sleeper sleeper = new Sleeper();
		GUI gui = new GUI("title",400,400);
		int argsSize = args.length;
		int i;
		int[] sizes = stringsToInts(args);
		Ball balls[] = new Ball[argsSize];
		for (i = 0; i < args.length; i++){
		balls[i] = new Ball(new Point(rand.nextInt(200),rand.nextInt(200)), sizes[i], java.awt.Color.BLACK, new Point(200, 200));
		balls[i].setVelocity(argsSize - i +5,argsSize - i +5);
		}
		
		while (true) {
			{
		       DrawSurface d = gui.getDrawSurface();
		    for(i = 0; i < argsSize; i++){
		    	balls[i].moveOneStep();
		    	balls[i].drawOn(d);
		    }
		    gui.show(d);
	    	sleeper.sleepFor(50);  // wait for 50 milliseconds.
		}
		}
}}