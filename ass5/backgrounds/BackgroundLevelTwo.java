package backgrounds;

import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;
import gameObjects.Sprite;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-19 */
public class BackgroundLevelTwo implements Sprite {

    private Rectangle frame;
    private Color color;

    /** Constructor - create the background rectangle and give it color. */
    public BackgroundLevelTwo() {
        this.frame = new Rectangle(new Point(20, 40), 760, 560);
        this.color = Color.WHITE;
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
        int x = 150;
        int y = 150;
        // Draw a sun
        // Draw the sun rays.
        surface.setColor(new Color(224, 224, 224));
        for (int i = 20; i < 650; i += 5) {
            surface.drawLine(x, y, 0 + i, 300);
        }
        // fill 3 circles to draw the sun body.
        surface.fillCircle(x, y, 50);
        surface.setColor(new Color(204, 204, 0));
        surface.fillCircle(x, y, 40);
        surface.setColor(Color.YELLOW);
        surface.fillCircle(x, y, 30);
    }

    @Override
    public void timePassed() {
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
