import java.awt.Color;

import biuoop.DrawSurface;

public class BackgroundLevelThree implements Sprite {

    private Rectangle frame;
    private Color color;
    
    public BackgroundLevelThree(Rectangle frame, Color color){
        this.frame = frame;
        this.color = color;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.frame.drawOn(surface);
        surface.setColor(Color.WHITE);
        surface.fillRectangle(50, 450, 110, 200);
        surface.setColor(Color.DARK_GRAY);
        surface.fillRectangle(50, 450, 10, 200);
        surface.fillRectangle(150, 450, 10, 200);
        surface.fillRectangle(50, 440, 110, 10);
        int i =0;
        int x = 70;
        for (i =0; i < 4; i++){
            surface.fillRectangle(x, 450, 10, 200);
            x += 20;
        }
        int y = 470;
        for(i = 0; i < 4; i++){
            surface.fillRectangle(50, y, 110, 10);
            y += 35;
        }

        surface.setColor(new Color(64, 64, 64));
        surface.fillRectangle(85, 390, 30, 50);
        surface.setColor(new Color(96, 96, 96));
        surface.fillRectangle(95, 290, 10, 100);
        x = 100;
        y = 280;
        surface.setColor(new Color(255, 204, 204));
        surface.fillCircle(x, y, 10);
        surface.setColor(new Color(225, 51, 51));
        surface.fillCircle(x, y, 7);
        surface.setColor(Color.WHITE);
        surface.fillCircle(x, y, 3);
    
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
