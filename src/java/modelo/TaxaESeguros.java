/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author AhmedJorge
 */
public class TaxaESeguros implements Serializable
{

    private String dias;
    private String tipoCredito;
    private String taxa;
    private String valorSeguros;
    private String estadoSeguros;
    private String a;
    private String de;
    
    public TaxaESeguros() 
    {
    }

    public TaxaESeguros(String valorSeguros, String estadoSeguros) {
        this.valorSeguros = valorSeguros;
        this.estadoSeguros = estadoSeguros;
    }
    
    public TaxaESeguros(String dias, String taxa, String a, String de) {
        this.dias = dias;
        this.taxa = taxa;
        this.a = a;
        this.de = de;
    }
    
    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getValorSeguros() {
        return valorSeguros;
    }

    public void setValorSeguros(String valorSeguros) {
        this.valorSeguros = valorSeguros;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public TaxaESeguros(String estadoSeguros) {
        this.estadoSeguros = estadoSeguros;
    }

    public String getEstadoSeguros() {
        return estadoSeguros;
    }

    public void setEstadoSeguros(String estadoSeguros) {
        this.estadoSeguros = estadoSeguros;
    }
    
}
