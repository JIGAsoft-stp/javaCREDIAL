
package bean;

import dao.ClienteDao;
import dao.PagamentoDao;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mensagem.Mensagem;
import modelo.Cliente;
import modelo.Comobox;
import modelo.HistoricoCredito;
import modelo.Pagamento;
import modelo.Prestacoes;
import org.primefaces.context.RequestContext;
import validacao.Validation;


// Autor Helcio Guadalupe
@ManagedBean
@ViewScoped
public class ClienteBean implements  Serializable
{
    private static final long serialVersionUID = 1L;
    private Cliente cliente = new Cliente();
    private Cliente clienteShow = new Cliente();
    private ClienteDao clienteDAO;
    private String anoAtual;
    private HistoricoCredito credito = new HistoricoCredito();
    private List<Cliente> clientes;
    private List<Comobox> listaProfissao;
    private List<Comobox> listaLocalTrabalho;
    private List<Comobox> listaLocalidade;
    private PagamentoDao pagamentoDao = new PagamentoDao();
    private List<Comobox> sexos;
    private List<Comobox> estados;
    private String pesquisa;
    private String campoPesquisa ="2";
    private String tamanhoLista;
    private List meses;
    private List letra;
    private Prestacoes prestacoes = new Prestacoes();
    private String valor;
    private List<Prestacoes> listaPrestacoes;
    private List<HistoricoCredito> historicoCreditos;
    private String estadoPrestacao; // estado da prestação. (Não Pago=0 Pago=1)
    private List<Comobox> bancos;
    private List<Pagamento> listaPagamento;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final Date data = new Date();
 
    
    public ClienteBean()
    {
       clienteDAO = new ClienteDao();
       pagamentoDao = new PagamentoDao();
       historicoCreditos = new ArrayList<>();
       listaPrestacoes = new ArrayList<>();
       clientes = new ArrayList<>();
       clientes = clienteDAO.listaClientes(null, null);
       tamanhoLista = "metade";
 
    }
    @PostConstruct
    public void init()
    {
        meses();
       letras();
       sexos = new ArrayList<>();
       listaLocalidade = new ArrayList<>();
       listaLocalTrabalho = new ArrayList<>();
       listaProfissao = new ArrayList<>();
       listaPagamento = new ArrayList<>();
       bancos = new ArrayList<>();
       estados = clienteDAO.listaEstados();
       this.sexos = clienteDAO.listaSexos();
       listaProfissao = clienteDAO.ListaProfissao();
       listaLocalTrabalho = clienteDAO.LocalTrabalho();
       listaLocalidade = clienteDAO.ListaLocalidade();
       this.anoAtual=sdf.format(data).substring(0, 4);
    
    }
    public List<Prestacoes> getListaPrestacoes() {
        return listaPrestacoes;
    }

    public String getValor() {
        return valor;
    }

    public String getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(String campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }

 

    public void setListaPrestacoes(List<Prestacoes> listaPrestacoes) {
        this.listaPrestacoes = listaPrestacoes;
    }


    public List<Comobox> getBancos() {
        return bancos;
    }

    public void setBancos(List<Comobox> bancos) {
        this.bancos = bancos;
    }

    public Prestacoes getPrestacoes() {
        return (prestacoes == null) ? prestacoes = new Prestacoes() : prestacoes;
    }

    public void setPrestacoes(Prestacoes prestacoes) 
    {
        this.prestacoes = prestacoes;
    }

    public String getEstadoPrestacao() {
        return estadoPrestacao;
    }

    public HistoricoCredito getCredito()
    {
        return (credito == null) ? credito = new HistoricoCredito() : credito;
    }

    public void setCredito(HistoricoCredito credito)
    {
        this.credito = credito;
    }

    public List<Comobox> getListaProfissao() {
        return listaProfissao;
    }
    public List<Comobox> getListaLocalTrabalho() {
        return listaLocalTrabalho;
    }

    public List<Comobox> getListaLocalidade() {
        return listaLocalidade;
    }

    public List<HistoricoCredito> getHistoricoCreditos() 
    {   
         return historicoCreditos;
    }

    public void setHistoricoCreditos(List<HistoricoCredito> historicoCreditos) {
        this.historicoCreditos = historicoCreditos;
    }
 
    public List getMeses() {
        return meses;
    }

