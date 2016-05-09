/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicslauncher;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.physicsclient.PhysicsProcessActivate;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Frederik
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class ProcessorWrapper implements IEntityProcessingService
{
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        if (PhysicsProcessActivate.physicsProcessor != null)
        {
            PhysicsProcessActivate.physicsProcessor.process(gameData, world, entity);
        }

    }
}
