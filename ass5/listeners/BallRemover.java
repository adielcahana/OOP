package listeners;
import animations.GameLevel;
import gameObjects.Ball;
import gameObjects.Block;

public class BallRemover implements HitListener{
    private GameLevel game;
    private Counter ballsCounter;
    
    public BallRemover(GameLevel game, Counter ballsCounter ) {
        this.game = game;
        this.ballsCounter = ballsCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        ballsCounter.decrease(1);
    }
}
