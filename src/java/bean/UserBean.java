/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UtilizadorDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import mensagem.Mensagem;
import modelo.Comobox;
import modelo.Utilizador;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import sun.misc.IOUtils;
import validacao.Validation;

/**
 *
 * @author AhmedJorge
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable
{
    private Utilizador utilizador = new Utilizador();
    private List<Utilizador> listaUtilizadores;
    private List<Comobox> listaAgencia;
    private Utilizador utilizadorSelected;
    private Utilizador lastUserSelected;
    @SuppressWarnings("FieldMayBeFinal")
    private UtilizadorDao utilizadorDao = new UtilizadorDao();
    private boolean estadoUtilizador = false;
    private String pesquisar;
    private String mensagem;
    private boolean operacao= false;
    private String imagem = "/Images/user2.png";
    @SuppressWarnings("FieldMayBeFinal")
    private  RequestContext requestContext = RequestContext.getCurrentInstance();
    private int i = 0;
    
    public UserBean() 
    {
        this.listaUtilizadores = new ArrayList<>();
        this.listaAgencia = new ArrayList<>();
        this.listaUtilizadores = utilizadorDao.listaUtilizadores(null, "1");
        this.listaAgencia = utilizadorDao.listaAgencia();
    }
    public Utilizador getUtilizador() {
        return (utilizador==null)?new Utilizador():utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }   

    public String getMensagem() {
        return mensagem;
    }

    public List<Utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }

    public boolean isEstadoUtilizador() {
        return estadoUtilizador;
    }

    public void setEstadoUtilizador(boolean estadoUtilizador) {
        this.estadoUtilizador = estadoUtilizador;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setListaUtilizadores(List<Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Utilizador getUtilizadorSelected() {
        return utilizadorSelected;
    }

    public void setUtilizadorSelected(Utilizador utilizadorSelected) {
        this.utilizadorSelected = utilizadorSelected;
    }

    public String getPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }

    public boolean isOperacao() {
        return operacao;
    }

    public void setOperacao(boolean operacao) {
        this.operacao = operacao;
    }

    public List<Comobox> getListaAgencia() {
        return listaAgencia = ((listaAgencia==null) ? new ArrayList<>():listaAgencia);
    }

    public void setListaAgencia(List<Comobox> listaAgencia) {
        this.listaAgencia = listaAgencia;
    }
    
    public void logar()
    {
        UtilizadorDao ud= new UtilizadorDao();
         RequestContext.getCurrentInstance().execute("acessoRestrito()");
        boolean re = ud.logar(utilizador);
        mensagem=((!re)?"Utilizador e/ou senha inválido(s)!":((re&&utilizador.getEstado().equals("0"))?"Utilizador Desativado, conctate o Administrador":""));
    }

     public void handleFileUpload() 
    {
        System.out.println("imagem "+utilizador.getFoto());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        try
        {       
         
            
            byte[] bd = IOUtils.readFully(utilizador.getFoto().getInputStream(), (int) utilizador.getFoto().getSize(), true);
           
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            String arquivo = scontext.getRealPath("/Imagem/");
            
            File f = new File(arquivo);
            for(File dd: f.listFiles())
                if(dd.getName().contains(SessionUtil.getUserlogado().getNif()+"d"))
                    dd.delete();
            
            try (FileOutputStream fos = new FileOutputStream(arquivo+"/"+SessionUtil.getUserlogado().getNif()+"d"+i+".jpg")) 
            {fos.write(bd);}
            
            imagem="/Imagem/"+SessionUtil.getUserlogado().getNif()+"d"+i+".jpg";
            i++;
            RequestContext.getCurrentInstance().update("formRegUser:userImage");
        }
        catch (IOException ex) 
        {
            Logger.getLogger(UtilizadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @SuppressWarnings("null")
    public void registrarUtilizador()
    {   
        if (operacao) {
            RequestContext.getCurrentInstance().execute("$('.modalGestConfig3').show()");
        } else {
            Object result;
            if (utilizador.getFoto() == null) {
                Mensagem.msgWarm("Adicione uma imagem");
                Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
            } 
//            else if (utilizadorDao.verificarNif(utilizador.getNif()) != -1) {
//                requestContext.execute("NIFInvalido()");
//                Mensagem.msgWarm("NIF já existe");
//                Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
             else if (validarNif(utilizador.getNif()) == true) 
            {
                result = utilizadorDao.registrarUtilizador(utilizador);

                if (result == null) {
                    Mensagem.msgError("Os dados não foram guardados");
                    Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
                } else if (result.equals("true")) {
                    Mensagem.msgInfo("Utilizador registrado com sucesso");
                    RequestContext.getCurrentInstance().execute("utilizadorRegistrado()");
                    Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
                } else {
                    Mensagem.msgError(result.toString());
                    Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
                }

            }
        }  
    } 
    
    public boolean validarNif(String nif)
    {
        boolean valido = true;
        if(utilizador.getNif().length()!=9)
        {
            valido = false;
            requestContext.execute("NIFInvalido()");
            Mensagem.msgWarm("NIF deve ter nove(9) dígitos");
            Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
        }
        return valido;
    }
    
    public void pesquisarUtilizador()
    {
        System.out.println(estadoUtilizador);
        if(estadoUtilizador == true)
             listaUtilizadores = utilizadorDao.listaUtilizadores(pesquisar, "0");
        else
            listaUtilizadores = utilizadorDao.listaUtilizadores(pesquisar, "1");
    }
    
    public void alterarConfiguracao()
    {
        utilizador.setNome(getUtilizadorSelected().getNome());//nomeUtilizador
        utilizador.setNif(getUtilizadorSelected().getNif());//nomeUtilizador
        imagem = ((getUtilizadorSelected().getFotoLocal()==null||getUtilizadorSelected().getFotoLocal().isEmpty()) ?
                "/Images/user2.png" :getUtilizadorSelected().getFotoLocal() );//getImagem
        utilizador.setPerfil(getUtilizadorSelected().getPerfil());//getImagem
        utilizador.setIdAgencia(getUtilizadorSelected().getIdAgencia());//agenciaUtilizador
        
        operacao=true;
        lastUserSelected =  new Utilizador();
        lastUserSelected.setNif(getUtilizadorSelected().getNif());//nomeUtilizador
        lastUserSelected.setNome(getUtilizadorSelected().getNome());//nomeUtilizador
        lastUserSelected.setPerfil(getUtilizadorSelected().getPerfil());//getImagem
        lastUserSelected.setIdAgencia(getUtilizadorSelected().getIdAgencia());//agenciaUtilizador
        
        Validation.AtualizarCompoente("formRegUser", "nomeUtilizador");
        Validation.AtualizarCompoente("formRegUser", "nifUtilizador");
        Validation.AtualizarCompoente("formRegUser", "userImage");
        Validation.AtualizarCompoente("formRegUser", "agenciaUtilizador");
        Validation.AtualizarCompoente("formRegUser", "perfilUtilizador");
        System.err.println("fjfhfj hfhfh");
        
        RequestContext.getCurrentInstance().execute("typePerfilAgencia()");
    }
    
    public void alterarConfiguracaoCancelar()
    {
        limparDadosUser();
        
        Mensagem.msgInfo("O processo de alteração dos dados do ultizador foi cancelado!");
        Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
        
        RequestContext.getCurrentInstance().execute("typePerfilAgenciaCancelar()");
    }
    
    public void confTestUser(int i)
    {
        System.err.println("hfhfh --- ggf");
        if (getUtilizadorSelected().getNif() == null || getUtilizadorSelected().getNif().isEmpty()) {
            Mensagem.msgWarm("Selecione o utilizador para alterar a configuração");
            Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
        } else if (i == 2) {
            RequestContext.getCurrentInstance().execute("typeOperacaoRedifinir()");
        } else if (i == 1) {
            RequestContext.getCurrentInstance().execute("typeOperacaoDesativar()");
        }else if(i==3&&operacao)
        {
            alterPerfilAndTrabalha();
        }
        else if(i==3&&!operacao){
            Mensagem.msgWarm("Selecione o utilizador para alterar a configuração");
            Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
        }
    }
    public void alteraConfiRedifinir() {
        utilizadorDao.redifiniraSenhaUsr(getUtilizadorSelected().getNif());
        pesquisarUtilizador();
        Validation.AtualizarCompoente("gestUser", "userTable");
        Mensagem.msgInfo("Senha do utilizador foi redifinida com susseso");
        Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
    }
    public void alteraConfiDesative() {
        utilizadorDao.desativeUser(getUtilizadorSelected().getNif(),estadoUtilizador);
        
        pesquisarUtilizador();
        
        Validation.AtualizarCompoente("gestUser", "userTable");
        Mensagem.msgInfo("Utilizador foi "+((!estadoUtilizador) ? "Ativado" : "desativado")+" com susseso!");
        Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
    }
    
    public void alterPerfilAndTrabalha()
    {
        int te=0;
        String sMensagem="";
        if(!utilizador.getPerfil().equals(lastUserSelected.getPerfil()))
        {
            utilizadorDao.alterPerfil(utilizador);
            sMensagem = "Perfil alterado com sucesso!\n";
            te++;
        }
        
        if(utilizador.getIdAgencia()!=(lastUserSelected.getIdAgencia()))
        {
            utilizadorDao.alterAgencia(utilizador);
            if (te == 1) {
                sMensagem = "Agencia e perfil alterada com sucesso!";
            } else {
                sMensagem = "Agencia alterada com sucesso!";
            }
            te++;
        }
        
        System.err.println(lastUserSelected+"\n"+utilizador);
        
        limparDadosUser();
        
        if (te!=0) {
            Mensagem.msgInfo(sMensagem);
            pesquisarUtilizador();
            Validation.AtualizarCompoente("gestUser", "userTable");
            Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
            RequestContext.getCurrentInstance().execute("typePerfilAgenciaCancelar()");
        } else {
            Mensagem.msgInfo("Nenhuma alteracão foi efectuada!");
            Validation.AtualizarCompoente("formRegUser", "infoUtilizador");
            RequestContext.getCurrentInstance().execute("typePerfilAgenciaCancelar()");
        }
        
    }

    private void limparDadosUser() {
        utilizador.setNome("");//nomeUtilizador
        utilizador.setNif("");
        imagem = ("/Images/user2.png");//getImagem
        operacao=false;
        lastUserSelected = new Utilizador();
        
        Validation.AtualizarCompoente("formRegUser", "nomeUtilizador");
        Validation.AtualizarCompoente("formRegUser", "nifUtilizador");
        Validation.AtualizarCompoente("formRegUser", "userImage");
        Validation.AtualizarCompoente("formRegUser", "agenciaUtilizador");
        Validation.AtualizarCompoente("formRegUser", "perfilUtilizador");
    }
    
    
}
