package backgrounds;
import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;
import gameObjects.Sprite;
import geometry.Rectangle;

public class BackgroundLevelTwo implements Sprite {

    private Rectangle frame;
    private Color color;
    
    public BackgroundLevelTwo(Rectangle frame, Color color){
        this.frame = frame;
        this.color = color;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
        int x = 150;
        int y = 150;
        surface.setColor(new Color(224, 224, 224));
        for (int i = 20; i < 650; i += 5) {
            surface.drawLine(x, y, 0 + i, 300);
        }
        surface.fillCircle(x, y, 50);
        surface.setColor(new Color(204, 204, 0));
        surface.fillCircle(x, y, 40);
        surface.setColor(Color.YELLOW);
        surface.fillCircle(x, y, 30);}

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
