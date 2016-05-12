/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Frederik
 */
public class AsteroidPluginTest
{

    GameData gameData;
    Map<String, Entity> world;

    public AsteroidPluginTest()
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
        world = new HashMap();
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of start method, of class AsteroidPlugin.
     */
    @Test
    public void testStart()
    {
        System.out.println("start");
        AsteroidPlugin instance = new AsteroidPlugin();
        instance.start(gameData, world);
    }

    /**
     * Test of stop method, of class AsteroidPlugin.
     */
    @Test
    public void testStop()
    {
        System.out.println("stop");
        int startLength = world.size();

        AsteroidPlugin instance = new AsteroidPlugin();
        instance.start(gameData, world);

        instance.stop(gameData);
        assertTrue(startLength == world.size());
    }

    /**
     * Test of createAsteroid method, of class AsteroidPlugin.
     */
    @Test
    public void testCreateAsteroid()
    {
        System.out.println("createAsteroid");
        Entity parent = null;
        Entity result = AsteroidPlugin.createAsteroid(gameData, parent);
        assertNotNull(result);
    }

}
