/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ASTEROIDS;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Frederik
 */
public class AsteroidPlugin implements IGamePluginService
{
    public static final int START_AMOUNT = 5;
    public static final int MAX_SPEED = 140;
    public static final int MIN_SPEED = 50;
    public static final int ASTEROID_CORNERS = 17;
    public static final int RADIUS = 35;
    public static final int BASE_SCORE = 10;
    private static final float SHRINKAGE = 0.6f;
    public static final double SPLIT_LIMIT = (RADIUS * Math.pow(SHRINKAGE, 2)) + 1; // 2 splits
    public static final long IMMORTALITY_TIME = 1000;
    public static int currentAsteroids = 0;
    public static Map<String, Entity> asteroids = new HashMap();
    private static Map<String, Entity> world;
    private static final int MAX_ROTATION_SPEED = 700;
    private static GameData gameData;

    public static void addAsteroid(Entity asteroid)
    {
        asteroids.put(asteroid.getID(), asteroid);
        world.put(asteroid.getID(), asteroid);
        currentAsteroids = asteroids.size();
    }

    public static void removeAsteroid(Entity asteroid)
    {

        asteroids.remove(asteroid.getID());
        world.remove(asteroid.getID());
        gameData.increaseScore(RADIUS - (int)asteroid.getRadius() + BASE_SCORE);
        currentAsteroids = asteroids.size();

    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        this.gameData = gameData;
        this.world = world;
        for (int i = 0; i < START_AMOUNT; i++)
        {
            Entity asteroid = createAsteroid(gameData);
            addAsteroid(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData)
    {
        for (Entity asteroid : asteroids.values())
        {
            world.remove(asteroid.getID());
        }
    }

    public static Entity createAsteroid(GameData gameData, Entity parent)
    {

        Entity asteroid = new Entity();
        float x = (float)(Math.random() * gameData.getDisplayWidth());
        float y = (float)(Math.random() * gameData.getDisplayHeight());
        float radius = RADIUS;
        int corners = ASTEROID_CORNERS;
        if (parent != null)
        {
            x = parent.getX();
            y = parent.getY();
            radius = parent.getRadius() * SHRINKAGE;
//            corners = (int)((float)(parent.getShapeDistances().length) * SHRINKAGE);  maybe its better this way
        }

        HashSet<EntityType> collidableTypes = new HashSet();
        collidableTypes.add(PLAYER);
        collidableTypes.add(BULLET);
        asteroid.setCollidableTypes(collidableTypes);
//        collidableTypes.add(ENEMY);   maybe?

        asteroid.setType(ASTEROIDS);
        asteroid.setPosition(x, y);

        Vector2 velocity = new Vector2(0, 1);
        velocity.rotateDegrees(Math.random() * 360);
        velocity.setMagnitude(MIN_SPEED + Math.random() * (MAX_SPEED - MIN_SPEED));
        asteroid.setVelocity(velocity);

        asteroid.setOrientation(new Vector2(1, 0));
        asteroid.setRotationSpeed((int)(Math.random() * MAX_ROTATION_SPEED));

        asteroid.setShapeX(new float[corners]);
        asteroid.setShapeY(new float[corners]);
        float[] distances = new float[corners];
        asteroid.setRadius(radius);
        float min = radius / 2;
        float max = radius;
        for (int i = 0; i < corners; i++)
        {
            distances[i] = (float)(min + Math.random() * (max - min));
        }
        asteroid.setShapeDistances(distances);

        return asteroid;
    }

    private static Entity createAsteroid(GameData gameData)
    {
        return createAsteroid(gameData, null);
    }
}
