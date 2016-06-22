package animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import gameobjects.Enemy;
import gameobjects.Sprite;
import gameobjects.Velocity;
import general.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.Counter;
import listeners.HitListener;

public class Swarm implements Sprite {

    private Velocity velocity;
    private Counter enemyNum;
    private ArrayList<ArrayList<Enemy>> enemySwarm;
    private GameLevel game;
    private GameEnvironment environment;

    public Swarm(BufferedImage image1, BufferedImage image2, GameLevel game, GameEnvironment environment){
        this.environment = environment;
        this.game = game;
        this.velocity = Velocity.fromAngleAndSpeed(90, 40);
        this.enemySwarm = createEnemySwarm(image1,image2);
    }

    private ArrayList<ArrayList<Enemy>> createEnemySwarm(BufferedImage image1, BufferedImage image2) {
        HitListener ballRemover = new BallRemover(this.game);
        ArrayList<ArrayList<Enemy>> swarm = new ArrayList<ArrayList<Enemy>>();
        int x = 50;
        int y = 80;
        for(int i = 0; i < 10; i++){
            ArrayList<Enemy> enemyColumn = new ArrayList<Enemy>();
            for(int j = 0; j < 5; j++){
                Enemy enemy = new Enemy((new Rectangle(new Point(x, y), 40, 30)), game, image1, image2, this.enemyNum, this.velocity, this.environment);
                x += 50;
                enemy.addHitListener(ballRemover);
                enemy.addToGame(game);
                enemyColumn.add(enemy);
            }
            swarm.add(enemyColumn);
            y += 40;
        }
        return swarm;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        for(ArrayList<Enemy> enemyColumn : this.enemySwarm){
            for(Enemy enemy : enemyColumn){
                enemy.drawOn(surface);
            }
        }
    }

    @Override
    public void timePassed(double dt) {
        checkBorders();
    }

    private void checkBorders() {
        double left = this.enemySwarm.get(0).get(0).getLocation().getX();
        double right = this.enemySwarm.get(this.enemySwarm.size() - 1).get(0).getLocation().getX() + 40;
        if( left <= 0 || right >= 800){
            this.velocity.setDx(- this.velocity.getDx());
        }
    }

    @Override
    public void addToGame(GameLevel game) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeFromGame(GameLevel game) {
        // TODO Auto-generated method stub

    }

}
