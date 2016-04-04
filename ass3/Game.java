import java.awt.Color;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class Game {
	private SpriteCollection sprites;
	private GameEnvironment environment;

	public Game(){
		this.sprites = new SpriteCollection();
		this.environment = new GameEnvironment(new Point(600, 600) , new Point(0, 0));
	}

	public void addCollidable(Collidable c) {
		this.environment.addCollidable(c);
	}

	public void addSprite(Sprite s) {
		this.sprites.addSprite(s);
	}
	
	public void removeSprite(Sprite s){
		this.sprites.removeSprite(s);
	}

	// Initialize a new game: create the Blocks and Ball (and Paddle) 
	// and add them to the game.
	public void initialize() {
		Random rand = new Random();   
		//Create the ball.
		Ball ball = new Ball(new Point(300, 300), 5 , Color.RED, this.environment);
		ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 8));
		ball.addToGame(this);
		//Create the paddle.
		biuoop.GUI gui = new biuoop.GUI("title", 800, 600);
		biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
		Paddle paddle = new Paddle(new Point(350,560), 100, 20, Color.BLACK, keyboard);
		paddle.addToGame(this);
		//Create the frame & blocks.
		this.createFrame();
		Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.PINK};
		Point start = new Point(230, 150);
		BlockFactory blockFactory = new BlockFactory(new Point(800, 600) , new Point(0, 0));
		Velocity velocity = new Velocity(50, 20);
		for (int i = 0; i < 6; i++) {
        	List blockList = null;
        	if (i == 0) {
        	    blockList = blockFactory.createBlockRaw(start, 2, colors[i % 4]);
        	} else {
        	    blockList = blockFactory.createBlockRaw(start, 1, colors[i % 4]);
        	}
        	for (int j = 0; j < blockList.size(); j++) {
        		((Block) blockList.get(j)).addToGame(this);
        	}
        	start = velocity.applyToPoint(start);
		}
	}
	
	// Run the game -- start the animation loop.
	public void run() {
		//...
		Sleeper sleeper = new Sleeper();
		int framesPerSecond = 60;
		int millisecondsPerFrame = 1000 / framesPerSecond;
		GUI gui = new GUI("title", 800, 600);
		while (true) {
			long startTime = System.currentTimeMillis(); // timing
			DrawSurface d = gui.getDrawSurface();
			this.environment.setSurface(d);
			Paddle paddle = (Paddle) this.environment.getCollidable(0);
            for (int i = 1; environment.getCollidable(i) != null; i++) {
            	Block b = (Block) this.environment.getCollidable(i);
//            	b.drawOn(surface);
            	if (b.getMaxHits() == 0) {
            		environment.removeCollidable(i);
            		sprites.removeSprite(b);
            		i--;
            	} 
            }
			this.sprites.drawAllOn(d);
			this.sprites.notifyAllTimePassed();
			gui.show(d);
			

			// timing
			long usedTime = System.currentTimeMillis() - startTime;
			long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
			if (milliSecondLeftToSleep > 0) {
				sleeper.sleepFor(milliSecondLeftToSleep);
			}
		}
	}

	public void createFrame() {
		Block upFrame = new Block(new Point(0, 0), 800, 20, -1, Color.GRAY);
        Block lowFrame = new Block(new Point(0, 580), 800, 20, -1, Color.GRAY);
        Block lFrame = new Block(new Point(0, 20), 20, 580, -1, Color.GRAY);
        Block rFrame = new Block(new Point(780, 20), 20, 580, -1, Color.GRAY);
        upFrame.addToGame(this);
        lowFrame.addToGame(this);
        lFrame.addToGame(this);
        rFrame.addToGame(this);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.initialize();
		game.run();
	}
}