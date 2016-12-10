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
public class Cheque {
    private String data;
    private String inicioSequ;
    private String fimSequ;
    private String maxFolha;
    private String agencia;
    private String estado;
    private String banco;
    private String totalDistribuido;
    private String totalFolhas;
    

    public Cheque() {
    }

    public Cheque(String data, String inicioSequ, String fimSequ, String maxFolha, String agencia, String estado) {
        this.data = data;
        this.inicioSequ = inicioSequ;
        this.fimSequ = fimSequ;
        this.maxFolha = maxFolha;
        this.agencia = agencia;
        this.estado = estado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInicioSequ() {
        return inicioSequ;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTotalDistribuido() {
        return totalDistribuido;
    }

    public void setTotalDistribuido(String totalDistribuido) {
        this.totalDistribuido = totalDistribuido;
    }

    public String getTotalFolhas() {
        return totalFolhas;
    }

    public void setTotalFolhas(String totalFolhas) {
        this.totalFolhas = totalFolhas;
    }

    public void setInicioSequ(String inicioSequ) {
        this.inicioSequ = inicioSequ;
    }

    public String getFimSequ() {
        return fimSequ;
    }

    public void setFimSequ(String fimSequ) {
        this.fimSequ = fimSequ;
    }

    public String getMaxFolha() {
        return maxFolha;
    }

    public void setMaxFolha(String maxFolha) {
        this.maxFolha = maxFolha;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
