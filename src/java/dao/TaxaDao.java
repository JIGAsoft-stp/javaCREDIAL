/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sessao.SessionUtil;

/**
 *
 * @author Ahmed Jorge Ferreira
 */
public class TaxaDao implements Serializable
{
    private String sql;
    private CallableStatement cs;
    private String resultado;
    
    /**
     * @param nifUser nif do Criado da Profissao
     * @param tipoCredito o nome do Credito a Ser Usado
     * @param periodo
     * @param valor Valor Credito 
     * @param dataInicio 
     * @param dataFim 
     * @param periodoReebolso 
    */
    public void registarTaxa(String tipoCredito,long periodo,long valor,String dataInicio ,String dataFim,String periodoReebolso)
    {
        sql="{call PRC_REG_TAXA(?,?,?,?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, SessionUtil.getUserlogado().getNif());
                cs.setString(2, tipoCredito);
                cs.setLong(3, periodo);
                cs.setLong(4, valor);
                cs.setString(5, dataInicio);
                cs.setString(6, dataFim);
                cs.setString(7,periodoReebolso);
                cs.setInt(8, SessionUtil.getUserlogado().getIdAgencia());
                cs.execute();
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ProfissaoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a regstrar Profissão"+ex.getMessage());
            }
        }
    }
}
