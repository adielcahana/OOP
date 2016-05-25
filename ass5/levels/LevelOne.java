package levels;
import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.BackgroundLevelOne;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Sprite;
import gameObjects.Velocity;

public class LevelOne implements LevelInformation{

    private BackgroundLevelOne background;
    
    public LevelOne(){
        this.background = new BackgroundLevelOne(new Rectangle(new Point(20, 40), 760, 560), Color.BLACK);
    }

    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        velocity.add(Velocity.fromAngleAndSpeed(0, 7));
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(390, 200), 20, 20), 1,Color.RED);
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Ball> Balls() {
        List<Ball> ballList = new ArrayList<Ball>();
        Ball ball = new Ball(400, 575, 5, Color.WHITE);
        ball.setVelocity(this.initialBallVelocities().get(0));
        ballList.add(ball);
        return ballList;
    }

}
