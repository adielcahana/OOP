import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;

public class Background implements Sprite {

    private Rectangle background;
    private Color color;
    private ArrayList<Drawable> list;
    
    public Background(Rectangle frame, Color color, ArrayList<Drawable> list){
        this.background = frame;
        this.color = color;
        this.list = list;
    }
    
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        this.background.drawOn(surface);
        for(int i = 0; i < this.list.size(); i++){
            list.get(i).drawOnDrawable(surface);
        }
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
