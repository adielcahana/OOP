package listeners;
import gameObjects.Ball;
import gameObjects.Block;

public class PrintingHitListener implements HitListener {
   public void hitEvent(Block beingHit, Ball hitter) {
      System.out.println("A Block with " + beingHit.getMaxHits() + " points was hit.");
   }
}