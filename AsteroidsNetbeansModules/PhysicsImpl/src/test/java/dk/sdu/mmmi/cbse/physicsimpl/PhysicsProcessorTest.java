/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicsimpl;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Vector2;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Frederik
 */
public class PhysicsProcessorTest
{

    GameData gameData;
    Map<String, Entity> world;
    Entity entity;
    Entity bullet;
    PhysicsProcessor instance;

    public PhysicsProcessorTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {

    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        gameData = new GameData();
        gameData.setDisplayWidth(1000);
        gameData.setDisplayHeight(1000);
        gameData.setDelta(1);
        world = new HashMap();
        entity = new Entity();
        entity.setPosition(0, 0);
        entity.setType(EntityType.PLAYER);
        entity.setVelocity(new Vector2(0, 0));
        world.put(entity.getID(), entity);

        float[] shapeX = new float[4];
        float[] shapeY = new float[4];
        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
        shapeX[0] = 0;
        shapeX[1] = 0;
        shapeX[2] = 10;
        shapeX[3] = 10;

        shapeY[0] = 0;
        shapeY[1] = 10;
        shapeY[2] = 10;
        shapeY[3] = 0;

        Set<EntityType> collidableTypes = new HashSet();
        collidableTypes.add(EntityType.BULLET);
        entity.setCollidableTypes(collidableTypes);

        Set<EntityType> collidableTypesBullet = new HashSet();
        collidableTypes.add(EntityType.PLAYER);
        bullet = new Entity();
        bullet.setType(EntityType.BULLET);
        bullet.setCollidableTypes(collidableTypesBullet);
        bullet.setVelocity(new Vector2(0, 0));
        instance = new PhysicsProcessor();

    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testMovement()
    {

        Vector2 velocity = new Vector2(3, 1);
        velocity.setMagnitude(10);
        entity.setVelocity(velocity);
        float oldX = entity.getX();
        float oldY = entity.getY();
        instance.process(gameData, world, entity);
        double a = oldX - entity.getX();
        double b = oldY - entity.getY();
        double distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        assertTrue(distance >= 9.9 && distance <= 10.1); // in case of precision error

    }

    @Test
    public void testCollisionInside()
    {
        try
        {
            Thread.sleep(200); // cause of collision delay
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        float[] shapeX =
        {
            9
        };
        float[] shapeY =
        {
            9
        };
        bullet.setShapeX(shapeX);
        bullet.setShapeY(shapeY);
        world.put(bullet.getID(), bullet);
        instance.process(gameData, world, entity);

        assertTrue(entity.getIsHit());
        shapeX[0] = 0;
        shapeY[0] = 0;
        bullet.setShapeX(shapeX);
        bullet.setShapeY(shapeY);
        world.put(bullet.getID(), bullet);
        instance.process(gameData, world, entity);

        assertTrue(entity.getIsHit());

    }

    @Test
    public void testCollisionOutside()
    {
        try
        {
            Thread.sleep(200); // cause of collision delay
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        float[] shapeX =
        {
            11
        };
        float[] shapeY =
        {
            10
        };
        bullet.setShapeX(shapeX);
        bullet.setShapeY(shapeY);
        world.put(bullet.getID(), bullet);
        instance.process(gameData, world, entity);
        shapeX[0] = -1;
        shapeY[0] = -1;
        bullet.setShapeX(shapeX);
        bullet.setShapeY(shapeY);
        world.put(bullet.getID(), bullet);
        instance.process(gameData, world, entity);
        assertFalse(entity.getIsHit());

    }

}
