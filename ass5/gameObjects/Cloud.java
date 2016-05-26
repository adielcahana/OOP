package gameObjects;

import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;

/**
* @author Ori Engelberg <turht50@gmail.com>
* @version 1.0
* @since 2016-05-21 */
public class Cloud implements Sprite {
    private int x;
    private int y;
    private int space;
    private int length;

    /**
     * Constructor - Create a start point an end point and the line slope.
     * <p>
     * @param x - a x value for circles that create the cloud.
     * @param y - a y value for circles that create the cloud.
     * @param space - the space between the lines that create the rain.
     * @param length - the length of the rain lines. */
    public Cloud(int x, int y, int space, int length) {
        this.x = x;
        this.y = y;
        this.space = space;
        this.length = length;
    }

    @Override
    /**
     * Draw the cloud.
     * Create 5 circles with different levels of gray color and draw a lines for the rain.
     * <p>
     * @param surface - the surface that given. */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 100; i += 10) {
            surface.drawLine(x + i, y, x + i - this.space, y + this.length);
        }
        surface.setColor(new Color(230, 230, 230));
        surface.fillCircle(x, y, 25);
        surface.setColor(new Color(210, 210, 210));
        surface.fillCircle(x + 15, y - 10, 25);
        surface.setColor(new Color(170, 170, 170));
        surface.fillCircle(x + 40 , y + 10, 25);
        surface.fillCircle(x + 60, y - 20, 25);
        surface.setColor(new Color(115, 115, 115));
        surface.fillCircle(x + 80, y + 15, 25);
    }

    /**
     * Draw another kind of cloud.
     * Create 5 circles with different levels of gray color and draw a lines for the rain.
     * <p>
     * @param surface - the surface that given. */
    public void drawOnCloud2(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 100; i += 10) {
            surface.drawLine(x + i, y, x + i - this.space, y + this.length);
        }
        surface.setColor(new Color(230, 230, 230));
        surface.fillCircle(x - 5, y - 10, 25);
        surface.setColor(new Color(115, 115, 115));
        surface.fillCircle(x + 5, y + 10, 25);
        surface.setColor(new Color(170, 170, 170));
        surface.fillCircle(x + 35 , y, 30);
        surface.setColor(new Color(150, 150, 150));
        surface.fillCircle(x + 60, y - 20, 25);
        surface.fillCircle(x + 75, y + 10, 30);
    }
    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {

    }

    @Override
    public void removeFromGame(GameLevel game) {

    }

}
