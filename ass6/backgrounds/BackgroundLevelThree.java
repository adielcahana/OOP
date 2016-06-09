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
public class BackgroundLevelThree implements Sprite {

    private Rectangle frame;
    private Color color;

    /** Constructor - create the background rectangle and give it color. */
    public BackgroundLevelThree() {
        this.frame = new Rectangle(new Point(20, 40), 760, 560);
        this.color = new Color(0, 102, 0);
    }

    @Override
    /**
     * draw the background.
     * <p>
     * @param surface - the given DrawSurface. */
    public void drawOn(DrawSurface surface) {
        // Draw the background.
        surface.setColor(this.color);
        this.frame.fillOn(surface);
        // Draw a building.
        surface.setColor(Color.WHITE);
        surface.fillRectangle(50, 450, 110, 200);
        surface.setColor(Color.DARK_GRAY);
        surface.fillRectangle(50, 450, 10, 200);
        surface.fillRectangle(150, 450, 10, 200);
        surface.fillRectangle(50, 440, 110, 10);
        int i = 0;
        int x = 70;
        // Draw the windows.
        for (i = 0; i < 4; i++) {
            surface.fillRectangle(x, 450, 10, 200);
            x += 20;
        }
        int y = 470;
        for (i = 0; i < 4; i++) {
            surface.fillRectangle(50, y, 110, 10);
            y += 35;
        }
        surface.setColor(new Color(64, 64, 64));
        surface.fillRectangle(90, 390, 30, 50);
        surface.setColor(new Color(96, 96, 96));
        surface.fillRectangle(100, 290, 10, 100);
        x = 105;
        y = 280;
        // Draw a torch on the top of the building.
        surface.setColor(new Color(255, 204, 204));
        surface.fillCircle(x, y, 10);
        surface.setColor(new Color(225, 51, 51));
        surface.fillCircle(x, y, 7);
        surface.setColor(Color.WHITE);
        surface.fillCircle(x, y, 3);

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
