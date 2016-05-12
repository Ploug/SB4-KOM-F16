/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Frederik
 */
@ServiceProvider(service = IGamePluginService.class)
public class EnemyPlugin implements IGamePluginService
{
    public static Set<String> enemies = new HashSet();
    private static Map<String, Entity> world;
    private static GameData gameData;

    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        this.world = world;
        this.gameData = gameData;
        for (int i = 0; i < 1; i++)
        {
            addEnemy(createEnemy(gameData));
        }
    }

    @Override
    public void stop(GameData gameData)
    {
        for (String enemyID : enemies)
        {
            world.remove(enemyID);
        }
    }

    public static void addEnemy(Entity enemy)
    {

        enemies.add(enemy.getID());
        world.put(enemy.getID(), enemy);
    }

    public static void removeEnemy(Entity enemy)
    {

        enemies.remove(enemy.getID());
        world.remove(enemy.getID());
        gameData.increaseScore(500);

    }

    public static Entity createEnemy(GameData gameData)
    {
        Entity enemy = new Entity();
        enemy.setType(ENEMY);

        enemy.setPosition((float)Math.random() * gameData.getDisplayWidth(), (float)Math.random() * gameData.getDisplayHeight());
        HashSet<EntityType> collidableTypes = new HashSet();
        collidableTypes.add(BULLET);
        collidableTypes.add(PLAYER);
        enemy.setCollidableTypes(collidableTypes);
        enemy.setMaxSpeed(150);
        enemy.setAcceleration(200);
        enemy.setDeacceleration(10);

        enemy.setShapeX(new float[4]);
        enemy.setShapeY(new float[4]);
        enemy.setVelocity(new Vector2(0, 0));
        enemy.setOrientation(new Vector2(1, 0));
        enemy.setRotationSpeed(180);
        enemy.setColor(Color.BLUE);
        enemy.setLife(3);

        return enemy;
    }

}
