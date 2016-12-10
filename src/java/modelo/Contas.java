
package modelo;

import java.io.Serializable;

/**
 *
 * @author Helio
 */
public class Contas implements Serializable
{
    private String numeroDoc;
    private String valor;
    private String banco;
    private String tipoOperacao ="D";

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void limpar (String numeroDoc, String valor) {
        this.numeroDoc = numeroDoc;
        this.valor = valor;
 
    }
    
   
    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getValor() {
        return valor;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
    
}
