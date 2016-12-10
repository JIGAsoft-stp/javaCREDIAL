/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import modelo.Simulacao;

/**
 *
 * @author AhmedJorge
 */
public class TabelaSimulacao implements Serializable{
   private double capitalReebolso=0;
   private int nperiodoReebolso=0;
   
   private String dataT;
   private String dataIng;
   private String reebolso;
   private double reebolsoSf;
   private String CapitaRe;
   private String Nundocumento;
   private String TipoPagamento;
   private int idTipoPagamento;
   private String banco;
   private String idbanco;

    public void primeiroCalculoReebolso(Simulacao s){
        double dd= s.getTotalPagarSC()-s.getReembalsoPeriodoSC();
        setCapitalReebolso((dd<=1)?0:dd);
    }

     public void outroCalculoReebolso(Simulacao s){
        double dd = getCapitalReebolso() - (s.getReembalsoPeriodoSC()+s.getDescontoSC());
        setCapitalReebolso((dd<=1)?0:dd);
    }
     
    public double getCapitalReebolso() {
        return capitalReebolso;
    }

    public void setCapitalReebolso(double capitalReebolso) {
        this.capitalReebolso = capitalReebolso;
    }

    public int getNperiodoReebolso() {
        return nperiodoReebolso;
    }

    public void setNperiodoReebolso(int nperiodoReebolso) {
        this.nperiodoReebolso = nperiodoReebolso;
    }

    public String getDataT() {
        return dataT;
    }

    public void setDataT(String dataT) {
        this.dataT = dataT;
    }

    public String getReebolso() {
        return reebolso;
    }

    public void setReebolso(String reebolso) {
        this.reebolso = reebolso;
    }

    public String getCapitaRe() {
        return CapitaRe;
    }

    public void setCapitaRe(String CapitaRe) {
        this.CapitaRe = CapitaRe;
    }

    public String getNundocumento() {
        return Nundocumento;
    }

    public void setNundocumento(String Nundocumento) {
        this.Nundocumento = Nundocumento;
    }

    public String getTipoPagamento() {
        return TipoPagamento;
    }

    public void setTipoPagamento(String TipoPagamento)
    {
        this.TipoPagamento = TipoPagamento;
    }

    public TabelaSimulacao() {
    }
    
    public TabelaSimulacao(String dataT, String reebolso, String CapitaRe, 
            String Nundocumento, String TipoPagamento, String banco, 
            int idTipoPagamento,String idbanco,String dataIng,double reebolsoSf)
    {
        this.dataT = dataT;
        this.reebolso = reebolso;
        this.CapitaRe = CapitaRe;
        this.Nundocumento = Nundocumento;
        this.TipoPagamento = TipoPagamento;
        this.idTipoPagamento = idTipoPagamento;
        this.banco=banco;
        this.idbanco = idbanco;
        this.dataIng=dataIng;
        this.reebolsoSf=reebolsoSf;
    }
    public TabelaSimulacao(TabelaSimulacao ts)
    {
        this.dataT = ts.dataT;
        this.reebolso = ts.reebolso;
        this.CapitaRe = ts.CapitaRe;
        this.Nundocumento = ts.Nundocumento;
        this.TipoPagamento = ts.TipoPagamento;
        this.idTipoPagamento = ts.idTipoPagamento;
        this.banco= ts.banco;
        this.idbanco = ts.idbanco;
        this.dataIng= ts.dataIng;
        this.reebolsoSf= ts.reebolsoSf;
    }
    

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBanco() {
        return banco;
    }

    public int getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(int idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(String idbanco) {
        this.idbanco = idbanco;
    }

    public String getDataIng() {
        return dataIng;
    }

    public void setDataIng(String dataIng) {
        this.dataIng = dataIng;
    }

    public void setReebolsoSf(double reebolsoSf) {
        this.reebolsoSf = reebolsoSf;
    }

    public double getReebolsoSf() {
        return reebolsoSf;
    }
   
    
}
