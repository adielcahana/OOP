import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

public class LevelTwo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<Velocity>();
        int j = 310;
        for (int i = 0; i < 10; i++){
            velocity.add(Velocity.fromAngleAndSpeed(j % 360, 7));
            j += 10;
            if(j == 360){
                j += 10;
            }
        }
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Rectangle(new Point(20, 40), 760, 560), Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        double j = 20;
        int k = 0;
        for (int i =0; i < 15; i++){
            blockList.add(new Block(new Point(j, 300), 760 / 15 + 0.7, 20, 1, colors[k]));
            if((i < 6) && (i % 2 == 1)){
                k++;
            }
            else if((i > 7) && (i % 2 == 0)){
                k++;
            }
            j += 760/15 + 0.7;
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
        return 15;
    }

    @Override
    public void drawBackgroundImge(DrawSurface surface) {
        int x = 150;
        int y = 150;
        surface.setColor(new Color(224, 224, 224));
        for (int i = 20; i < 650; i += 5) {
            surface.drawLine(x, y, 0 + i, 300);
        }
        surface.fillCircle(x, y, 50);
        surface.setColor(new Color(204, 204, 0));
        surface.fillCircle(x, y, 40);
        surface.setColor(Color.YELLOW);
        surface.fillCircle(x, y, 30);
    }
}
