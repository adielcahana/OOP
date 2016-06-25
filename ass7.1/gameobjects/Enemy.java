package gameobjects;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import animations.GameLevel;
import biuoop.DrawSurface;
import general.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;

public class Enemy implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private GameLevel game;
    private GameEnvironment environment;
    private BufferedImage image1;
    private BufferedImage image2;
    private long imageTiming;
    private int imageNum;
    private Velocity velocity;
    private List<HitListener> hitListeners;
    private int formationNum;

    public Enemy(Rectangle shape, GameLevel game, BufferedImage image1, BufferedImage image2,
            Velocity velocity, GameEnvironment environmet, int formationNum) {
        this.shape = shape;
        this.game = game;
        this.image1 = image1;
        this.image2 = image2;
        this.imageNum = 1;
        this.velocity = velocity;
        this.hitListeners = new ArrayList<HitListener>();
        this.environment = environmet;
        this.formationNum = formationNum;
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
    public void drawOn(DrawSurface d) {
        long currentTime = System.currentTimeMillis();
        switch(imageNum) {
        case 1:
            d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), this.image1);
            if ( (currentTime - this.imageTiming)/1000 >= 0.09) {
                this.imageTiming = currentTime;
                this.imageNum = 2;
            }
            break;
        case 2:
            d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), this.image2);
            if ( (currentTime - this.imageTiming)/1000 >= 0.9) {
                this.imageTiming = currentTime;
                this.imageNum = 1;
            }
            break;
        }
    }

    @Override
    public void timePassed(double dt) {
        this.shape = new Rectangle(this.velocity.applyToPoint(this.shape.getUpperLeft(), dt),
                this.shape.getWidth(), this.shape.getHeight());
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        if(ball.getVelocity().getDy() > 0){
            ball.removeFromGame(this.game);
            return null;
        }
        this.notifyHit(ball);
        this.removeFromGame(this.game);
        return null;
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

    public Ball shoot() {
        Ball shot = new Ball((int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2),
                (int) (this.shape.getUpperLeft().getY() + this.shape.getHeight() + 1), 5, Color.RED);
        shot.setVelocity(Velocity.fromAngleAndSpeed(180, 400));
        shot.setGameEnvironment(this.environment);
        shot.addToGame(this.game);
        return shot;
    }

    public Point getLocation() {
        return this.shape.getUpperLeft();
    }

    public void removeFromSwarm(Swarm swarm) {
        swarm.removeEnemy(this);

    }

    public void setNewPlace(int x, int y) {
        int column = (this.formationNum / 6);
        int line = (this.formationNum % 6);
        this.shape = new Rectangle(new Point(x + (column * 50),  y + (line * 40)), 40, 30);
    }
}