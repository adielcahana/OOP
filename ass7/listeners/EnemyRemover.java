package listeners;

import animations.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Enemy;
import gameobjects.HitNotifier;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-10 */
public class EnemyRemover implements HitListener {
   private GameLevel game;
   private Swarm swarm;
   private Counter enemyCounter;

   /** BlockRemover constructor.
    * <p>
    * @param game - the game to remove from.
    * @param blocksCounter - num of blocksCounter Counter. */
   public EnemyRemover(GameLevel game, Counter enemyCounter, Swarm swarm) {
       this.game = game;
       this.swarm = swarm;
       this.enemyCounter = enemyCounter;
   }

   @Override
   /**
    * Called when beingHit object is hit.
    * <p>
    * @param beingHit - the block that hit.
    * @param hitter - the ball that hit. */
   public void hitEvent(HitNotifier beingHit, Ball hitter) {
           ((Enemy)beingHit).removeFromSwarm(this.swarm);
           beingHit.removeFromGame(this.game);
           this.enemyCounter.decrease(1);
       }
   }
}