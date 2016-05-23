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
        surface.setColor(Color.WHITE);
        int x = 600;
        int y = 500;
        for(int i = 0; i < 100; i += 10){
            surface.drawLine(x + i, y, x + i - 30, y + 100);
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
        x = 100;
        y = 400;
        surface.setColor(Color.WHITE);
        for(int i = 0; i < 100; i += 10){
            surface.drawLine(x + i, y, x + i - 15, y + 210);
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

    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    
    public void removeFromGame(GameLevel game){
        game.removeSprite(this);
    }
}
