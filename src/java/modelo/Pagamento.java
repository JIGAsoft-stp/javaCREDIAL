
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Helcio
 */
public class Pagamento 
{
    private String fontePagamento;
    private String numeroDoc;
    private String fonteRendimento;
    private String banco;
    private String montantePagar;
    private String designacao;
    private String descricao;
    private String tipoP;
    private String montanteRestante;
    private Date data;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String dataFormatada;
    private String desconto;
    private int efetuado;
    
    public String getFontePagamento() {
        return fontePagamento;
    }

    public void setFontePagamento(String fontePagamento) {
        this.fontePagamento = fontePagamento;
    }

    public String getTipoP() {
        return tipoP;
    }

    public void setTipoP(String tipoP) {
        this.tipoP = tipoP;
    }

    public String getFonteRendimento() {
        return fonteRendimento;
    }

    public void setFonteRendimento(String fonteRendimento) {
        this.fonteRendimento = fonteRendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public int getEfetuado() {
        return efetuado;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setEfetuado(int efetuado) {
        this.efetuado = efetuado;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getMontantePagar() {
        return montantePagar;
    }

    public void setMontantePagar(String montantePagar) {
        this.montantePagar = montantePagar;
    }

    public String getMontanteRestante() {
        return montanteRestante;
    }

    public void setMontanteRestante(String montanteRestante) {
        this.montanteRestante = montanteRestante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "fontePagamento=" + fontePagamento + ", tipoPagamento=" + tipoP + ", numeroDoc=" + numeroDoc + ", banco=" + banco + ", montantePagar=" + montantePagar + ", montanteRestante=" + montanteRestante + ", data=" + data + ", sdf=" + sdf + ", dataFormatada=" + dataFormatada + ", desconto=" + desconto + '}';
    }
    
    
}
