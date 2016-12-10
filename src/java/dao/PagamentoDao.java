
package dao;

import conexao.Call;
import conexao.Conexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Comobox;
import sessao.SessionUtil;
import validacao.OperacaoData;


public class PagamentoDao 
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    
      /**
     * Registrar a forma de pagamento
     * @param nome a forma de pagamento
     * @param idUtilizador número do utilizador que registrou a forma de pagamento
     */
    public void registrarFontePagamento(String nome)
    {
        sql="{call PRC_REG_FONTEPAGAMENTO(?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, nome);
                cs.setString(2, SessionUtil.getUserlogado().getNif());
                cs.setInt(3, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar fonte de pagamento");
            }
        }
    }
    /**
     * @param idUtilizador número do utilizador
     * @param designacao nome do tipo de pagamento
     */
    public void registrarTipoPagamento( String designacao)
    {
        sql="{call PRC_REG_TIPOPAGAMENTO(?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, SessionUtil.getUserlogado().getNif());
                cs.setString(2, designacao);
                cs.setInt(3, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar tipo de pagamento "+ex.getMessage());
            }
        }
    }
    // function to search payment
    public ResultSet buscarPagamento(String codigoPagamento) 
    {
        sql="{?=call FUNC_SEARCH_PAGAMENTO(?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, codigoPagamento);
                cs.execute();
                rs = cs.getResultSet();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    
      // lista todos os bancos
     public ArrayList<Comobox> listaBancos()
     {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
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
                        al.add(new Comobox(rs.getString("ID"), rs.getString("NOME")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter bancos "+ex.getMessage());
            }
        }
        return al;
    }
    public String devolverNome(String id)
    {
         sql="select * FROM VER_BANCO WHERE ID=?";
         Conexao conexao = new Conexao();
         if(conexao.getCon()!=null)
         {
            try 
            {
               ps = conexao.getCon().prepareStatement(sql);
               ps.setString(1, id);
               ps.execute();
               rs = ps.getResultSet();
               if(rs!=null)
               {
                   while(rs.next())
                   {
                       resultado = rs.getString("NOME");
                   }
               }
               rs.close();
               conexao.desCon();
            } catch (SQLException ex) {
                Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        return resultado;
    }
       public String devolverNomeAgencia(String id)
    {
         sql="select * FROM VER_AGENCIA WHERE ID=?";
         Conexao conexao = new Conexao();
         if(conexao.getCon()!=null)
         {
            try 
            {
               ps = conexao.getCon().prepareStatement(sql);
               ps.setString(1, id);
               ps.execute();
               rs = ps.getResultSet();
               if(rs!=null)
               {
                   while(rs.next())
                   {
                       resultado = rs.getString("AGENCIA");
                   }
               }
               rs.close();
               conexao.desCon();
            } catch (SQLException ex) {
                Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        return resultado;
    }
     public String DadosPagamento(String id)
     {
        Conexao conexao = new Conexao();
         if(conexao.getCon()!=null)
         {
            sql="{?=call FUNC_GET_DOCPAGAMENTO(?)}";  
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setString(2, id);
                cs.execute();
                resultado = cs.getString(1);
                conexao.desCon();
                System.out.println("DOC DATABASE  = "+resultado);
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter dados de pagamento "+ex.getMessage());
            }
         }
        return resultado;
     }
     // efetua o pagamento como fonte semelhante
     public String efetuarPagamento(String idPagamento,
             String numDoc,String idBanco,String fonteP, float valorPrestacao, Date dataDocumentoPagamento)
     {
         sql ="{?=call FUNC_EFECTUAR_PAGANAOFAZEADO(?,?,?,?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setString(2, idPagamento);
                cs.setString(3, SessionUtil.getUserlogado().getNif());
                cs.setString(4, numDoc);
                cs.setString(5, idBanco);
                cs.setString(6, fonteP);
                cs.setFloat(7, valorPrestacao);
                cs.setDate(8, OperacaoData.toSQLDate(dataDocumentoPagamento));
                cs.setInt(9, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                resultado = cs.getString(1);
                conexao.desCon();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a efetuar pagamento "+ex.getMessage());
            }
        }
        return resultado;
     }
    
    public String EfetuarPagamento(int idPagamento,String numDoc,int idBanco,String tipoPagamento,float valorPrestacao,Date dataPagamentoPago)
    {
        
        resultado = (String) Call.callSampleFunction("FUNC_EFECTUAR_PAGAMENTO", Types.VARCHAR,
                idPagamento,
                Integer.valueOf(SessionUtil.getUserlogado().getNif()),
                numDoc,
                idBanco,
                tipoPagamento,
                valorPrestacao, 
                (dataPagamentoPago != null)? OperacaoData.toSQLDate(dataPagamentoPago): null,
                SessionUtil.getUserlogado().getIdAgencia());
        return resultado;
    }
    
    public Object RegObecto(String designacao,int tipoObjeto)
    {
        String functionName = "FUNC_REG_OBJECTO";
        Object resp = Call.callSampleFunction(functionName,Types.INTEGER,
                 SessionUtil.getUserlogado().getNif(),
                 designacao,
                 tipoObjeto,
                 SessionUtil.getUserlogado().getIdAgencia()
        );
        return resp;
    }
    
    public ArrayList<Comobox> listaInfo(int tipoObjeto)
    {
        String functionName = "FUNCT_LOAD_ADM_OBJECTO";
        ArrayList<Comobox> info = new ArrayList<>();
        ResultSet rs = Call.callTableFunction(functionName, "*", tipoObjeto);
        try
        {
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("DESCRICAO") != null)
                    {
                        info.add( new Comobox(rs.getString("ID"), rs.getString("DESCRICAO")));
                    }
                        
                }
                rs.close();
            }
        }
        catch(SQLException ex)
        {
            System.out.println("erro a obter dados de pagamento "+ex.getMessage());
        }
        return  info;
                
    }
    
    public void objectoDesativar(String idObjecto)
    {
        Call.callProcedure("PRC_OBJECT_DISATIVE",SessionUtil.getUserlogado().getNif(),idObjecto);
    }

}
 