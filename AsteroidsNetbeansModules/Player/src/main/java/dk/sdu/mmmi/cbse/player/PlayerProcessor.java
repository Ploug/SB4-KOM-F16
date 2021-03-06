package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IEntityProcessingService.class)
public class PlayerProcessor implements IEntityProcessingService
{
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        float dt = gameData.getDelta();
        Vector2 velocity = entity.getVelocity();
        Vector2 orientation = entity.getOrientation();
        float acceleration = entity.getAcceleration();
        float deceleration = entity.getDeacceleration();
        float maxSpeed = entity.getMaxSpeed();
        float rotationSpeed = entity.getRotationSpeed();

        if (entity.getType().equals(PLAYER))
        {
            // turning
            if (gameData.getKeys().isDown(LEFT))
            {
                orientation.rotateDegrees(rotationSpeed * dt);
            }

            if (gameData.getKeys().isDown(RIGHT))
            {
                orientation.rotateDegrees(-rotationSpeed * dt);
            }

            //Shoot
            if (gameData.getKeys().isDown(SPACE))
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
                world.remove(entity.getID());
            }
            // accelerating
            if (gameData.getKeys().isDown(UP))
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
    }

    private void updateShape(Entity entity)
    {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();
        double radians = Math.toRadians(entity.getOrientation().getAngle());

        shapex[0] = (float)(x + Math.cos(radians) * 8);
        shapey[0] = (float)(y + Math.sin(radians) * 8);

        shapex[1] = (float)(x + Math.cos(radians - 4 * Math.PI / 5) * 8);
        shapey[1] = (float)(y + Math.sin(radians - 4 * Math.PI / 5) * 8);

        shapex[2] = (float)(x + Math.cos(radians + Math.PI) * 5);
        shapey[2] = (float)(y + Math.sin(radians + Math.PI) * 5);

        shapex[3] = (float)(x + Math.cos(radians + 4 * Math.PI / 5) * 8);
        shapey[3] = (float)(y + Math.sin(radians + 4 * Math.PI / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
