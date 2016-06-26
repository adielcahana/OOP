package gameobjects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import animations.GameLevel;
import biuoop.DrawSurface;
import general.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BonusRemover;
import listeners.Counter;
import listeners.EnemyRemover;
import listeners.HitListener;
import listeners.ShootListener;

public class Swarm implements Sprite, ShootNotifier {
    private Velocity Initialvelocity;
    private Velocity velocity;
    public Counter enemyNum;
    private ArrayList<ArrayList<Enemy>> enemySwarm;
    private List<ShootListener> shootListeners;
    //private Enemy bonus;
    private Boolean goDown;
    private int lowBorder;
    private Counter spaceShipCounter;
    private long lastShootTime;

    public Swarm(Counter spaceShipCounter, Velocity velocity, Counter enemyNum) {
        this.enemyNum = enemyNum;
        this.spaceShipCounter = spaceShipCounter;
        this.velocity = velocity;
        this.Initialvelocity = new Velocity(velocity.getDx(), velocity.getDy());
        this.enemySwarm = new ArrayList<ArrayList<Enemy>>();
        this.goDown = true;
        this.lowBorder = 500;
        this.lastShootTime = 0;
        this.shootListeners = new ArrayList<ShootListener>();
    }

    public void createEnemySwarm(HitListener scoreListener, GameLevel game) {
        HitListener ballRemover = new BallRemover(game);
        HitListener enemyRemover = new EnemyRemover(game, this.enemyNum, this);
        InputStream is1 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 1.png");
        InputStream is2 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 2.png");
        InputStream is3 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 3.png");
        InputStream is4 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 4.png");
        InputStream is5 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 5.png");
        InputStream is6 = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy - 6.png");
        try {
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            images.add(ImageIO.read(is1));
            images.add(ImageIO.read(is2));
            images.add(ImageIO.read(is3));
            images.add(ImageIO.read(is4));
            images.add(ImageIO.read(is5));
            images.add(ImageIO.read(is6));
            int x = 50;
            int y = 80;
            for(int i = 0; i < 10; i++) {
                int k = -2;
                ArrayList<Enemy> enemyColumn = new ArrayList<Enemy>();
                for(int j = 0; j < 6; j++) {
                    if (j % 2 == 0) {
                        k += 2;
                    }
                    Enemy enemy = new Enemy((new Rectangle(new Point(x, y), 40, 30)), images.get(k),
                            images.get(k+1), this.velocity, this.enemyNum.getValue());
                    y += 40;
                    this.enemyNum.increase(1);
                    enemy.addHitListener(ballRemover);
                    enemy.addHitListener(enemyRemover);
                    enemy.addHitListener(scoreListener);
                    enemy.addToGame(game);
                    enemyColumn.add(enemy);
                }
                this.enemySwarm.add(enemyColumn);
                x += 50;
                y = 80;
            }
        } catch (IOException e) {
            System.out.println("Failed to load enemy pic");
            System.exit(1);
        } finally {
            try {
                is1.close();
                is2.close();
                is3.close();
                is4.close();
                is5.close();
                is6.close();
            } catch (IOException e) {
                System.out.println("can't close Enemy img input stream");
                e.printStackTrace();
            }

        }
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
            ArrayList<Enemy> enemyColumn = this.enemySwarm.get(rand.nextInt(this.enemySwarm.size()));
            notifyShoot( enemyColumn.get(enemyColumn.size() - 1).shoot());
            this.lastShootTime = System.currentTimeMillis();
        }
    }

    private void checkBorders(double dt) {
        double left = this.enemySwarm.get(0).get(0).getLocation().getX();
        double right = this.enemySwarm.get(this.enemySwarm.size() - 1).get(0).getLocation().getX() + 40;
        if(left < 0 || right > 800){
            if(goDown){
                this.velocity.setDy(1000);
                this.velocity.setDx(-1.1 * this.velocity.getDx());
                goDown = false;
                for(ArrayList<Enemy> enemyColumn : this.enemySwarm){
                    Enemy enemy = enemyColumn.get(enemyColumn.size() -1);
                    if(this.lowBorder <= this.velocity.applyToPoint(enemy.getLocation(), dt).getY() + 30) {
                        this.spaceShipCounter.decrease(1);
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
        this.velocity.setDx(Initialvelocity.getDx());
        this.velocity.setDy(Initialvelocity.getDy());
        /*if (this.bonus != null) {
            this.bonus.removeFromGame(game);
            game.removeCollidable(this.bonus);
        }*/
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
        for(int i = 0; i < this.enemySwarm.size(); i++){
            ArrayList<Enemy> enemyColumn = this.enemySwarm.get(i);
            enemyColumn.remove(enemy);
            if(enemyColumn.isEmpty()) {
                this.enemySwarm.remove(enemyColumn);
            }
        }
    }

    @Override
    public void addShootListener(ShootListener sl) {
        this.shootListeners.add(sl);        
    }

    @Override
    public void removeShootListener(ShootListener sl) {
        this.shootListeners.add(sl);
    }

    private void notifyShoot(Ball shot) {
        // Copy the hitListeners before iterating over them.
        List<ShootListener> listeners = new ArrayList<ShootListener>(this.shootListeners);
        // Notify all listeners about a hit event.
        for (ShootListener hl : listeners) {
            hl.shootEvent(shot);
        }
    }
    
//    private void createBonusSpaceship() {
//     Enemy bonus  = new Enemy((new Rectangle(new Point(850, 50), 40, 22)), game, this.bonusImage,
//             this.bonusImage, Velocity.fromAngleAndSpeed(-90, 100), this.environment, 0);
//     bonus.addHitListener(new BonusRemover(game, this.scoreListener));
//     bonus.addHitListener(new BallRemover(game));
//     bonus.addToGame(this.game);
//     this.game.addSprite(bonus);
//    }
}