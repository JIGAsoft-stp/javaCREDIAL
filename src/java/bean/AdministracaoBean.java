/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.AdministracaoDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Banco;
import modelo.Cheque;
import modelo.Movimento;

/**
 *
 * @author AhmedJorge
 */
@ManagedBean
@ViewScoped
public class AdministracaoBean implements Serializable{
    private Banco banco= new Banco();
    private Banco bancoSelected= new Banco();
    private ArrayList<Banco> ListaBancos;
    private ArrayList<Movimento> ListaCreditoBancos;
    private ArrayList<Cheque> ListaChequeBancos;
    private AdministracaoDao  ad= new AdministracaoDao();
    
    public AdministracaoBean() {
        this.ListaBancos = ad.listaTodosBanco();
    }

    public Banco getBanco() {
        return (banco==null)? new Banco():banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ArrayList<Banco> getListaBancos() {
        return (ListaBancos==null)? new ArrayList<>():ListaBancos;
    }

    public void setListaBancos(ArrayList<Banco> ListaBancos) {
        this.ListaBancos = ListaBancos;
    }

    public ArrayList<Movimento> getListaCreditoBancos() {
        return ((ListaCreditoBancos==null)?new ArrayList():ListaCreditoBancos);
    }

    public void setListaCreditoBancos(ArrayList<Movimento> ListaCreditoBancos) {
        this.ListaCreditoBancos = ListaCreditoBancos;
    }

    public Banco getBancoSelected() {
        return ((bancoSelected==null)? new Banco():bancoSelected);
    }

    public void setBancoSelected(Banco bancoSelected) {
        this.bancoSelected = bancoSelected;
    }

    public ArrayList<Cheque> getListaChequeBancos() {
        return ((ListaChequeBancos==null)?new ArrayList<>():ListaChequeBancos);
    }

    public void setListaChequeBancos(ArrayList<Cheque> ListaChequeBancos) {
        this.ListaChequeBancos = ListaChequeBancos;
    }
    
 
    public void regBanco(String d)
    {
        System.err.println("entru reg banco " +d);
        if(!banco.getNome().isEmpty())
        {
            if(!banco.getSigla().isEmpty())
            {
                String ff =ad.registrarBanco(banco);
                
                if(ff!=null)
                {
                    banco= new Banco();
                    this.ListaBancos = ad.listaTodosBanco();
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Create New Banco", ff));
                }
            }
            else
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Create New Banco", "Sigla Campo Vasio!"));
            } 
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Create New Banco", "Banco Campo Vasio!"));
        }
        
    }
    
    public void MovimentoEChequeBanco(String s)
    {
        ListaCreditoBancos=ad.MovimentacaoBanco(bancoSelected,null);
        ListaChequeBancos=ad.getSeguenciaBanco(bancoSelected);
    }
    
    public void sequenciaBanco(String bank, String agencia)
    {
        if(bank != null && !bank.equals(""))
        {
            ListaChequeBancos=ad.getSeguenciaBancoCheque(Integer.valueOf(bank), agencia);
        }
        
    }
  
}
