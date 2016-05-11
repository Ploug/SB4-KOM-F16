/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicsclient;

import dk.sdu.mmmi.cbse.osgicommon.services.IEntityProcessingService;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

/**
 *
 * @author Frederik
 */
public class PhysicsProcessActivate
{

    public static ComponentContext context;
    public static ServiceReference processorReference;
    public static IEntityProcessingService physicsProcessor;

    public void activate(ComponentContext context)
    {
        if (processorReference != null)
        {
            physicsProcessor = (IEntityProcessingService)context.locateService(
                    "IEntityProcessingService", processorReference);

        }

    }

    public void gotProcessorService(ServiceReference reference)
    {
        processorReference = reference;
    }

    public void lostProcessorService(ServiceReference reference)
    {
        processorReference = null;
    }

}
