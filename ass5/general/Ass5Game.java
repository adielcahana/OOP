package general;
import java.util.ArrayList;
import java.util.List;

import animations.AnimationRunner;
import biuoop.KeyboardSensor;
import levels.LevelFour;
import levels.LevelInformation;
import levels.LevelOne;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-22-05 */
public class Ass5Game {

    /**
     * Create ArrayList of levels.
     * @return list of the levels. */
    public static List<LevelInformation> levels() {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // Add all the levels to the ArrayList.
        levels.add(new LevelOne());
        levels.add(new LevelTwo());
        levels.add(new LevelThree());
        levels.add(new LevelFour());
        return levels;
    }


    /**
     * Main function than run the game.
     * <p>
     * @param args - string of arguments to the main.*/
    public static void main(String[] args) {

        // Create a new GameFlow and run it.
        GameFlow game = new GameFlow();
        game.runLevels(levels());
    }
}