/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author AhmedJorge
 */
public class Email implements Serializable{
    private String nome;
    private String assunto;
    private String mesagem;
    private String emailReenvio;
    
    String receivers[] = {"jigasoft_stp@hotmail.com"};

    public Email() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMesagem() {
        return mesagem;
    }

    public void setMesagem(String mesagem) {
        this.mesagem = mesagem;
    }

    public String getEmailReenvio() {
        return emailReenvio;
    }

    public void setEmailReenvio(String emailReenvio) {
        this.emailReenvio = emailReenvio;
    }

    @Override
    public String toString() {
        return "Email{" + "nome=" + nome + ", assunto=" + assunto + ", mesagem=" + mesagem + ", emailReenvio=" + emailReenvio + ", receivers=" + receivers + '}';
    }
    
    

   
}
