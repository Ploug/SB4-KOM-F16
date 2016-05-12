/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Frederik
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class EnemyProcessor implements IEntityProcessingService
{
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        if (!entity.getType().equals(ENEMY))
        {
            return;
        }
        float dt = gameData.getDelta();
        Vector2 velocity = entity.getVelocity();
        Vector2 orientation = entity.getOrientation();
        float acceleration = entity.getAcceleration();
        float deceleration = entity.getDeacceleration();
        float maxSpeed = entity.getMaxSpeed();
        float rotationSpeed = entity.getRotationSpeed();

        // turning
        if (Math.random() > 0.5)
        {
            orientation.rotateDegrees(rotationSpeed * dt);
        }
        else
        {
            orientation.rotateDegrees(-rotationSpeed * dt);
        }

        //Shoot
        if (Math.random() > 0.95)
        {
            entity.setIsShooting(true);
        }
        if (entity.getIsHit())
        {
            entity.setIsHit(false);
            entity.setLife(entity.getLife() - 1);
        }
        if (entity.getLife() < 0)
        {
            EnemyPlugin.removeEnemy(entity);
            EnemyPlugin.addEnemy(EnemyPlugin.createEnemy(gameData));

        }
        // accelerating
        if (Math.random() > 0.1)
        {
            Vector2 accelerationVec = new Vector2(orientation);
            accelerationVec.setMagnitude(acceleration * dt);
            velocity.addVector(accelerationVec);
        }

        // deceleration
        double vec = velocity.getMagnitude();
        if (vec > 0)
        {
            velocity.setMagnitude(velocity.getMagnitude() - deceleration * dt);
        }
        if (vec > maxSpeed)
        {
            velocity.setMagnitude(maxSpeed);
        }

        updateShape(entity);
    }

    private void updateShape(Entity entity)
    {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();
        double radians = Math.toRadians(entity.getOrientation().getAngle());

        shapex[0] = (float)(x + Math.cos(radians) * 16);
        shapey[0] = (float)(y + Math.sin(radians) * 16);

        shapex[1] = (float)(x + Math.cos(radians - 4 * Math.PI / 5) * 12);
        shapey[1] = (float)(y + Math.sin(radians - 4 * Math.PI / 5) * 12);

        shapex[2] = (float)(x + Math.cos(radians + Math.PI) * 10);
        shapey[2] = (float)(y + Math.sin(radians + Math.PI) * 10);

        shapex[3] = (float)(x + Math.cos(radians + 4 * Math.PI / 5) * 12);
        shapey[3] = (float)(y + Math.sin(radians + 4 * Math.PI / 5) * 12);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
