/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import conexao.Call;
import dao.CreditoDao;
import dao.RegSimulacaoDao;
import dao.TabelaSimulacao;
import dao.TaxaESegurosDao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Types;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Comobox;
import modelo.Simulacao;
import modelo.TaxaESeguros;
import modelo.Utilizador;
import sessao.SessionUtil;
import static validacao.OperacaoData.compareDates;
    
/**
 *
 * @author AhmedJorge
 */
@ManagedBean
@ViewScoped
public class RegSimulacao implements Serializable{
  
   private ConsultaClienteBean consultaClienteBean;
   private ArrayList<Comobox>  addGarrantia = new ArrayList();
   private ArrayList<Comobox> addDocumento= new  ArrayList();
   private Comobox garrantia;
   private Comobox documento;
   private Object selectCliente;
   private String removeGarrantia;
   private String removedocumento;
   private String numeroDossier;
   private String nome;
   private String nif;
   private String apelido;
   private String fonteRedimento;
   private String docEntrege;
   private String bancoCredito;
   private String modalidadePagamento;
   private String numDocumentoCredial;
   private String idCheque;
   private int tamanhoNumValideteChaque;
   private String sequenciaCheque = "";
   private boolean BotonImprortar=false;
   private String sequenciaNumIncial = "";
   private String Mensagem="Erro a Importar simulação!";
   private String Mensagem2="";
   private String Mensagem3="";
   private String Mensagem4="";
   private ArrayList<TaxaESeguros> listaTaxa= new ArrayList<>();
   
