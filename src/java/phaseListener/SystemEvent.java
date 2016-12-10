
package phaseListener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEventListener;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Helio
 */
public class SystemEvent implements SystemEventListener
{

    @Override
    public void processEvent(javax.faces.event.SystemEvent event) throws AbortProcessingException 
    {
          
        System.out.println("Evento do sistema a ser processado "+event.toString());
    }

    @Override
    public boolean isListenerForSource(Object source) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
