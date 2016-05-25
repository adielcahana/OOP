package listeners;

import gameObjects.Ball;
import gameObjects.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;    

    public ScoreTrackingListener(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getMaxHits() == 0) {
            this.currentScore.increase(10);
        }
    }

}
