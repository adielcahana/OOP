package general;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;
import levels.LevelInformation;

public class Level implements LevelInformation {

    private List<Velocity> velocities = new ArrayList<Velocity>();
    private List<Block> blockList = new ArrayList<Block>();
    private List<Ball> balls = new ArrayList<Ball>();
    private int numberOfBlocksToRemove;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private int numberOfBalls;
    private Sprite background;

    
    private void setBackgroundSprite(Sprite sprite) {
        // TODO Auto-generated method stub

    }



    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    public void addVelocity(Velocity velocity) {
        this.velocities.add(velocity);
    }

    public void addBlock(Block block) {
        this.blockList.add(block);
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }


    
    
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    @Override
    public String levelName() {
        return this.levelName;
    }
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    @Override
    public List<Block> blocks() {
        return blockList;
    }
    @Override
    public List<Ball> balls() {
        return balls;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}