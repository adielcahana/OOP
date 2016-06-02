package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean flag;


    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation){
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.flag = true;
        // think about the implementations of doOneFrame and shouldStop.
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
        if(this.sensor.isPressed(this.key)){
            if(!this.flag){
                this.stop = true;
            }
        } else{
            this.flag = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}