    public String getAnoAtual() {
        return this.anoAtual;
    }

    public void setAnoAtual(String anoAtual) {
        this.anoAtual= anoAtual;
    }

    public List<Comobox> getSexos() {
        return sexos;
    }

    public void setSexos(List<Comobox> sexos) {
        this.sexos = sexos;
    }

    public List<Comobox> getEstados() {
        return estados;
    }

    public void setEstados(List<Comobox> estados) {
        this.estados = estados;
    }
    
    
    public Cliente getCliente() {
        return (cliente==null) ? cliente = new Cliente() : cliente;
    }

    public List getLetra() {
        return letra;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public void setCliente(Cliente cliente) throws IOException {
        this.cliente = cliente;  
    }
    
    public void letras()
    {
        letra = new ArrayList();
        letra.add("A");
        letra.add("B");
        letra.add("C");
        letra.add("D");
        letra.add("E");
        letra.add("F");
        letra.add("G");
        letra.add("H");
        letra.add("I");
        letra.add("J");
        letra.add("K");
        letra.add("L");
        letra.add("M");
        letra.add("N");
        letra.add("O");
        letra.add("P");
        letra.add("Q");
        letra.add("R");
        letra.add("S");
        letra.add("T");
        letra.add("U");
        letra.add("V");
        letra.add("W");
        letra.add("X");
        letra.add("Y");
         letra.add("Z");
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
     
    public void meses()
    {
         meses = new ArrayList();
        for(int i=1;i<=12;i++)
        {
            if(i<10)
                meses.add("0"+i);
            else
                meses.add(i);
        }
    }

    // Ao fazer duplo clique numa linha da tabela cliente redireciona para o formulario Simulacao.xhtml, enviando informações do cliente selecinado
    public void clienteSelecionado() throws IOException
    {
         // cria uma nova sessão
        HttpSession session = ( HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("cliente", cliente);
        FacesContext.getCurrentInstance().getExternalContext().redirect("Simulacao.xhtml");
    }

    
    public void ListarHistoricoCreditos(String param) throws IOException
    {
        historicoCreditos.clear();
        historicoCreditos.add(credito);
        historicoCreditos = clienteDAO.historicoCredito(cliente.getNif());   
        if(pesquisa != null && !pesquisa.equals(""))
        {
           for(int i =0;i<historicoCreditos.size();i++)
           {
               if(historicoCreditos.get(i).getNumeroCheque().equals(pesquisa))
               {
                   credito.setNumeroCheque(pesquisa);
               }
           }
        }
        RequestContext.getCurrentInstance().update("forma:h");
    }
    
    public void AtualizarPrestacao()
    {
        listaPrestacoes = clienteDAO.listaPrestacoes(credito.getIdCredito());  
    }
    
    public void updateP()
    {
        listaPrestacoes = clienteDAO.listaPrestacoes(credito.getIdCredito());     
    }
 
    public void mostrarDados(PagamentoBean bean)
    {
         String [] campos = null;
        System.err.println(prestacoes+" -->>fjfjjffhhfhf");
        String info = pagamentoDao.DadosPagamento(prestacoes .getIdPagamento());
        campos = info.split(";");
        String d = prestacoes.getReembolso()+";"+info;
        bean.setPayment(null);
        bean.setPayment(d);
       
        if(!prestacoes.getEstadoP().equals("Pago"))
        {
            RequestContext.getCurrentInstance().addCallbackParam("pagar", campos[2]);
            RequestContext.getCurrentInstance().addCallbackParam("cheque", campos[0]);
        }
        if(prestacoes.getEstadoP().equals("Pago"))
              RequestContext.getCurrentInstance().addCallbackParam("estadoP", "Pago");
        else   
            RequestContext.getCurrentInstance().addCallbackParam("estadoP", "não pago");
    }
  
    public void registrarCliente()
    {
        FacesContext fc = FacesContext.getCurrentInstance();

        System.out.println(cliente.toString());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        boolean emailIsValid = validarEmail();
        if(verificarNif() == false && emailIsValid)
        {
            if(verificarIdadeCliente() ==1)
            {
                 String result = clienteDAO.adicionarCliente(cliente);
                if(result.equals("true"))
                {
                    cliente.setNumeroDossier(clienteDAO.devolverDossier(cliente.getNif()));
                    clientes.add(cliente);
                    tamanhoLista = "metade";
                    requestContext.update("forma:clienteTable");
                    requestContext.update("forma:growl-error");
                    Mensagem.msgInfo("Cliente registrado com sucesso");
                    requestContext.execute("limparTudo()");

                }
            }
            else
            {
                Mensagem.msgError("Idade tem que ser superior ou igual a 18 anos");
                  requestContext.update("forma:growl-error");
                 requestContext.execute("dataNascimentoFoco()");
            }

        }      

    }
    
  
    /**
     * Verifica-se o nif já existe
     * @return true se existe e false caso contrário
     */
    public boolean verificarNif()
    {
         boolean existe = false;
        FacesContext facesContext = FacesContext.getCurrentInstance();
         if((cliente.getNif().length()<9 || cliente.getNif().length()>9) && (cliente.getNif() != null && !cliente.getNif().equals("")))
        {
            Mensagem.msgWarm( "NIF deve ter nove(9) dígítos");
            Validation.AtualizarCompoente("forma", "growl-error");
            RequestContext.getCurrentInstance().addCallbackParam("nif", "tamanho inválido");
            return true;
        }
         if(cliente.getNif()!= null && cliente.getNif().length() >0 && clientes.size()>0)
          {
              if(clienteDAO.verificarNif(cliente.getNif()) ==1)
              {
                      existe = true;
                      cliente.setNif(null);
                      Mensagem.msgError("NIF já existe");
                      RequestContext.getCurrentInstance().update("forma:growl-error");
                      RequestContext.getCurrentInstance().addCallbackParam("nif", "existe");
              }
                  
               
          }
        return existe;
    }

    public void setTamanhoLista(String tamanhoLista) {
        this.tamanhoLista = tamanhoLista;
    }
    
    public void pesquisarCliente()
    {
        clientes = clienteDAO.listaClientes(campoPesquisa, pesquisa);
        Validation.AtualizarCompoente("forma", "clienteTable");
    }
    /**
     * funcão que verifica-se o cliente tem idade superior ou igual a 18 anos
     * @return 1 superior ou igual a 18 anos e 0 caso contrário
     */
    
    public int verificarIdadeCliente()
    {
         Calendar dataNascimento = Calendar.getInstance();  
        dataNascimento.setTime(cliente.getDataNasc()); 
        Calendar hoje = Calendar.getInstance();  
        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        
        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) 
          idade--;  
        else 
        { 
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH))
                idade--;      
        }
        if(idade>=18)
            return 1;
        else
            return 0;
    }
    
