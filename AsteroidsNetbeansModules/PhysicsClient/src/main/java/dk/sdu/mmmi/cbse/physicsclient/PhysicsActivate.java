package dk.sdu.mmmi.cbse.physicsclient;

import dk.sdu.mmmi.cbse.gamepluginspi.IGamePlugin;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

public class PhysicsActivate
{

    public static ComponentContext context;
    public static ServiceReference pluginReference;
    public static IGamePlugin physicsPlugin;

    public void activate(ComponentContext context)
    {

        if (pluginReference != null)
        {
            physicsPlugin = (IGamePlugin)context.locateService(
                    "IGamePlugin", pluginReference);

        }

    }

    public void gotPluginService(ServiceReference reference)
    {
        System.out.println("Bind Service");
        pluginReference = reference;
    }

    public void lostPluginService(ServiceReference reference)
    {
        System.out.println("unbind Service");
        pluginReference = null;
    }
}
