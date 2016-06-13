package animations;

import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameobjects.Sprite;
import general.Menu;
import general.Selection;

public class MenuAnimation<T> implements Menu<T> {
    private ArrayList<Selection<T>> selections;
    private T status;
    private Sprite background;
    private boolean stop;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;

    public MenuAnimation(Sprite background, KeyboardSensor keyboard) {
        this.background = background;
        this.selections = new ArrayList<Selection<T>>();
        this.status = null;
        this.stop = false;
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.background.drawOn(d);
        d.drawText(50, 50, "Arknoid" , 50);
        int i = 250;
        for (Selection<T> select : this.selections) {
            d.setColor(Color.BLACK);
            d.drawText(160, i, "(" + select.getKey() + ")", 30);
            d.drawText(200, i, select.getMessage() , 30);
            i += 50;
        }
        for (Selection<T> select : this.selections) {
            if (this.keyboard.isPressed(select.getKey())) {
                this.status = select.getReturnVal();
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selection<T>(key, message, returnVal));
    }

    @Override
    public void addSelection(Selection<T> select) {
        this.selections.add(select);
    }

    //    @Override
    //    public void addSubMenu(String key, String message,final Menu<T> subMenu) {
    //        this.addSelection("s", "Play", new T<T>(){
    //            @Override
    //            public T run() {
    //                runner.run(subMenu);
    //                return null;
    //            }
    //        });
    //    }

    @Override
    public T getStatus() {
        return this.status;
    }

    public void resetStatus() {
        this.status = null;
        this.stop = false;
    }
}
