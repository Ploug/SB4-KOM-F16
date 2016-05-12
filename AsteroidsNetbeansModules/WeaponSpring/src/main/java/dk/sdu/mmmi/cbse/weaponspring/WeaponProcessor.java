/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weaponspring;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ASTEROIDS;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import java.awt.Color;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Frederik
 */
class WeaponProcessor
{
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        long lastShot = System.currentTimeMillis() - entity.getShootTime();
        if (entity.getType() != BULLET)
        {
            if (entity.getIsShooting() && lastShot > 300)
            {

                WeaponPlugin.addBullet(createBullet(entity, gameData));
                entity.setShootTime(System.currentTimeMillis());

            }
            entity.setIsShooting(false);
        }
        else
        {
            if (entity.getIsHit() || lastShot > 2200)
            {
                WeaponPlugin.removeBullet(entity);
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

        shapex[0] = (float)(x + Math.cos(radians) * 2);
        shapey[0] = (float)(y + Math.sin(radians) * 2);

        shapex[1] = (float)(x + Math.cos(radians - 4 * Math.PI / 2) * 4);
        shapey[1] = (float)(y + Math.sin(radians - 4 * Math.PI / 2) * 4);

        shapex[2] = (float)(x + Math.cos(radians + Math.PI) * 2);
        shapey[2] = (float)(y + Math.sin(radians + Math.PI) * 2);

        shapex[3] = (float)(x + Math.cos(radians + 4 * Math.PI / 2) * 4);
        shapey[3] = (float)(y + Math.sin(radians + 4 * Math.PI / 2) * 4);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private Entity createBullet(Entity shooter, GameData gameData)
    {
        Entity bullet = new Entity();
        bullet.setType(BULLET);
        bullet.setParent(shooter);
        bullet.setPosition(shooter.getX(), shooter.getY());
        HashSet<EntityType> collidableTypes = new HashSet();
        collidableTypes.add(ASTEROIDS);
        collidableTypes.add(PLAYER);
        collidableTypes.add(ENEMY);
        bullet.setCollidableTypes(collidableTypes);

        bullet.setShapeX(new float[4]);
        bullet.setShapeY(new float[4]);
        Vector2 velocity = new Vector2(shooter.getOrientation());
        velocity.setMagnitude(350);
        bullet.setVelocity(velocity);
        bullet.setColor(Color.red);
        bullet.setShootTime(System.currentTimeMillis());

        bullet.setOrientation(new Vector2(shooter.getOrientation()));

        return bullet;
    }

}
