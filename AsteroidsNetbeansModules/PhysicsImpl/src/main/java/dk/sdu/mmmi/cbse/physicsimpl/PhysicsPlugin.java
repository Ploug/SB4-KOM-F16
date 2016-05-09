/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicsimpl;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.gamepluginspi.IGamePlugin;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class PhysicsPlugin implements IGamePlugin
{
    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        System.out.println("STARTED PHYSICS IMPLENTATION PLUGIN");
    }

    @Override
    public void stop(GameData gameData)
    {
        System.out.println("STOPPED PHYSICS IMPLENTATION PLUGIN");
    }

}
