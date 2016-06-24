package listeners;

import animations.GameLevel;
import gameobjects.Ball;
import gameobjects.HitNotifier;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-10 */
public class BonusRemover implements HitListener {
    private GameLevel game;
    private HitListener scoreListener;

    /** BlockRemover constructor.
     * <p>
     * @param game - the game to remove from.
     * @param blocksCounter - num of blocksCounter Counter. */
    public BonusRemover(GameLevel game, HitListener scoreListener) {
        this.game = game;
        this.scoreListener = scoreListener;
    }

    @Override
    /**
     * Called when beingHit object is hit.
     * <p>
     * @param beingHit - the block that hit.
     * @param hitter - the ball that hit. */
    public void hitEvent(HitNotifier beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        game.removeSprite(beingHit);
        for(int i = 0; i < 5; i++) {
            this.scoreListener.hitEvent(beingHit, hitter);
        }
    }
}