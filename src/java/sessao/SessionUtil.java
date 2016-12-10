
package sessao;

import modelo.Utilizador;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 * Classe responsavel por criar, recuperar e remover dados da sessão
 * @author Helio
 */
@ManagedBean
public class SessionUtil implements  Serializable
{
      private static long serialVersionUID = 1L;
      private String nomeAcesso;
      private String nomeAgecia;
      private String perfil;
      
      @PostConstruct
      public void init()
      {
    
      }
      public static HttpSession getSession()
      {
          FacesContext  facesContext = FacesContext.getCurrentInstance();
          HttpSession hs = (HttpSession) facesContext.getExternalContext().getSession(true);
          hs.setMaxInactiveInterval(-1);
         return  hs;
      }
      
      public static void definirParametro(String nomeParametro, Object valor)
      {
          if(getSession().getAttribute(nomeParametro)==null)
          {
                getSession().setAttribute(nomeParametro, valor);
          }
      }
      
      public static Object obterValor(String nomeParametro)
      {
          return getSession().getAttribute(nomeParametro);
      }

    public String getPerfil() {
         if(obterValor("user")==null)
            return "";
        return ((Utilizador)obterValor("user")).getPerfil();
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
      
      public static void removerParametro(String nomeParametro)
      {
          getSession().removeAttribute(nomeParametro);
      }
      
      public static void invalidar()
      {
          getSession().invalidate();
      }

    public String getNomeAcesso() {
        if(obterValor("user")==null)
            return "";
        return ((Utilizador)obterValor("user")).getNomeUtilizador();
    }

    public void setNomeAcesso(String nomeAcesso) {
        this.nomeAcesso = nomeAcesso;
    }

    public static Utilizador getUserlogado() {
        if(obterValor("user")==null)
            return new Utilizador();//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        return ((Utilizador)obterValor("user"));
    }
      
     public void controloAcesso(String perfil)
    {
//    
        
             RequestContext.getCurrentInstance().execute("acessoRestrito()");
//        else
//            RequestContext.getCurrentInstance().execute("acessoTotal()");
        
    }  

    public String getNomeAgecia() {
        if(obterValor("user")==null)
            return "";
        return ((Utilizador)obterValor("user")).getAgenciaNome();
    }
     
    
}
