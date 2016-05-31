package backgrounds;
import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-19 */
public class BackgroundLevelOne implements Sprite {

    private Rectangle frame;
    private Color color;

    /** Constructor - create the background rectangle and give it color. */
    public BackgroundLevelOne() {
        this.frame = new Rectangle(new Point(20, 40), 760, 560);
        this.color = Color.BLACK;
    }

    @Override
    /**
     * draw the background.
     * <p>
     * @param surface - the given DrawSurface. */
    public void drawOn(DrawSurface surface) {
        // Draw the background.
        surface.setColor(this.color);
        this.frame.drawOn(surface);
        // Draw a scope.
        surface.setColor(Color.blue);
        surface.drawLine(520, 210, 420, 210);
        surface.drawLine(280, 210, 380, 210);
        surface.drawLine(400, 90, 400, 190);
        surface.drawLine(400, 330, 400, 230);
        surface.drawCircle(400, 210, 100);
        surface.drawCircle(400, 210, 75);
        surface.drawCircle(400, 210, 50);
    }

    @Override
    public void timePassed(double dt) {
    }

    /**
     * adds the background to the game.
     * <p>
     * @param game - the game. */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * remove the background from the game.
     * <p>
     * @param game - the game*/
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
