package levelcreator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import animations.MenuAnimation;
import backgrounds.MenuBackground;
import biuoop.KeyboardSensor;
import general.GameFlow;
import general.Menu;
import general.Selection;
import general.Task;

/**
 * @author Adiel cahana <adiel.cahana@gmail.com>
 * @version 1.0
 * @since 2016-05-06*/
public class LevelSetReader {

    private GameFlow game;

    /**
     * Instantiates a new level set reader.
     * <p>
     * @param game -  a game flow
     */
    public LevelSetReader(GameFlow game) {
        this.game = game;
    }

    /**
     * @author Adiel cahana <adiel.cahana@gmail.com>
     * @version 1.0
     * @since 2016-05-06
     * private Task class to run the levels */
    private class LevelTask<T> implements Task<T> {
        /** The level list. */
        private List<LevelInformation> levelList;

        /**
         * Instantiates a new level task.
         *
         * @param levelList the level list
         */
        public LevelTask(List<LevelInformation> levelList) {
            this.levelList = levelList;
        }

        @Override
        /**
         * Run the levels.
         * <p>
         * @return null*/
        public T run() {
            game.runLevels(levelList);
            return null;
        }
    }

    /**
     * Gets the level set menu.
     *
     * @param keyboard - keyboard sensor
     * @param levelSet - the level-set path
     * @return the level set menu
     * @throws FileNotFoundException the file not found exception*/
    public Menu<Task<Void>> getLevelSetMenu(KeyboardSensor keyboard, String levelSet) throws FileNotFoundException {
        LineNumberReader reader = null;
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(new MenuBackground(), keyboard);
        try {
            // bytes to characters wrapper
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSet);
            reader = new LineNumberReader(new InputStreamReader(is));
            //count the current line in the file
            int linenum = reader.getLineNumber();
            String line = reader.readLine();
            String[] parts = null;
            List<LevelInformation> levelList = null;
            while (line != null) {
                // get the key and the level name from even lines
                if (linenum % 2 == 0) {
                    parts = line.split(":");
                    // get the level-set path from odd lines
                } else {
                    LevelSpecificationReader lsReader = new LevelSpecificationReader();
                    InputStream is1 = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                    //bytes to characters wrapper
                    levelList = lsReader.fromReader(
                            new BufferedReader(
                                    new InputStreamReader(is1)));
                    Selection<Task<Void>> select =
                            new Selection<Task<Void>>(parts[0], parts[1], new LevelTask<Void>(levelList));
                    menu.addSelection(select);
                }
                linenum = reader.getLineNumber();
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
            throw e;
        } catch (IOException e) {
            System.err.println("Failed reading file" + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + levelSet);
            }
        }
        return menu;
    }
}