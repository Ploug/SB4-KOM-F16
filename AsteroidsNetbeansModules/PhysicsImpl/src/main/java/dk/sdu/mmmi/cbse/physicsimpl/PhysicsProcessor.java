/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicsimpl;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.ASTEROIDS;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.Vector2;
import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class PhysicsProcessor implements IEntityProcessingService
{
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        float x = entity.getX();
        float y = entity.getY();
        float dt = gameData.getDelta();
        //Wrap
        if (x > gameData.getDisplayWidth())
        {
            x = 0;
        }
        else if (x < 0)
        {
            x = gameData.getDisplayWidth();
        }
        if (y > gameData.getDisplayHeight())
        {
            y = 0;
        }
        else if (y < 0)
        {
            y = gameData.getDisplayHeight();
        }

        //Movement
        Vector2 velocity = entity.getVelocity();
        x = (float)(x + velocity.getX() * dt);
        y = (float)(y + velocity.getY() * dt);

        //Collision
        for (Entity ent : world.values())
        {
            long entitySinceBirth = System.currentTimeMillis() - entity.getBirthTime();
            long entSinceBirth = System.currentTimeMillis() - ent.getBirthTime();
            Entity parent = ent.getParent();
            if (parent != null && parent.getID().equals(entity.getID())
                    || entitySinceBirth < 150 || entSinceBirth < 150 || ent.getID().equals(entity.getID())
                    || !entity.getCollidableTypes().contains(ent.getType())
                    || parent != null && parent.getType().equals(ENEMY) && entity.getType().equals(ASTEROIDS))

            {
                continue;
            }
            if (contains(entity, ent.getShapeX(), ent.getShapeY()))
            {
                entity.setIsHit(true);
            }
        }

        entity.setX(x);
        entity.setY(y);

    }

    private boolean contains(Entity entity, float[] otherShapex, float[] otherShapey)
    {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        boolean b = false;
        for (int k = 0; k < otherShapex.length; k++)
        {
            for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++)
            {
                if ((shapey[i] > otherShapey[k]) != (shapey[j] > otherShapey[k])
                        && (otherShapex[k] < (shapex[j] - shapex[i])
                        * (otherShapey[k] - shapey[i]) / (shapey[j] - shapey[i])
                        + shapex[i]))
                {
                    b = !b;
                }
            }
            if (b)
            {
                return true;
            }
        }
        return false;
    }
}
