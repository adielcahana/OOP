import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    
    public PauseScreen(KeyboardSensor keyboard, boolean stop) {
        super();
        this.keyboard = keyboard;
        this.stop = stop;
    }

    public PauseScreen(KeyboardSensor k) {
       this.keyboard = k;
       this.stop = false;
    }
    
    public void doOneFrame(DrawSurface d) {
       d.drawText((d.getWidth() / 5) + 2, d.getHeight() / 2, "paused -- press space to continue", 32);
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    
    public boolean shouldStop() { 
        return this.stop; 
    }



}
