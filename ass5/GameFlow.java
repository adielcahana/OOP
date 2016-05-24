import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private biuoop.GUI gui;
    private Counter scoreCounter;
    private Counter numberOfLives;

    
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard, biuoop.GUI gui) {
        this.animationRunner = runner;
        this.keyboard =keyboard;
        this.gui = gui;
        this.scoreCounter = new Counter();
        this.numberOfLives = new Counter(7);
    }

    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.animationRunner, this.gui, this.scoreCounter, this.numberOfLives);

            level.initialize();

            while (level.HaveBlocks() && this.numberOfLives.getValue() > 0) {
                level.playOneTurn();
            }

            if (this.numberOfLives.getValue() == 0) {
                break;
            }
        }
    }
}