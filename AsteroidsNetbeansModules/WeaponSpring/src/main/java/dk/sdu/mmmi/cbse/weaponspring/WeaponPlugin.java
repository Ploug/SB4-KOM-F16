/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.weaponspring;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.openide.util.lookup.ServiceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Frederik
 */
@ServiceProvider(service = IGamePluginService.class)
public class WeaponPlugin implements IGamePluginService
{
    public static Set<String> bullets = new HashSet();
    private static Map<String, Entity> world;

    public static void addBullet(Entity bullet)
    {

        bullets.add(bullet.getID());
        world.put(bullet.getID(), bullet);
    }

    public static void removeBullet(Entity bullet)
    {

        bullets.remove(bullet.getID());
        world.remove(bullet.getID());

    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world)
    {
        this.world = world;
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        WeaponProcessorWrapper.reference = (WeaponProcessor)context.getBean("weaponProcess");
    }

    @Override
    public void stop(GameData gameData)
    {
        for (String bulletID : bullets)
        {
            world.remove(bulletID);
        }
    }

}
