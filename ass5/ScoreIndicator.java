import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;

public class ScoreIndicator implements Sprite {

    private Rectangle shape;
    private final Color color = Color.WHITE;
    private Counter scoreCounter; 
    
    
    /** Block constructor.
     * <p>
     * @param upperLeft - coordinate
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     * @param maxHits - hits available.
     * @param color - the block color*/
    public ScoreIndicator(Point upperLeft, double width, double height, Counter scoreCounter) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.scoreCounter = scoreCounter;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        String hits = "Score: " + Integer.toString(this.scoreCounter.getValue());
        surface.setColor(this.color);
        this.shape.drawOn(surface);
        Line[] lines = this.shape.myLines();
        //draw the hit number on Hits remaining
        surface.drawText((int) lines[0].middle().getX() - 3, (int) lines[1].middle().getY() + 7, hits, 20);
    }

    @Override
    public void timePassed() {   
    }
    
    public void addToGame(Game game) {
        game.addSprite(this);
    }
    
    public void removeFromGame(Game game){
        game.removeSprite(this);
    }

}
