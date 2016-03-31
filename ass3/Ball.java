import biuoop.DrawSurface;
/**
* @author Adiel cahana <adiel.cahana@gmail.com>
* @version 1.0
* @since 2016-03-25 */
public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Point lowerFrameEdge;
    private Point upperFrameEdge;
    private GameEnvironment enviroment;

       /**
       * Ball constructor.
       * <p>
       * @param center - the change in x axis.
       * @param r - the change in y axis.
       * @param color - the change in y axis.*/
       public Ball(Point center, int r, java.awt.Color color) {
           this.center = center;
           this.radius = r;
           this.color = color;
           this.velocity = new Velocity(0, 0);
       }
       /**
        Ball constructor.
       * <p>
       * @param x - coordinate.
       * @param y - coordinate.
       * @param r - the change in y axis.
       * @param color - the change in y axis.*/
       public Ball(int x, int y, int r, java.awt.Color color) {
           this(new Point(x, y), r, color);
       }
       /**
       Ball constructor.
       * <p>
       * @param center - the change in x axis.
       * @param r - the change in y axis.
       * @param color - the change in y axis.
       * @param lowerFrameEdge - low right edge
       * @param upperFrameEdge - upper left edge
       * @param enviroment - the game envitoment*/
       public Ball(Point center, int r, java.awt.Color color, Point lowerFrameEdge, Point upperFrameEdge, GameEnvironment enviroment) {
           this(center, r, color);
           this.upperFrameEdge = upperFrameEdge;
           this.lowerFrameEdge = lowerFrameEdge;
           this.enviroment = enviroment;
       }
       /**
       Ball constructor.
       * <p>
       * @param x - coordinate.
       * @param y - coordinate.
       * @param r - the change in y axis.
       * @param color - the change in y axis.
       * @param lowerFrameEdge - low right edge
       * @param upperFrameEdge - upper left edge
       * @param enviroment - the game envitoment*/
       public Ball(int x, int y, int r, java.awt.Color color, Point lowerFrameEdge, Point upperFrameEdge, GameEnvironment enviroment) {
           this(new Point(x, y), r, color);
           this.upperFrameEdge = upperFrameEdge;
           this.lowerFrameEdge = lowerFrameEdge;
           this.enviroment = enviroment;
       }

       /**
        * x coordinate query.
        * <p>
        * @return x - x coordinate*/
       public int getX() {
           return (int) this.center.getX();
       }
       /**
        * y coordinate query.
        * <p>
        * @return y - y coordinate*/
       public int getY() {
           return (int) this.center.getY();
       }
       /**
        * center point query.
        * <p>
        * @return point - center*/
       public Point getCenter() {
           return this.center;
       }
       /**
        * radius query.
        * <p>
        * @return this.radius - radius*/
       public int getSize() {
           return this.radius;
       }
       /**
        * color query.
        * <p>
        * @return Color - ball color*/
       public java.awt.Color getColor() {
           return this.color;
       }
       /**
        * drawing ball method on the given DrawSurface.
        * <p>
        * @param surface - the given DrawSurface*/
       public void drawOn(DrawSurface surface) {
           surface.setColor(this.color);
           surface.fillCircle(this.getX(), this.getY(), this.getSize());
       }
       /**
        * velocity query.
        * <p>
        * @return velocity - this.velocity*/
       public Velocity getVelocity() {
           return this.velocity;
       }
       /**
        * velocity setter.
        * <p>
        * @param v - a give new velocity*/
       public void setVelocity(Velocity v) {
           this.velocity = v;
       }
       /**
        * velocity setter.
        * <p>
        * @param dx - change in x axis
        * @param dy - change in y axis */
       public void setVelocity(double dx, double dy) {
           this.velocity = new Velocity(dx, dy);
       }
       /**
        * ball animation step.
        * <p>
        * changes the center according to the velocity
        * <p>
        * keeps the ball in its boundaries*/
       public void moveOneStep() {
           Line trajectory = enviroment.getTrajectory(this.getCenter(), this.velocity);
           CollisionInfo info = enviroment.getClosestCollision(trajectory);
           
           if (null == info) {
        	   return;
           }
           
           AbstractArtDrawing.drawLine(trajectory, this.enviroment.d);
           AbstractArtDrawing.drawPoint(info.collisionPoint(),  this.enviroment.d);
           if (info != null) {
        	   this.keepInFrame(info);
           }
           this.center = this.getVelocity().applyToPoint(this.center);
       }
       /**
        * changes the ball velocity according to its position.*/
       private void keepInFrame(CollisionInfo info) {
           double dx = getVelocity().getDx();
           double dy = getVelocity().getDy();
           double speed = Math.sqrt(dx*dx + dy*dy);
                    
           if (info.collisionPoint().distance(this.center) - speed < this.getSize()) {
               this.velocity = info.collisionObject().hit(info.collisionPoint(), this.velocity);
          }
     }
}
