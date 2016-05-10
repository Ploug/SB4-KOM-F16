package dk.sdu.mmmi.cbse.osgicommon.services;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import java.util.Map;

public interface IEntityProcessingService
{

    void process(GameData gameData, Map<String, Entity> world, Entity entity);
}
