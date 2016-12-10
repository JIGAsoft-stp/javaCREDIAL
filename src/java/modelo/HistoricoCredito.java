
package modelo;

/**
 *
 * @author Helcio
 */
public class HistoricoCredito
{
    private String numeroDossier;
    private String dataFimCredito;
    private String dataCnt;
    private String estado;
    private String capitalInicial;
    private String totalCredito;
    private String teag;
    private String valorPago;
    private String numeroCheque;
    private String penalidade;
    private String idCredito;
    private String totalEfetivo;
    private String numeroCredito;
    private int selecionado;

    public HistoricoCredito()
    {
        
    }
   

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public int getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(int selecionado) {
        this.selecionado = selecionado;
    }

    public String getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(String idCredito) {
        this.idCredito = idCredito;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getDataFimCredito() {
        return dataFimCredito;
    }

    public void setDataFimCredito(String dataFimCredito) {
        this.dataFimCredito = dataFimCredito;
    }

    public String getDataCnt() {
        return dataCnt;
    }

    public void setDataCnt(String dataCnt) {
        this.dataCnt = dataCnt;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCapitalInicial() {
        return capitalInicial;
    }

    public void setCapitalInicial(String capitalInicial) {
        this.capitalInicial = capitalInicial;
    }

    public String getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(String totalCredito) {
        this.totalCredito = totalCredito;
    }

    public String getTeag() {
        return teag;
    }

    public void setTeag(String teag) {
        this.teag = teag;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(String penalidade) {
        this.penalidade = penalidade;
    }

    public String getTotalEfetivo() {
        return totalEfetivo;
    }

    public void setTotalEfetivo(String totalEfetivo) {
        this.totalEfetivo = totalEfetivo;
    }

    @Override
    public String toString() {
        return "HistoricoCredito{" + "numeroDossier=" + numeroDossier + ", dataFimCredito=" + dataFimCredito + ", dataCnt=" + dataCnt + ", estado=" + estado + ", capitalInicial=" + capitalInicial + ", totalCredito=" + totalCredito + ", teag=" + teag + ", valorPago=" + valorPago + ", numeroCheque=" + numeroCheque + ", penalidade=" + penalidade + ", idCredito=" + idCredito + ", totalEfetivo=" + totalEfetivo + ", numeroCredito=" + numeroCredito + '}';
    }

  
    
    
}
