package listeners;

import java.util.List;

import animations.GameLevel;
import gameobjects.Ball;
import general.GameEnvironment;


/**
 * @author Adiel cahana <adiel.cahana@gmail.com>
 * @version 1.0
 * @since 2016-06-17
 */
public class SpaceShipShotListener implements ShootListener {

    private List<Ball> shots;
    private GameLevel game;
    private GameEnvironment environment;
    private long lastShootTime;

    /**
     * Instantiates a new space ship shot listener.
     *
     * @param shots - the game's pool of balls
     * @param game - the game
     * @param environment - the environment
     */
    public SpaceShipShotListener(List<Ball> shots, GameLevel game, GameEnvironment environment) {
        this.shots = shots;
        this.game = game;
        this.environment = environment;
        this.lastShootTime = 0;
    }


    @Override
    public void shootEvent(Ball shot) {
        //make a shot only if enough time passed sice the last shot
        if ((System.currentTimeMillis() - this.lastShootTime) / 400 >= 0.35) {
            shot.addToGame(this.game);
            shot.setGameEnvironment(this.environment);
            shots.add(shot);
            this.lastShootTime = System.currentTimeMillis();
        }
    }
}
