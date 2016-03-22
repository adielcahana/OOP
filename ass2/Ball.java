package ass2;

import biuoop.DrawSurface;

public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;

     // constructor
       public Ball(Point center, int r, java.awt.Color color) {
           this.center = center;
           this.radius = r;
           this.color = color;
           this.velocity = new Velocity(0,0);
       }
       public Ball(int x, int y, int r, java.awt.Color color) {
           this(new Point(x, y), r, color);
       }
       // accessors
       public int getX() {
           return (int) this.center.getX();
       }
       public int getY() {
           return (int) this.center.getY();
       }

       public int getSize() {
           return this.radius;
       }

       public java.awt.Color getColor() {
           return this.color;
       }

       // draw the ball on the given DrawSurface
       public void drawOn(DrawSurface surface) {
           surface.setColor(this.color);
           surface.fillCircle(this.getX(), this.getY(), this.getSize());
       }
       
       public void setVelocity(Velocity v) {
    	   this.velocity = v;
       }
       public void setVelocity(double dx, double dy) {
    	   this.velocity = new Velocity(dx, dy);
       }
       public Velocity getVelocity() {
    	   return this.velocity;
       }

       public void moveOneStep() {
           this.center = this.getVelocity().applyToPoint(this.center);
       }
       
       public void isInFrame(int width, int height) {
           
       }
}
