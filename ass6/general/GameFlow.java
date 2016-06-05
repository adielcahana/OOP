package general;
import java.io.File;
import java.io.IOException;
import java.util.List;

import animations.AnimationRunner;
import animations.EndGameAnimation;
import animations.GameLevel;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import listeners.Counter;
import score.HighScoresTable;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-18 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private biuoop.GUI gui;


    /**
     * Constructor - Create the GUI the AnimationRunner and KeyboardSensor of the game.. */
    public GameFlow() {
        this.gui = new biuoop.GUI("title", 800, 600);
        this.animationRunner = new AnimationRunner(this.gui, 60);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Run the game.
     * Get ArrayList of levels and run it in a loop as long left lives
     * <p>
     * @param levels - the levels ArrayList. */
    public void runLevels(List<LevelInformation> levels) {
        // Create the score counter and lives counter.
        Counter scoreCounter = new Counter();
        Counter numberOfLives = new Counter(1);
        HighScoresTable table = new HighScoresTable(5);
        File file = new File("./highscore.txt");
        try {
            table.load(file);
        } catch (IOException e) {
            try {
                table.save(file);
            } catch (IOException e1) {
                System.err.println("Unable to find file: " + file.getName());
            }
        }
        int i;
        for (i = 0; i < levels.size(); i++) {

            // Create the level
            GameLevel level = new GameLevel(levels.get(i), this.keyboard,
                    this.animationRunner, this.gui, scoreCounter, numberOfLives);


            // Initialize the level.
            level.initialize();

            // while the level left blocks and still have lives run one turn.
            while (level.haveBlocks() && numberOfLives.getValue() > 0) {
                level.playOneTurn();
            }

            // if no more lives game end.
            if (numberOfLives.getValue() == 0) {
                this.animationRunner.run(
                        new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                                new EndGameAnimation(false, scoreCounter, this.keyboard)));
                table.newScore(gui, scoreCounter);
                break;
            }
        }
        if (i > levels.size()) {
            this.animationRunner.run(
                    new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                            new EndGameAnimation(true, scoreCounter, this.keyboard)));
            table.newScore(gui, scoreCounter);
        }
        this.animationRunner.run(
                new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(table, KeyboardSensor.SPACE_KEY, keyboard)));
        try {
            table.save(file);
        } catch (IOException e) {
            System.err.println("Unable to find file: " + file.getName());
        }
        this.gui.close();
    }
}