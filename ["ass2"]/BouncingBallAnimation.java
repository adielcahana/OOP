package ass2;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class BouncingBallAnimation {
    public static void main(String[] args) {
		GUI gui = new GUI("title",400,400);
	    Sleeper sleeper = new Sleeper();
	    Ball ball = new Ball(50, 50, 30, java.awt.Color.BLACK, new Point(200, 200));
	    Velocity v = Velocity.fromAngleAndSpeed(10, 10);
	    ball.setVelocity(v);
	    while (true) {
	       ball.moveOneStep();
	       DrawSurface d = gui.getDrawSurface();
	       ball.drawOn(d);
	       gui.show(d);
	       sleeper.sleepFor(50);  // wait for 50 milliseconds.
	    }
	   }
}
