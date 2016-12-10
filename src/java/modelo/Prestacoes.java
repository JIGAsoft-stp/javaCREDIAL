
package modelo;

import java.io.Serializable;

/**
 *
 * @author Helio
 */
public class Prestacoes implements Serializable
{
    private String dataEmissao;
    private String dataEndose;
    private String numeroDossier;
    private String reembolso;
    private String prestacaoPaga;
    private String idPagamento;
    private String estadoP;
    
    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(String idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getDataEndose() {
        return dataEndose;
    }

    public String getEstadoP() {
        return estadoP;
    }

    public void setEstadoP(String estadoP) {
        this.estadoP = estadoP;
    }


    public void setDataEndose(String dataEndose) {
        this.dataEndose = dataEndose;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getReembolso() {
        return reembolso;
    }

    public void setReembolso(String reembolso) {
        this.reembolso = reembolso;
    }

    public String getPrestacaoPaga() {
        return prestacaoPaga;
    }

    public void setPrestacaoPaga(String prestacaoPaga) {
        this.prestacaoPaga = prestacaoPaga;
    }

    @Override
    public String toString() {
        return "Prestacoes{" + "dataEmissao=" + dataEmissao + ", dataEndose=" + dataEndose + ", numeroDossier=" + numeroDossier + ", reembolso=" + reembolso + ", prestacaoPaga=" + prestacaoPaga + ", idPagamento=" + idPagamento + ", estadoP=" + estadoP + '}';
    }


    
    
}
