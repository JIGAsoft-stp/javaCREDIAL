/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.TaxaESegurosBean;
import conexao.Call;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TaxaESeguros;
import sessao.SessionUtil;

/**
 *
 * @author AhmedJorge
 */
public class TaxaESegurosDao implements Serializable
{
    public Object regTaxa(TaxaESegurosBean tesb)
    {
        Object o=Call.callSampleFunction("FUNC_REG_TAXA",Types.VARCHAR
                ,SessionUtil.getUserlogado().getNif()
                ,tesb.getTesbReg().getTipoCredito()
                ,tesb.getTesbReg().getDias()
                ,tesb.getTesbReg().getTaxa()
                ,SessionUtil.getUserlogado().getIdAgencia()
                );
        return o;
    }
    public ArrayList<TaxaESeguros> listaTaxa (TaxaESeguros tesb)
    {
         ArrayList<TaxaESeguros> taxa= new ArrayList<>();
        try {
            //SELECT * FROM Table(FUNCT_LOAD_ADM_TAXA(24));
           
            ResultSet rs = Call.callTableFunction("FUNCT_LOAD_ADM_TAXA", "*", tesb.getTipoCredito());
            while (rs.next()) {
                taxa.add( new TaxaESeguros(rs.getString("DIAS"), rs.getString("TAXA"), rs.getString("À"), rs.getString("DE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaxaESegurosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taxa;
    }
    public ArrayList<TaxaESeguros> listaSeguros ()
    {
         ArrayList<TaxaESeguros> taxa= new ArrayList<>();
        try {
            //SELECT * FROM Table(FUNCT_LOAD_ADM_TAXA(24));
           
            ResultSet rs = Call.selectFrom("VER_SEGUROS", "*");
            if(rs!=null)
            while (rs.next()) {
                taxa.add( new TaxaESeguros(rs.getString("SEGURO"), rs.getString("STATE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaxaESegurosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taxa;
    }
    public void regSeguros(TaxaESegurosBean tesb)
    {
        Call.callProcedure("PRC_REG_SEGURO"
                ,SessionUtil.getUserlogado().getNif()
                ,tesb.getTesb().getValorSeguros()
                ,SessionUtil.getUserlogado().getIdAgencia()
                );
    }
}
