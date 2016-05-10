
public class BallRemover implements HitListener{
    private Game game;
    private Counter ballsCounter;
    
    public BallRemover(Game game, Counter ballsCounter ) {
        this.game = game;
        this.ballsCounter = ballsCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        ballsCounter.decrease(1);
    }
}
