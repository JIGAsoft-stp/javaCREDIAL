
package modelo;

/**
 *
 * @author Helio
 */
public class Transferencia 
{
    private String bancoDebitar;
    private String bancoCreditar;
    private String valorTransferir;
    private String descricao;

    public String getBancoDebitar() {
        return bancoDebitar;
    }

    public void setBancoDebitar(String bancoDebitar) {
        this.bancoDebitar = bancoDebitar;
    }

    public String getBancoCreditar() {
        return bancoCreditar;
    }

    public void setBancoCreditar(String bancoCreditar) {
        this.bancoCreditar = bancoCreditar;
    }

    public String getValorTransferir() {
        return valorTransferir;
    }

    public void setValorTransferir(String valorTransferir) {
        this.valorTransferir = valorTransferir;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Transferencia{" + "bancoDebitar=" + bancoDebitar + ", bancoCreditar=" + bancoCreditar + ", valorTransferir=" + valorTransferir + ", descricao=" + descricao + '}';
    }
    
    
    
}
