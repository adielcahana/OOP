package animations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameObjects.Ball;
import gameObjects.Velocity;
import levels.LevelInformation;
import listeners.Counter;

public class EndGameAnimation implements Animation {
    private boolean stop;
    private boolean win;
    private KeyboardSensor keyboard;
    private Counter scoreCounter;
    private Counter frameCounter;
    private LevelInformation level;
    private List<Ball> ballList;
    private GameLevel game;


    public EndGameAnimation(boolean win, Counter scoreCounter, LevelInformation level, KeyboardSensor keyboard) {
        this.stop = false;
        this.win = win;
        this.scoreCounter = scoreCounter;
        this.frameCounter = new Counter();
        this.level = level;
        this.ballList = new ArrayList<Ball>();
        this.balls();
        this.keyboard = keyboard;
        this.game = game;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.getBackground().drawOn(d);
        if (win) {
            d.drawText((d.getWidth() / 5) + 2, d.getHeight() / 2, "You Win! Your score is " + this.scoreCounter.getValue(), 32);
            if (this.frameCounter.getValue() > 1000) {
                this.balls();
                this.frameCounter.decrease(this.frameCounter.getValue());
            }
            for (int i = 0; i < this.ballList.size(); i++) {
                Ball b = this.ballList.get(i);
                b.drawOn(d);
                b.timePassed();
                frameCounter.increase(1);
            }
        } else {
            d.drawText((d.getWidth() / 5) + 2, d.getHeight() / 2, "Game Over. Your score is " + this.scoreCounter.getValue(), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true; 
        }
    }

    @Override
    public boolean shouldStop() {
        // TODO Auto-generated method stub
        return stop;
    }

    public void balls() {
        for (int i = 0, j = 0; i < 9; i++ , j+=45) {
            Ball ball = new Ball(100, 100, 5, Color.WHITE);
            ball.setVelocity(Velocity.fromAngleAndSpeed(j , 7));
            ball.addToGame(game);
            this.ballList.add(ball);

        }
        for (int i = 0, j = 0; i < 9; i++ , j+=45) {
            Ball ball = new Ball(300, 400, 5, Color.BLUE);
            ball.setVelocity(Velocity.fromAngleAndSpeed(j , 7));
            ball.addToGame(game);
            this.ballList.add(ball);

        }
        for (int i = 0, j = 0; i < 9; i++ , j+=45) {
            Ball ball = new Ball(500, 700, 5, Color.BLUE);
            ball.setVelocity(Velocity.fromAngleAndSpeed(j , 7));
            ball.addToGame(game);
            this.ballList.add(ball);
        }
    }
}