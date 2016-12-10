/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author AhmedJorge
 */
public class Banco implements Serializable{
    private String nome;
    private String data;
    private String sigla;
    private String debito;
    private String credito;
    private String saldo;
    private String id;
    private String saldoNaoFormatado;
    
    public Banco() {
    }

    public Banco(String nome, String sigla, String saldo, String id) {
        this.nome = nome;
        this.sigla = sigla;
        this.saldo = saldo;
        this.id = id;
    }

    public Banco(String nome, String sigla, String saldo, String id, String saldoNaoFormatado) {
        this.nome = nome;
        this.sigla = sigla;
        this.saldo = saldo;
        this.id = id;
        this.saldoNaoFormatado = saldoNaoFormatado;
    }

    public String getDebito() {
        return debito;
    }

    public void setDebito(String debito) {
        this.debito = debito;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public String getSaldoNaoFormatado() {
        return saldoNaoFormatado;
    }

    public void setSaldoNaoFormatado(String saldoNaoFormatado) {
        this.saldoNaoFormatado = saldoNaoFormatado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