   TabelaSimulacao ts = new TabelaSimulacao();
    ArrayList<TabelaSimulacao> tabelaSimulacao  = new ArrayList<>();
    private ArrayList<String[]>tabela=new ArrayList<>();
    private ArrayList<Comobox>listaCredito= new ArrayList<>();
    private ArrayList<Comobox>listaUserBanco= new ArrayList<>();
    private ArrayList<Comobox>listaTodosBanco= new ArrayList<>();
    private ArrayList<Comobox>listaBancoPagamento= new ArrayList<>();
    private ArrayList<Comobox>listaModalidadePagamento= new ArrayList<>();
    private ArrayList<Comobox>listaFonteRendimento= new ArrayList<>();
    private ArrayList<Comobox>listaGatantia= new ArrayList<>();
    private ArrayList<Comobox>listaNumeroDoc= new ArrayList<>();
    private ArrayList<Comobox>listaDocumentos= new ArrayList<>();
    String[] Data_Num;
    private Simulacao simulacao= new Simulacao();
    double lastDescont=0;
    private TabelaSimulacao selectTabelaSimulacao= new TabelaSimulacao();
    private TabelaSimulacao tabelaSimulacaoEdtite= new TabelaSimulacao();
   SimpleDateFormat sdfIngle = new  SimpleDateFormat("yyyy-MM-dd");
   RegSimulacaoDao rsd;
   HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );  
   
   @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    public RegSimulacao() 
    {
        CreditoDao cd= new CreditoDao();
        rsd= new RegSimulacaoDao();
        
        //carrega Lista de Tipo de Credito da Base de dados para a Variavel listaTipoCredito
        if(cd.getTipoCredito().size()>0)
        this.listaCredito=cd.getTipoCredito();
        this.listaTodosBanco =rsd.listaTodosBanco();
        this.listaGatantia=rsd.listaGarantias();
        this.listaFonteRendimento=rsd.fontesRendimentos();
        this.listaModalidadePagamento=rsd.listaFormaPagamento(); 
        this.listaDocumentos=rsd.listaDocumentos();
        
        if(session.getAttribute("cliente")!=null)
        {
            selectCliente= session.getAttribute("cliente");
            nome=((Cliente)(selectCliente)).getNome();
            nif=((Cliente)(selectCliente)).getNif();
            apelido=((Cliente)(selectCliente)).getApelido();
            numeroDossier=((Cliente)(selectCliente)).getNumeroDossier();
        }
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }
    
    public Simulacao getSimulacao() {
        return simulacao=((simulacao==null)?new Simulacao():simulacao);
    }

    public void setSimulacao(Simulacao simulacao) {
        this.simulacao = simulacao;
    }

    public ArrayList<String[]> getTabela() {
        return tabela;
    }
    
    public void setTabela(ArrayList<String[]> tabela) {
        this.tabela = tabela;
    }

    public ArrayList<Comobox> getListaCredito() {
        return listaCredito=((listaCredito==null)?new ArrayList<>():listaCredito);
    }

    public void setListaCredito(ArrayList<Comobox> listaCredito) {
        this.listaCredito = listaCredito;
    }

    public boolean isBotonImprortar() {
        return BotonImprortar;
    }

    public void setBotonImprortar(boolean BotonImprortar) {
        this.BotonImprortar = BotonImprortar;
    }

    public ArrayList<Comobox> getListaBancoPagamento() {
        return listaBancoPagamento =((listaBancoPagamento==null)? new ArrayList<>():listaBancoPagamento);
    }

    public void setListaBancoPagamento(ArrayList<Comobox> listaBancoPagamento) {
        this.listaBancoPagamento = listaBancoPagamento;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String Mensagem) {
        this.Mensagem = Mensagem;
    }

    public String getMensagem2() {
        return Mensagem2;
    }

    public void setMensagem2(String Mensagem2) {
        this.Mensagem2 = Mensagem2;
    }

    public String getMensagem3() {
        return Mensagem3;
    }

    public void setMensagem3(String Mensagem3) {
        this.Mensagem3 = Mensagem3;
    }

    public String getMensagem4() {
        return Mensagem4;
    }

    public void setMensagem4(String Mensagem4) {
        this.Mensagem4 = Mensagem4;
    }
    
    public ArrayList<TaxaESeguros> getListaTaxa() {
        return listaTaxa=((listaTaxa==null)?new ArrayList<>():listaTaxa);
    }

    public void setListaTaxa(ArrayList<TaxaESeguros> listaTaxa) {
        this.listaTaxa = listaTaxa;
    }

    public TabelaSimulacao getTabelaSimulacaoEdtite() {
        return ((tabelaSimulacaoEdtite==null)? tabelaSimulacaoEdtite= new TabelaSimulacao() :tabelaSimulacaoEdtite);
    }

    public void setTabelaSimulacaoEdtite(TabelaSimulacao tabelaSimulacaoEdtite) {
        this.tabelaSimulacaoEdtite = tabelaSimulacaoEdtite;
    }
    
    /**
     * 
     * @param data
     * @return retorna o a data mais os numeros dia a adicionar Como data YYYY-MM-dd
     */
    @SuppressWarnings("UnusedAssignment")
    public String DataFinal(String data)
     {
        String A="",M="",D="";   
         String[] ff;
        if(data.contains("-"))
            ff= data.split("-");
        else
             ff= data.split("/");
        
          int a,m,d;
        if(ff[0].length()==4)
        {
            a=Integer.valueOf(ff[0]);
            m=Integer.valueOf(ff[1]);
            d=Integer.valueOf(ff[2]);
        }else
        {
            d=Integer.valueOf(ff[0]);
            m=Integer.valueOf(ff[1]);
            a=Integer.valueOf(ff[2]);
        }
        int i;
        for (i= 0; i < (Integer.valueOf(simulacao.getDias())/Integer.valueOf(simulacao.getPeriodo())); i++) 
        {
            d=d+1;
            switch (m) {
                case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                    if(d>31){d=1;m++;}
                    break;
                case 4:case 6:case 9:case 11:
                    if(d>30){d=1;m++;}  
                    break;
                default:
                    if (a % 400 == 0&&a%4 == 0 && a%100!=0)
                    {
                        if(d>29){d=1;m++;}
                    } else {
                        if(d>28){d=1;m++;}
                    }   break;
            }
            if(m>12){m=1;a++;d=1;}
            A=a+"";M=m+"";D=d+"";       
            if(M.length()<2){M="0"+m;}
            if(D.length()<2){D="0"+d;}
        } 
         return (D+"-"+M+"-"+A)+";"+i+";"+(A+"-"+M+"-"+D);
     }
    
