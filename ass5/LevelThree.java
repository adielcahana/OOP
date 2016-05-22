import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

public class LevelThree implements LevelInformation {
    
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
        return new Background(new Rectangle(new Point(20, 40), 760, 560), new Color(0,102,0));
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

    @Override
    public void drawBackgroundImge(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle(50, 450, 110, 200);
        surface.setColor(Color.DARK_GRAY);
        surface.fillRectangle(50, 450, 10, 200);
        surface.fillRectangle(150, 450, 10, 200);
        surface.fillRectangle(50, 440, 110, 10);
        int i =0;
        int x = 70;
        for (i =0; i < 4; i++){
            surface.fillRectangle(x, 450, 10, 200);
            x += 20;
        }
        int y = 470;
        for(i = 0; i < 4; i++){
            surface.fillRectangle(50, y, 110, 10);
            y += 35;
        }

        surface.setColor(new Color(64, 64, 64));
        surface.fillRectangle(85, 390, 30, 50);
        surface.setColor(new Color(96, 96, 96));
        surface.fillRectangle(95, 290, 10, 100);
        x = 100;
        y = 280;
        surface.setColor(new Color(255, 204, 204));
        surface.fillCircle(x, y, 10);
        surface.setColor(new Color(225, 51, 51));
        surface.fillCircle(x, y, 7);
        surface.setColor(Color.WHITE);
        surface.fillCircle(x, y, 3);
    }
}
