/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.ASTEROIDS;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.Vector2;
import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class AsteroidProcessor implements IEntityProcessingService
{
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        if (entity.getType() != ASTEROIDS)
        {
            return;
        }
        float dt = gameData.getDelta();
        float x = entity.getX();
        float y = entity.getY();
        Vector2 orientation = entity.getOrientation();
        orientation.rotateDegrees(entity.getRotationSpeed() * dt);
        long timeSinceBirth = System.currentTimeMillis() - entity.getBirthTime();
        if (entity.getIsHit() && timeSinceBirth > AsteroidPlugin.IMMORTALITY_TIME)
        {

            AsteroidPlugin.removeAsteroid(entity);
            if (entity.getRadius() > AsteroidPlugin.SPLIT_LIMIT)
            {
                AsteroidPlugin.addAsteroid(AsteroidPlugin.createAsteroid(gameData, entity));
                AsteroidPlugin.addAsteroid(AsteroidPlugin.createAsteroid(gameData, entity));
            }
            if (AsteroidPlugin.currentAsteroids < 5)
            {
                AsteroidPlugin.addAsteroid(AsteroidPlugin.createAsteroid(gameData, null));
            }
        }
        else if (entity.getIsHit())
        {
            entity.setIsHit(false);
        }

        updateShape(entity);
    }

    private void updateShape(Entity entity)
    {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float[] distances = entity.getShapeDistances();
        float x = entity.getX();
        float y = entity.getY();
        float angle = 0;
        double radians = Math.toRadians(entity.getOrientation().getAngle());
        for (int i = 0; i < distances.length; i++)
        {
            shapex[i] = (float)(x + Math.cos(angle + radians) * distances[i]);
            shapey[i] = (float)(y + Math.sin(angle + radians) * distances[i]);
            angle += 2 * 3.1415f / distances.length;
        }
    }
}
