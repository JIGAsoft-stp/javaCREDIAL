
package validacao;

import conexao.Conexao;
import dao.ClienteDao;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Helio
 */
public class Validation 
{
      /**
     * 
     * @param idFormulario identificação do formulário que está o componente
     * @param idComponente identificação do componente a ser atualizado
     */
    public static void AtualizarCompoente(String idFormulario, String idComponente)
    {
        RequestContext.getCurrentInstance().update(idFormulario+":"+idComponente);
    }
    
    public static void callBackParam(String nomeObjeto, String valor)
    {
        RequestContext.getCurrentInstance().addCallbackParam(nomeObjeto, valor);
    }
    public static void redirecionar(String url)
    {
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     * 
     * @param campoRetorno O valor do campo a ser retornado
     * @param campoSql campo sql a comparar
     * @param view view que se efetuará a pesquisa
     * @return valor do campo campoRetorno
     */
    public static String DevolverValor(String campoRetorno,String view, String valor, String campo)
    {
        String sql = null, resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        sql = "SELECT "+campoRetorno+" FROM "+view+" WHERE "+campo+"=?";
           Conexao conexao = new Conexao();
        try
        {
            if(conexao.getCon()!=null)
            {
                ps = conexao.getCon().prepareStatement(sql);
                ps.setString(1, valor);
                ps.execute();
               
                rs = ps.getResultSet();
                if( rs!= null)
                {
                    while(rs.next())
                    {
                        resultado = rs.getString(campoRetorno);
                    }
                    rs.close();
                    conexao.closeConect();
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
  
  
}
