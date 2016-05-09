/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicsimpl;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import dk.sdu.mmmi.cbse.entityprocessorspi.IEntityProcessor;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class PhysicsProcessor implements IEntityProcessor
{
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        float x = entity.getX();
        float y = entity.getY();
        float dt = gameData.getDelta();
        // Movement
        Vector2 velocity = entity.getVelocity();
        entity.setX((float)(x + velocity.getX() * dt));
        entity.setY((float)(y + velocity.getY() * dt));
    }

}