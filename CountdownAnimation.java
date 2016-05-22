import biuoop.DrawSurface;
import biuoop.Sleeper;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int countLeft;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;
    
    
    // The CountdownAnimation will display the given gameScreen,
    // for numOfSeconds seconds, and on top of them it will show
    // a countdown from countFrom back to 1, where each number will
    // appear on the screen for (numOfSeconds / countFrom) secods, before
    // it is replaced with the next one.
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.countLeft = countFrom;
        this.stop = false;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
    }
       
    public void doOneFrame(DrawSurface d) {
        long startTime = System.currentTimeMillis(); // timing
        gameScreen.drawAllOn(d);
        if (this.countLeft < 0) {
            this.stop = true;
        } else if (this.countLeft > 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(this.countLeft), 32);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO", 32);
        }
        this.countLeft--;
        // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = (long) ((numOfSeconds * 1000) / (countFrom + 1)) - usedTime;
        if (milliSecondLeftToSleep > 0) {
            this.sleeper.sleepFor(milliSecondLeftToSleep);
        }
        //this.sleeper.sleepFor((long) ((numOfSeconds * 1000) / countFrom) );
    }
    
    public boolean shouldStop() { 
       return this.stop;
    }
}
