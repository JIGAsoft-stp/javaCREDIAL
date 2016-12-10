package bean;

import modelo.Utilizador;
import java.io.Serializable;
import dao.UtilizadorDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import validacao.Validation;






@ManagedBean
@ViewScoped
public class UtilizadorBean implements Serializable
{ 
    private Utilizador utilizador = new Utilizador();
    private UtilizadorDao utilizadorDao = new UtilizadorDao();
    private  RequestContext requestContext = RequestContext.getCurrentInstance();
    private int i = 0;
    
    public Utilizador getUtilizador() {
        return (utilizador==null)?new Utilizador():utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    } 
    
    public void terminarSessao()
    {
        if (SessionUtil.getUserlogado().getLogin()!=null) 
        {
            utilizadorDao.logOut(Integer.valueOf(SessionUtil.getUserlogado().getLogin()));
            SessionUtil.removerParametro("user");
        }
        Validation.redirecionar("index.xhtml");
    }
    
    public void AlterarSenha(int p)
    {
        String result;
        if((utilizador.getSenhaAtual()!= null && !utilizador.getSenhaAtual().equals("")) &&
                (utilizador.getNovaSenha()!= null && !utilizador.getNovaSenha().equals("")) &&
                (utilizador.getConfirmarSenha() != null && !utilizador.getConfirmarSenha().equals("")))
        {
            if(!utilizador.getSenhaAtual().equals(SessionUtil.obterValor("senha").toString()))
                    RequestContext.getCurrentInstance().execute("senhaAtualInvalida()");
           else
            {
                 if(!utilizador.getNovaSenha().equals(utilizador.getConfirmarSenha()))
                       RequestContext.getCurrentInstance().execute("senhasDiferentes()");
                 else
                 {
                     result = utilizadorDao.alterarSenha(SessionUtil.getUserlogado().getNif(),utilizador.getSenhaAtual(),utilizador.getNovaSenha());
                     if(result != null && result.equals("true"))
                     {
                         SessionUtil.removerParametro("senha");
                         SessionUtil.definirParametro("senha", utilizador.getNovaSenha());
                         RequestContext.getCurrentInstance().execute("senhaAlterada()");
                     }
                           
                 }          
            }
        }
      
        
    }
    
    public void senhaAtual()
    {
        if(utilizador.getSenhaAtual()!= null && !utilizador.getSenhaAtual().equals(""))
        {
            if(!utilizador.getSenhaAtual().equals(SessionUtil.obterValor("senha").toString()))
            {
                RequestContext.getCurrentInstance().execute("senhaAtualInvalida()");
            }
            else
                RequestContext.getCurrentInstance().execute("senhaCorBordaVerde()");
        }
    }
    
 
}
