/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.services.IGamePluginService;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class AsteroidPlugin implements IGamePluginService
{
    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        System.out.println("STARTED ASTEROIDING");
    }

    @Override
    public void stop(GameData gameData)
    {
        System.out.println("STOPPED ASTEROIDING");

    }
}
