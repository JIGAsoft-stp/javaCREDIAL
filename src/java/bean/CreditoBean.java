
package bean;

import dao.AdministracaoDao;
import dao.CreditoDao;
import dao.PagamentoDao;
import dao.TransferenciaDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mensagem.Mensagem;
import modelo.Banco;
import modelo.Cheque;
import modelo.Comobox;
import modelo.Contas;
import modelo.Relatorio;
import modelo.SequenciaCheque;
import modelo.Transferencia;
import org.primefaces.context.RequestContext;
import validacao.OperacaoData;

/**
 *
 * @author Helio
 */
@ManagedBean
@ViewScoped
 @SuppressWarnings("FieldMayBeFinal")
public class CreditoBean implements Serializable
{
    private ArrayList<Banco> listaBancos;
    private AdministracaoDao administracaoDao;
    private Banco banco = new Banco();
    private PagamentoDao pagamentoDao;
    private List<Comobox> bancos = new ArrayList<>();
    private Transferencia transferencia = new Transferencia();
    private TransferenciaDAO dAO;
    private Relatorio relatorio = new Relatorio();
    private Contas contas = new Contas();
    private SequenciaCheque sequenciaCheque = new SequenciaCheque();
    private List<SequenciaCheque> cheques = new ArrayList<>();
    private ArrayList<Comobox> listaAgencia;
    private ArrayList<Cheque> ListaChequeBancos;
    private List<Banco> cobrancas;
    private List<Banco> saldosD;
    private String totalSaldo;
    
    public CreditoBean()
    {
        administracaoDao = new AdministracaoDao();
        cheques =  administracaoDao.listaChequesDisponiveis();
        cobrancas = new ArrayList<>();
         saldosD = new ArrayList<>();
        listaAgencia = Comobox.loadCombo("VER_AGENCIA", "ID", "AGENCIA");
        listaBancos = new ArrayList<>();
        dAO = new TransferenciaDAO();
        pagamentoDao = new PagamentoDao();

        listaBancos = administracaoDao.listaTodosBanco2();
        bancos = pagamentoDao.listaBancos();
    }

    public ArrayList<Banco> getListaBancos() {
        return listaBancos;
    }