//    public boolean validarCampos(Object obj)
//    {
//       return !obj.toString().isEmpty();
//    }
    /**
     * 
     * @param obj
     * @return retorna true ou false
     * se true compos preenchido
     * se false compos não preenchido
     */
    public boolean validarCampos(Object obj)
    {
       return !obj.toString().isEmpty();
    }

    
    public ConsultaClienteBean getConsultaClienteBean()
    {
        return (consultaClienteBean==null) ? consultaClienteBean = new ConsultaClienteBean() : consultaClienteBean;
    }

    /**
     * 
     * @param consultaClienteBean
     */
    public void setConsultaClienteBean(ConsultaClienteBean consultaClienteBean) {
        this.consultaClienteBean = consultaClienteBean;
    }

    public boolean validar() {
        int i=0;
        if(!validarCampos(this.simulacao.getDias()))
            i++;
        if(!validarCampos(this.simulacao.getTipoCredeito()))
            i++;
        if(!validarCampos(this.simulacao.getValorSimulacao()))
            i++;
        if(!testeConvetData())
            i++;
        return (i==0);
    }
    public boolean validar2()
    {
        int i=0;
        if(!validarCampos(this.simulacao.getCapital()))
            i++;
        if(!validarCampos(this.simulacao.getCorrecao()))
            i++;
        if(!validarCampos(this.simulacao.getDesconto()))
            i++;
        if(!validarCampos(this.simulacao.getDias()))
            i++;
        if(!validarCampos(this.simulacao.getPeriodo()))
            i++;
        if(!validarCampos(this.simulacao.getPestacao()))
            i++;
        if(!validarCampos(this.simulacao.getReembalsoPeriodo()))
            i++;
        if(!validarCampos(this.simulacao.getSeguro()))
            i++;
        if(!validarCampos(this.simulacao.getTaxaComDesconto()))
            i++;
        if(!validarCampos(this.simulacao.getTaxa()))
            i++;
        if(!validarCampos(this.simulacao.getTaxaSemDesconto()))
            i++;
        if(!validarCampos(this.simulacao.getTipoCredeito()))
            i++;
        if(!validarCampos(this.simulacao.getTotalPagar()))
            i++;
        if(!validarCampos(this.simulacao.getValorSimulacao()))
            i++;
        return (i==0);
    }
    public void regSumulacao() {
        String mensagem;
        if (validarDesconto()) {
            if (validar()) {
                if (testeDataSimulasao()) {
                    if (diaInt()) {
                        if (ValorSimulacaoDoube()) {
                            RegSimulacaoDao d = new RegSimulacaoDao();
                            mensagem = d.registrarTipoCredito(this.simulacao);
                            if (!mensagem.equals("TRUE")) {
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Simulacao", mensagem));
                            } else {
                                System.out.println(simulacao.getDataCredito());
                                Funcao();
                                this.listaUserBanco = rsd.listaUserBancos(this.simulacao.getValorSimulacao());
                            }
                        } else {
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Simulacao", "A Valor Simulacção é Real"));
                        }
                    } else {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Simulacao", "A Data é Inteira"));
                    }
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Simulacao", "Data Invalida Selecione Outra!"));
                }
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Simulação", "Existe Campos Vazios!"));
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Simulação", "Desconto Invalido!!"));
        }
    }
    public boolean diaInt(){
        try
        {
            Integer.valueOf(this.simulacao.getDias());
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean ValorSimulacaoDoube(){
        try
        {
            Double.valueOf(this.simulacao.getValorSimulacao());
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public boolean validarDesconto()
    {
//        System.err.println("ccc");
        if(descontodouble())
        {
            if(Double.valueOf(simulacao.getDesconto())>20)
            {
                simulacao.setDesconto(0f);
//                System.err.println("0");
                return false; 
            }
            if(Double.valueOf(simulacao.getDesconto())<0)
            {
                simulacao.setDesconto(0f);
//                System.err.println("0");
                return false; 
            }
            return true; 
        }else{
             return false; 
        }
    }
    
    public boolean descontodouble(){
        try
        {
            Double.valueOf(this.simulacao.getDesconto());
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
     public void testeDia_NSemanaMes()
        {
            if(Integer.valueOf(simulacao.getDias())>simulacao.getnSemanasMes())
            {
                Data_Num =(DataFinal(sdfIngle.format(simulacao.getDataCredito()))).split(";");
                
                ts.setNperiodoReebolso(Integer.valueOf(DataFinal(sdfIngle.format(simulacao.getDataCredito())).split(";")[1]));
            }
            else
            {
                Data_Num =(DataFinal(sdfIngle.format(simulacao.getDataCredito()))).split(";");
                
                ts.setNperiodoReebolso(Integer.valueOf(DataFinal(sdfIngle.format(simulacao.getDataCredito())).split(";")[1]));
            }
            System.out.println(simulacao.getDias()+" "+simulacao.getnSemanasMes());
        }

    public ArrayList<TabelaSimulacao> getTabelaSimulacao() {
        return tabelaSimulacao=((tabelaSimulacao==null)?new ArrayList<>():tabelaSimulacao);
    }

    public void setTabelaSimulacao(ArrayList<TabelaSimulacao> tabelaSimulacao) {
        this.tabelaSimulacao = tabelaSimulacao;
    }
    
    public void Funcao()
    {
        tabelaSimulacao.removeAll(tabelaSimulacao);
        testeDia_NSemanaMes();
        ts.setDataT(Data_Num[0]);
        ts.setDataIng(Data_Num[2]);
        getSimulacao().setDataFinal(Data_Num[2]);
        ts.primeiroCalculoReebolso(simulacao);
        
        int to =Integer.parseInt(simulacao.getPeriodo());
        for (int i = 0; i < to; i++)
        {          
            tabelaSimulacao.add( new TabelaSimulacao(ts.getDataT(), simulacao.getReembalsoPeriodo(),NumberFormat.getNumberInstance(Locale.FRENCH).format(ts.getCapitalReebolso()), ts.getNundocumento(), ts.getTipoPagamento(),ts.getBanco(),ts.getIdTipoPagamento(), ts.getIdbanco(),ts.getDataIng(),simulacao.getReembalsoPeriodoSC()));
            ts.outroCalculoReebolso(simulacao);
            Data_Num =DataFinal(Data_Num[0]).split(";");
            ts.setNperiodoReebolso(Integer.valueOf(Data_Num[1]));
            ts.setDataT(Data_Num[0]);
            ts.setDataIng(Data_Num[2]);
            if(i+2==to)
            {
                getSimulacao().setDataFinal(Data_Num[2]);
                System.out.println(Data_Num[2]);
            }
        }
        
    }
    
     public void correcaoValidar()
    {
        @SuppressWarnings("UnusedAssignment")
        double totalDoub=0;
        if("A".equals(simulacao.getOpcao()))
        {
            if(simulacao.getTotalPagar()!=null&&simulacao.getCorrecao()!=null)
            {
                totalDoub=(simulacao.getTotalPagarSC())
                        +(Double.valueOf(simulacao.getCorrecao()));
                
                simulacao.setTotalPagar(NumberFormat.getNumberInstance(Locale.FRENCH).format(totalDoub));
                simulacao.setTotalPagarSC(totalDoub);
            }
        }else{
            if(simulacao.getTotalPagar()!=null&&simulacao.getCorrecao()!=null)
            {
                totalDoub=(simulacao.getTotalPagarSC())
                        -(Double.valueOf(simulacao.getCorrecao()));
                
                simulacao.setTotalPagar(NumberFormat.getNumberInstance(Locale.FRENCH).format(totalDoub));
                simulacao.setTotalPagarSC(totalDoub);
            }
        }
    }

    public TabelaSimulacao getTs() {
        return ts;
    }

    public void setTs(TabelaSimulacao ts) {
        this.ts = ts;
    }

    public ArrayList<Comobox> getListaModalidadePagamento() {
        return listaModalidadePagamento=((listaModalidadePagamento==null)?new ArrayList<>():listaModalidadePagamento);
    }

    public void setListaModalidadePagamento(ArrayList<Comobox> listaModalidadePagamento) {
        this.listaModalidadePagamento = listaModalidadePagamento;
    }

    public ArrayList<Comobox> getListaFonteRendimento() {
        return listaFonteRendimento=((listaFonteRendimento==null)? new ArrayList<>():listaFonteRendimento);
    }

    public void setListaFonteRendimento(ArrayList<Comobox> listaFonteRendimento) {
        this.listaFonteRendimento = listaFonteRendimento;
    }

    public ArrayList<Comobox> getListaGatantia() {
        return listaGatantia=((listaGatantia==null)?listaGatantia=new ArrayList<>():listaGatantia);
    }

    public void setListaGatantia(ArrayList<Comobox> listaGatantia) {
        this.listaGatantia = listaGatantia;
    }

    public ArrayList<Comobox> getListaNumeroDoc() {
        return listaNumeroDoc =(listaNumeroDoc==null)?new ArrayList<>():listaNumeroDoc;
    }

    public void setListaNumeroDoc(ArrayList<Comobox> listaNumeroDoc) {
        this.listaNumeroDoc = listaNumeroDoc;
    }

    public String[] getData_Num() {
        return Data_Num;
    }

    public void setData_Num(String[] Data_Num) {
        this.Data_Num = Data_Num;
    }
    
    public void altararLinhaSelecionada(){
        for (int i = 0; i < tabelaSimulacao.size()&&getTabelaSimulacaoEdtite().getDataT()!=null; i++) {
            if(getTabelaSimulacaoEdtite().getDataT().equals(tabelaSimulacao.get(i).getDataT()))
            { 
                int idTipoPagamento = Integer.parseInt(tabelaSimulacaoEdtite.getTipoPagamento().split(";")[0]);
                tabelaSimulacao.set(i, new TabelaSimulacao(tabelaSimulacaoEdtite.getDataT(), tabelaSimulacaoEdtite.getReebolso(),tabelaSimulacaoEdtite.getCapitaRe() ,
                        tabelaSimulacaoEdtite.getNundocumento(),tabelaSimulacaoEdtite.getTipoPagamento().split(";")[1], tabelaSimulacaoEdtite.getBanco().split(";")[1], idTipoPagamento, tabelaSimulacaoEdtite.getBanco().split(";")[0],tabelaSimulacao.get(i).getDataIng(),tabelaSimulacao.get(i).getReebolsoSf()));
                tabelaSimulacaoEdtite = new TabelaSimulacao("", "", "", "", "", "",0,"","",0);
                break;
            }
        }
        
    }
    public void trsnsSelected()
    {
        if(selectTabelaSimulacao!=null)
            tabelaSimulacaoEdtite = new TabelaSimulacao(selectTabelaSimulacao);
    }
    public TabelaSimulacao getSelectTabelaSimulacao() {
        return (selectTabelaSimulacao==null) ? selectTabelaSimulacao= new TabelaSimulacao() : selectTabelaSimulacao;
    }

    public void setSelectTabelaSimulacao(TabelaSimulacao selectTabelaSimulacao) {
        this.selectTabelaSimulacao = selectTabelaSimulacao;
    }

    public ArrayList<Comobox> getListaDocumentos() {
        return listaDocumentos=((listaDocumentos==null)?new ArrayList<>():listaDocumentos);
    }

    public void setListaDocumentos(ArrayList<Comobox> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public ArrayList<Comobox>  getAddGarrantia() {
        return addGarrantia=((addGarrantia==null)?new ArrayList<>():addGarrantia);
    }

    public void setAddGarrantia(ArrayList<Comobox>  addGarrantia) {
        this.addGarrantia = addGarrantia;
    }

    public ArrayList<Comobox>  getAddDocumento() {
        return addDocumento=((addDocumento==null)?new ArrayList<>():addDocumento);
    }

    public void setAddDocumento(ArrayList<Comobox>  addDocumento) {
        this.addDocumento = addDocumento;
    }

    public ArrayList<Comobox> getListaUserBanco() {
        return listaUserBanco=((listaUserBanco==null)?new ArrayList<>():listaUserBanco);
    }

    public void setListaUserBanco(ArrayList<Comobox> listaUserBanco) {
        this.listaUserBanco = listaUserBanco;
    }

    public ArrayList<Comobox> getListaTodosBanco() {
        return listaTodosBanco =((listaTodosBanco == null)? new ArrayList<>():listaTodosBanco);
    }

    public void setListaTodosBanco(ArrayList<Comobox> listaTodosBanco) {
        this.listaTodosBanco = listaTodosBanco;
    }
    
    public boolean addObj(Comobox ob,ArrayList<Comobox> al)
    {
       boolean add = false;
        if(ob.getDescricao()!=null && !ob.getDescricao().isEmpty())
        {
            if( al.size()>0)
            {
                for (Comobox addDocumento1 : al) {
                    if (addDocumento1.getId().equals(ob.getValue().split(";")[0]) && addDocumento1.getDescricao().equals(ob.getDescricao())) {
                        add=true; 
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Garantia ou Documento",  "Já foi adcionado!!") );
                    }
                }
                if(!add)
                {
                    al.add(new Comobox(ob.getValue().split(";")[0],ob.getValue().split(";")[1], ob.getDescricao())); //setAddDocumento(getAddDocumento()+"\n"+getDocumento());
                }
            }
            else
            {
                al.add(new Comobox(ob.getValue().split(";")[0],ob.getValue().split(";")[1], ob.getDescricao()));  
            }
        }
        else
        {
            add = true;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Garantia ou Documento",  "Campo Vazio!!") );
        }
        
        return add;
    }
    
    public void selecedObj(boolean b)
    {
        if(b && removeGarrantia != null){ garrantia = new Comobox(removeGarrantia.split(";")[0], removeGarrantia.split(";")[1], removeGarrantia.split(";")[2]); }
        else if(removedocumento != null){ documento = new Comobox(removedocumento.split(";")[0], removedocumento.split(";")[1], removedocumento.split(";")[2]); }
    }
    
    public void addRegDocumento()
    {
        if(!addObj(documento, addDocumento)) { documento = new Comobox(); }
    }

    public void addRegGarrantia(){
        if(!addObj(garrantia, addGarrantia)) {garrantia = new Comobox();}
    }

    public Comobox getGarrantia() {
        return garrantia = ( (garrantia == null) ? new Comobox() :  garrantia) ;
    }

    public void setGarrantia(Comobox garrantia) {
        this.garrantia = garrantia;
    }

    public Comobox getDocumento() {
        return documento = (( documento == null) ? new Comobox() : documento);
    }

    public void setDocumento(Comobox documento) {
        this.documento = documento;
    }

    public String getRemoveGarrantia() {
        return removeGarrantia;
    }

    public void setRemoveGarrantia(String removeGarrantia) {
        this.removeGarrantia = removeGarrantia;
    }

    public String getRemovedocumento() {
        return removedocumento;
    }

    public void setRemovedocumento(String removedocumento) {
        this.removedocumento = removedocumento;
    }
    
    public void removeSelectGarrantia()
    {
       if(!getRemoveGarrantia().isEmpty())
       {
           for (int i = 0; i < addGarrantia.size(); i++) 
           {
               Comobox get = addGarrantia.get(i);
               if(getRemoveGarrantia().equals(get.getToString()))
               {
                   addGarrantia.remove(get);
                   break;
               }
           }
       }
    }
    
    public void removeSelectDocumento()
    {
       if(!getRemovedocumento().isEmpty())
       {
           for (int i = 0; i < addDocumento.size(); i++) 
           {
               Comobox get = addDocumento.get(i);
               if(getRemovedocumento().equals(get.getToString()))
               {
                   addDocumento.remove(get);
                   break;
               }
           }
       }
    }

    public Object getSelectCliente() {
        return selectCliente;
    }

    public void setSelectCliente(Object selectCliente) {
        this.selectCliente = selectCliente;
    }

    public String getFonteRedimento() {
        return fonteRedimento;
    }

    public void setFonteRedimento(String fonteRedimento) {
        this.fonteRedimento = fonteRedimento;
    }

    public String getDocEntrege() {
        return docEntrege;
    }

    public void setDocEntrege(String docEntrege) {
        this.docEntrege = docEntrege;
    }

    public String getModalidadePagamento() {
        return modalidadePagamento;
    }

    public void setModalidadePagamento(String modalidadePagamento) {
        this.modalidadePagamento = modalidadePagamento;
    }

    public String getBancoCredito() {
        return bancoCredito;
    }

    public void setBancoCredito(String bancoCredito) {
        this.bancoCredito = bancoCredito;
    }

    public void setNumDocumentoCredial(String numDocumentoCredial) {
        this.numDocumentoCredial = numDocumentoCredial;
    }

    public String getNumDocumentoCredial() {
        return numDocumentoCredial;
    }

    public String getIdCheque() {
        return idCheque;
    }

    public void setIdCheque(String idCheque) {
        this.idCheque = idCheque;
    }

    public String getSequenciaCheque() {
        return sequenciaCheque;
    }

    public void setSequenciaCheque(String sequenciaCheque) {
        this.sequenciaCheque = sequenciaCheque;
    }
    
    
   @SuppressWarnings("null")
    public void ImportaSimulacao(String dd)
    {
        RegSimulacaoDao dao= new RegSimulacaoDao();
        String getId=dao.regCredito(nif, this);
        if(getId!=null&&!getId.split(";")[0].equals("-1"))
        {
            for (int i = 0; i < tabelaSimulacao.size(); i++) 
            {
               System.out.println("Pagemento "+tabelaSimulacao.get(i).getReebolsoSf());
               dao.regPagemento(getId.split(";")[0], this,tabelaSimulacao.get(i), (i+1));
            }

            addGarrantia.stream().forEach((addGarrantia1) -> {
                dao.regGarrantia(getId.split(";")[0],addGarrantia1);
            });

            addDocumento.stream().forEach((addDocumento1) -> {
                dao.regdocumeto(getId.split(";")[0], addDocumento1);
            }); /*
            idUser NUMBER,
            idBanco NUMBER,
            idCredito NUMBER,
            totalDocumetosEntrges NUMBER,
            totalGarantiaCredito NUMBER
             */

            Object resul = Call.callSampleFunction("FUNC_CONFIRMAR_REGCREDITO", Types.VARCHAR,
                            SessionUtil.getUserlogado().getNif(),
                            getBancoCredito().split(";")[0],
                            getId.split(";")[0],
                            addDocumento.size(),
                            addGarrantia.size(),
                            SessionUtil.getUserlogado().getIdAgencia());

//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Registo Credito", resul.toString()) );
            BotonImprortar=false;
            String[] sms = resul.toString().split(";");
            Mensagem = sms[0];
            Mensagem2 = sms[1];
            Mensagem3 = sms[2];
            Mensagem4 = sms[3];
            validacao.Validation.AtualizarCompoente("regform", "regMensagem2");
            validacao.Validation.AtualizarCompoente("regform", "regMensagem3");
            validacao.Validation.AtualizarCompoente("regform", "regMensagem4");
            this.simulacao = new Simulacao();
            this.sequenciaCheque="";
            selectTabelaSimulacao= new TabelaSimulacao();
            setBancoCredito("Selecione");
            tabelaSimulacao = new ArrayList<>();
            session.removeAttribute("cliente");
//            addDocumento = new ArrayList<>();
//            addGarrantia = new ArrayList<>();
        }
        else if (getId.split(";")[0].equals("-1"))
        {
            Mensagem = "Erro a Importar simulação!";
            Mensagem3 = getId.split(";")[1];
            validacao.Validation.AtualizarCompoente("regform", "regMensagem3");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Registo Credito", getId.split(";")[1]) );
            validacao.Validation.AtualizarCompoente("regform", "RegGrowl");
        }

    }
       
   @SuppressWarnings("null")
    public void CarregarChequeBanco(String dd)
    {
        RegSimulacaoDao dao= new RegSimulacaoDao();
        System.err.println(getBancoCredito());
        if(!simulacao.getValorSimulacao().isEmpty())
        {
            if(!getBancoCredito().split(";")[0].equals("Selecione"))
            {
                tamanhoNumValideteChaque = Integer.valueOf(getBancoCredito().split(";")[2]);
                String re= dao.BancoChequeID(getBancoCredito().split(";")[0],SessionUtil.getUserlogado().getIdAgencia(), Double.valueOf(simulacao.getValorSimulacao()));
                if(re!=null)
                {
                    System.out.println("Retrutno res "+re);
                    if(!re.split(";")[0].contains("-1"))
                    {
                        idCheque=re.split(";")[0];
                        sequenciaCheque=re.split(";")[1];
                        sequenciaNumIncial=re.split(";")[1];
                    }
                    else
                    {
                        sequenciaCheque="";
                        sequenciaNumIncial="";
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,re.split(";")[1], re.split(";")[2]));
                        setBotonImprortar(false);
                    }
                }
            }
            else
            {
                sequenciaCheque="";
                sequenciaNumIncial="";
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cheque", "Selecione um Banco"));
                setBotonImprortar(false);
            }
            
        }
        else
        {
            sequenciaCheque="";
            sequenciaNumIncial="";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cheque", "Digite o Valor do Credito"));
            setBotonImprortar(false);
        }
    }
    
    public boolean testeConvetData()
    {
        try
        {
            sdfIngle.format(simulacao.getDataCredito());
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean testeTabelaValida()
    {
        try
        {
            return tabelaSimulacao.stream().noneMatch((tabelaSimulacao1) -> (tabelaSimulacao1.getBanco().isEmpty() || tabelaSimulacao1.getTipoPagamento().isEmpty() || tabelaSimulacao1.getNundocumento().isEmpty()));
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean testeGarrantiaEDocumtento()
    {
        int i=0;
        if(addDocumento.size()<=0)
          i++;  
        if(addGarrantia.size()<=0)
          i++;
        return (i==0);
    }
    public boolean testeDataSimulasao()
    {
        return (compareDates (new Date(), this.simulacao.getDataCredito()))<=0;   
    }
    
    public void Cliente(String D)
    {
        System.err.println("fnfhffhhdhdh hhdhdhd");
       try { 
           FacesContext.getCurrentInstance().getExternalContext().redirect("R.ClienteTest.xhtml");
       } catch (IOException ex) {
           Logger.getLogger(RegSimulacao.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void Validacao(String dd)
    {
        BotonImprortar=false;
        System.err.println("ddff");
        boolean validar=testeTabelaValida();
        if(nif != null)
        {
            if(sequenciaCheque!=null&&!"".equals(sequenciaCheque))
            {
                if(validar)
                {
                   
                    if(!testeGarrantiaEDocumtento())  
                    {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credito", "Adicione Documento ou Garrantia"));
                    }
                    else
                    {
                        if(chequeIsValid())
                        BotonImprortar=true;
                    }
                }
                else
                {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Tabela Credio", "Tabela Incorretamente Priencida!"));
                }
            }
            else
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Banco Credito", "Selecionte um Banco valido para Fazer Credito!"));
            }
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Credito", "Sem Cliente Selecionado!"));
        }
    }
    public void susseco(String dd)
    {
        Mensagem=dd;
    }
    
   @SuppressWarnings("ResultOfMethodCallIgnored")
    public void validarNumCheque()
    {
       if(sequenciaCheque.startsWith(sequenciaNumIncial))
        {
            if((sequenciaNumIncial.length() - sequenciaCheque.length()) > 0)
            {
                sequenciaCheque = sequenciaNumIncial;
            }
            if((sequenciaNumIncial.length() - sequenciaCheque.length()) < -tamanhoNumValideteChaque )
            {
                sequenciaCheque = sequenciaCheque.substring(0, (sequenciaNumIncial.length()+tamanhoNumValideteChaque));
            }
        }
        else
        {
            sequenciaCheque = sequenciaNumIncial;
        }        
    }
    
    public void taxaCreditoSelecionado()
    {
        TaxaESegurosDao dao = new TaxaESegurosDao();
        TaxaESeguros tes = new TaxaESeguros();
        tes.setTipoCredito(simulacao.getTipoCredeito());
        listaTaxa =dao.listaTaxa(tes);
    }
    
    public boolean chequeIsValid(){
        if(!sequenciaCheque.startsWith(sequenciaNumIncial) || (sequenciaNumIncial.length()+tamanhoNumValideteChaque) != sequenciaCheque.length())
        {
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cheque Invalido", "Para o banco selecionado deves completar "+tamanhoNumValideteChaque+" digitos!"));
           return false;
        }
        else
            return true;
    }
}
