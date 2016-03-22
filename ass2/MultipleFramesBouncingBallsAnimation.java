package ass2;

import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class MultipleFramesBouncingBallsAnimation {

	public static void main(String[] args) {
		System.out.println(args[0]);
		Sleeper sleeper = new Sleeper();
		int[] sizes = Sort.bubbleSort(args);
		Random rand = new Random();
		Ball[] balls = new Ball[args.length];
		GUI gui = new GUI("Multple frames", 850, 850);
		for(int i = 0; i < args.length/2; i++){
            balls[i]= new Ball(51 + rand.nextInt(450),51 +rand.nextInt(450),sizes[i]
            		           ,Color.BLACK, new Point(50,50), new Point(500,500));
			balls[i].setVelocity(args.length - i + 1, args.length - i +1);
		}
		for(int i = args.length/2 ; i < args.length; i++){
            balls[i]= new Ball(451 + rand.nextInt(150), 451 + rand.nextInt(150), sizes[i] ,
            		           Color.BLACK, new Point(450,450), new Point(600,600));
			balls[i].setVelocity(args.length - i + 1, args.length - i + 1);
		}
		
		while (true) {
			DrawSurface d = gui.getDrawSurface();
			d.setColor(Color.GRAY);
			d.drawRectangle(50, 50, 450, 450);
			d.setColor(Color.YELLOW);
			d.drawRectangle(450, 450, 150, 150);
			for(int i = 0; i < args.length; i++){
			   balls[i].moveOneStep();
			   balls[i].drawOn(d);
			}
		    gui.show(d);
		    sleeper.sleepFor(50);  // wait for 50 milliseconds.
		    }
	}

}
