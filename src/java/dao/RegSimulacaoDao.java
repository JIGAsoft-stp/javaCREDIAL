package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import bean.RegSimulacao;
import conexao.Call;
import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Comobox;
import modelo.Simulacao;
import sessao.SessionUtil;

/**
 *
 * @author AhmedJorge
 */
public class RegSimulacaoDao implements Serializable
{
    SimpleDateFormat sdfIngles =new SimpleDateFormat("yyyy-MM-dd");
    private String sql;
    private CallableStatement cs;
    private String resultado;
    public String registrarTipoCredito(Simulacao s)
    {
        resultado = "";
        sql="select * from table(FUNC_VIEW_SIMULACAO(?,?,?,?,?,?))";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, s.getDias());
                cs.setString(2, s.getValorSimulacao());
                cs.setString(3, s.getTipoCredeito());
                cs.setDouble(4, Double.valueOf(s.getDesconto()));
                cs.setDouble(5, Double.valueOf(s.getCorrecao()));
                cs.setString(6, s.getOpcao());
                ResultSet rr = cs.executeQuery();
                if(rr!=null) 
                {
                    for(int i=0;rr.next();i++)
                    {
                        if(i==0&&!rr.getString("MESSAGE").equals("TRUE"))
                        {
                           return rr.getString("MESSAGE");
                        }
                        else if(i==0){
                            resultado=rr.getString("MESSAGE");
                        }
                        if(i==1)
                        {
                           s.setTaxa(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble("VALOR")));
                           s.setTaxaSC(rr.getDouble("VALOR"));
                        }
                        if(i==2)
                        {
                           s.setPeriodo(rr.getInt("VALOR")+"");
                        }
                        if(i==3)
                        {
                           s.setCapital(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble("VALOR")));
                           s.setCapitalSC(rr.getDouble("VALOR"));
                        }
                        if(i==4)
                        {
                           s.setTaxaSemDesconto(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble(("VALOR"))));
                           s.setTaxaSemDescontoSC(rr.getDouble(("VALOR")));
                        }
                        if(i==5)
                        {
                           s.setTaxaComDesconto(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble(("VALOR"))));
                           s.setTaxaComDescontoSC(rr.getDouble(("VALOR")));
                        }
                        if(i==6)
                        {
                           s.setTotalPagar(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble(("VALOR"))));
                           s.setTotalPagarSC(rr.getDouble(("VALOR")));
                        }
                        if(i==7)
                        {
                           s.setPestacao(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble(("VALOR"))));
                           s.setPestacaoSC(rr.getDouble(("VALOR")));
                        }
                        if(i==8)
                        {
                           s.setReembalsoPeriodo(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble("VALOR")));
                           s.setReembalsoPeriodoSC(rr.getDouble("VALOR"));
                        }
                        if(i==9)
                        {
                           s.setSeguro(NumberFormat.getNumberInstance(Locale.FRENCH).format(rr.getDouble("VALOR")));
                           s.setSeguroSC(rr.getDouble("VALOR"));
                        }
                        if(i==10)
                        {
                            s.setnSemanasMes(rr.getInt("VALOR"));
                        }
                        if(i==11)
                        {
                            s.setIdTaxa(rr.getString("MESSAGE"));
                        }
                        System.err.println(i+"º - mensagem "+rr.getString("MESSAGE"));
                        System.err.println(i+"º - valor"+rr.getString("VALOR"));
                    }

    //                s.setDescontoSC(Integer.valueOf(s.getDesconto()));
    //                s.setDesconto(s.getDesconto());
    //                s.setCorrecao(s.getCorrecao());
                }
                conexao.desCon();

            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar tipo de crédito "+ex.getMessage());
            }
        }
        return resultado;
        
    }
    // lista todas as fontes dde rendimento
    public ArrayList<Comobox> fontesRendimentos()
     {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        sql="select * FROM VER_FONTEPAGAMENTO";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    while (rs.next())
                    {  
                        al.add(new Comobox(rs.getString("REALID"), rs.getString("FONTE")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter fontes rendimentos "+ex.getMessage());
            }
        }
        return al;
        }
    // lista todos os bancos
     public ArrayList<Comobox> listaUserBancos(String valor)
     {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        ResultSet rs = null;

        sql="select * FROM table(FUNCT_LOAD_BANCO_SIMULACAO(?,?,?))";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setInt(1, SessionUtil.getUserlogado().getIdAgencia());
                cs.setString(2, SessionUtil.getUserlogado().getNif());
                cs.setFloat(3, Float.valueOf(valor));
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    al.add(new Comobox("Selecione", "Selecione"));
                    while (rs.next())
                    {  
                        al.add(new Comobox(rs.getString("ID"), rs.getString("SIGLA"), rs.getString("QUANTIDADE DE CHEQUES VARIAVEL")));
                    }  
                }
                rs.close();
                
                if(al.size() == 1){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cheque",  "Nenhum Cheque disponivel para essa agencia!") );
                }
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter bancos "+ex.getMessage());
            }
        }
        return al;
    }
  
    // lista todos os bancos
    public ArrayList<Comobox> listaDocumentos()
    {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        ResultSet rs = null;

        sql="select * FROM VER_TIPODOCUMENTO";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    while (rs.next())
                    {  
                        al.add(new Comobox(rs.getString("REALID"), rs.getString("DOCUMENTO")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter documentos "+ex.getMessage());
            }
        }
        return al;
    }
    public ArrayList<Comobox> listaGarantias()
    {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        
        ResultSet rs = null;

        sql="select * FROM VER_GARRANTIA";

        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    while (rs.next())
                    {  
                        al.add(new Comobox(rs.getString("REALID"), rs.getString("GARRANTIA")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter fontes garantias "+ex.getMessage());
            }
        }
        return al;
    }
    
    public ArrayList<Comobox> listaFormaPagamento()
    {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        ResultSet rs = null;
        sql="select * FROM VER_TIPOPAGAMENTO";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    while (rs.next())
                    {  
                        al.add(new Comobox(rs.getString("REALID"), rs.getString("TIPO")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a Tipo Pagamento"+ex.getMessage());
            }
        }
        return al;
    }
    
    public String regCredito(String nifCliente,RegSimulacao s)
    {
        try {
            Object ret= Call.callSampleFunction("FUNC_REG_CREDITO",
                    Types.VARCHAR
                    ,SessionUtil.getUserlogado().getNif()
                    ,nifCliente
                    ,Float.parseFloat(s.getSimulacao().getValorSimulacao())
                    ,s.getSimulacao().getTotalPagarSC()
                    ,Integer.parseInt(s.getSimulacao().getPeriodo())
                    ,s.getSimulacao().getDataCredito()
                    ,sdfIngles.parse(s.getSimulacao().getDataFinal())
                    ,Integer.parseInt(s.getSimulacao().getIdTaxa())
                    ,s.getSequenciaCheque()
                    ,s.getFonteRedimento()
                    ,s.getIdCheque()
                    ,s.getSimulacao().getDescontoSC()
                    ,s.getModalidadePagamento()
                    ,s.getSimulacao().getTaxaComDescontoSC()
                    ,SessionUtil.getUserlogado().getIdAgencia());
            
            resultado= ret.toString();
            return resultado;
        } catch (ParseException ex) { return ""; }
    }
    
    public String regPagemento(String idCredito,RegSimulacao r,TabelaSimulacao ts,int i)
    {
        try {
            String campos [] = ts.getTipoPagamento().split(";");
            
            Object ret= Call.callSampleFunction("FUNC_REG_PAGAMENTO",Types.VARCHAR,
                    SessionUtil.getUserlogado().getNif(),
                    ts.getNundocumento(),
                    sdfIngles.parse(ts.getDataIng()),
                    ts.getIdTipoPagamento(),
                    idCredito,
                    i,
                    sdfIngles.parse(ts.getDataIng()),
                    0,
                    0,
                    ts.getReebolsoSf(),
                    0,
                    ts.getReebolsoSf(),
                    "",
                    "",
                    ts.getIdbanco(),
                    "",
                    "",
                    SessionUtil.getUserlogado().getIdAgencia());
            resultado = ((ret == null) ? "" : ret.toString() );
            return resultado;
        } catch (ParseException ex) {
            Logger.getLogger(RegSimulacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
        
    public String regGarrantia(String idCredito, Comobox c)
    {
        sql="{?=call FUNC_REG_GARRANTIADOCREDITO(?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setString(2,SessionUtil.getUserlogado().getNif());
                cs.setString(3,c.getId());
                cs.setString(4,idCredito);
                cs.setInt(5, SessionUtil.getUserlogado().getIdAgencia());
                cs.setString(6, c.getDescricao());
                cs.execute();
               resultado= cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(SalarioDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a Registar Garrantia "+ex.getMessage());
            }
        }
        return resultado;

    }
        
    public String regdocumeto(String idCredito, Comobox c)
    {
        sql="{?=call FUNC_REG_DOCUMENTOENTREG(?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setString(2,SessionUtil.getUserlogado().getNif());
                cs.setString(3,c.getId());
                cs.setString(4,idCredito);
                cs.setInt(5, SessionUtil.getUserlogado().getIdAgencia());
                cs.setString(6, c.getDescricao());
                cs.execute();
               resultado= cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(SalarioDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a a registar documentos"+ex.getMessage());
            }
        }
        return resultado;

    }
       
    public String BancoChequeID(String idBanco,int idAgencia,double valor)
    {
        Object o = Call.callSampleFunction("FUNC_RETIRAR_CHEQUE", Types.VARCHAR, 
                SessionUtil.getUserlogado().getNif(),
                idBanco,
                idAgencia,
                valor);
        resultado = ((o == null) ? "" : o.toString());
     return resultado;          
    }
    
//    public String d()
    
    public String regMovimento(String idBanco,double debito,double credico,String numCheque,String numDossier)
    {
        sql="{?=call FUNC_REG_MOVIMENTOBANCO(?,?,?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setString(2,SessionUtil.getUserlogado().getNif());
                cs.setString(3,idBanco);
                cs.setDouble(4,debito);
                cs.setDouble(5,credico);
                cs.setString(6,numCheque);
                cs.setString(7,numDossier);
                cs.setInt(8, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
               resultado= cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(SalarioDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("A registar Movimento"+ex.getMessage());
            }
        }
        return resultado;

    }
    // lista todos os Bancos
    public ArrayList<Comobox> listaTodosBanco()
     {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        sql="select * FROM VER_BANCO";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                if(conexao.getCon()!=null)
                {
                    cs = conexao.getCon().prepareCall(sql);
                    cs.execute();
                    rs=cs.executeQuery(); 
                    if (rs!=null) 
                    {  
                        while (rs.next())
                        {  
                            al.add(new Comobox(rs.getString("ID"), rs.getString("SIGLA")));
                        }  
                    }
                    rs.close();
                }
                else
                {
                    al.add(new Comobox("djdj","ddj"));
                    al.add(new Comobox("1dj","dmsmdj"));
                }
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter fontes rendimentos "+ex.getMessage());
            }
        }
        return al;
        }
    
}
