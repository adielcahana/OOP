package ass2;

import biuoop.DrawSurface;

public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;

    ;
    private Point frameEdge;

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
       
       public Ball(Point center, int r, java.awt.Color color, Point frameEdge) {
    	   this(center, r, color);
    	   this.frameEdge = frameEdge;
       }
       
       public Ball(int x, int y, int r, java.awt.Color color, Point frameEdge) {
           this(new Point(x, y), r, color);
           this.frameEdge = frameEdge;
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
    	   Point BackUpCenter = new Point(this.center.getX(), this.center.getY());
           this.center = this.getVelocity().applyToPoint(this.center);
           if (this.isInFrame() == false) {
        	   this.StayInFrame(BackUpCenter);
           }
       }
       
       private boolean isInFrame() {
           return ((this.center.getX() + this.radius <= this.frameEdge.getX()) //upper bound
             && (this.center.getX() - this.radius >= 0)
             && (this.center.getY() + this.radius <= this.frameEdge.getY())
             && (this.center.getY() - this.radius >= 0));
       }
       
       private void StayInFrame(Point BackUpCenter) {
    	   this.velocity = this.velocity.changeDirection();
    	   this.center = this.getVelocity().applyToPoint(BackUpCenter);
       }
}
