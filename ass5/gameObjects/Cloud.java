package gameObjects;
import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;

public class Cloud implements Sprite{
    private int x;
    private int y;
    private int space;
    private int length;
    
    public Cloud(int x, int y, int space, int length){
        this.x = x;
        this.y = y;
        this.space = space;
        this.length = length;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        for(int i = 0; i < 100; i += 10){
            surface.drawLine(x + i, y, x + i - this.space, y + this.length);
        }
        surface.setColor(new Color(240, 240, 240));
        surface.fillCircle(x, y, 25);
        surface.setColor(new Color(210, 210, 210));
        surface.fillCircle(x + 15, y -10, 25);
        surface.setColor(new Color(170, 170, 170));
        surface.fillCircle(x + 40 , y + 10, 25);
        surface.setColor(new Color(150, 150, 150));
        surface.fillCircle(x + 60, y -20, 25);
        surface.setColor(new Color(115, 115, 115));
        surface.fillCircle(x + 80, y + 15, 25);
        
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
