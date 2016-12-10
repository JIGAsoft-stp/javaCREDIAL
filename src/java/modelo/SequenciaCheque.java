/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author AhmedJorge
 */
public class SequenciaCheque 
{
    private String banco;
    private String agencia;
    private String inicio;
    private String fim;
    private String totalfolhas;
    private String totalDestribuido;

    public SequenciaCheque() {
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getTotalDestribuido() {
        return totalDestribuido;
    }

    public void setTotalDestribuido(String totalDestribuido) {
        this.totalDestribuido = totalDestribuido;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getTotalfolhas() {
        return totalfolhas;
    }

    public void setTotalfolhas(String totalfolhas) {
        this.totalfolhas = totalfolhas;
    }

    
    
}