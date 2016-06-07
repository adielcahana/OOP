package general;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;
import levels.LevelInformation;

public class LevelSpecificationReader implements LevelInformation{


    private List<Velocity> velocities;
    private List<Block> blockList;
    private List<Ball> balls;
    private int numberOfBlocksToRemove;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private int numberOfBalls;



    public LevelSpecificationReader(){
        this.velocities = new ArrayList<Velocity>();
        this.blockList = new ArrayList<Block>();
        this.balls = new ArrayList<Ball>();
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.numberOfBalls = 0;
        this.numberOfBlocksToRemove = 0;
        this.levelName = null;

    }

    
    
    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        LevelSpecificationReader level = null;
        BlockDefinitions blockDefinition = null;
        BufferedReader bufferReader = new BufferedReader(reader);
        try {
            String line = null;
            while (( line = bufferReader.readLine ()) != null ){
                if("START_LEVEL".equals(line)) {
                    level = new LevelSpecificationReader();
                    continue;
                }
                String[] parts = line.split(":");
                String part1 = parts[0];
                String part2 = parts[1];
                if(part1.equals("level_name")){
                    level.setLevelName(part2);
                    continue;
                }
                if(part1.equals("ball_velocities")){
                    String[] velocities = part2.split(" ");
                    for(int i = 0; i < velocities.length; i++){
                        String[] velocity = velocities[i].split(",");
                        level.addVelocity(Velocity.fromAngleAndSpeed(Double.parseDouble(velocity[0]), (Double.parseDouble(velocity[1]))));
                    }
                    continue;
                }
                if(part1.equals("background")){

                    continue;
                }
                if(part1.equals("paddle_speed")){
                    level.setPaddleSpeed(Integer.parseInt(part2));
                }
                if(part1.equals("paddle_width")){
                    level.setPaddleWidth(Integer.parseInt(part2));
                }
                if(part1.equals("block_definitions")){

                }



                if("END_LEVEL".equals(line)){
                    levels.add(level);
                    continue;
                }
            }
        } catch ( IOException e) {
            System.out.println (" Something went wrong while reading !");
        }
        return levels;
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
        // TODO Auto-generated method stub
        return null;
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