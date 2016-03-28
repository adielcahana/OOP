import java.awt.Color;

import biuoop.DrawSurface;

public class Block implements Collidable {
    private Rectangle shape;
    private int maxHits;
    public Block(Point upperLeft, double width, double height, int maxHits) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.maxHits = maxHits;
    }

    public Block(Rectangle shape, int maxHits) {
        this.shape = shape;
        this.maxHits = maxHits;
    }

    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        final int UP = 1;
        final int RIGHT = 2;
        final int DOWN = 3;
        final int LEFT = 4;
        double  dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity newVelocity = null;
        int hitPlace = this.shape.pointPlace(collisionPoint);
        if (maxHits > 0) {
            maxHits--;
        }
        switch(hitPlace) {
            case UP:
                newVelocity = new Velocity(dx, -Math.abs(dy));
                break;
            case DOWN:
                newVelocity = new Velocity(dx, Math.abs(dy));
                break;
            case RIGHT:
                newVelocity = new Velocity(-Math.abs(dx), dy);
                break;
            case LEFT:
                newVelocity = new Velocity(-Math.abs(dx), dy);
                break;
        }
        return newVelocity;
    }
    public void drawOn(DrawSurface surface, Color color) {
        this.shape.drawOn(surface, color);
    }
}