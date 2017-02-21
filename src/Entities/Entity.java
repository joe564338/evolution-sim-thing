package Entities;

/**
 * Created by Joe on 2/2/2017.
 */
public abstract class Entity {
    protected int xPosition;
    protected int yPosition;
    protected int xVelocity;
    protected int yVelocity;
    protected int xAcceleration;
    protected int yAcceleration;
    protected Entity(int xPosition, int yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
