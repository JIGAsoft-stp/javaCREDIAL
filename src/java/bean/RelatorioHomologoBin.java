/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Export.RelatorioConverter;
import dao.ListagemDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
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
public class RelatorioHomologoBin implements Serializable
{
    Relatorio relatorioHomologoBin = new Relatorio();
    List<Comobox> listaLocalTrabalho;
    List<Relatorio> TableRelatorioHomologo;
    ListagemDao ld= new ListagemDao();
    private String totalAnoAtual, totalAnoPassado, totalDiferenca;
    private List<Comobox> agencias;
    public RelatorioHomologoBin() 
    {
        ld= new ListagemDao();
        agencias = new ArrayList<>();
        agencias.add(new Comobox("-1","Todas"));
        List<Comobox> value = Comobox.loadCombo("VER_AGENCIA","ID", "AGENCIA");
        if(value!= null){ agencias.addAll(value); }
        listaLocalTrabalho = ld.listaLocalidade();
    }

    public Relatorio getRelatorioHomologoBin() {
        return relatorioHomologoBin;
    }

    public void setRelatorioHomologoBin(Relatorio relatorioHomologoBin) {
        this.relatorioHomologoBin = relatorioHomologoBin;
    }

    public List<Comobox> getAgencias() {
        return agencias;
    }

    public List<Comobox> getListaLocalTrabalho() {
        return (listaLocalTrabalho==null) ? new ArrayList<>() : listaLocalTrabalho;
    }

    public void setListaLocalTrabalho(List<Comobox> listaLocalTrabalho) {
        this.listaLocalTrabalho = listaLocalTrabalho;
    }

    public List<Relatorio> getTableRelatorioHomologo() {
        return (TableRelatorioHomologo==null)? new ArrayList<>():TableRelatorioHomologo;
    }

    public void setTableRelatorioHomologo(List<Relatorio> TableRelatorioHomologo) {
        this.TableRelatorioHomologo = TableRelatorioHomologo;
    } 
    
    public void pesquisar()
    {
        if(OperacaoData.compareDates(getRelatorioHomologoBin() .getDataInicial(), getRelatorioHomologoBin().getDataFinal()) != -1)
        {
            ld.listaRelatorioHomologo(this);
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data Invalida", "Verifique as Datas"));
//            FacesContext fc = FacesContext.getCurrentInstance();
//            UIViewRoot viewRoot = fc.getViewRoot();
//            HtmlInputText capa = (HtmlInputText) viewRoot.findComponent("formmHo:ReHoGrowl");
            RequestContext.getCurrentInstance().update("formmHoform:ReHoGrowl");
            System.err.println("data erro");
        }
        
    }
    
    public void exportPDF(){
        RelatorioConverter.xports(new RelatorioConverter.ClienteHomologo(), TableRelatorioHomologo, RelatorioConverter.Type.PDF);
    }
    
    public void exportEcell(){
        RelatorioConverter.xports(new RelatorioConverter.ClienteHomologo(), TableRelatorioHomologo, RelatorioConverter.Type.EXCEL);
    }
}