
package bean;

import dao.UtilizadorDao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.Timer;
import modelo.Comobox;
import modelo.Utilizador;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import validacao.Validation;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable
{
    private static final long serialVersion = 1L;
    private Utilizador utilizador = new Utilizador();
    private ArrayList<Comobox> listaUser;
    private String mensagem;
    private UtilizadorDao utilizadorDao;
    Timer t;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public LoginBean()
    {
        this.utilizadorDao = new UtilizadorDao();
        if (utilizador.getNif() != null && !utilizador.getNif().isEmpty()) {
            utilizador.setImageLogar(utilizadorDao.getImagemUser(this));
        } else {
            utilizador.setImageLogar("/Images/user2.png");
        } 
    }  
    
    @PostConstruct
    public void cerregar()
    {
        listaUser = utilizadorDao.carregarAllUser(getContexto());
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
        utilizador.setImageLogar(utilizadorDao.getImagemUser(this));
    }

    public ArrayList<Comobox> getListaUser() {
        return listaUser = ((listaUser==null)?new ArrayList<>():listaUser);
    }

    public void setListaUser(ArrayList<Comobox> listaUser) {
        this.listaUser = listaUser;
    }
    
    public void logar()
    {
        UtilizadorDao ud= new UtilizadorDao();
        if((utilizador.getNif() != null && !utilizador.getNif().equals("")) && (utilizador.getSenha() != null && !utilizador.getSenha().equals("")))
        {
             boolean re = ud.logar(utilizador);
             mensagem=((!re)?"Nome de utilizador e/ou palavra-passe inválida(s)!":((re&&utilizador.getEstado().equals("0"))?"Acesso negado ao sistema,Por favor conctate o Administrador":""));
        }
       
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }
    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }
     
    public void alterarSenha()
    {
        @SuppressWarnings("UnusedAssignment")
        String result = null;
        System.out.println(utilizador.toString());
         if(utilizador.getNovaSenha() != null && !utilizador.getNovaSenha().equals("") && utilizador.getConfirmarSenha() != null && !utilizador.getConfirmarSenha().equals(""))
         {
             
             if(!utilizador.getNovaSenha().equals(utilizador.getConfirmarSenha()))
             {
                  RequestContext.getCurrentInstance().execute("mensagem()");
             } 
             else
             {
                 
                 SessionUtil.definirParametro("senha", utilizador.getNovaSenha());
                 utilizadorDao.ativarUtilizador(SessionUtil.getUserlogado().getNif(), utilizador.getNovaSenha());
                 if(SessionUtil.getUserlogado().getPerfil().equals("2"))
                       Validation.redirecionar("Relatorio.xhtml");
                 else
                       Validation.redirecionar("R.ClienteTest.xhtml");
                 
             }
         }
    }
    
    public String getContexto()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        String arquivo = scontext.getRealPath("/Imagem/");
        return arquivo;
    }
    
}
