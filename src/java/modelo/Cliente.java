
package modelo;

import java.io.Serializable;
import java.util.Date;
public class Cliente implements Serializable
{
  

    private String nif;
    private String numeroDossier;
    private String nome;
    private String apelido;
    private String genero;
    private Date dataNasc;
    private String morada;
    private String telefone;
    private String telemovel;
    private String servico;
    private String estadoCivil;
    private String profissao;
    private String localTrabalho;
    private String salario;
    private String numeroCapa;
    private String mes;
    private String ano;
    private String letra;
    private String email;
    
    
    public Cliente ()
    {  
    }
    
    public Cliente(String nif, String nome, String apelido, String dossier)
    {
        this.nif = nif;
        this.nome = nome;
        this.apelido = apelido;
        this.numeroDossier = dossier;
    }
    
    public String getLocalidade() {
        return localidade;
    }

  

    public String getNumeroCapa() {
        return numeroCapa;
    }

    public void setNumeroCapa(String numeroCapa) {
        this.numeroCapa = numeroCapa;
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


    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    private String localidade;
    

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
       
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nif=" + nif + ", numeroDossier=" + numeroDossier + ", nome=" + nome + ", apelido=" + apelido + ", genero=" + genero + ", dataNasc=" + dataNasc + ", morada=" + morada + ", telefone=" + telefone + ", telemovel=" + telemovel + ", servico=" + servico + ", estadoCivil=" + estadoCivil + ", profissao=" + profissao + ", localTrabalho=" + localTrabalho + ", salario=" + salario + ", numeroCapa=" + numeroCapa + ", mes=" + mes + ", ano=" + ano + ", letra=" + letra + ", email=" + email + ", localidade=" + localidade + '}';
    }
    
}
