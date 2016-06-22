package animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import biuoop.DrawSurface;
import gameobjects.Enemy;
import gameobjects.Sprite;
import gameobjects.Velocity;
import general.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.Counter;
import listeners.EnemyRemover;
import listeners.HitListener;

public class Swarm implements Sprite {

    private Velocity velocity;
    public Counter enemyNum;
    private ArrayList<ArrayList<Enemy>> enemySwarm;
    private GameLevel game;
    private GameEnvironment environment;
    private Boolean goDown;
    private int lowBorder;
    private Counter spaceShipCounter;
    private long lastShootTime;

    public Swarm(BufferedImage image1, BufferedImage image2, GameLevel game, GameEnvironment environment, HitListener scoreListener, Counter spaceShipCounter){
        this.environment = environment;
        this.enemyNum = new Counter();
        this.spaceShipCounter = spaceShipCounter;
        this.game = game;
        this.velocity = Velocity.fromAngleAndSpeed(90, 40);
        this.enemySwarm = createEnemySwarm(image1,image2, scoreListener);
        this.goDown = true;
        this.lowBorder = 500;
        this.lastShootTime = 0;
    }

    private ArrayList<ArrayList<Enemy>> createEnemySwarm(BufferedImage image1, BufferedImage image2, HitListener scoreListener) {
        HitListener ballRemover = new BallRemover(this.game);
        HitListener enemyRemover = new EnemyRemover(this.game, this.enemyNum, this);
        ArrayList<ArrayList<Enemy>> swarm = new ArrayList<ArrayList<Enemy>>();
        int x = 50;
        int y = 80;
        for(int i = 0; i < 10; i++){
            ArrayList<Enemy> enemyColumn = new ArrayList<Enemy>();
            for(int j = 0; j < 5; j++){
                Enemy enemy = new Enemy((new Rectangle(new Point(x, y), 40, 30)), game, image1, image2, this.enemyNum, this.velocity, this.environment, j, i);
                y += 40;
                this.enemyNum.increase(1);
                enemy.addHitListener(ballRemover);
                enemy.addHitListener(enemyRemover);
                enemy.addHitListener(scoreListener);
                enemy.addToGame(game);
                enemyColumn.add(enemy);
            }
            swarm.add(enemyColumn);
            x += 50;
            y = 80;
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
        checkBorders(dt);
        for(ArrayList<Enemy> enemyColumn : this.enemySwarm){
            for(Enemy enemy : enemyColumn){
                enemy.timePassed(dt);
            }
        }
        if ((System.currentTimeMillis() - this.lastShootTime) / 1000 >= 0.5) {
            Random rand = new Random();
            ArrayList<Enemy> enemyColumn = this.enemySwarm.get(rand.nextInt(this.enemySwarm.size() -1));
            enemyColumn.get(enemyColumn.size() -1).shoot();
            this.lastShootTime = System.currentTimeMillis();
        }
    }

    private void checkBorders(double dt) {
        double left = this.enemySwarm.get(0).get(0).getLocation().getX();
        double right = this.enemySwarm.get(this.enemySwarm.size() - 1).get(0).getLocation().getX() + 40;
        if( left < 0 || right > 800){
            if(goDown){
                this.velocity.setDy(1000);
                this.velocity.setDx(-1.1 * this.velocity.getDx());
                goDown = false;
                for(ArrayList<Enemy> enemyColumn : this.enemySwarm){
                    Enemy enemy = enemyColumn.get(enemyColumn.size() -1);
                    if(this.lowBorder <= this.velocity.applyToPoint(enemy.getLocation(), dt).getY() + 30) {
                        this.spaceShipCounter.decrease(1);
                        resetSwarm();
                    }
                 }         
            }
        } else {
            this.velocity.setDy(0);     
            goDown = true;
        }
    }

    public void resetSwarm() {
        int x = 50;
        int y = 80;
        for(int i = 0; i < this.enemySwarm.size(); i++){
            for(int j = 0; j < this.enemySwarm.get(i).size(); j++){
                Enemy enemy = this.enemySwarm.get(i).get(j);
                enemy.setNewPlace(x, y);
            }
        }
        this.velocity.setDx(40);
        this.velocity.setDy(0);
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    public void removeEnemy(Enemy enemy) {
        for(ArrayList<Enemy> enemyColumn : this.enemySwarm){
           enemyColumn.remove(enemy);
        }
    }

}
