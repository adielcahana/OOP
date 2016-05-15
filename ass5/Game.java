import java.awt.Color;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-03 */
public class Game implements Animation{
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter ballsCounter;
    private Counter blocksCounter;
    private Counter scoreCounter;
    private Counter numberOfLives;
    private boolean running;
    
    /**
     * Contractor - Create a list of sprites a new environment and a gui for the game. */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new Point(600, 600) , new Point(0, 0));
        this.gui = new biuoop.GUI("title", 800, 600);
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.scoreCounter = new Counter();
        this.numberOfLives = new Counter();
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * Add the collidable object to the collidable list.
     * <p>
     * @param c - the collidable object to add. */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add the sprite object to the sprites list.
     * <p>
     * @param s - the collidable object to add. */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize the game.
     * Create the variables for the games. */
    public void initialize() {
        this.numberOfLives.increase(4);
        LivesIndicator lives = new LivesIndicator(new Point(0, 0), 800, 20, this.numberOfLives);
        ScoreIndicator score = new ScoreIndicator(new Point(0, 0), 800, 20, this.scoreCounter, this.numberOfLives);
        lives.addToGame(this);
        score.addToGame(this);
        // Create the frame & blocks.
        this.createFrame();
        // The colors for the blocks.
        Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.PINK, Color.GREEN, Color.MAGENTA};
        Point start = new Point(230, 150);
        // Create all the blocks and add them to the game.
        BlockFactory blockFactory = new BlockFactory(new Point(800, 600) , new Point(0, 0));
        Velocity velocity = new Velocity(50, 20);
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        HitListener scoreListener = new ScoreTrackingListener(this.scoreCounter);
        for (int i = 0; i < 6; i++) {
            List blockList = null;
            // Create the first line of the blocks.
            if (i == 0) {
                blockList = blockFactory.createBlockRaw(start, 2, colors[i], this.blocksCounter);
            } else {
                // Create the other block lines.
                blockList = blockFactory.createBlockRaw(start, 1, colors[i], this.blocksCounter);
            }
            // Add all the blocks to the games.
            for (int j = 0; j < blockList.size(); j++) {
                Block temp = ((Block) blockList.get(j));
                temp.addHitListener(blockRemover);
                temp.addHitListener(scoreListener);
                temp.addToGame(this);
            }
            start = velocity.applyToPoint(start);
        }
    }

    public void run(){
        while(this.numberOfLives.getValue() > 0 && this.blocksCounter.getValue() != 0){
            this.playOneTurn();
        }
            this.gui.close();
            return;
    }
    
    
    /**
     * Run the game.
     * Create the variables for the games. */
    public void playOneTurn() {
        
        // Create 2 balls and add them to the game.
        Random rand = new Random();
        Ball ball1 = new Ball(new Point(400, 400), 5 , Color.RED, this.environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 7));
        ball1.addToGame(this);
        Ball ball2 = new Ball(new Point(200, 200), 5 , Color.BLUE, this.environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 7));
        ball2.addToGame(this);
        this.ballsCounter.increase(2);
        // Create the paddle and add it to the game.
        Paddle paddle = new Paddle(new Rectangle(new Point(390, 560), 80, 20), Color.BLACK, 5, this.keyboard, 20, 780);
        paddle.addToGame(this);
        this.running = true;
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); 
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        paddle.removeFromGame(this);
    }

    /**
     * Create the frame.
     * Create 4 blocks for the frame and add them to the game. */
    public void createFrame() {
        Block upFrame = new Block(new Point(0, 20), 800, 20, -1, Color.GRAY);
        Block lowFrame = new Block(new Point(0, 600), 800, 20, -1, Color.GRAY);
        lowFrame.addHitListener(new BallRemover(this, this.ballsCounter));
        Block lFrame = new Block(new Point(0, 40), 20, 580, -1, Color.GRAY);
        Block rFrame = new Block(new Point(780, 40), 20, 580, -1, Color.GRAY);
        lFrame.addToGame(this);
        rFrame.addToGame(this);
        upFrame.addToGame(this);
        lowFrame.addToGame(this);
    }
    
    public boolean shouldStop() {
        return !this.running; 
    }
    
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        this.environment.setSurface(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.ballsCounter.getValue() == 0) {
            this.numberOfLives.decrease(1);
            this.running = false;
        } else if (this.blocksCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.sprites.drawAllOn(d);
            this.running = false;
        }
       // the logic from the previous playOneTurn method goes here.
       // the `return` or `break` statements should be replaced with
       // this.running = false;
    }
}