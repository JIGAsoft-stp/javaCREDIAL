
package dao;

import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import sessao.SessionUtil;


public class MenuDao implements Serializable
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    
    /**
     * Registra um novo menu
     * @param nifPessoaLogada nif da pessoa que está logada
     * @param numeroMenu número do menu
     * @param nomeMenu niome do menu
     * @param linkMenu link do menu
     */
    public void registrarMenu(int numeroMenu,String nomeMenu,String linkMenu)
    {
        sql="{call PRC_REG_MENU(?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1,Types.VARCHAR);
                cs.setString(2, SessionUtil.getUserlogado().getNif());
                cs.setInt(3, numeroMenu);
                cs.setString(4, nomeMenu);
                cs.setString(5, linkMenu);
                cs.setInt(6, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar menu "+ex.getMessage());
            }
        }
    }
    /**Desativa um determinado menu.
     * @param nifPessoaLogada
     * @param numeroMenu 
     */
    public void desativarMenu( int numeroMenu)
    {
        sql="{call PRC_DISABLE_MENU(?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, SessionUtil.getUserlogado().getNif());
                cs.setInt(2, numeroMenu);
                cs.execute();
                conexao.desCon();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(MenuDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a desativar menu "+ex.getMessage());
            }
        }
    }
    
    
}
