/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author AhmedJorge
 */
public class Movimento
{
    private Date date;
    private String datePT;
    private String Credito;
    private String Dedito;
    private String disignacao;

    public Movimento() {
    }

    public Movimento(Date date, String datePT, String Credito, String disignacao,String Dedito) {
        this.date = date;
        this.datePT = datePT;
        this.Credito = Credito;
        this.Dedito = Dedito;
        this.disignacao = disignacao;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDatePT() {
        return datePT;
    }

    public void setDatePT(String datePT) {
        this.datePT = datePT;
    }

    public String getCredito() {
        return Credito;
    }

    public void setCredito(String Credito) {
        this.Credito = Credito;
    }

    public String getDisignacao() {
        return disignacao;
    }

    public void setDisignacao(String disignacao) {
        this.disignacao = disignacao;
    }

    public String getDedito() {
        return Dedito;
    }

    public void setDedito(String Dedito) {
        this.Dedito = Dedito;
    }
    
    
}
