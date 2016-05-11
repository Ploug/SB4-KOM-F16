package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.osgicommon.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator
{

    public void start(BundleContext context) throws Exception
    {
        context.registerService(IGamePluginService.class.getName(), new AsteroidPlugin(), null);
        context.registerService(IEntityProcessingService.class.getName(), new AsteroidProcessor(), null);
    }

    public void stop(BundleContext context) throws Exception
    {
        // TODO add deactivation code here
    }

}
