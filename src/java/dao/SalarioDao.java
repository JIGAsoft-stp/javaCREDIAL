
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

public class SalarioDao implements Serializable
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
   
  
    public void registrarSalario( String nif,String valor)
    {
        sql="{call PRC_REG_SALARIO(?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, SessionUtil.getUserlogado().getNif());
                cs.setString(2, nif);
                cs.setDouble(3, Double.valueOf(valor));
                cs.setInt(4, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(SalarioDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar salário "+ex.getMessage());
            }
        }
    }
    
    
}
