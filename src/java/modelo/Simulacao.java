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
public class Simulacao implements Serializable{
    private String valorSimulacao;
    private String tipoPagamento;
    private String tipoCredeito;
    private String dias;
    private String taxa;
    private double taxaSC;
    private String periodo;
    private String capital;
    private String taxaSemDesconto;
    private String taxaComDesconto;
    private Float desconto = 0f;
    private String pestacao;
    private String reembalsoPeriodo;
    private String seguro;
    private double capitalSC;
    private double taxaSemDescontoSC;
    private double taxaComDescontoSC;
    private double descontoSC=0;
    private double pestacaoSC;
    private double reembalsoPeriodoSC;
    private double seguroSC;
    private Float correcao = 0f;
    private String opcao="A";
    private String totalPagar;
    private double totalPagarSC;
    private int nSemanasMes;
    private Date dataCredito; 
    private String dataFinal;
    private String idTaxa ;
    double lasttaxa=0;

    public Simulacao() {
    }

    public String getValorSimulacao() {
        return valorSimulacao;
    }

    public void setValorSimulacao(String valorSimulacao) {
        this.valorSimulacao = valorSimulacao;
    }

    public String getTipoCredeito() {
        return tipoCredeito;
    }

    public void setTipoCredeito(String tipoCredeito) {
        this.tipoCredeito = tipoCredeito;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getTaxaSemDesconto() {
        return taxaSemDesconto;
    }

    public void setTaxaSemDesconto(String taxaSemDesconto) {
        this.taxaSemDesconto = taxaSemDesconto;
    }

    public String getTaxaComDesconto() {
        return taxaComDesconto;
    }

    public void setTaxaComDesconto(String taxaComDesconto) {
        this.taxaComDesconto = taxaComDesconto;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public String getPestacao() {
        return pestacao;
    }

    public void setPestacao(String pestacao) {
        this.pestacao = pestacao;
    }

    public String getReembalsoPeriodo() {
        return reembalsoPeriodo;
    }

    public void setReembalsoPeriodo(String reembalsoPeriodo) {
        this.reembalsoPeriodo = reembalsoPeriodo;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public Float getCorrecao() {
        return correcao;
    }

    public void setCorrecao(Float correcao) {
        this.correcao = correcao;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getnSemanasMes() {
        return nSemanasMes;
    }

    public void setnSemanasMes(int nSemanasMes) {
        this.nSemanasMes = nSemanasMes;
    }

    public Date getDataCredito() {
        return dataCredito;
    }

    public void setDataCredito(Date dataCredito) {
        this.dataCredito = dataCredito;
    }

    public double getTaxaSC() {
        return taxaSC;
    }

    public void setTaxaSC(double taxaSC) {
        this.taxaSC = taxaSC;
    }

    public double getCapitalSC() {
        return capitalSC;
    }

    public void setCapitalSC(double capitalSC) {
        this.capitalSC = capitalSC;
    }

    public double getTaxaSemDescontoSC() {
        return taxaSemDescontoSC;
    }

    public void setTaxaSemDescontoSC(double taxaSemDescontoSC) {
        this.taxaSemDescontoSC = taxaSemDescontoSC;
    }

    public double getTaxaComDescontoSC() {
        return taxaComDescontoSC;
    }

    public void setTaxaComDescontoSC(double taxaComDescontoSC) {
        this.taxaComDescontoSC = taxaComDescontoSC;
    }

    public double getDescontoSC() {
        return descontoSC;
    }

    public void setDescontoSC(double descontoSC) {
        this.descontoSC = descontoSC;
    }

    public double getPestacaoSC() {
        return pestacaoSC;
    }

    public void setPestacaoSC(double pestacaoSC) {
        this.pestacaoSC = pestacaoSC;
    }

    public double getReembalsoPeriodoSC() {
        return reembalsoPeriodoSC;
    }

    public void setReembalsoPeriodoSC(double reembalsoPeriodoSC) {
        this.reembalsoPeriodoSC = reembalsoPeriodoSC;
    }

    public double getSeguroSC() {
        return seguroSC;
    }

    public void setSeguroSC(double seguroSC) {
        this.seguroSC = seguroSC;
    }

    public double getTotalPagarSC() {
        return totalPagarSC;
    }

    public void setTotalPagarSC(double totalPagarSC) {
        this.totalPagarSC = totalPagarSC;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getIdTaxa() {
        return idTaxa;
    }

    public void setIdTaxa(String idTaxa) {
        this.idTaxa = idTaxa;
    }
    
    
}