    public void pegarTodosClientes()
    {
        System.out.println("tamanho L "+tamanhoLista);
    
            if(tamanhoLista !=null && tamanhoLista.equals("metade"))
            {
                if(pesquisa == null || pesquisa.equals(""))
                {
                      clientes = clienteDAO.listaClientesTotal();
                      tamanhoLista = "total";
                      Validation.AtualizarCompoente("forma", "clienteTable");
                }
            }
    }
    
    public boolean validarEmail() {
        boolean valido = true;
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        if (cliente.getEmail() != null && !cliente.getEmail().isEmpty()) {
            Matcher m = p.matcher(cliente.getEmail());
            if (!m.find()) {
                valido = false;
                Mensagem.msgError("Email Invalido!");
                RequestContext.getCurrentInstance().update("forma:growl-error");
            }
        }
        
        if(valido) { RequestContext.getCurrentInstance().execute("$('.clienteEmail').css('border','')"); }
        else { RequestContext.getCurrentInstance().execute("$('.clienteEmail').css('border','1px solid red')"); }
        
        return valido;
    }
    
    public void showInfoCliente(Cliente c)
    {
        clienteShow = clienteDAO.showAllInfomationClient(c.getNif());
        RequestContext.getCurrentInstance().execute("$('.mp-userProfile').fadeIn(800);");
    }
    
    private boolean isVisualiction = false;

    public boolean isIsVisualiction() {
        return isVisualiction;
    }

    public void setIsVisualiction(boolean isVisualiction) {
        this.isVisualiction = isVisualiction;
    }

    public Cliente getClienteShow() {
        return (clienteShow == null) ? clienteShow = new Cliente() : clienteShow;
    }

    public void setClienteShow(Cliente clienteShow) {
        this.clienteShow = clienteShow;
    }
    
    
}
