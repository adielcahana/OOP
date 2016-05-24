import java.awt.Color;

import biuoop.DrawSurface;

public class BackgroundLevelFour implements Sprite {

    private Rectangle frame;
    private Color color;

    public BackgroundLevelFour(Rectangle frame, Color color){
        this.frame = frame;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
            Cloud cloud1 = new Cloud(600, 500, 30, 100);
            cloud1.drawOn(surface);
            Cloud cloud2 = new Cloud(100, 400, 15, 210);
            cloud2.drawOn(surface);
        }

    @Override
    public void timePassed() {
    }

    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    public void removeFromGame(GameLevel game){
        game.removeSprite(this);
    }
}
