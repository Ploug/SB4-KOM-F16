package dk.sdu.mmmi.cbse.osgicommon.services;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import java.util.Map;

public interface IGamePluginService
{
    void start(GameData gameData, Map<String, Entity> world);

    void stop(GameData gameData);
}
