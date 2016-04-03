package factory.step1;

import biuoop.DrawSurface;

import java.awt.*;

public class Circle {

    private double x;
    private double y;
    private double radius;

    public Circle(double radius) {
        this.x = 0;
        this.y = 0;
        this.radius = radius;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void drawOn(DrawSurface ds) {
        ds.setColor(Color.BLUE);
        ds.fillCircle((int) x, (int) y, (int) radius);
        ds.setColor(Color.BLACK);
        ds.drawCircle((int) x, (int) y, (int) radius);
    }
}