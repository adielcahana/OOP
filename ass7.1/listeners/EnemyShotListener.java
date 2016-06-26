package listeners;

import java.util.List;

import animations.GameLevel;
import gameobjects.Ball;
import general.GameEnvironment;

public class EnemyShotListener implements ShootListener {
    List<Ball> shots;
    private GameLevel game;
    private GameEnvironment environment;

    public EnemyShotListener(List<Ball> Shots, GameLevel game, GameEnvironment environment) {
        this.shots = Shots;
        this.game = game;
        this.environment = environment;
    }
    
    @Override
    public void shootEvent(Ball shot) {
        shot.addToGame(this.game);
        shot.setGameEnvironment(this.environment);
        shots.add(shot);
    }
}
