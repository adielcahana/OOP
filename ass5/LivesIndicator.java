import java.awt.Color;

import biuoop.DrawSurface;

public class LivesIndicator implements Sprite {
    private Rectangle shape;
    private final Color color = Color.WHITE;
    private Counter lives;

    public LivesIndicator(Point upperLeft, double width, double height, Counter lives) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.lives = lives;
    }
    
    
    @Override
    public void drawOn(DrawSurface surface) {
        String lives = "lives: " + Integer.toString(this.lives.getValue());
        surface.setColor(this.color);
        this.shape.drawOn(surface);
        Line[] lines = this.shape.myLines();
        //draw the lives number remaining.
        surface.drawText((int) lines[0].middle().getX() - 200, (int) lines[1].middle().getY() + 7, lives, 20);
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
