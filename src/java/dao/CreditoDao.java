
package dao;

import conexao.Call;
import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Comobox;
import modelo.SequenciaCheque;
import modelo.TaxaESeguros;
import sessao.SessionUtil;


public class CreditoDao implements Serializable
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    
    public String registrarTipoCredito(String nomeCredito)
    {
        sql="{call PRC_REG_TIPOCREDITO(?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1,SessionUtil.getUserlogado().getNif());
                cs.setString(2, nomeCredito);
                cs.setInt(3, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
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
    /**
     * esse metodo retorna um array com todos os tipo de Creditos da base de dados
     * @return 
     */
    public ArrayList<Comobox> getTipoCredito()
    {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<Comobox> al= new ArrayList<>();
        sql="select * from VER_TIPOCREDITO";
        Conexao conexao = new Conexao();
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
                        al.add(new Comobox( rs.getString("REALID") , rs.getString("CREDITO")));
                    }  
                }
            }
            else
            {
                al.add(new Comobox("dndnd", "jjdjnc"));
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro a registrar tipo de crédito "+ex.getMessage());
        }
        return al;
        
    }
    public Object regSequenciaCheque(SequenciaCheque sc)
    {
        Object o =Call.callSampleFunction("FUNC_REG_CHEQUEMPRESA", Types.VARCHAR,
            SessionUtil.getUserlogado().getNif()
            ,sc.getBanco()
        ,sc.getAgencia()
        ,sc.getInicio()
        ,sc.getFim()
        ,sc.getTotalfolhas()
        ,SessionUtil.getUserlogado().getIdAgencia());    
        return o;
    }
            
}
