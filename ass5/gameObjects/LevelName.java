package gameObjects;
import java.awt.Color;

import animations.GameLevel;
import biuoop.DrawSurface;

public class LevelName implements Sprite {
    
    private final Color color = Color.BLACK;
    private String levelName;
    
    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    
    
    @Override
    public void drawOn(DrawSurface surface) {
        String levelName = "levelName: " + this.levelName;
        surface.setColor(this.color);
        //draw the level name.
        surface.drawText(550, 17, levelName, 20);
    }

    public void timePassed() {        
    }
    
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    
    public void removeFromGame(GameLevel game){
        game.removeSprite(this);
    }
    
}
