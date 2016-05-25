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
 * @since 2016-04-05 */
public class Ass5Game {

    public static List<LevelInformation> levels(){
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels.add(new LevelOne());
        //levels.add(new LevelTwo());
        //levels.add(new LevelThree());
        //levels.add(new LevelFour());
        return levels;
    }


    /**
     * Main function than run the game.
     * <p>
     * @param args - string of arguments to the main.*/
    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("title", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();   
        
        GameFlow game = new GameFlow(runner, keyboard, gui);
        game.runLevels(levels());
    }
}