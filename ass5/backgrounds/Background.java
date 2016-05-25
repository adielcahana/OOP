package backgrounds;

import animations.GameLevel;

public interface Background {

    public void addToGame(GameLevel game);
    
    public void removeFromGame(GameLevel game);
}
