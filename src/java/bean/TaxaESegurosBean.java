/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CreditoDao;
import dao.TaxaESegurosDao;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Comobox;
import modelo.TaxaESeguros;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AhmedJorge
 */
@ManagedBean
@ViewScoped
public class TaxaESegurosBean implements Serializable
{
    
    private TaxaESeguros tesb = new TaxaESeguros();
    private TaxaESeguros taxaSelcionada = new TaxaESeguros();
    private TaxaESeguros tesbReg = new TaxaESeguros();
    private ArrayList<Comobox> listaCredito = new ArrayList<>();
    private ArrayList<TaxaESeguros> listaCreditoValor = new ArrayList<>();
    private ArrayList<TaxaESeguros> tesbSegurdo = new ArrayList<>();
    public TaxaESegurosBean() 
    {
        CreditoDao cd = new CreditoDao();
        listaCredito= cd.getTipoCredito();
        CreateList();
    }

    public TaxaESeguros getTesb() {
        return tesb=((tesb==null)? new TaxaESeguros():tesb);
    }

    public void setTesb(TaxaESeguros tesb) {
        this.tesb = tesb;
    }

    public ArrayList<Comobox> getListaCredito() {
        return listaCredito=((listaCredito==null)?new ArrayList<>():listaCredito);
    }

    public void setListaCredito(ArrayList<Comobox> listaCredito) {
        this.listaCredito = listaCredito;
    }

    public TaxaESeguros getTesbReg() {
        return tesbReg=((tesbReg==null)?new TaxaESeguros():tesbReg);
    }

    public void setTesbReg(TaxaESeguros tesbReg) {
        this.tesbReg = tesbReg;
    }

    public ArrayList<TaxaESeguros> getListaCreditoValor() {
        return listaCreditoValor=(listaCreditoValor==null)?new ArrayList<>():listaCreditoValor;
    }

    public void setListaCreditoValor(ArrayList<TaxaESeguros> listaCreditoValor) {
        this.listaCreditoValor = listaCreditoValor;
    }

    public TaxaESeguros getTaxaSelcionada() {
        return taxaSelcionada=((taxaSelcionada==null)?new TaxaESeguros():taxaSelcionada);
    }

    public void setTaxaSelcionada(TaxaESeguros taxaSelcionada) {
        this.taxaSelcionada = taxaSelcionada;
    }

    public ArrayList<TaxaESeguros> getTesbSegurdo() {
        return tesbSegurdo=((tesbSegurdo==null)?new ArrayList<>():tesbSegurdo);
    }

    public void setTesbSegurdo(ArrayList<TaxaESeguros> tesbSegurdo) {
        this.tesbSegurdo = tesbSegurdo;
    }
    
    public void regTaxa()
    {
        TaxaESegurosDao dao = new TaxaESegurosDao();
        Object o= dao.regTaxa(this);
        if(!o.toString().equals("true"))
        {
            mensagem.Mensagem.msgWarm(o.toString());
            validacao.Validation.AtualizarCompoente("ts_tipoCreditoLista", "ts_tipoCreditoGrowl");
        }
        else
        {
            
            RequestContext.getCurrentInstance().execute("CloseRegTaxa()");
            tesb.setTipoCredito(tesbReg.getTipoCredito());
            dadosTable();
            RequestContext.getCurrentInstance().execute("limarTaxaValor()");
            validacao.Validation.AtualizarCompoente("ts_tipoCreditoLista","ts_tipoCreditoListaCP");
            validacao.Validation.AtualizarCompoente("ts_tipoCreditoTabela","ts_tipoCreditoTabelaDados");
        }
    }
    public void regSeguro()
    {
        TaxaESegurosDao dao = new TaxaESegurosDao();
        dao.regSeguros(this);
        RequestContext.getCurrentInstance().execute("CloseRegSeguros()");
        RequestContext.getCurrentInstance().execute("limarSeguroValor()");
        CreateList();
        validacao.Validation.AtualizarCompoente("ts_tipoCreditoSeguroTable","ts_tipoCreditoSeguroTableDados");
    }
    public void dadosTable()
    {
        TaxaESegurosDao dao = new TaxaESegurosDao();
        listaCreditoValor =dao.listaTaxa(this.tesb);
    }

    private void CreateList() {
        TaxaESegurosDao dao= new TaxaESegurosDao();
        tesbSegurdo= dao.listaSeguros();
    }
    
    public String coverteToMoeda(String valor)
    {
        Double d= Double.valueOf(valor);
        String re=NumberFormat.getInstance(Locale.FRENCH).format(d);
        return re;
    }
}
