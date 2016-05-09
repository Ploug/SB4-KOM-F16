/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.gamepluginspi;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public interface IGamePlugin
{
    void start(GameData gameData, Map<String, Entity> world);

    void stop(GameData gameData);
}
