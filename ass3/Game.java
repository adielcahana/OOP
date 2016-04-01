import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class Game {
   private SpriteCollection sprites;
   private GameEnvironment environment;
   
   public Game(){
       this.sprites = sprites;
	   this.environment = environment;
   }

   public void addCollidable(Collidable c) {
	   this.environment.addCollidable(c);
   }
   
   public void addSprite(Sprite s) {
	   this.sprites.addSprite(s);
   }

   // Initialize a new game: create the Blocks and Ball (and Paddle) 
   // and add them to the game.
   public void initialize() {
	   Random rand = new Random();   
	   Ball ball = new Ball(new Point(300, 300), 5 , Color.RED, new Point(600, 600), new Point(0, 0), this.environment);
	   ball.addToGame(this);
	   for (int i = 0; i < 20; i++) {
	      Block block = new Block(new Point(rand.nextInt(600) + 1, rand.nextInt(600) +1), 60, 20, 3);
	      block.addToGame(this);
	      }
   }
   // Run the game -- start the animation loop.
   public void run() {
	   //...
	   Sleeper sleeper = new Sleeper();
	   int framesPerSecond = 60;
	   int millisecondsPerFrame = 1000 / framesPerSecond;
	   GUI gui = new GUI("title", 600, 600);
	   while (true) {
	      long startTime = System.currentTimeMillis(); // timing

	      DrawSurface d = gui.getDrawSurface();
	      this.sprites.drawAllOn(d);
	      gui.show(d);
	      this.sprites.notifyAllTimePassed();

	      // timing
	      long usedTime = System.currentTimeMillis() - startTime;
	      long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
	      if (milliSecondLeftToSleep > 0) {
	          sleeper.sleepFor(milliSecondLeftToSleep);
	      }
	   }
   }
   
   public static void main(String[] args) {
	   Game game = new Game();
	   game.initialize();
	   game.run();
	}
   }