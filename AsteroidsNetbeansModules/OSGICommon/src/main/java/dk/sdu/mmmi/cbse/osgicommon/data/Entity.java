package dk.sdu.mmmi.cbse.osgicommon.data;

import java.awt.Color;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public final class Entity implements Serializable
{
    private final UUID ID = UUID.randomUUID();
    private EntityType type;
    private float x;
    private float y;
    private Vector2 velocity;
    private Vector2 orientation;
    private float maxSpeed;
    private float acceleration;
    private float deacceleration;
    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private int rotationSpeed;
    private int life;
    private Set<EntityType> collidableTypes;
    private float radius;
    private boolean isHit = false;
    private float expiration;
    private float[] shapeDistances;
    private long birthTime;
    private boolean isShooting;
    private long shootTime;
    private Color color;
    private Entity parent;

    public Entity getParent()
    {
        return this.parent;
    }

    public void setParent(Entity parent)
    {
        this.parent = parent;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Entity()
    {
        birthTime = System.currentTimeMillis();

    }

    public void setIsShooting(boolean shooting)
    {
        this.isShooting = shooting;
    }

    public boolean getIsShooting()
    {
        return this.isShooting;
    }

    public void setShootTime(long shootTime)
    {
        this.shootTime = shootTime;
    }

    public long getShootTime()
    {
        return this.shootTime;
    }

    public long getBirthTime()
    {
        return this.birthTime;
    }

    public void setCollidableTypes(Set<EntityType> collidableTypes)
    {
        this.collidableTypes = collidableTypes;
    }

    public Set<EntityType> getCollidableTypes()
    {
        return collidableTypes;
    }

    public void reduceExpiration(float delta)
    {
        this.expiration -= delta;
    }

    public float getExpiration()
    {
        return expiration;
    }

    public void setExpiration(float value)
    {
        this.expiration = value;
    }

    public boolean getIsHit()
    {
        return isHit;
    }

    public void setIsHit(boolean hit)
    {
        this.isHit = hit;
    }

    public void setRadius(float r)
    {
        this.radius = r;
    }

    public float getRadius()
    {
        return radius;
    }

    public int getLife()
    {
        return life;
    }

    public void setLife(int life)
    {
        this.life = life;
    }

    public String getID()
    {
        return ID.toString();
    }

    public void setType(EntityType type)
    {
        this.type = type;
    }

    public EntityType getType()
    {
        return type;
    }

    public void setOrientation(Vector2 orientation)
    {
        this.orientation = orientation;
    }

    public Vector2 getOrientation()
    {
        return orientation;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getMaxSpeed()
    {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(float acceleration)
    {
        this.acceleration = acceleration;
    }

    public float getDeacceleration()
    {
        return deacceleration;
    }

    public void setDeacceleration(float deacceleration)
    {
        this.deacceleration = deacceleration;
    }

    public float[] getShapeX()
    {
        return shapeX;
    }

    public void setShapeX(float[] shapeX)
    {
        this.shapeX = shapeX;
    }

    public float[] getShapeY()
    {
        return shapeY;
    }

    public void setShapeY(float[] shapeY)
    {
        this.shapeY = shapeY;
    }

    public int getRotationSpeed()
    {
        return rotationSpeed;
    }

    public float[] getShapeDistances()
    {
        return shapeDistances;
    }

    public void setShapeDistances(float[] shapeDistances)
    {
        this.shapeDistances = shapeDistances;
    }

    public void setRotationSpeed(int rotationSpeed)
    {
        this.rotationSpeed = rotationSpeed;
    }

}
