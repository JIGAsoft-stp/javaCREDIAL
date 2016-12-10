/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CreditoDao;
import dao.ListagemDao;
import dao.RegSimulacaoDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Comobox;
import modelo.Relatorio;
import org.primefaces.context.RequestContext;
import validacao.OperacaoData;

/**
 *
 * @author AhmedJorge
 */
@ManagedBean
@ViewScoped
public class relatorioCobranca implements Serializable
{
    Relatorio relatorioCobranca = new Relatorio();
    List<Comobox> listaBanco;
    List<Comobox> listaCredito;
    List<Relatorio> TableRelatorioCobranca;
    ListagemDao ld= new ListagemDao();

    public relatorioCobranca() {
        CreditoDao cd = new CreditoDao();
        RegSimulacaoDao rs= new RegSimulacaoDao();
        listaCredito = cd.getTipoCredito();
        listaBanco=  rs.listaTodosBanco();
    }

    public Relatorio getRelatorioCobranca() {
        return ((relatorioCobranca==null)? new Relatorio():relatorioCobranca);
    }

    public void setRelatorioHomologoBin(Relatorio relatorioHomologoBin) {
        this.relatorioCobranca = relatorioHomologoBin;
    }

    public List<Comobox> getListaBanco() {
        return ((listaBanco==null)?new ArrayList<>():listaBanco);
    }

    public void setListaBanco(List<Comobox> listaBanco) {
        this.listaBanco = listaBanco;
    }

    public List<Comobox> getListaCredito() {
        return listaCredito;
    }

    public void setListaCredito(List<Comobox> listaCredito) {
        this.listaCredito = listaCredito;
    }

    public List<Relatorio> getTableRelatorioCobranca() {
        return TableRelatorioCobranca;
    }

    public void setTableRelatorioCobranca(List<Relatorio> TableRelatorioCobranca) {
        this.TableRelatorioCobranca = TableRelatorioCobranca;
    }
    
    public void pesquisar()
    {
        if(OperacaoData.compareDates(getRelatorioCobranca().getDataInicial(), getRelatorioCobranca().getDataFinal()) != -1)
        {
            ld.listaRelatorioCobrnaca(this);
        }
        else
        {
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data Invalida", "Verifique as Datas"));
//            FacesContext fc = FacesContext.getCurrentInstance();
//            UIViewRoot viewRoot = fc.getViewRoot();
//            HtmlInputText capa = (HtmlInputText) viewRoot.findComponent("formmHo:ReHoGrowl");
            RequestContext.getCurrentInstance().update("formmCo:ReCoGrowl");
            System.err.println("data erro");
        }
    }
    
}
