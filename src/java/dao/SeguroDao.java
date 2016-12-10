
package dao;

import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sessao.SessionUtil;


public class SeguroDao implements Serializable
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    
    /**
     * Desativa um determinado seguro
     * @param nifPessoaLogada nif da pessoa que está logada no sistema
     * @param numeroSeguro  número do seguro
     */
    public void desativarSeguro( int numeroSeguro)
    {
        sql="{call PRC_DISABLE_SEGURO(?,?)}";
        Conexao conexao = new Conexao();
        try
        {
            cs = conexao.getCon().prepareCall(sql);
            cs.setString(1, SessionUtil.getUserlogado().getNif());
            cs.setInt(2, numeroSeguro);
            cs.execute();
            conexao.desCon();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SeguroDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro a desativar seguro "+ex.getMessage());
        }
    }
    /**
     * Registra um novo seguro
     * @param nifPessoaLogada nif da pessoa que está logada no sistema
     * @param valor valor do seguro
     */
    public void registrarSeguro(double valor)
    {
        sql="{call PRC_REG_SEGURO(?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, SessionUtil.getUserlogado().getNif());
                cs.setDouble(2, valor);
                cs.setInt(3, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(SeguroDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar seguro "+ex.getMessage());
            }  
        }
    }
    
    
}
