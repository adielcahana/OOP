package gameObjects;
import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;
import listeners.Counter;

public class LivesIndicator implements Sprite {
    private final Color color = Color.BLACK;
    private Counter lives;

    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    
    
    @Override
    public void drawOn(DrawSurface surface) {
        String lives = "lives: " + Integer.toString(this.lives.getValue());
        surface.setColor(this.color);
        surface.drawText(100, 17, lives, 20);
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
