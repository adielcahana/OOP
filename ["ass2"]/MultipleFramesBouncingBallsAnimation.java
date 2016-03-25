package ass2;

import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class MultipleFramesBouncingBallsAnimation {

	public static void main(String[] args) {
		String[] sizes = new String[args.length / 2];
		for (int i = 0; i < sizes.length / 2; i++) {
			sizes[i] = args[i];
		}
		Ball[] balls1 = MultipleBouncingBallsAnimation.getBallsArray(sizes, new Point(50, 50), new Point(500, 500));
		sizes = new String[args.length - args.length / 2];
		for (int i = args.length / 2 + 1; i < args.length; i++) {
			sizes[i] = args[i];
		}
		Ball[] balls2 = MultipleBouncingBallsAnimation.getBallsArray(sizes, new Point(450, 450), new Point(600, 600));
		Sleeper sleeper = new Sleeper();
		Random rand = new Random();
		GUI gui = new GUI("Multiple frames", 610, 610);
		while (true) {
			DrawSurface d = gui.getDrawSurface();
			d.setColor(Color.GRAY);
			d.drawRectangle(50, 50, 450, 450);
			d.setColor(Color.YELLOW);
			d.drawRectangle(450, 450, 150, 150);
			MultipleBouncingBallsAnimation.drawBallsArray(balls1, d);
			MultipleBouncingBallsAnimation.drawBallsArray(balls2, d);
		    gui.show(d);
		    sleeper.sleepFor(50);  // wait for 50 milliseconds.
		    }
	}
}

