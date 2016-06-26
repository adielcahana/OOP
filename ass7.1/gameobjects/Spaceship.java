package gameobjects;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.ShootListener;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-02 */
public class Spaceship implements Sprite, Collidable, HitNotifier, ShootNotifier {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;
    private double leftBoundary;
    private double rightBoundary;
    private int speed;
    private List<HitListener> hitListeners;
    private List<ShootListener> shootListeners;

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
            KeyboardSensor keyboard, double leftBoundary, double rightBoundary) {
        this.speed = speed;
        this.keyboard = keyboard;
        this.shape = shape;
        this.color = color;
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.hitListeners = new ArrayList<HitListener>();
        this.shootListeners = new ArrayList<ShootListener>();
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
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.notifyShoot(this.shoot());
        }
    }

    private Ball shoot() {
        Ball shot = new Ball((int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2),
                (int) this.shape.getUpperLeft().getY() - 10, 3, Color.WHITE);
        shot.setVelocity(Velocity.fromAngleAndSpeed(0, 400));
        return shot;
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
    
    private void notifyShoot(Ball shot) {
        // Copy the hitListeners before iterating over them.
        List<ShootListener> listeners = new ArrayList<ShootListener>(this.shootListeners);
        // Notify all listeners about a hit event.
        for (ShootListener hl : listeners) {
            hl.shootEvent(shot);
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

    @Override
    public void addShootListener(ShootListener sl) {
        this.shootListeners.add(sl);
    }

    @Override
    public void removeShootListener(ShootListener sl) {
        this.shootListeners.add(sl);
    }
}
