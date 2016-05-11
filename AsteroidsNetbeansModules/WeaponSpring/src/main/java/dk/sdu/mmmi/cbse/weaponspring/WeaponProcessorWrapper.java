/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weaponspring;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Frederik
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class WeaponProcessorWrapper implements IEntityProcessingService
{
    public static WeaponProcessor reference;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity)
    {
        if (reference != null)
        {
            reference.process(gameData, world, entity);
        }
    }

}
