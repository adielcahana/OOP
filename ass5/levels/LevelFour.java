package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import geometry.Rectangle;
import geometry.Point;
import backgrounds.BackgroundLevelFour;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Sprite;
import gameObjects.Velocity;

public class LevelFour implements LevelInformation {

    private BackgroundLevelFour background;
    
    public LevelFour(){
        this.background = new BackgroundLevelFour(new Rectangle(new Point(20, 40), 760, 560), new Color(0, 128, 255));
    }
    
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        int j = 330;
        for (int i = 0; i < 3; i++){
            velocity.add(Velocity.fromAngleAndSpeed(j % 360, 7));
            j += 30;
        }
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        double y = 100;
        Color[] colors = { Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for(int i = 0; i < 7; i++){
            double x = 20;
            for(int j =0; j < 15; j++){
                blockList.add(new Block(new Point(x, y),  760 / 15 + 0.7, 20, 1, colors[i]));
                x +=  760 / 15 + 0.7;
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
        return 105;
    }
}
