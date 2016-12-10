/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Call;
import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Agencia;
import modelo.Banco;
import modelo.Cheque;
import modelo.Movimento;
import modelo.SequenciaCheque;
import modelo.Utilizador;
import sessao.SessionUtil;
import validacao.OperacaoData;

/**
 *
 * @author AhmedJorge
 */
public class AdministracaoDao implements Serializable{
    private String sql;
    private CallableStatement cs;
    private String resultado;
    private SimpleDateFormat sdfIngles= new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfPT= new SimpleDateFormat("dd-MM-yyyy");
    // FUNC_REG_BANCO
    public ArrayList<Banco> listaTodosBanco()
     {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Banco> al= new ArrayList<>();
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        sql="select * FROM VER_BANCO";
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
                        al.add(new Banco(rs.getString("Nome"), rs.getString("SIGlA"), rs.getString("SALDO"), rs.getString("ID")));
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
      public ArrayList<Banco> listaTodosBanco2()
     {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Banco> al= new ArrayList<>();
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        sql="select * FROM VER_BANCO";
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
                        al.add(new Banco(rs.getString("NOME"), rs.getString("SIGLA"), rs.getString("SALDO"), rs.getString("ID"), rs.getString("SALDOSF")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a listar bancos"+ex.getMessage());
            }
        }
        return al;
        }
    public String registrarBanco(Banco b)
    {
        sql="{?=call FUNC_REG_BANCO(?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1,Types.VARCHAR);
                cs.setString(2, SessionUtil.getUserlogado().getNif());
                cs.setString(3, b.getSigla());
                cs.setString(4, b.getNome());
                cs.setInt(5, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                resultado = cs.getString(1);
                System.err.println("banco "+ resultado);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao Registar Credito"+ex.getMessage());
            }
        }
        return resultado;
    }
    public ArrayList<Movimento> MovimentacaoBanco(Banco b,Date data)
    {
        ArrayList<Movimento> al = new ArrayList<>();
        try {
            ResultSet rs = Call.callTableFunction("FUNCT_LOAD_MOVIMENTOBANCO", "*", b.getId(), data, SessionUtil.getUserlogado().getIdAgencia());

            if (rs != null) {
                while (rs.next()) {
                    al.add(new Movimento(null, rs.getString("DATA"), rs.getString("CREDITO"), rs.getString(4), rs.getString("DEBITO")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("listar Movimeto do Banco" + ex.getMessage());
        }
        return al;
    }
    public ArrayList<Cheque> getSeguenciaBanco2(String idBanco)
    {
        ArrayList<Cheque> al= new ArrayList<>();
        sql="SELECT * from table(FUNCT_LOAD_CHEQUEEMPRESA(?))";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, idBanco);
                cs.execute();
                ResultSet rs = cs.getResultSet();
                if(rs!=null)
                {
                    while (rs.next()) 
                    {
                       al.add( new Cheque(rs.getString("DATA"), rs.getString("INICIO"),rs.getString("FIM"), rs.getString(5),rs.getString("AGENCIA"), rs.getString("ESTADO")));
                    }
                }
                System.err.println("banco "+ resultado);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("listar Movimeto do Banco"+ex.getMessage());
            }
        }
        return al;
    }
    public ArrayList<Cheque> getSeguenciaBanco(Banco b)
    {
        ArrayList<Cheque> al= new ArrayList<>();
        sql="SELECT * from table(FUNCT_LOAD_CHEQUEEMPRESA(?,?))";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, b.getId());
                cs.setInt(2, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                ResultSet rs = cs.getResultSet();
                if(rs!=null)
                {
                    while (rs.next()) 
                    {
                       al.add( new Cheque(rs.getString("DATA"), rs.getString("INICIO"),rs.getString("FIM"), rs.getString(5),rs.getString("AGENCIA"), rs.getString("ESTADO")));
                    }
                }
                System.err.println("banco "+ resultado);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("listar Movimeto do Banco"+ex.getMessage());
            }
        }
        return al;
    }
    public ArrayList<Cheque> getSeguenciaBancoCheque(int b,  String agencia)
    {
        ArrayList<Cheque> al= new ArrayList<>();
        sql="SELECT * from table(FUNCT_LOAD_CHEQUEEMPRESA(?,?))";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setInt(1, b);
                cs.setObject(2, ((agencia == null || agencia.equals("")) ? null : agencia));
                cs.execute();
                ResultSet rs = cs.getResultSet();
                if(rs!=null)
                {
                    while (rs.next()) 
                    {
                       al.add( new Cheque(rs.getString("DATA"), rs.getString("INICIO"),rs.getString("FIM"), rs.getString(5),rs.getString("AGENCIA"), rs.getString("ESTADO")));
                    }
                }
                System.err.println("banco "+ resultado);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("listar Movimeto do Banco"+ex.getMessage());
            }
        }
        return al;
    }
   
    public List<Banco> cobrancaDepositos(int idBanco, Date dataInicial, Date dataFinal, String agencia)
    {
        List<Banco> info = new ArrayList<>();
        Object agency = (agencia == null || agencia.equals("")) ? null : agencia;
        String functionName = "FUNCT_LOAD_COBRANCABANCO";
        ResultSet rs = Call.callTableFunction(functionName, "*",idBanco,OperacaoData.toSQLDate(dataInicial),OperacaoData.toSQLDate(dataFinal), agency);
        if(rs != null)
        {
            try {
                while(rs.next())
                {
                    Banco b = new Banco();
                    b.setData(rs.getString("DATA"));
                    b.setCredito(rs.getString("CREDITO"));
                    b.setDebito(rs.getString("DEBITO"));
                    b.setSigla(rs.getString("SIGLA"));
                    b.setNome(rs.getString("BANCO"));
                    info.add(b);
                }
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(AdministracaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("tamanho da lista "+info.size());
        return  info;
    }
    
    public List<Banco> saldosDiarios(String idBanco, Date dia, String agencia)
    {
        List<Banco> info = new ArrayList<>();

        Object numAgencia = ((agencia == null || agencia.equals("")) ? null : agencia);
        String functionName = "FUNCT_LOAD_MOVIMENTOBANCO";
        ResultSet rs = Call.callTableFunction(functionName,"*", idBanco,((dia == null)? null : OperacaoData.toSQLDate(dia)),agencia);
        if(rs != null)
        {
            try {
                while(rs.next())
                {
                    Banco banco = new Banco();
                    banco.setCredito(rs.getString("CREDITO"));
                    banco.setDebito(rs.getString("DEBITO"));
                    banco.setNome(rs.getString("DESIGNAÇÃO OPERAÇÃO"));
                    banco.setData(rs.getString("DATA"));
                    info.add(banco);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdministracaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return info;
    }
    
    public String regAgencia(Agencia agencia)
    {
        String functionName = "FUNC_REG_AGENCIA";
        Object resp = Call.callSampleFunction(functionName, Types.VARCHAR, 
               Integer.valueOf(SessionUtil.getUserlogado().getNif()),
               Integer.valueOf(agencia.getLocalidade()),
              SessionUtil.getUserlogado().getIdAgencia(),
              agencia.getNome());
        return resp.toString();
    }
    
    public List<SequenciaCheque> listaChequesDisponiveis()
    {
        List<SequenciaCheque> cheques = new ArrayList<>();
        ResultSet rs = Call.selectFrom("VER_CHEQUES_DIPONIVEIS", "*");
        
        if(rs != null)
        {
            try {
                while(rs.next())
                {
                    SequenciaCheque c = new SequenciaCheque();
                    c.setAgencia(rs.getString("AGENCIA"));
                    c.setBanco(rs.getString("BANCO"));
                    c.setTotalfolhas(rs.getString("TOTAL"));
                    c.setTotalDestribuido(rs.getString("DESTRIBUIDA"));
                    cheques.add(c);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdministracaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cheques;
    }
}