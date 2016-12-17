
package dao;

import bean.RelatorioHomologoBin;
import bean.relatorioCobranca;
import conexao.Call;
import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Comobox;
import modelo.Relatorio;
import sessao.SessionUtil;
import validacao.OperacaoData;

/**
 *
 * @author Helio
 */
public class ListagemDao implements Serializable
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    private Relatorio relatorio = null;
    
    public List<Relatorio> info(String s,Date dataInicial, Date dataFinal,String idLocalTrabalo, String idLocalidade,String agencia, int idTipoCredito)
    {
        sql = "SELECT *FROM TABLE(FUNCT_REP_CREDITOCONCEBIDO(?,?,?,?,?,?,?))";
        Conexao conexao = new Conexao();
        ResultSet re2 = null;
        List<Relatorio> info = new ArrayList<>();
        if(conexao.getCon() != null)
        {
            try
            {
                ps= conexao.getCon().prepareCall(sql);
                ps.setString(1, s);
                ps.setDate(2, OperacaoData.toSQLDate(dataInicial));
                ps.setDate(3, OperacaoData.toSQLDate(dataFinal));
                ps.setString(4, idLocalTrabalo);
                ps.setString(5, idLocalidade);
                ps.setObject(6, agencia);
                ps.setObject(7, idTipoCredito);
                re2 = ps.executeQuery();
                if(re2 != null)
                {
                    while(re2.next())
                    {
                        Relatorio relatorio = new Relatorio();
                        relatorio.setNif(re2.getString("NIF"));
                        relatorio.setNome(re2.getString("NOME"));
                        relatorio.setApelido(re2.getString("APELIDO"));
                        relatorio.setValorCredito(re2.getString("VALOR"));
                        relatorio.setLocalTrabalho(re2.getString("NOME LOCAL TRABALHO"));
                        info.add(relatorio);
                    }
                    re2.close();
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
       return info;
    }
    

    public List<Comobox> listaLocalidade()
    {
         List<Comobox> l= new ArrayList<>();
         Conexao conexao = new Conexao();
        try {
            if(conexao.getCon()!=null)
            {
                sql = "select * from CREDIAL.VER_LOCALTRABALHO";

                ps = conexao.getCon().prepareCall(sql);
                rs=ps.executeQuery();
                while (rs.next())
                {                
                  l.add(new Comobox(rs.getString("REALID"), rs.getString("LOCAL")));
                }
            }
            else
            {
                l.add(new Comobox("ndnd","ndndn"));
                l.add(new Comobox("ndnd","ndndn"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            return l;
        }
         return l;
    }
    
    public void listaRelatorioHomologo(RelatorioHomologoBin bin)
    {
         List<Relatorio> l= new ArrayList<>();
         double somaAnoAtual = 0, somaAnoPassado = 0, diferencaAno = 0;
         Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try {
                sql = "select * from TABLE(FUNCT_LOAD_CONTACREDITO(?,?,?,?,?,?))";  
                cs = conexao.getCon().prepareCall(sql);
                    cs.setDate(1, OperacaoData.toSQLDate(bin.getRelatorioHomologoBin().getDataInicial()));
                    cs.setDate(2, OperacaoData.toSQLDate(bin.getRelatorioHomologoBin().getDataFinal()));
                    cs.setInt(3, Integer.valueOf(bin.getRelatorioHomologoBin().getDiferenca()));
                    cs.setInt(4, Integer.valueOf(bin.getRelatorioHomologoBin().getLocalTrabalho()));
                    cs.setInt(5, Integer.valueOf(bin.getRelatorioHomologoBin().getAgencia()));
                    cs.setInt(6, Integer.valueOf(bin.getRelatorioHomologoBin().getTipoCredito()));
                rs=cs.executeQuery();
                while (rs.next())
                {                
                  l.add(new Relatorio( rs.getString("DIFIFERENCA VALOR") , rs.getString("VALOR DATA"), rs.getString("VALOR ANTIGO")));
                }
                rs.close();

//                Relatorio r = new Relatorio((l.get(l.size()-1).getDiferenca()),(l.get(l.size()-1).getAnoAtual()), (l.get(l.size()-1).getAnoPassado()));
//                l.clear();
//                l.add(r);
                bin.setTableRelatorioHomologo(l);
            } catch (SQLException ex) {
                Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Relatorio> infoCredito(Date dataInicial, Date dataFinal, int tipoCredito,int periodoComparacao, int agencia)
    {
        sql ="SELECT *FROM TABLE(FUNCT_LOAD_CREDITO_CLIENTE(?,?,?,?,?))";
        Conexao conexao = new Conexao();
        List<Relatorio> info = new ArrayList<>();
        if(conexao.getCon() != null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setDate(1, OperacaoData.toSQLDate(dataInicial));
                cs.setDate(2, OperacaoData.toSQLDate(dataFinal));
                 cs.setInt(3, tipoCredito);
                cs.setInt(4, periodoComparacao);
                cs.setInt(5, agencia);
                cs.execute();
                rs = cs.getResultSet();
                if(rs != null)
                {
                    while(rs.next())
                    {
                        relatorio = new Relatorio();
                        relatorio.setDossier(rs.getString("DOSSIER"));
                        relatorio.setNome(rs.getString("NOME"));
                        relatorio.setMontanteDivida(rs.getString("MONTANTE DIVIDA"));
                        relatorio.setTaeg(rs.getString("TAEG"));
                        relatorio.setEstado(rs.getString("ESTADO"));
                        relatorio.setCapitalConcebido(rs.getString("VALOR CREDITO"));
                
                        info.add(relatorio);
                    }
                    rs.close();
                    conexao.desCon();
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return info;
    }
    public Relatorio LastValues()
    {
        return relatorio;
    }

     public void listaRelatorioCobrnaca(relatorioCobranca  bin)
    {
         List<Relatorio> l= new ArrayList<>();
         Conexao conexao = new Conexao();
         if(conexao.getCon()!=null)
         {
            try {
                sql = "select * from TABLE(FUNCT_LOAD_COBRANCA(?,?,?,?,?))";
                cs = conexao.getCon().prepareCall(sql);
                cs.setDate(1, OperacaoData.toSQLDate(bin.getRelatorioCobranca().getDataInicial()));
                cs.setDate(2, OperacaoData.toSQLDate(bin.getRelatorioCobranca().getDataFinal()));
                cs.setInt(3, Integer.valueOf(bin.getRelatorioCobranca().getTipoCredito()));
                cs.setInt(4, Integer.valueOf(bin.getRelatorioCobranca().getBanco()));
                cs.setInt(5, Integer.valueOf(bin.getRelatorioCobranca().getDiferenca()));
                rs=cs.executeQuery();
                while (rs.next())
                {                
                  l.add(new Relatorio(rs.getObject("NOME")+" "+rs.getObject("APELIDO"), rs.getString("SIGLA BANCO"), rs.getString("REMBOLSO"), rs.getString("NUM DOC REAL"), rs.getString("DATA DOC PAG REAL"), rs.getString("NUMERO DOCUMENTO")));
                }
                bin.setTableRelatorioCobranca(l);
            } catch (SQLException ex) {
                Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
     
    public List<Relatorio> regRelatorioCheque(Date dataInicial, Date dataFinal, String estado, String banco, String localTrabalho,int agencia, int idTipoCredtido)
    {
        List<Relatorio> info = new ArrayList<>();
        
        String functionName = "FUNCT_LOAD_REQ_CHEQUES_COBRANC";  
        ResultSet rs = Call.callTableFunction(functionName, "*", OperacaoData.toSQLDate(dataInicial), OperacaoData.toSQLDate(dataFinal),estado,agencia, banco,localTrabalho, idTipoCredtido);
        try
        {
            if(rs != null)
            {
                 while(rs.next())
                {
                  
                    info.add(new Relatorio(rs.getString("NIF"),rs.getString("NOME"),rs.getString("VALOR CHEQUE"), rs.getString("ENDOSSADO")));
                }
                rs.close();
            }
           
        }
        catch(SQLException ex)
        {
            System.out.println("Erro a obter cheques "+ex.getMessage());
        }
        System.out.println("Tamanho da lista "+info.size());
        return info;
    }
    
    public List<Relatorio> regRelatorioDivida(Date dataInicial, Date dataFinal,String localTrabalho,int agencia, int diferenciaAno, int tipoCredito)
    {
        List<Relatorio> info = new ArrayList<>();
        String functionName = "FUNCT_LOAD_REQ_CRED_DIVIDPRDOT";
        rs = Call.callTableFunction(functionName, "*", OperacaoData.toSQLDate(dataInicial),
                OperacaoData.toSQLDate(dataFinal),
                localTrabalho,
                agencia, 
                diferenciaAno,
                tipoCredito);
        try
        {
              while(rs.next())
              {
                  Relatorio relatorio = new Relatorio();
                  relatorio.setNif(rs.getString("NIF"));
                  relatorio.setNome(rs.getString("NOME"));
                  relatorio.setCreditoSolicitado(rs.getString("CREDITO SOLICITADO"));
                  relatorio.setTotalCredito(rs.getString("MONTANTE TOTAL CREDITO"));
                  relatorio.setValorPago(rs.getString("VALOR JA PAGO"));
                  info.add(relatorio);
              } 
              rs.close();
        }
        catch(SQLException exception)
        {
            System.out.println("erro a obter relatórios de divida por produto "+exception.getMessage());
        }
        
        return info;

    }
    
    public List<Relatorio> relatorioDividaCapital(Date dataInicial, Date dataFinal, int quantidadeComparacao, int agencia, int idTipoCredito)
    {
        String functionName = "FUNCT_LOAD_REQ_DIVIDA_TAGE";
        List<Relatorio> info = new ArrayList<>();
        rs = Call.callTableFunction(functionName, "*", OperacaoData.toSQLDate(dataInicial), 
                OperacaoData.toSQLDate(dataFinal), 
                quantidadeComparacao,
                agencia,
                idTipoCredito);
        if(rs != null)
        {
            try
            {
                while(rs.next())
                {
                    Relatorio relatorio = new Relatorio();
                    relatorio.setNif(rs.getString("NIF"));
                    relatorio.setNome(rs.getString("NOME"));
                    relatorio.setMontanteDivida(rs.getString("MONTANTE DIVIDA"));
                    relatorio.setValorCredito(rs.getString("VALOR CREDITO"));
                    relatorio.setDossier(rs.getString("DOSSIER"));
                    relatorio.setTaeg(rs.getString("TAEG"));
                    relatorio.setEstado(rs.getString("ESTADO"));
                    info.add(relatorio);
                }
                rs.close();  
            }
            catch (SQLException ex) {
                Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return info;
    }
    
    public List<Relatorio> listaCobrancas(Date dataInicial, Date dataFinal, int tipoCredito,int idBanco, int peridoComparacao, int agencia)
    {
         String functionName = "FUNCT_LOAD_REQ_COBRANCA";
        List<Relatorio> info = new ArrayList<>();
        rs = Call.callTableFunction(functionName, "*", OperacaoData.toSQLDate(dataInicial), OperacaoData.toSQLDate(dataFinal), tipoCredito, idBanco, peridoComparacao, agencia);
        if(rs != null)
        {
            try
            {
                while(rs.next())
                {
                    Relatorio relatorio = new Relatorio();
                    relatorio.setNif(rs.getString("NIF"));
                    relatorio.setNome(rs.getString("NOME"));
                    relatorio.setApelido(rs.getString("APELIDO"));
                    relatorio.setNumDocReal(rs.getString("NUM DOC REAL"));
                    relatorio.setReembolso(rs.getString("REMBOLSO"));
                    relatorio.setNumDocPrevisao(rs.getString("NUMERO DOCUMENTO"));
                    relatorio.setDataReal(rs.getString("DATA DOC PAG REAL"));
                    relatorio.setDataPrevista(rs.getString("DATA DOC PAGAMENTO"));
                    info.add(relatorio);
                }
                rs.close();  
            }
            catch (SQLException ex) {
                Logger.getLogger(ListagemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return info;
    }
   
}
