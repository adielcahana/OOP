package gameobjects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

public class Swarm implements Sprite {
    private Velocity Initialvelocity;
    private Velocity velocity;
    public Counter enemyNum;
    private ArrayList<ArrayList<Enemy>> enemySwarm;
    private Enemy bonus;
    private GameLevel game;
    private GameEnvironment environment;
    private Boolean goDown;
    private int lowBorder;
    private Counter spaceShipCounter;
    private long lastShootTime;
    private ArrayList<Ball> balls;
    private long lastBonusTime;
    private int BonusCount;
    private BufferedImage bonusImage;
    private HitListener scoreListener;

    public Swarm(GameLevel game, GameEnvironment environment,
            HitListener scoreListener, Counter spaceShipCounter, Velocity velocity,
            Counter enemyNum, ArrayList<Ball> balls) {
        this.game = game;
        this.environment = environment;
        this.enemyNum = enemyNum;
        this.spaceShipCounter = spaceShipCounter;
        this.velocity = velocity;
        this.Initialvelocity = new Velocity(velocity.getDx(), velocity.getDy()); 
        this.enemySwarm = createEnemySwarm(scoreListener);
        this.goDown = true;
        this.lowBorder = 500;
        this.lastShootTime = 0;
        this.lastBonusTime = 0;
        this.balls = balls;
        InputStream is1 = ClassLoader.getSystemClassLoader().getResourceAsStream("bonus enemy.png");
        try {
            this.bonusImage = ImageIO.read(is1);
        } catch (IOException e) {
            System.out.println("can't load bonus enemy pic");
            System.exit(1);
        }
    }

    private ArrayList<ArrayList<Enemy>> createEnemySwarm(HitListener scoreListener) {
        this.scoreListener = scoreListener;
        HitListener ballRemover = new BallRemover(this.game);
        HitListener enemyRemover = new EnemyRemover(this.game, this.enemyNum, this);
        ArrayList<ArrayList<Enemy>> swarm = new ArrayList<ArrayList<Enemy>>();
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
            for(int i = 0; i < 10; i++){
                int k = -2;
                ArrayList<Enemy> enemyColumn = new ArrayList<Enemy>();
                for(int j = 0; j < 6; j++){
                    if (j % 2 == 0) {
                        k+=2;
                    }
                    Enemy enemy = new Enemy((new Rectangle(new Point(x, y), 40, 30)), game, images.get(k), images.get(k+1), this.velocity, this.environment, j, i);
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
        } catch (IOException e) {
            System.out.println("Failed to load enemy pic");
            System.exit(1);
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
            ArrayList<Enemy> enemyColumn = this.enemySwarm.get(rand.nextInt(this.enemySwarm.size()));
            Ball b = enemyColumn.get(enemyColumn.size() -1).shoot();
            balls.add(b);
            this.lastShootTime = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - this.lastBonusTime) / 1000 >= 20 && this.BonusCount <= 2) {
            this.createBonusShip();
            this.lastBonusTime = System.currentTimeMillis();
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
        if (this.bonus != null) {
            this.bonus.removeFromGame(game);
            game.removeCollidable(this.bonus);
        }
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
    private void createBonusShip() {
        if (this.bonus != null) {
            this.bonus.removeFromGame(game);
            game.removeCollidable(this.bonus);
        }
        this.bonus = new Enemy((new Rectangle(new Point(850, 50), 40, 22)), game, this.bonusImage, this.bonusImage, Velocity.fromAngleAndSpeed(-90, 100), this.environment, 0, 0);
        bonus.addHitListener(new BonusRemover(game, this.scoreListener));
        bonus.addHitListener(new BallRemover(game));
        bonus.addToGame(this.game);
        this.game.addSprite(bonus);
        this.BonusCount++;
    }
}