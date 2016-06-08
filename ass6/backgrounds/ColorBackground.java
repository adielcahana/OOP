package backgrounds;

import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;
import gameobjects.Sprite;

public class ColorBackground implements Sprite {

    private Color color;

    public ColorBackground(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
    }

    @Override
    public void timePassed(double dt) {       
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
