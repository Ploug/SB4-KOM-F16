package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.EntityType;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.ASTEROIDS;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.ENEMY;
import static dk.sdu.mmmi.cbse.osgicommon.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.Vector2;
import dk.sdu.mmmi.cbse.osgicommon.services.IGamePluginService;
import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService
{

    private Map<String, Entity> world;
    private Entity player;

    public EntityPlugin()
    {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        this.world = world;
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.put(player.getID(), player);
    }

    private Entity createPlayerShip(GameData gameData)
    {
        Entity playerShip = new Entity();
        playerShip.setType(PLAYER);

        playerShip.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        HashSet<EntityType> collidableTypes = new HashSet();
        collidableTypes.add(ASTEROIDS);
        collidableTypes.add(BULLET);
        collidableTypes.add(ENEMY);
        playerShip.setCollidableTypes(collidableTypes);
        playerShip.setMaxSpeed(300);
        playerShip.setAcceleration(200);
        playerShip.setDeacceleration(10);

        playerShip.setShapeX(new float[4]);
        playerShip.setShapeY(new float[4]);
        playerShip.setVelocity(new Vector2(0, 0));
        playerShip.setOrientation(new Vector2(1, 0));
        playerShip.setRotationSpeed(180);
        playerShip.setColor(Color.green);
        playerShip.setLife(25);

        playerShip.setRadius(4);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData)
    {
        // Remove entities
        world.remove(player.getID());
    }

}
