package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

public interface IGamePluginService {

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     *
     * @param gameData
     * @param world
     */
    void start(GameData gameData, Map<String, Entity> world);

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     *
     * @param gameData
     */
    void stop(GameData gameData);
}
