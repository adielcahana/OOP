import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
	private List SpriteCollection;

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