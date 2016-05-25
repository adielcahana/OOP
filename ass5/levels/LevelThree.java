package levels;
import geometry.Rectangle;
import geometry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.BackgroundLevelThree;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Sprite;
import gameObjects.Velocity;

public class LevelThree implements LevelInformation {
    
    private BackgroundLevelThree background;
    
    public LevelThree(){
        this.background = new BackgroundLevelThree(new Rectangle(new Point(20, 40), 760, 560), new Color(0,102,0));
    }
    
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        velocity.add(Velocity.fromAngleAndSpeed(30, 7));
        velocity.add(Velocity.fromAngleAndSpeed(330, 7));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int y = 200;
        Color[] colors = { Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for(int i = 0; i < 5; i++){
            int x = 730;
            for(int j =i; j < 10; j++){
                blockList.add(new Block(new Point(x, y), 50, 20, 1, colors[i]));
                x -= 50;
            }
            y += 20;
        }
        return blockList;
    }

    @Override
    public List<Ball> Balls() {
        List<Ball> ballList = new ArrayList<Ball>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 575, 5, Color.WHITE);
            ball.setVelocity(this.initialBallVelocities().get(i));
            ballList.add(ball);
        }
        return ballList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

}
