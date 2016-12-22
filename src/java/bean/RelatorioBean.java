
package bean;

import Export.RelatorioConverter;
import dao.ClienteDao;
import dao.CreditoDao;
import dao.ListagemDao;
import dao.PagamentoDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Comobox;
import modelo.Relatorio;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import validacao.OperacaoData;
import validacao.Validation;

/**
 *
 * @author Helio
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("FieldMayBeFinal")
public class RelatorioBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Relatorio relatorio = new Relatorio();
    private List<Relatorio> info;
    private List<Comobox> listaLocalTrabalho;
    private List<Relatorio> infoCredito;
    private List<Relatorio> lastInfo;
    private List<Comobox> listaLocalidade;
    private ListagemDao listagemDao;
    private ClienteDao clienteDao;
    private List<Relatorio> listaCobranca;
    private List<Comobox> agencias;
    private ArrayList<Comobox> tipoCredito;
    private List<Relatorio> listaDividaProduto;
    private List<Relatorio> listaDividaCapital;
    private List<Relatorio> cheques;
    private List<Comobox> bancos;
    private String totalTaegCapital, totalCreditoCapital, totalDividaCapital;
    private String reembolso;
    private CreditoDao creditoDao;
    private PagamentoDao pagamentoDao;
    private String taeg;
    private String tipoCheque ="1";
    private String total;
    
    public RelatorioBean()
    {
        cheques = new ArrayList<>();
        listaDividaCapital= new ArrayList<>();
        pagamentoDao = new PagamentoDao();
        clienteDao = new ClienteDao();
        bancos = new ArrayList<>();
         bancos = pagamentoDao.listaBancos();   
        creditoDao = new CreditoDao();
        infoCredito = new ArrayList<>();
        listaDividaProduto = new ArrayList<>();
        lastInfo = new ArrayList<>();
        tipoCredito = creditoDao.getTipoCredito();
        tipoCredito.add(0, new Comobox("-1","Todos"));
        listagemDao = new ListagemDao();
        agencias = new ArrayList<>();
        agencias.add(new Comobox("-1", "Todas"));
        List<Comobox> value = Comobox.loadCombo("VER_AGENCIA","ID", "AGENCIA");
        if(value != null){ agencias.addAll(value); }
        info = new ArrayList<>();
        listaLocalTrabalho = new ArrayList<>();
        listaLocalTrabalho = clienteDao.LocalTrabalho();
        listaLocalidade = new ArrayList<>();
        listaLocalidade = clienteDao.ListaLocalidade();
        
    }
    public Relatorio getRelatorio() {
        return (relatorio == null) ? relatorio = new Relatorio() : relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public List<Comobox> getAgencias() {
        return agencias;
    }

    public List<Comobox> getBancos() {
        return bancos;
    }

    public void setBancos(List<Comobox> bancos) {
        this.bancos = bancos;
    }

    public ArrayList<Comobox> getTipoCredito() {
        return tipoCredito;
    }

    public String getTaeg() {
        return taeg;
    }

    public String getReembolso() {
        return reembolso;
    }

    public void setTipoCredito(ArrayList<Comobox> tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public List<Relatorio> getLastInfo() {
        return lastInfo;
    }

    public void setLastInfo(List<Relatorio> lastInfo) {
        this.lastInfo = lastInfo;
    }

    public List<Comobox> getListaLocalTrabalho() {
        return listaLocalTrabalho;
    }

    public void setListaLocalTrabalho(List<Comobox> listaLocalTrabalho) {
        this.listaLocalTrabalho = listaLocalTrabalho;
    }

    public List<Relatorio> getInfoCredito() {
        return infoCredito;
    }

    public void setInfoCredito(List<Relatorio> infoCredito) {
        this.infoCredito = infoCredito;
    }

    public List<Comobox> getListaLocalidade() {
        return listaLocalidade;
    }

    public void setListaLocalidade(List<Comobox> listaLocalidade) {
        this.listaLocalidade = listaLocalidade;
    }

    public List<Relatorio> getInfo() {
        return info;
    }

    public void setInfo(List<Relatorio> info) {
        this.info = info;
    }

    public String getTotal() {
        return total;
    }
    
    public void CarregarDados(String param)
    {
        if(relatorio.getDataInicial() != null && relatorio.getDataFinal() != null)
        {
           if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
           {
               info = listagemDao.info("S", relatorio.getDataInicial(), relatorio.getDataFinal(), null, null,relatorio.getAgencia(),Integer.valueOf(relatorio.getTipoCredito()));
              if(info.size()>0)
              {
                  total = info.get((info.size()-1)).getValorCredito();
              }
           }
        }
    }
    
    public void CarregarQuantidadeCredito(String param)
    {
        if(relatorio.getDataInicial() != null && relatorio.getDataFinal() != null)
        {
             if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataInicial()) != -1)
              {
                  if(relatorio.getParametro().equals("SP"))
                  {
                      info = listagemDao.info(relatorio.getParametro(), relatorio.getDataInicial(), relatorio.getDataFinal(), null, null,relatorio.getAgencia(),Integer.valueOf(relatorio.getTipoCredito()));
                       total = info.get((info.size()-1)).getValorCredito();
                       info.remove((info.size()-1));
                      RequestContext.getCurrentInstance().update("QCredito:tabelaQ");
                      RequestContext.getCurrentInstance().update("QCredito:totalRelatorioCreditoObtido");
                  }
                  else
                  {
                      if((relatorio.getLocalTrabalho() != null && relatorio.getLocalTrabalho().length()>0) && (relatorio.getLocalidade() != null && relatorio.getLocalidade().length()>0))
                      {
//                          System.err.println(relatorio);
                            info = listagemDao.info(relatorio.getParametro(), relatorio.getDataInicial(), relatorio.getDataFinal(), relatorio.getLocalTrabalho(), relatorio.getLocalidade(),relatorio.getAgencia(),Integer.valueOf(relatorio.getTipoCredito()));
                            total = info.get((info.size()-1)).getValorCredito();
                             info.remove((info.size()-1));
                            RequestContext.getCurrentInstance().update("QCredito:tabelaQ");
                            RequestContext.getCurrentInstance().update("QCredito:totalRelatorioCreditoObtido");
                      }        
                  }
              }
        }
    } 
    public void limpar(String param)
    {
        info.clear();
       RequestContext.getCurrentInstance().update("QCredito:tabelaQ");
    }
    
    public void RelatorioCredito(String param)
    {
        @SuppressWarnings("UnusedAssignment")
        int tamanho = 0;
        lastInfo.clear();
        infoCredito.clear();
        String valorEmprestado = null,capitalConcebido = null, taeg =null;
        if(relatorio.getDataInicial() != null && relatorio.getDataFinal() != null && 
                (relatorio.getPeriodoEmComparacao() != null && relatorio.getPeriodoEmComparacao().length()>0) &&
                (relatorio.getTipoCredito() != null && relatorio.getTipoCredito().length()>0))
        {
            if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
            {
                infoCredito = listagemDao.infoCredito(relatorio.getDataInicial(), relatorio.getDataFinal(),Integer.valueOf(relatorio.getTipoCredito()), Integer.valueOf(relatorio.getPeriodoEmComparacao()),
                        Integer.valueOf(relatorio.getAgencia()));
                if(infoCredito.size() > 0)
                {   
                    tamanho = infoCredito.size()-1;
                    relatorio.setCapitalConcebido(infoCredito.get(tamanho).getCapitalConcebido());
                    relatorio.setValorEmprestado(infoCredito.get(tamanho).getCapitalConcebido());
                    relatorio.setTaeg(infoCredito.get(tamanho).getTaeg());
                    relatorio.setTaegFooter(infoCredito.get((infoCredito.size()-2)).getTaeg());
                    relatorio.setCapitalConcebidoFooter(infoCredito.get((infoCredito.size()-2)).getCapitalConcebido());
                    relatorio.setMontanteDividaFooter(infoCredito.get((infoCredito.size()-2)).getMontanteDivida());
                    infoCredito.remove((infoCredito.size()-1));
                    infoCredito.remove((infoCredito.size()-1));
                    
                    lastInfo.add(relatorio);
                  
                }
                RequestContext.getCurrentInstance().update("infoCredito:tabelaCredito");
                RequestContext.getCurrentInstance().update("infoCredito:totalCapitaCon");
                RequestContext.getCurrentInstance().update("infoCredito:totalMontanteD");
                RequestContext.getCurrentInstance().update("infoCredito:totalTaegC");
            }
        }
    }

    public List<Relatorio> getCheques() {
        return cheques;
    }

    public void setCheques(List<Relatorio> cheques) {
        this.cheques = cheques;
    }
    
    public void tipoCheque(String tipo)
    {
        System.out.println("tipo "+tipo);
        SessionUtil.removerParametro("cheque");
        SessionUtil.definirParametro("cheque", tipo);
            
    }

    public List<Relatorio> getListaDividaProduto() {
        return listaDividaProduto;
    }

    public void setListaDividaProduto(List<Relatorio> listaDividaProduto) {
        this.listaDividaProduto = listaDividaProduto;
    }

    public List<Relatorio> getListaDividaCapital() {
        return listaDividaCapital;
    }

    public void setListaDividaCapital(List<Relatorio> listaDividaCapital) {
        this.listaDividaCapital = listaDividaCapital;
    }

    public List<Relatorio> getListaCobranca() {
        return listaCobranca;
    }

    public void setListaCobranca(List<Relatorio> listaCobranca) {
        this.listaCobranca = listaCobranca;
    }
    
    public void relatorioCheque()
    {
        System.out.println(relatorio.toString());
        if(SessionUtil.obterValor("cheque") == null)
        {
            SessionUtil.definirParametro("cheque", tipoCheque);
        }
        if((relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) &&
                (relatorio.getLocalTrabalho() != null && !relatorio.getLocalTrabalho().equals("")) &&
                (relatorio.getBanco() != null && !relatorio.getBanco().equals("")))
        {
            if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
            {         
                System.out.println("tipo de cheque "+SessionUtil.obterValor("cheque"));
                 switch(SessionUtil.obterValor("cheque").toString())
                {     
                    case "1":
                        cheques = listagemDao.regRelatorioCheque(relatorio.getDataInicial(), relatorio.getDataFinal(),"1", relatorio.getBanco(),
                                relatorio.getLocalTrabalho(),Integer.valueOf(relatorio.getAgencia()), Integer.valueOf(relatorio.getTipoCredito()));
                        break;
                    case "nulo":
                         cheques = listagemDao.regRelatorioCheque(relatorio.getDataInicial(), relatorio.getDataFinal(), null, relatorio.getBanco(), relatorio.getLocalTrabalho(),
                                 Integer.valueOf(relatorio.getAgencia()),Integer.valueOf(relatorio.getTipoCredito()));
                        break;
                    case "0":
                       cheques = listagemDao.regRelatorioCheque(relatorio.getDataInicial(), relatorio.getDataFinal(), "0", relatorio.getBanco(), relatorio.getLocalTrabalho(),Integer.valueOf(relatorio.getAgencia())
                       ,Integer.valueOf(relatorio.getTipoCredito()));
                        break;
                }
                 if(cheques.size()>0)
                 {
                     total = cheques.get((cheques.size()-1)).getValorCheque();
                     cheques.remove((cheques.size()-1));
                 }
       
                SessionUtil.removerParametro("cheque");
                Validation.AtualizarCompoente("relatorioCheque", "tabelaCheques");
                Validation.AtualizarCompoente("relatorioCheque", "totalValorCheque");
            }
                 
        }
    }

    public String getTotalTaegCapital() {
        return totalTaegCapital;
    }

    public String getTotalCreditoCapital() {
        return totalCreditoCapital;
    }

    public String getTotalDividaCapital() {
        return totalDividaCapital;
    }
    
    /**
     * Mostra os registros de divida
     */
    public void relatorioDividaProduto()
    {
        if((relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) &&
            (relatorio.getLocalTrabalho() != null && !relatorio.getLocalTrabalho().equals("")) &&
            (relatorio.getTipoCredito() != null && !relatorio.getTipoCredito().equals("")))
        {
            if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
            {
                listaDividaProduto = listagemDao.regRelatorioDivida(relatorio.getDataInicial(),relatorio.getDataFinal(), relatorio.getLocalTrabalho(),
                        Integer.valueOf(relatorio.getAgencia()),0, Integer.valueOf(relatorio.getTipoCredito()));
                if(listaDividaProduto.size()>0)
                {
                    totalCreditoCapital = listaDividaProduto.get((listaDividaProduto.size()-1)).getCreditoSolicitado();
                    totalDividaCapital = listaDividaProduto.get((listaDividaProduto.size()-1)).getTotalCredito();
                    totalTaegCapital = listaDividaProduto.get((listaDividaProduto.size()-1)).getValorPago();
                    listaDividaProduto.remove((listaDividaProduto.size()-1));
                    listaDividaProduto.remove((listaDividaProduto.size()-1)); 
                }
                Validation.AtualizarCompoente("relatorioDividaProduto", "tabelaDividaProduto");
                Validation.AtualizarCompoente("relatorioDividaProduto", "totalValorCreditoProduto");
                Validation.AtualizarCompoente("relatorioDividaProduto", "totalMontanteDividaProduto");
                Validation.AtualizarCompoente("relatorioDividaProduto", "totalValorPagoProduto");
            }
        }
    }
    
    public void relatorioDividaCapital()
    {
        if((relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) &&
            (relatorio.getPeriodoEmComparacao() != null && !relatorio.getPeriodoEmComparacao().equals("")))
        {
            if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
            {
                listaDividaCapital = listagemDao.relatorioDividaCapital(relatorio.getDataInicial(), relatorio.getDataFinal(), Integer.valueOf(relatorio.getPeriodoEmComparacao()),Integer.valueOf(relatorio.getAgencia()),Integer.valueOf(relatorio.getTipoCredito()));
                if(listaDividaCapital.size()>0)
                {
                    totalCreditoCapital = listaDividaCapital.get((listaDividaCapital.size()-2)).getValorCredito();
                    totalDividaCapital = listaDividaCapital.get((listaDividaCapital.size()-2)).getMontanteDivida();
                    totalTaegCapital = listaDividaCapital.get((listaDividaCapital.size()-2)).getTaeg();
                    listaDividaCapital.remove((listaDividaCapital.size()-2));
                }
                Validation.AtualizarCompoente("relatorioDividaCapital", "tabelaDividaCapital");
                Validation.AtualizarCompoente("relatorioDividaCapital", "totalCapitalCredito");
                Validation.AtualizarCompoente("relatorioDividaCapital", "totalMontanteCapital");
                Validation.AtualizarCompoente("relatorioDividaCapital", "totalTaegCapital");
            }
        }
    }
    
     public void relatorioCobranca()
    {
        if((relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) &&
            (relatorio.getPeriodoEmComparacao() != null && !relatorio.getPeriodoEmComparacao().equals("")) &&
             (relatorio.getTipoCredito() != null && !relatorio.getTipoCredito().equals("")) &&
             (relatorio.getBanco() != null && !relatorio.getBanco().equals("")))
        {
            if(OperacaoData.compareDates(relatorio.getDataInicial(), relatorio.getDataFinal()) != -1)
            {
                listaCobranca = listagemDao.listaCobrancas(relatorio.getDataInicial(), relatorio.getDataFinal(),
                        Integer.valueOf(relatorio.getTipoCredito()), Integer.valueOf(relatorio.getBanco()), Integer.valueOf(relatorio.getPeriodoEmComparacao()),Integer.valueOf(relatorio.getAgencia()));
                if(listaCobranca.size()>0)
                {
                    reembolso =  listaCobranca.get((listaCobranca.size()-1)).getReembolso();
                   listaCobranca.remove((listaCobranca.size()-1));
                   listaCobranca.remove((listaCobranca.size()-1)); 
                }
               
                Validation.AtualizarCompoente("relatorioCobranca", "tabelaCobranca");
                Validation.AtualizarCompoente("relatorioCobranca", "totalCobranca");
            }
        }
    }
    public void exportPDFCliente() {
        RelatorioConverter.xports(new RelatorioConverter.Cliente(), info, RelatorioConverter.Type.PDF);
    }

    public void exportEcellCliente() {
        RelatorioConverter.xports(new RelatorioConverter.Cliente(), info, RelatorioConverter.Type.EXCEL);
    }

    public void exportPDFCobranca() {
        RelatorioConverter.xports(new RelatorioConverter.Cobranca(), listaCobranca, RelatorioConverter.Type.PDF);
    }

    public void exportEcellCobranca() {
        RelatorioConverter.xports(new RelatorioConverter.Cobranca(), listaCobranca, RelatorioConverter.Type.EXCEL);
    }

    public void exportPDFTAEG() {
        RelatorioConverter.xports(new RelatorioConverter.CapitalTAEG(), listaDividaCapital, RelatorioConverter.Type.PDF);
    }

    public void exportEcellTAEG() {
        RelatorioConverter.xports(new RelatorioConverter.CapitalTAEG(), listaDividaCapital, RelatorioConverter.Type.EXCEL);
    }

    public void exportPDFCheqe() {
        RelatorioConverter.xports(new RelatorioConverter.Cheque(), cheques, RelatorioConverter.Type.PDF);
    }

    public void exportEcellCheque() {
        RelatorioConverter.xports(new RelatorioConverter.Cheque(), cheques, RelatorioConverter.Type.EXCEL);
    }

    public void exportPDFConcebido() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoConebido(), infoCredito, RelatorioConverter.Type.PDF);
    }

    public void exportEcellConcebido() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoConebido(), infoCredito, RelatorioConverter.Type.EXCEL);
    }

    public void exportPDFCreditoObetido() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoObtido(), info, RelatorioConverter.Type.PDF);
    }

    public void exportEcellCreditoObetido() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoObtido(), info, RelatorioConverter.Type.EXCEL);
    }

    public void exportPDFDivida() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoObtido(), listaDividaProduto, RelatorioConverter.Type.PDF);
    }

    public void exportEcellDivida() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoObtido(), listaDividaProduto, RelatorioConverter.Type.EXCEL);
    }
    
    public void exportPDFHomologo() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoObtido(), listaDividaProduto, RelatorioConverter.Type.PDF);
    }

    public void exportEcellHomologo() {
        RelatorioConverter.xports(new RelatorioConverter.CreditoObtido(), listaDividaProduto, RelatorioConverter.Type.EXCEL);
    }
}
