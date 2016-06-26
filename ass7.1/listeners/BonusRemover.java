package listeners;

import java.io.IOException;
import java.io.InputStream;

import animations.GameLevel;
import gameobjects.Ball;
import gameobjects.HitNotifier;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-10 */
public class BonusRemover implements HitListener {
    private GameLevel game;
    private Counter score;
    private InputStream inputStrream;

    /** BlockRemover constructor.
     * <p>
     * @param game - the game to remove from.
     * @param blocksCounter - num of blocksCounter Counter. */
    public BonusRemover(GameLevel game, Counter score, InputStream is) {
        this.game = game;
        this.score = score;
        this.inputStrream = is;
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
        this.score.increase(500);
        try {
            this.inputStrream.close();
        } catch (IOException e) {
            System.out.println("couldn't close bonus image stream");
        }
    }
}