    public Relatorio getRelatorio() {
        return (relatorio == null) ? relatorio = new Relatorio() :relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public List<Banco> getCobrancas() {
        return cobrancas;
    }

    public void setCobrancas(List<Banco> cobrancas) {
        this.cobrancas = cobrancas;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    public Contas getContas() {
        return contas;
    }

    public void setContas(Contas contas) {
        this.contas = contas;
    }

    public List<Comobox> getBancos() {
        return bancos;
    }

    public void setBancos(List<Comobox> bancos) {
        this.bancos = bancos;
    }

    public void setListaBancos(ArrayList<Banco> listaBancos) {
        this.listaBancos = listaBancos;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ArrayList<Comobox> getListaAgencia() {
        return listaAgencia;
    }

    public void setListaAgencia(ArrayList<Comobox> listaAgencia) {
        this.listaAgencia = listaAgencia;
    }

    public SequenciaCheque getSequenciaCheque() {
        return sequenciaCheque=((sequenciaCheque==null)? new SequenciaCheque():sequenciaCheque);
    }

    public void setSequenciaCheque(SequenciaCheque sequenciaCheque) {
        this.sequenciaCheque = sequenciaCheque;
    }

    public void transferir(String param)
    {
        @SuppressWarnings("UnusedAssignment")
        String resultado = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if((transferencia.getBancoDebitar()!= null && transferencia.getBancoDebitar().length()>0) &&
            (transferencia.getBancoCreditar()!=null && transferencia.getBancoCreditar().length()>0) &&
            (transferencia.getValorTransferir()!=null && transferencia.getValorTransferir().length()>0) &&
            (transferencia.getDescricao()!=null && transferencia.getDescricao().length()>0))
        {
              for(int i = 0; i<listaBancos.size();i++)
            {
                if((transferencia.getBancoDebitar().equals(listaBancos.get(i).getId())) &&
                    (!transferencia.getBancoCreditar().equals(listaBancos.get(i).getId())))
                {
                    if(Double.valueOf(transferencia.getValorTransferir())>Double.valueOf(listaBancos.get(i).getSaldoNaoFormatado()))
                    {
                        Mensagem.msgError("Saldo insuficiente para efetuar a movimentação bancária");
                        RequestContext.getCurrentInstance().update("transf:mensagem");
                        RequestContext.getCurrentInstance().execute("limparTransf()");
                        break;
                    }
                }
                else
                {
                    resultado =dAO.registrarMovimentacaoBancaria(Double.valueOf(transferencia.getValorTransferir()), Integer.valueOf(transferencia.getBancoDebitar()),Integer.valueOf(transferencia.getBancoCreditar()), transferencia.getDescricao());
                    if(resultado.equals("true"))
                    {
                        Mensagem.msgInfo("Movimentação bancária efetuada com êxito");
                        listaBancos = administracaoDao.listaTodosBanco2();
                        RequestContext.getCurrentInstance().update("transf:mensagem");
                        RequestContext.getCurrentInstance().execute("limparTransf()");
                        break;
                    }
                }
            }
            
        }
      
    }
    
    
    
    public void DebitoCredito(String param)
    {
        @SuppressWarnings("UnusedAssignment")
        String  resultado = null;
        FacesContext fc = FacesContext.getCurrentInstance();
       
        if((contas.getNumeroDoc() != null && contas.getNumeroDoc().length()>0) && 
           (contas.getValor() != null && contas.getValor().length()>0) &&
            (contas.getBanco() != null && contas.getBanco().length()>0))
        {
             switch(contas.getTipoOperacao())
             {
                 case "D":
                 {
                     resultado = dAO.registrarCredito(Integer.valueOf(contas.getBanco()), Double.valueOf(contas.getValor()),0, contas.getNumeroDoc());
                     if(resultado.equals("true"))
                     {
                        Mensagem.msgInfo("Conta débitada");
                        listaBancos = administracaoDao.listaTodosBanco2();
                        RequestContext.getCurrentInstance().update("transf:mensagem");
                        RequestContext.getCurrentInstance().execute("limparDebitoCredito()");
                     }
                     else
                     {
                         Mensagem.msgError(resultado);
                         RequestContext.getCurrentInstance().update("transf:mensagem");
                     }
                 }
                 break;
                 case "C":
                 {
                      resultado = dAO.registrarCredito(Integer.valueOf(contas.getBanco()),0,Double.valueOf(contas.getValor()), contas.getNumeroDoc());
                     if(resultado.equals("true"))
                     {
                        Mensagem.msgInfo("Conta Créditada");
                        listaBancos = administracaoDao.listaTodosBanco2();
                        RequestContext.getCurrentInstance().update("transf:mensagem");
                        RequestContext.getCurrentInstance().execute("limparDebitoCredito()");
                     }
                 }
                 break;
             }
        }
    }
    
    @SuppressWarnings("null")
    public void regSequenciaCredito()
    {
        if(!getSequenciaCheque().getBanco().equals("xxx0xxx") && !getSequenciaCheque().getAgencia().equals("xxx0xxx")&& 
                !getSequenciaCheque().getInicio().isEmpty() && !getSequenciaCheque().getFim().isEmpty()
                && !getSequenciaCheque().getTotalfolhas().isEmpty())
        {
            CreditoDao cd = new CreditoDao();
            Object o = cd.regSequenciaCheque(getSequenciaCheque());
            if (o != null && o.equals("true")) {
                Mensagem.msgInfo("Nova Sequência de Cheque");
                sequenciaCheque.setBanco(pagamentoDao.devolverNome(sequenciaCheque.getBanco()));
                sequenciaCheque.setAgencia(pagamentoDao.devolverNomeAgencia(sequenciaCheque.getAgencia()));
                cheques.add(sequenciaCheque);
                RequestContext.getCurrentInstance().execute("limparCamposSequencia()");
                RequestContext.getCurrentInstance().update("formAdmAh:admGrowl");
                RequestContext.getCurrentInstance().update("formAdmAh:chequesTabela");
            } else if (o != null && !o.equals("true")) {
                Mensagem.msgWarm(o.toString());
                RequestContext.getCurrentInstance().update("formAdmAh:admGrowl");
            } else {
                Mensagem.msgError("Erro a registrar a Sequencia!");
                RequestContext.getCurrentInstance().update("formAdmAh:admGrowl");
            }
        }
        else
        {
            Mensagem.msgWarm("Campos vazios!!");
            RequestContext.getCurrentInstance().update("formAdmAh:admGrowl");
        }
    }

    public List<SequenciaCheque> getCheques() {
        return cheques;
    }

    public List<Banco> getSaldosD() {
        return saldosD;
    }
    
    public void sequenciaBanco()
    {
        ListaChequeBancos = administracaoDao.getSeguenciaBanco(banco);
    }

    public String getTotalSaldo() {
        return totalSaldo;
    }
    
    public void saldos()
    {
        if(sequenciaCheque.getBanco() != null &&  !sequenciaCheque.getBanco().equals(""))
        {
            saldosD = administracaoDao.saldosDiarios(sequenciaCheque.getBanco(), relatorio.getData(), sequenciaCheque.getAgencia());
        } 
    }
    
    public void listaCobrancas()
    {
         if((sequenciaCheque.getBanco() != null && !sequenciaCheque.getBanco().equals("")) &&
            (relatorio.getDataInicial() != null && relatorio.getDataFinal() != null))
         {
             if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
             {
                    cobrancas = administracaoDao.cobrancaDepositos(Integer.valueOf(sequenciaCheque.getBanco()), relatorio.getDataInicial(), relatorio.getDataFinal(), sequenciaCheque.getAgencia());
                   
             }

         }
    }
}