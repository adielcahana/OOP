import biuoop.DrawSurface;
import java.util.ArrayList;

public class SpriteCollection {
	private ArrayList SpriteCollection;

	public SpriteCollection(){
		this.SpriteCollection = new ArrayList();
	}
	public void addSprite(Sprite s){
		this.SpriteCollection.add(s);
	}
	public void removeSprite(Sprite s){
		this.SpriteCollection.remove(s);
	}

	// call timePassed() on all sprites.
	public void notifyAllTimePassed(){
		for (int i = 0; i<this.SpriteCollection.size(); i++){
			Sprite s = (Sprite) this.SpriteCollection.get(i);
			s.timePassed();
		}
	}

	// call drawOn(d) on all sprites.
	public void drawAllOn(DrawSurface surface){
		for (int i = 0; i<this.SpriteCollection.size(); i++){
			Sprite s = (Sprite) this.SpriteCollection.get(i);
			s.drawOn(surface);
		}
	}
}