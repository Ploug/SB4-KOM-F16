/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicslauncher;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.physicsclient.PhysicsActivate;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IGamePluginService.class)
public class PluginWrapper implements IGamePluginService
{
    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        if (PhysicsActivate.physicsPlugin != null)
        {
            PhysicsActivate.physicsPlugin.start(gameData, world);
        }
    }

    @Override
    public void stop(GameData gameData)
    {
        if (PhysicsActivate.physicsPlugin != null)
        {
            PhysicsActivate.physicsPlugin.stop(gameData);
        }
    }

}
