package gameobjects;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-02 */
public class Spaceship implements Sprite, Collidable, HitNotifier {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;
    private double leftBoundary;
    private double rightBoundary;
    private int speed;
    private long lastShootTime;
    private GameEnvironment environment;
    private GameLevel game;
    private List<HitListener> hitListeners;

    /**
     * Contractor - Create the paddle as rectangle with color keyboard sensor and boundaries.
     * <p>
     * @param shape - the rectangle shape of the paddle.
     * @param color - the color of the paddle.
     * @param keyboard - the keyboard sensor of the paddle.
     * @param leftBoundary - the down left boundary of the frame.
     * @param rightBoundary - the down right boundary of the frame.
     * @param speed - the paddle speed. 
     * @param gameLevel 
     * @param environment */
    public Spaceship(Rectangle shape, Color color, int speed,
            KeyboardSensor keyboard, double leftBoundary, double rightBoundary, GameEnvironment environment, GameLevel gameLevel) {
        this.speed = speed;
        this.keyboard = keyboard;
        this.shape = shape;
        this.color = color;
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.lastShootTime = System.currentTimeMillis();
        this.environment = environment;
        this.game = gameLevel;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * The paddle move left.
     * <p>
     * @param dt - the speed per frame. */
    public void moveLeft(double dt) {
        double x  = this.shape.getUpperLeft().getX();
        double y  = this.shape.getUpperLeft().getY();
        double width = this.shape.getWidth();
        double height = this.shape.getHeight();
        // If the paddle stay in the boundaries after the move, make the move left.
        if (x > this.leftBoundary) {
            this.shape = new Rectangle(new Point(x - (speed * dt), y), width, height);
        } else {
            // Else go to the left boundary.
            this.shape = new Rectangle(new Point(this.leftBoundary, y), width, height);
        }
    }

    /**
     * The paddle move right.
     * <p>
     * @param dt - the speed per frame. */
    public void moveRight(double dt) {
        double x  = this.shape.getUpperLeft().getX();
        double y  = this.shape.getUpperLeft().getY();
        double width = this.shape.getWidth();
        double height = this.shape.getHeight();
        // If the paddle stay in the boundaries after the move, make the move right.
        if (x + width + (speed * dt) < this.rightBoundary) {
            this.shape = new Rectangle(new Point(x + (speed * dt), y), width, height);
        } else {
            // Else go to the right boundary.
            this.shape = new Rectangle(new Point(this.rightBoundary - width, y), width, height);
        }
    }

    /**
     * Move the paddle when notify that time passed.
     * If pressed left arrow move left, if pressed right arrow move right.
     * <p>
     * @param dt - the speed per frame. */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY) &&
                ((System.currentTimeMillis() - this.lastShootTime) / 1000 >= 0.35)) {
            this.shoot();
            this.lastShootTime = System.currentTimeMillis();
        }
    }

    private void shoot() {
        Ball shot = new Ball((int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2),
                (int) this.shape.getUpperLeft().getY() - 10, 3, Color.WHITE);
        shot.setVelocity(Velocity.fromAngleAndSpeed(0, 400));
        shot.setGameEnvironment(this.environment);
        shot.addToGame(this.game);
    }

    /**
     * @return the rectangle that the trajectory collision with it. */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Change the velocity of the ball when it hit the paddle.
     * Check where the ball hit and return the velocity (like the block).
     *  the upper part of the paddle divided to 5,
     *  and every part return a different angle of the velocity.
     * <p>
     * @param hitter - the ball that hit the paddle.
     * @param collisionPoint - the collision point of the ball with the paddle.
     * @param currentVelocity - the current velocity of the point.
     * @return the new velocity vector of the ball. */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        return null;
    }

    /**
     * Add the paddle to the game as sprite and as collidable.
     * <p>
     * @param game - the game that the paddle added to. */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * remove the paddle from the game as sprite and as collidable.
     * <p>
     * @param game - the game that the paddle added to. */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Draw the paddle.
     * <p>
     * @param surface - the surface of the paddle that draw. */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.shape.fillOn(surface);
    }
    
    /** Notify all the listeners that about the event.
     * <p>
     * @param hitter - the ball that hit the block. */
    private void notifyHit(Ball hitter) {
        // Copy the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event.
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
