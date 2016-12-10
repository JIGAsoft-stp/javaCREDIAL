
package bean;

import dao.ClienteDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.HistoricoCredito;

/**
 *
 * @author Helio
 */
@ManagedBean
@ViewScoped
public class ConsultaClienteBean implements Serializable
{
    private Cliente cliente = new Cliente();
    private ClienteDao clienteDao;
    private String numeroDossier;
    private HistoricoCredito historicoCredito = new HistoricoCredito();
    private List<HistoricoCredito> historicoCreditos = new ArrayList<>();
    private String nif = null;
    
    public ConsultaClienteBean()
    {
        clienteDao = new ClienteDao();
    }
    public Cliente getCliente() {
        return (cliente == null) ? cliente = new Cliente() : cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        nif = this.cliente.getNif();
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }


    public HistoricoCredito getHistoricoCliente() {
        return (historicoCredito == null ) ? historicoCredito = new HistoricoCredito() : historicoCredito;
    }

    public void setHistoricoCliente(HistoricoCredito historicoCredito) {
        this.historicoCredito = historicoCredito;
    }

    public List<HistoricoCredito> getHistoricoClientes() {
        return historicoCreditos;
    }

    public void setHistoricoClientes(List<HistoricoCredito> historicoCreditos) {
        this.historicoCreditos = historicoCreditos;
    }
    
    public void clienteSelecionado() throws IOException
    {
         FacesContext.getCurrentInstance().getExternalContext().redirect("Crediall.xhtml");  
    }
    /**
     * Método invocado quando o utilizador clicar duas vezes numa linha da tabela de listagem de clientes
     * @throws IOException 
     */
    public void historicoCreditos() throws IOException
    {
        nif = cliente.getNif();
        historicoCreditos = clienteDao.historicoCredito(nif);
        FacesContext.getCurrentInstance().getExternalContext().redirect("R.ClienteTest.xhtml#um");
       
    }
     /**
     * Método invocado quando o utilizador clicar numa linha da tabela de  de historico de créditos de um cliente.
     * Ao fazer isso, a terceira tabela abrirá mostrando a amortização
     * @throws IOException 
     */
    public void tabelaAmortizacao() throws IOException
    {
         FacesContext.getCurrentInstance().getExternalContext().redirect("R.ClienteTest.xhtml#dois");
    }
    
}
