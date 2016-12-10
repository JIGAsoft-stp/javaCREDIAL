
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
import modelo.Utilizador;
import sessao.SessionUtil;

/**
 *
 * @author Helio
 */
public class TransferenciaDAO implements Serializable
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    
    
    public String registrarMovimentacaoBancaria( double valorTransferir, int  idBancoEnviar, int idBancoReceber, String descricao)
    {
        
        sql="{?=call FUNC_EFETUAR_TRANSFERENCIA(?,?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1,Types.VARCHAR);
                cs.setInt(2, Integer.valueOf(SessionUtil.getUserlogado().getNif()));
                cs.setDouble(3, valorTransferir);
                cs.setInt(4, idBancoEnviar);
                cs.setInt(5, idBancoReceber);
                cs.setString(6, descricao);
                cs.setInt(7, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                resultado = cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
    public String registrarCredito(int idBanco,double debito, double credito, String desc)
    {
        sql="{?=call FUNC_REG_MOVIMENTOBANCO(?,?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setInt(2, Integer.valueOf(SessionUtil.getUserlogado().getNif()));
                cs.setInt(3, idBanco);
                cs.setDouble(4, debito);
                cs.setDouble(5, credito);
                cs.setString(6, desc);
                cs.setInt(7, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                resultado = cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(TransferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
}
