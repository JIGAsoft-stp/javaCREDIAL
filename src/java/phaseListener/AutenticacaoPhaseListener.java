
package phaseListener;


import conexao.Conexao;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import validacao.Validation;

/**
 * Verifica-se existe algum usuário logado
 * @author ildo
 */
@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener
{
    Conexao conectar;
    private int i = 0;
    @Override
    public void afterPhase(PhaseEvent event) 
    {
       
        i++;
        FacesContext facesContext = event.getFacesContext();// saber em que pagina se encontra
        UIViewRoot uiViewRoot = facesContext.getViewRoot();// devolve a página atual
        String paginaAtual = uiViewRoot.getViewId();
        boolean paginaAutenticacao = paginaAtual.contains("index.xhtml");
        boolean paginaAutenticacaoD = paginaAtual.contains("PaginaNaoDisponivel.xhtml");
        boolean paginaAutenticacaoA = paginaAtual.contains("AcessoNegado.xhtml");
        boolean paginaSimulacao = paginaAtual.contains("Simulacao.xhtml");
        if(SessionUtil.getUserlogado().getNif() != null && SessionUtil.getUserlogado().getPerfil().equals("1"))
        {
                RequestContext.getCurrentInstance().execute("restricao()");  
                System.out.println("operario");
        }
        if(SessionUtil.getUserlogado().getNif() != null && SessionUtil.getUserlogado().getPerfil().equals("2"))
        {
               RequestContext.getCurrentInstance().execute("perfilAnalista()");  
               System.out.println("analista");
        }   
        if(SessionUtil.getUserlogado().getNif() == null && !paginaAutenticacao)
              Validation.redirecionar("index.xhtml"); 
        
        if(SessionUtil.getUserlogado().getNif() != null && SessionUtil.getUserlogado().getPerfil().equals("1"))
           if(( paginaAtual.contains("Administracao")) || paginaAtual.contains("Relatorio"))
               Validation.redirecionar("AcessoNegado.xhtml");
      
        if(SessionUtil.getUserlogado().getNif() != null && SessionUtil.getUserlogado().getPerfil().equals("2"))
           if(( paginaAtual.contains("Administracao") || paginaAtual.contains("R.ClienteTest")) || paginaAtual.contains("Simulacao"))
               Validation.redirecionar("AcessoNegado.xhtml");
        
      
      if(SessionUtil.getSession().getAttribute("cliente")!=null && !paginaSimulacao && !paginaAtual.contains("R.ClienteTest"))
      {
          SessionUtil.getSession().removeAttribute("cliente");
      }
            
    }

    @Override
    public void beforePhase(PhaseEvent event) 
    {   

       
    }
    
    @Override
    public PhaseId getPhaseId() 
    {
        return PhaseId.RESTORE_VIEW;
    }
    
}
