import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;

public class ScoreIndicator implements Sprite {

    private final Color color = Color.BLACK;
    private Counter scoreCounter;
    
    /** Block constructor.
     * <p>
     * @param upperLeft - coordinate
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     * @param maxHits - hits available.
     * @param color - the block color*/
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        String hits = "Score: " + Integer.toString(this.scoreCounter.getValue());
        surface.setColor(this.color);
        surface.drawText(350, 17, hits, 20);
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
