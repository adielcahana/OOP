package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import animations.LevelsetMenu;
import backgrounds.BackgroundLevelFour;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

public class LevelSetReader {
    private GameFlow game;
    
    public LevelSetReader(GameFlow game) {
        this.game = game;
    }

    private class LevelTask<T> implements Task<T>{
        private List<LevelInformation> levelList;

        @Override
        public T run() {
            game.runLevels(levelList);
            return null;
        }

        public LevelTask(List<LevelInformation> levelList) {
            this.levelList = levelList;
        }
    }

    public Menu<Task<Void>> getLevelSetMenu(KeyboardSensor keyboard, File levelSet) throws FileNotFoundException{
        LineNumberReader reader = null;
        Menu<Task<Void>> menu = new LevelsetMenu<Task<Void>>(new BackgroundLevelFour(), keyboard);
        try {
            reader = new LineNumberReader(
                    new InputStreamReader( // bytes to characters wrapper0
                            new FileInputStream(levelSet)));
            int linenum = reader.getLineNumber();
            String line = reader.readLine();
            String[] parts = null;
            List<LevelInformation> levelList = null;

            while(line != null) {
                if (linenum % 2 == 0) {
                    parts = line.split(":");
                } else {
                    File levelSpecification = new File(line);
                    try {
                        LevelSpecificationReader LSReader = new LevelSpecificationReader();
                        levelList = LSReader.fromReader(new BufferedReader(
                                new InputStreamReader( // bytes to characters wrapper
                                        new FileInputStream(levelSpecification))));
                        Selection<Task<Void>> select = new Selection<Task<Void>>(parts[0], parts[1], new LevelTask<Void>(levelList));
                        menu.addSelection(select);    
                    } catch (FileNotFoundException e) {
                        System.err.println("Unable to find file: " + levelSpecification.getName());
                        throw e;
                    } catch (IOException e) {
                        System.err.println("Failed reading file: " + levelSpecification.getName()
                        + ", message:" + e.getMessage());
                        e.printStackTrace(System.err);
                    }
                }
                linenum = reader.getLineNumber();
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + levelSet.getName());
            throw e;
        } catch (IOException e) {
            System.err.println("Failed reading file: " + levelSet.getName()
            + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + levelSet.getName());
            }
        }
        return menu;
    }
}
