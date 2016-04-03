import java.awt.Color;

import biuoop.DrawSurface;

public class Block implements Collidable {
    private Rectangle shape;
    private int maxHits;
    private Color color;

    public int getMaxHits() {
        return maxHits;
    }

    public Block(Point upperLeft, double width, double height, int maxHits, Color color) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.maxHits = maxHits;
        this.color = color;
    }

    public Block(Rectangle shape, int maxHits, Color color) {
        this.shape = shape;
        this.maxHits = maxHits;
        this.color = color;
    }

    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        final int UP = 0;
        final int RIGHT = 1;
        final int DOWN = 2;
        final int LEFT = 3;
        double  dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity newVelocity = null;
        int hitPlace = this.shape.placeInsideMe(collisionPoint);
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
                newVelocity = new Velocity(Math.abs(dx), dy);
                break;
            case LEFT:
                newVelocity = new Velocity(-Math.abs(dx), dy);
                break;
            default:
                System.out.println("Error: no velocity");
        }
        return newVelocity;
    }
    public void drawOn(DrawSurface surface) {
        String hits;
        if (this.maxHits > 0) {
            hits =  Integer.toString(this.maxHits);
    	} else {
    		hits = "x";
    	}
    	surface.setColor(this.color);
        this.shape.drawOn(surface);
        Line[] lines = this.shape.myLines();
        //draw the hit number on Hits remaining
        surface.drawText((int) lines[0].middle().getX() - 3,(int) lines[1].middle().getY() + 7, hits, 20);
//        surface.drawText((int) (this.shape.getUpperLeft().getX() + (this.shape.getWidth() / 2) - 3),
//                         (int) (this.shape.getUpperLeft().getY() + this.shape.getHeight() - 4), hits, 20);
    }
}
