import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

public class LevelOne implements LevelInformation{

    private Background background;

    public LevelOne(){
        ArrayList<Drawable> imge = new ArrayList<Drawable>();
        imge.add(new Line(520, 210, 420, 210));
        imge.add(new Line(280, 210, 380, 210));
        imge.add(new Line(400, 90, 400, 190));
        imge.add(new Line(400, 330, 400, 230));
        imge.add(new Circle(400, 210, 100, Color.BLUE));
        imge.add(new Circle(400, 210, 75, Color.BLUE));
        imge.add(new Circle(400, 210, 50, Color.BLUE));
        this.background = new Background(new Rectangle(new Point(20, 40), 760, 560), Color.BLACK, imge);
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

    public void drawBackgroundImge(DrawSurface surface) {
        /*surface.setColor(Color.blue);
        surface.drawLine(520, 210, 420, 210);
        surface.drawLine(280, 210, 380, 210);
        surface.drawLine(400, 90, 400, 190);
        surface.drawLine(400, 330, 400, 230);
        surface.drawCircle(400, 210, 100);
        surface.drawCircle(400, 210, 75);
        surface.drawCircle(400, 210, 50);*/
    }
}
