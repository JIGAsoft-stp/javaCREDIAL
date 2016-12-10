
package bean;

import dao.PagamentoDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mensagem.Mensagem;
import modelo.Comobox;
import modelo.Pagamento;
import modelo.Utilizador;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Helcio
 */
@ManagedBean(name = "pagamento")
@SuppressWarnings("FieldMayBeFinal")
@ViewScoped
public class PagamentoBean implements Serializable
{
     private Pagamento pagamento = new Pagamento();
     private List<Comobox> bancos = new ArrayList<>();
     private PagamentoDao pagamentoDao;
     private String [] campos;
     private Utilizador utilizador = new Utilizador();
     private boolean ativarDesativarBotao = true;
     private double valor = 0;
     private boolean ativarDesativarData = false,ativarDesativarTipoPagamento = true, ativarDesativarBanco = true, ativarDesativarNumeroDoc = true,ativarDesativarDesconto = true;
     private boolean ativarDesativarMontantePagar = true;
     private boolean dataObrigatorio = true;
     private boolean ativarDesatiavarMontanteRestante = true, ativarDesativarFonteP = false;
     private String resultado;
     private String tipo;
     private String estado;
     private String payment;
     private List<Pagamento> listaPagamento = new ArrayList<>();
     private String[] valores;
     private  HttpSession session = ( HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    
     
     public PagamentoBean()
     {
        pagamentoDao = new PagamentoDao();
        bancos = pagamentoDao.listaBancos();     
     }
     
   
    public Pagamento getPagamento() {
        return (pagamento == null) ? pagamento = new Pagamento() : pagamento;
    }

    public boolean isAtivarDesativarMontantePagar() {
        return ativarDesativarMontantePagar;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<Pagamento> getListaPagamento() {
        return listaPagamento;
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isDataObrigatorio() {
        return dataObrigatorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setListaPagamento(List<Pagamento> listaPagamento) {
        this.listaPagamento = listaPagamento;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public boolean isAtivarDesatiavarMontanteRestante() {
        return ativarDesatiavarMontanteRestante;
    }

    public boolean isAtivarDesativarDesconto() {
        return ativarDesativarDesconto;
    }

    public boolean isAtivarDesativarNumeroDoc() {
        return ativarDesativarNumeroDoc;
    }

    public List<Comobox> getBancos() {
        return bancos;
    }

    public boolean isAtivarDesativarBanco() {
        return ativarDesativarBanco;
    }


    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public boolean isAtivarDesativarBotao() {
        return ativarDesativarBotao;
    }
    public void alterarEstadoCampoDiferente()
    {
        ativarDesativarNumeroDoc = false;
        ativarDesativarBanco = false;
        ativarDesativarTipoPagamento = false;
    }
    public boolean isAtivarDesativarData() {
        return ativarDesativarData;
    }

    public boolean isAtivarDesativarTipoPagamento() {
        return ativarDesativarTipoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setCampos(String[] campos) {
        this.campos = campos;
    }
    
    public void alterarEstadoCampo()// Altera o estado dos campos de acordo com a opção escolhida
    {
//  
        System.out.println("fonte de pagamento "+pagamento.getFontePagamento());
        campos = payment.split(";");
            
        if(pagamento.getFontePagamento().equals("S")) // Se a fonte de pagamento for Semelhante, ativa o botão guardar
        {
            tipo ="S";
            pagamento.setNumeroDoc(campos[1]);
            pagamento.setMontantePagar(campos[3]);
            dataObrigatorio = true;
            pagamento.setMontanteRestante(campos[3]); //CAMPOS[3]
            bancos.clear();
             bancos.add(new Comobox(campos[2], pagamentoDao.devolverNome(campos[2])));
            ativarDesativarData = false; // desativa o campo data
            ativarDesativarDesconto = true; // desativa o campo desconto
            pagamento.setTipoP(null);
            ativarDesativarTipoPagamento = true; // desativa o campo tipo de pagamento
            ativarDesativarNumeroDoc = true;
            ativarDesativarBanco = true;
            ativarDesativarMontantePagar = true;
        }
        else
        {// se for diferente ativa os campo numero de documento, banco e tipo de pagamento
            tipo = "D";
            dataObrigatorio = true;
            ativarDesativarData = false;
            pagamento.setNumeroDoc(null);
            ativarDesativarNumeroDoc = false;
            ativarDesativarBanco = false;
            ativarDesativarTipoPagamento = false;
            bancos = pagamentoDao.listaBancos();
           pagamento.setMontantePagar(campos[3]);
            pagamento.setMontanteRestante(campos[3]);
        }
        if(ativarDesativarTipoPagamento == false)
        {
            if(pagamento.getTipoP()!=null )
            {
                if(pagamento.getTipoP().equals("F"))
                {
                    tipo = "F";
                    dataObrigatorio = true;
                    pagamento.setMontantePagar(null);
                    ativarDesativarData = false;
                    ativarDesativarDesconto = true;
                    ativarDesativarMontantePagar = false;
                    ativarDesatiavarMontanteRestante = true;
                }
                else
                {
                     pagamento.setMontantePagar(null);
                }
            }
        }    
    }
   public void calcularValorRestante()
   {
     
       @SuppressWarnings("UnusedAssignment")
       double valorCalculado = 0;
       double valorRestanteReal = Double.valueOf(campos[3].replace(",", "."));
      if( !pagamento.getMontantePagar().isEmpty())
      {
          valor = Double.valueOf(pagamento.getMontantePagar());
          if(valorRestanteReal>=valor)
          {
              valorCalculado = valorRestanteReal-valor;
              pagamento.setMontanteRestante(String.valueOf(valorCalculado));
          }
          else
          {
             
              pagamento.setMontanteRestante(campos[3]);
              pagamento.setMontantePagar(null);
          }
      }
      else
      {
          pagamento.setMontanteRestante(campos[3]);
      }
   }
  
    public boolean isAtivarDesativarFonteP() {
        return ativarDesativarFonteP;
    }
   
   
   public void efetuarPagamento()
   {            
       @SuppressWarnings("UnusedAssignment")
       String result = null;
       FacesContext facesContext = FacesContext.getCurrentInstance();
       RequestContext requestContext = RequestContext.getCurrentInstance();
        System.out.println(pagamento.toString());    
        if(pagamento.getFontePagamento().equals("S"))// se a fonte de pagamento for semelhante
        {
 
            result = pagamentoDao.EfetuarPagamento(Integer.valueOf(campos[4]), pagamento.getNumeroDoc(), Integer.valueOf(campos[2]),"S", Float.parseFloat(pagamento.getMontantePagar().replace(",", ".")), pagamento.getData());
            if(result.equals("true"))
            {
                 Mensagem.msgInfo("Pagamento efetuado com êxito");
                RequestContext.getCurrentInstance().update("forma:growl-error");
                RequestContext.getCurrentInstance().execute("fecharModal()");
                payment = null;
                pagamento.setData(null);
                 pagamento.setFontePagamento(null);
                pagamento.setTipoP(null);
            }
            else
            {
                mapearMensagem(result);
            }

        }
        else
        { 
            if(pagamento.getNumeroDoc() != null && pagamento.getNumeroDoc().length() >0)
            {
                if(pagamento.getTipoP()==null)
                {
                    result = pagamentoDao.EfetuarPagamento(Integer.valueOf(campos[4]), pagamento.getNumeroDoc(), Integer.valueOf(pagamento.getBanco()),"D", Float.parseFloat(pagamento.getMontantePagar().replace(",", ".")), pagamento.getData());
                    if(result.equals("true"))
                    {
                        Mensagem.msgInfo("Pagamento efetuado com êxito");
                        payment = null;
                        RequestContext.getCurrentInstance().update("forma:growl-error");
                        RequestContext.getCurrentInstance().execute("fecharModal()");
                        pagamento.setData(null);
                        pagamento.setFontePagamento(null);
                        pagamento.setTipoP(null);
                    }
                    else
                    {
                          mapearMensagem(result);
                    }
                    
                }
                else
                {
                    if((pagamento.getNumeroDoc() != null && pagamento.getNumeroDoc().length() >0 ) && pagamento.getData()!=null && (pagamento.getMontantePagar()!=null && pagamento.getMontantePagar().length() >0 ))
                    {
                        result = pagamentoDao.EfetuarPagamento(Integer.valueOf(campos[4]), pagamento.getNumeroDoc(), Integer.valueOf(pagamento.getBanco()),"F", Float.parseFloat(pagamento.getMontantePagar().replace(",", ".")), pagamento.getData());
                        if(result.equals("true"))
                         {
                            Mensagem.msgInfo("Pagamento efetuado com êxito");
                            payment = null;
                            RequestContext.getCurrentInstance().update("forma:growl-error");
                            RequestContext.getCurrentInstance().execute("fecharModal()");
                            pagamento.setData(null);
                             pagamento.setFontePagamento(null);
                             pagamento.setTipoP(null);
                         }
                         else
                         {
                             mapearMensagem(result);
                         }
                    }       
                }            
            }
        }
    }
   

   public void cancelar()
   {
       FacesContext context = FacesContext.getCurrentInstance();
       ativarDesativarTipoPagamento = true;
       session.removeAttribute("valor");
       ativarDesativarBanco = true;
       pagamento.setTipoP(null);
       pagamento.setFontePagamento(null);
      RequestContext.getCurrentInstance().update("forma:modalPagamento:tipoP");
      RequestContext.getCurrentInstance().update("forma:modalPagamento:Pbanco");
      RequestContext.getCurrentInstance().update("forma:modalPagamento:fonteP");
    
 
   }
   
   public void verificarFontePagamento()
   {
       if(pagamento.getFontePagamento() == null || pagamento.getFontePagamento().equals(""))
       {
             Mensagem.msgWarm("Selecione a fonte de pagamento");
             RequestContext.getCurrentInstance().update("forma:growl-error");
       }
       else
       {
           efetuarPagamento();
       }
   }
   
   public void mapearMensagem(String msg)
   {
       
       if(msg != null)
       {
           if(msg.contains("Essa prestacao ja foi paga"))
           {
               Mensagem.msgWarm("A prestacao já foi paga");
               RequestContext.getCurrentInstance().update("forma:growl-error");
           }
           else if(msg.contains("O pagamento anterior fazeado e o actual nao fazeado."))
           {
               Mensagem.msgWarm("O pagamento deve ser faseado");
               RequestContext.getCurrentInstance().update("forma:growl-error");
           }
           else if(msg.contains("Valor inserido superio"))
           {
                Mensagem.msgError("Valor a pagar superior ao esperado");
               RequestContext.getCurrentInstance().update("forma:growl-error");
           }
       }
   }
   
}
