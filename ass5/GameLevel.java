import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-03 */
public class GameLevel implements Animation{
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
    private LevelInformation level;

    /**
     * Contractor - Create a list of sprites a new environment and a gui for the game. 
     * @param animationRunner 
     * @param keyboard 
     * @param gui 
     * @param numberOfLives2 
     * @param scoreCounter */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner animationRunner, GUI gui, Counter scoreCounter, Counter numberOfLives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new Point(800, 600) , new Point(0, 0));
        this.gui = gui;
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.scoreCounter = scoreCounter;
        this.numberOfLives = numberOfLives;
        this.runner = animationRunner;
        //this.runner = new AnimationRunner(this.gui, 60);
        //this.keyboard = this.gui.getKeyboardSensor();
        this.keyboard = keyboard;
        this.level = level;
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
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        HitListener scoreListener = new ScoreTrackingListener(this.scoreCounter);
        // Create the frame & blocks.
        this.createFrame();
        ScoreIndicator score = new ScoreIndicator(this.scoreCounter);
        score.addToGame(this);
        LivesIndicator lives = new LivesIndicator(this.numberOfLives);
        lives.addToGame(this);
        LevelName name = new LevelName(this.level.levelName());
        name.addToGame(this);
        level.getBackground().addToGame(this);
        // The colors for the blocks.
        //Color[] colors = {Color.BLUE, Color.RED, Color.YELLOW, Color.PINK, Color.GREEN, Color.MAGENTA};
        Point start = new Point(230, 150);
        // Create all the blocks and add them to the game.
        //BlockFactory blockFactory = new BlockFactory(new Point(800, 600) , new Point(0, 0));
        Velocity velocity = new Velocity(50, 20);
        // Add all the blocks to the games.
        List<Block> blockList = new ArrayList<Block>(this.level.blocks());
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
        }
        this.blocksCounter.increase(level.numberOfBlocksToRemove());
        start = velocity.applyToPoint(start);
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
        List<Ball> BallList = new ArrayList<Ball>(this.level.Balls());
        for (Ball ball : BallList) {
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        this.ballsCounter.increase(level.numberOfBalls());
        // Create the paddle and add it to the game.
        Point paddlePoint = new Point(400 - (level.paddleWidth() / 2), 580);
        Paddle paddle = new Paddle(new Rectangle(paddlePoint, level.paddleWidth(), 20), Color.YELLOW, level.paddleSpeed(), this.keyboard, 20, 780);
        paddle.addToGame(this);
        this.running = true;
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.level)); 
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

    public boolean HaveBlocks(){
        if(blocksCounter.getValue() > 0){
            return true;
        }
        return false;
    }
}