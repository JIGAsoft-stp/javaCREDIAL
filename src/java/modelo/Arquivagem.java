/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.faces.component.html.HtmlInputText;

/**
 *
 * @author AhmedJorge
 */
public class Arquivagem {
    private String capa;
    private String mes;
    private String ano;
    private String letra;
    private String sequincia;
    
    private HtmlInputText capaH;
    private HtmlInputText mesH;
    private HtmlInputText anoH;
    private HtmlInputText letraH;
    private HtmlInputText sequinciaH;

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getSequincia() {
        return sequincia;
    }

    public void setSequincia(String sequincia) {
        this.sequincia = sequincia;
    }
    
    
}
