package backgrounds;

import java.awt.image.BufferedImage;

import animations.GameLevel;
import biuoop.DrawSurface;
import gameobjects.Sprite;

public class ImgeBackground implements Sprite {

    private BufferedImage imge;

    public ImgeBackground(BufferedImage imge) {
        this.imge = imge;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.drawImage(0, 0, imge);
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
