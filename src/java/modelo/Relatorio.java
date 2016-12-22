
package modelo;

import Export.RelatorioConverter;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Helio
 */
public class Relatorio implements Serializable
{
    private Date dataInicial;
    private Date dataFinal;
    private String taegFooter;
    private String tipoCredito;
    private String nif;
    private String nome;
    private String apelido;
    private String valorCredito;
    private String localTrabalho;
    private String dataCredito;
    private String parametro ="SP";
    private String localidade;
    private String quantidadeCredito;
    private String diferenca;
    private String anoAtual;
    private String anoPassado;
    private String periodoEmComparacao;
    private String dossier;
    private String taeg;
    private String capitalConcebido;
    private String montanteDivida;
    private String banco;
    private String valorCheque;
    private String numDocReal;
    private String dataReal;
    private String numDocPrevisao;
    private String estado;
    private String valorEmprestado;
    private String tag;
    private String capitalConcebidoFooter;
    private String montanteDividaFooter;
    private String relatorioChequeTipo;
    private String creditoSolicitado;
    private String valorPago;
    private String endossado;
    private String totalCredito;
    private String dataPrevista;
    private String reembolso;
    private String agencia;
    private Date data;
    public Object export;
    
    public Relatorio() {
 
    }

    public Relatorio(String diferenca, String anoAtual, String anoPassado) {
        this.diferenca = diferenca;
        this.anoAtual = anoAtual;
        this.anoPassado = anoPassado;
    }

    public String getRelatorioChequeTipo() {
        return relatorioChequeTipo;
    }

    public void setRelatorioChequeTipo(String relatorioChequeTipo) {
        this.relatorioChequeTipo = relatorioChequeTipo;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public String getAgencia() {
        return agencia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Relatorio(String nif, String nome, String valorCheque, String endossado)
    {
        this.nif = nif;
        this.nome = nome;
        this.valorCheque = valorCheque;
        this.endossado = endossado;
    }

    public String getReembolso() {
        return reembolso;
    }

    public void setReembolso(String reembolso) {
        this.reembolso = reembolso;
    }
    
    public String getEndossado() {
        return endossado;
    }

    public void setEndossado(String endossado) {
        this.endossado = endossado;
    }

    public Relatorio(Date dataInicial, Date dataFinal, String nome, String localTrabalho, String banco) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.nome = nome;
        this.localTrabalho = localTrabalho;
        this.banco = banco;
    }

    public String getCreditoSolicitado() {
        return creditoSolicitado;
    }

    public void setCreditoSolicitado(String creditoSolicitado) {
        this.creditoSolicitado = creditoSolicitado;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(String totalCredito) {
        this.totalCredito = totalCredito;
    }

    public String getTaegFooter() {
        return taegFooter;
    }

    public void setTaegFooter(String taegFooter) {
        this.taegFooter = taegFooter;
    }

    public Relatorio(String nome, String banco, String valorCheque, String numDocReal, String dataReal, String numDocPrevisao) {
        this.nome = nome;
        this.banco = banco;
        this.valorCheque = valorCheque;
        this.numDocReal = numDocReal;
        this.dataReal = dataReal;
        this.numDocPrevisao = numDocPrevisao;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public String getCapitalConcebidoFooter() {
        return capitalConcebidoFooter;
    }

    public void setCapitalConcebidoFooter(String capitalConcebidoFooter) {
        this.capitalConcebidoFooter = capitalConcebidoFooter;
    }

    public String getMontanteDividaFooter() {
        return montanteDividaFooter;
    }

    public void setMontanteDividaFooter(String montanteDividaFooter) {
        this.montanteDividaFooter = montanteDividaFooter;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getValorEmprestado() {
        return valorEmprestado;
    }

    public void setValorEmprestado(String valorEmprestado) {
        this.valorEmprestado = valorEmprestado;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public String getQuantidadeCredito() {
        return quantidadeCredito;
    }

    public void setQuantidadeCredito(String quantidadeCredito) {
        this.quantidadeCredito = quantidadeCredito;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public String getTaeg() {
        return taeg;
    }

    public void setTaeg(String taeg) {
        this.taeg = taeg;
    }

    public String getCapitalConcebido() {
        return capitalConcebido;
    }

    public void setCapitalConcebido(String capitalConcebido) {
        this.capitalConcebido = capitalConcebido;
    }

    public String getMontanteDivida() {
        return montanteDivida;
    }

    public void setMontanteDivida(String montanteDivida) {
        this.montanteDivida = montanteDivida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
//        return "Relatorio{" + "dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", taegFooter=" + taegFooter + ", tipoCredito=" + tipoCredito + ", nif=" + nif + ", nome=" + nome + ", apelido=" + apelido + ", valorCredito=" + valorCredito + ", localTrabalho=" + localTrabalho + ", dataCredito=" + dataCredito + ", parametro=" + parametro + ", localidade=" + localidade + ", quantidadeCredito=" + quantidadeCredito + ", diferenca=" + diferenca + ", anoAtual=" + anoAtual + ", anoPassado=" + anoPassado + ", periodoEmComparacao=" + periodoEmComparacao + ", dossier=" + dossier + ", taeg=" + taeg + ", capitalConcebido=" + capitalConcebido + ", montanteDivida=" + montanteDivida + ", banco=" + banco + ", valorCheque=" + valorCheque + ", numDocReal=" + numDocReal + ", dataReal=" + dataReal + ", numDocPrevisao=" + numDocPrevisao + ", estado=" + estado + ", valorEmprestado=" + valorEmprestado + ", tag=" + tag + ", capitalConcebidoFooter=" + capitalConcebidoFooter + ", montanteDividaFooter=" + montanteDividaFooter + ", relatorioChequeTipo=" + relatorioChequeTipo + ", creditoSolicitado=" + creditoSolicitado + ", valorPago=" + valorPago + ", totalCredito=" + totalCredito + '}';
        return "Relatorio{"   + tipoCredito + ", localTrabalho=" + localTrabalho + ", localidade=" + localidade+'}';
    }

 
   

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(String valorCredito) {
        this.valorCredito = valorCredito;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getDataCredito() {
        return dataCredito;
    }

    public void setDataCredito(String dataCredito) {
        this.dataCredito = dataCredito;
    }

    public String getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(String diferenca) {
        this.diferenca = diferenca;
    }

    public String getAnoAtual() {
        return anoAtual;
    }

    public void setAnoAtual(String anoAtual) {
        this.anoAtual = anoAtual;
    }

    public String getAnoPassado() {
        return anoPassado;
    }

    public void setAnoPassado(String anoPassado) {
        this.anoPassado = anoPassado;
    }

    public String getPeriodoEmComparacao() {
        return periodoEmComparacao;
       
        
    }

    public void setPeriodoEmComparacao(String periodoEmComparacao) {
        this.periodoEmComparacao = periodoEmComparacao;
       
    }   

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getValorCheque() {
        return valorCheque;
    }

    public void setValorCheque(String valorCheque) {
        this.valorCheque = valorCheque;
    }

    public String getNumDocReal() {
        return numDocReal;
    }

    public void setNumDocReal(String numDocReal) {
        this.numDocReal = numDocReal;
    }

    public String getDataReal() {
        return dataReal;
    }

    public void setDataReal(String dataReal) {
        this.dataReal = dataReal;
    }

    public String getNumDocPrevisao() {
        return numDocPrevisao;
    }

    public void setNumDocPrevisao(String numDocPrevisao) {
        this.numDocPrevisao = numDocPrevisao;
    }
    
    
}
