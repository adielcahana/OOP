package animations;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Collidable;
import gameobjects.Enemy;
import gameobjects.LevelName;
import gameobjects.LivesIndicator;
import gameobjects.Spaceship;
import gameobjects.ScoreIndicator;
import gameobjects.Sprite;
import gameobjects.SpriteCollection;
import gameobjects.Velocity;
import general.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import levelcreator.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import listeners.SpaceshipRemover;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.2
 * @since 2016-04-03 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter blocksCounter;
    private Counter scoreCounter;
    private Counter numberOfLives;
    private Counter numberOfSpaceships;
    private boolean running;
    private LevelInformation level;

    /**
     * Constructor - Create a list of sprites a new environment and a gui for the game.
     * @param animationRunner - the AnimationRunner of the game.
     * @param keyboard - the KeyboardSensor of the game.
     * @param gui - the gui of the game.
     * @param numberOfLives - Counter with the lives that left for the game.
     * @param scoreCounter - Counter with the score of the player.
     * @param level - the level that played. */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner animationRunner,
            GUI gui, Counter scoreCounter, Counter numberOfLives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new Point(800, 600) , new Point(0, 0));
        this.blocksCounter = new Counter();
        this.scoreCounter = scoreCounter;
        this.numberOfLives = numberOfLives;
        this.numberOfSpaceships = new Counter(numberOfLives.getValue());
        this.runner = animationRunner;
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

    /**
     * remove the collidable object from the sprites list.
     * <p>
     * @param c - the collidable object to remove. */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add the sprite object to the sprites list.
     * <p>
     * @param s - the sprite object to add. */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove the sprite object from the sprites list.
     * <p>
     * @param s - the sprite object to remove. */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize the level.
     * Create the sprites for the current level. */
    public void initialize() {
        // Create the HitListener for blocks and score.
        HitListener blockRemover = new BlockRemover(this, this.blocksCounter);
        HitListener scoreListener = new ScoreTrackingListener(this.scoreCounter);
        HitListener ballRemover = new BallRemover(this);
        // Add the background of the level.
        level.getBackground().addToGame(this);
        // Create the borders.
        this.createBorder(ballRemover);
        // Create the sprites that show the score lives and the level name.
        ScoreIndicator score = new ScoreIndicator(this.scoreCounter);
        score.addToGame(this);
        LivesIndicator lives = new LivesIndicator(this.numberOfLives);
        lives.addToGame(this);
        LevelName name = new LevelName(this.level.levelName());
        name.addToGame(this);
        InputStream is1 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 1.png");
        InputStream is2 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 2.png");
        try {
            BufferedImage image1 = ImageIO.read(is1);
            BufferedImage image2 = ImageIO.read(is2);
            Enemy enemy = new Enemy(new Rectangle(new Point(50,80), 40, 30), this, image1, image2, this.blocksCounter, Velocity.fromAngleAndSpeed(90, 40));
            enemy.addHitListener(ballRemover);
            enemy.addToGame(this);
        } catch (IOException e) {
        }
        // Get list of blocks from the level information and add them to the game.
        List<Block> blockList = new ArrayList<Block>(this.level.blocks());
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(ballRemover);
            block.addHitListener(scoreListener);
        }
        // Add the number of blocks to the block counter.
        this.blocksCounter.increase(level.numberOfBlocksToRemove());
    }

    /**
     * Run one turn (one live) of the game.
     * Create the balls and the paddle and run the level. */
    public void playOneTurn() {
        //createTheBallsOnTheTopOfThePaddle();
        // Add the number of balls to the balls counter.
        //this.ballsCounter.increase(level.numberOfBalls());
        // Create the paddle and add it to the game.
        Point paddlePoint = new Point(400 - (level.paddleWidth() / 2), 580);
        Spaceship paddle = new Spaceship(new Rectangle(paddlePoint, level.paddleWidth(), 20),
                Color.YELLOW, level.paddleSpeed(), this.keyboard, 25, 775, this.environment, this);
        HitListener ballRemover = new BallRemover(this);
        HitListener spaceshipRemover = new SpaceshipRemover(this, this.numberOfSpaceships);
        paddle.addHitListener(ballRemover);
        paddle.addHitListener(spaceshipRemover);
        paddle.addToGame(this);
        this.running = true;
        // Countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        // Use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
        // remove the paddle to create new paddle in the middle.
        paddle.removeFromGame(this);
    }

    /**
     * Create the balls on the top of the paddle.
     * Create balls and give any ball a velocity from the list of velocities. */
    private void createTheBallsOnTheTopOfThePaddle() {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 575, 5, Color.WHITE);
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * Create the border.
     * Create 4 blocks for the border and add them to the game. */
    public void createBorder(HitListener ballRemover) {
        TreeMap<Integer, Color> fillColor = new TreeMap<Integer, Color>();
        TreeMap<Integer, BufferedImage> fillImage = new TreeMap<Integer, BufferedImage>();
        fillColor.put(1, Color.GRAY);
        Block upFrame = new Block(new Rectangle(new Point(0, 20), 800, 25), -1, Color.BLACK, fillColor, fillImage);
        Block lowFrame = new Block(new Rectangle(new Point(0, 600), 800, 25), -1, Color.BLACK, fillColor, fillImage);
        Block lFrame = new Block(new Rectangle(new Point(0, 40), 25, 575), -1, Color.BLACK, fillColor, fillImage);
        Block rFrame = new Block(new Rectangle(new Point(775, 40), 25, 575), -1, Color.BLACK, fillColor, fillImage);
        upFrame.addHitListener(ballRemover);
        lowFrame.addHitListener(ballRemover);
        lFrame.addHitListener(ballRemover);
        rFrame.addHitListener(ballRemover);
        lFrame.addToGame(this);
        rFrame.addToGame(this);
        upFrame.addToGame(this);
        lowFrame.addToGame(this);
    }

    /** returns information about the continuation of the animation.
     * <p>
     * @return stop - if the animation should stop' returns true. */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one step of the game ( draw all the sprites and notify that time past).
     * <p>
     * @param d - the given surface.
     * @param dt - @param dt - the speed per frame. */
    public void doOneFrame(DrawSurface d, double dt) {
        // Pause the game.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        // Draw all the sprites.
        this.environment.setSurface(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        // If the balls over lose one life
        if (this.blocksCounter.getValue() == 0) {
            // Else if win the level increase 100 points.
            this.scoreCounter.increase(100);
            this.running = false;
        } else if (this.numberOfLives.getValue() != this.numberOfSpaceships.getValue()) {
            this.numberOfLives.decrease(1);
            this.running = false;
        }
    }

    /**
     * Check if left blocks in the level.
     * <p>
     * @return true if left blocks else return false. */
    public boolean haveBlocks() {
        if (blocksCounter.getValue() > 0) {
            return true;
        }
        return false;
    }
}