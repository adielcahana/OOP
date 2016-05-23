import java.awt.Color;

import biuoop.DrawSurface;

public class BackgroundLevelOne implements Sprite {

    private Rectangle frame;
    private Color color;
    
    public BackgroundLevelOne(Rectangle frame, Color color){
        this.frame = frame;
        this.color = color;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
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
    public void timePassed() {
    }

    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    
    public void removeFromGame(GameLevel game){
        game.removeSprite(this);
    }
}
