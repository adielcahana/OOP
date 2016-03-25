package ass2;

import biuoop.DrawSurface;

public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Point lowerFrameEdge;
    private Point upperFrameEdge;

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

       public Ball(Point center, int r, java.awt.Color color, Point lowerFrameEdge, Point upperFrameEdge) {
           this(center, r, color);
           this.upperFrameEdge = upperFrameEdge;
           this.lowerFrameEdge = lowerFrameEdge;
       }

       public Ball(int x, int y, int r, java.awt.Color color, Point lowerFrameEdge, Point upperFrameEdge) {
           this(new Point(x, y), r, color);
           this.upperFrameEdge = upperFrameEdge;
           this.lowerFrameEdge = lowerFrameEdge;

       }
       // accessors
       public int getX() {
           return (int) this.center.getX();
       }
       public int getY() {
           return (int) this.center.getY();
       }
       
       public Point getCenter() {
           return this.center;
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
       
       public Velocity getVelocity() {
    	   return this.velocity;
       }
       
       public void setVelocity(Velocity v) {
    	   this.velocity = v;
       }
       
       public void setVelocity(double dx, double dy) {
    	   this.velocity = new Velocity(dx, dy);
       }

       public void moveOneStep() {
    	   this.keepInFrame();
           this.center = this.getVelocity().applyToPoint(this.center);
       }
       
       private void keepInFrame(){
   		double  dx = this.getVelocity().getDx();
   		double dy = this.getVelocity().getDy();
   	    if (this.getCenter().getX() - this.getSize() + dx < this.upperFrameEdge.getX()) {
   	    	this.setVelocity(new Velocity(Math.abs(dx), dy));
   			}
   	    if (this.getCenter().getX() + this.getSize() + dx >= this.lowerFrameEdge.getX()) {
   		    this.setVelocity(new Velocity(-Math.abs(dx), dy));
   			}
   	    if (this.getCenter().getY() - this.getSize() + dy < this.upperFrameEdge.getY()) {
   	    	this.setVelocity(new Velocity(dx, Math.abs(dy)));
   			}
   	    if (this.getCenter().getY() + this.getSize() + dy >= this.lowerFrameEdge.getY()) {
   	    	this.setVelocity(new Velocity(dx, -Math.abs(dy)));
   			}
   	   }
}
