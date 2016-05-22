import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;

public class LevelFour implements LevelInformation {

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
        return new Background(new Rectangle(new Point(20, 40), 760, 560), new Color(0, 128, 255));
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

    @Override
    public void drawBackgroundImge(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        int x = 600;
        int y = 500;
        for(int i = 0; i < 100; i += 10){
            surface.drawLine(x + i, y, x + i - 30, y + 100);
        }
        surface.setColor(new Color(240, 240, 240));
        surface.fillCircle(x, y, 25);
        surface.setColor(new Color(210, 210, 210));
        surface.fillCircle(x + 15, y -10, 25);
        surface.setColor(new Color(170, 170, 170));
        surface.fillCircle(x + 40 , y + 10, 25);
        surface.setColor(new Color(150, 150, 150));
        surface.fillCircle(x + 60, y -20, 25);
        surface.setColor(new Color(115, 115, 115));
        surface.fillCircle(x + 80, y + 15, 25);
        x = 100;
        y = 400;
        surface.setColor(Color.WHITE);
        for(int i = 0; i < 100; i += 10){
            surface.drawLine(x + i, y, x + i - 15, y + 210);
        }
        surface.setColor(new Color(240, 240, 240));
        surface.fillCircle(x, y, 25);
        surface.setColor(new Color(210, 210, 210));
        surface.fillCircle(x + 15, y -10, 25);
        surface.setColor(new Color(170, 170, 170));
        surface.fillCircle(x + 40 , y + 10, 25);
        surface.setColor(new Color(150, 150, 150));
        surface.fillCircle(x + 60, y -20, 25);
        surface.setColor(new Color(115, 115, 115));
        surface.fillCircle(x + 80, y + 15, 25); 
    }
}
