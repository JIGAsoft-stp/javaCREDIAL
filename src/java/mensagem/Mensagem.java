
package mensagem;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Helio
 * 
 */
public class Mensagem
{
    public static void msgInfo(String mensagem)
    {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    public static void msgError(String mensagem)
    {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    
     public static void msgWarm(String mensagem)
    {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,mensagem,mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
     public static void msgFatalError(String mensagem)
    {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,mensagem,mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, facesMessage);
    }
    
}
