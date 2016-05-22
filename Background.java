import java.awt.Color;

import biuoop.DrawSurface;

public class Background implements Sprite {

    private Rectangle frame;
    private Color color;
    
    public Background(Rectangle frame, Color color){
        this.frame = frame;
        this.color = color;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
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
