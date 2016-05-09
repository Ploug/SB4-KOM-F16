/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.physicsclient;

import dk.sdu.mmmi.cbse.entityprocessorspi.IEntityProcessor;
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
    public static IEntityProcessor physicsProcessor;

    public void activate(ComponentContext context)
    {
        if (processorReference != null)
        {
            physicsProcessor = (IEntityProcessor)context.locateService(
                    "IEntityProcessor", processorReference);

        }

    }

    public void gotProcessorService(ServiceReference reference)
    {
        System.out.println("processor Bind Service");
        processorReference = reference;
    }

    public void lostProcessorService(ServiceReference reference)
    {
        System.out.println("processor unbind Service");
        processorReference = null;
    }

}
