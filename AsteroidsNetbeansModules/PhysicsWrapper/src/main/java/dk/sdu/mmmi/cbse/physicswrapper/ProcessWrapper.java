/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicswrapper;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.physicsclient.PhysicsProcessActivate;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Frederik
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class ProcessWrapper implements IEntityProcessingService
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
