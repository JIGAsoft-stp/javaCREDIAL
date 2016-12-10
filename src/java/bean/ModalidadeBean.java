
package bean;

import conexao.Call;
import dao.AdministracaoDao;
import dao.PagamentoDao;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mensagem.Mensagem;
import modelo.Agencia;
import modelo.Comobox;
import modelo.Pagamento;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import validacao.Validation;

/**
 *
 * @author Helio
 */
@ManagedBean
@ViewScoped
public class ModalidadeBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Pagamento pagamento = new Pagamento();
    private Agencia agencia = new Agencia();
    private PagamentoDao pagamentoDao = new PagamentoDao();
    private List<Comobox> listaLocalidade = new ArrayList<>();
    private List<Agencia> listaAGENCIA = new ArrayList<>();
    private ArrayList<Comobox> listaInfo = new ArrayList<>();
    private ArrayList<Pagamento> listaPagamento = new ArrayList<>();
    private HashMap<String, ArrayList<Comobox>> mapList;
    private Comobox localidade;
    
    public ModalidadeBean()
    {
        this.mapList = new HashMap<>();
        this.listaPagamento = new ArrayList<>();
        this.listaInfo = Comobox.loadCombo("TYPEOBJECTO", "TOBJ_ID", "TOBJ_DESC"); 
        for(Comobox c:listaInfo)
            mapList.put(c.getId(), pagamentoDao.listaInfo(Integer.valueOf(c.getId())));

    }
    
    @PostConstruct
    public void init()
    {
        listaLocalidade = Comobox.loadCombo("VER_LOCALIDADE", "ID", "LOCALIDADE");
        listaAGENCIA = LocalidadeDao.listAgencias();
    }

    public void setMapList(HashMap<String, ArrayList<Comobox>> mapList) {
        this.mapList = mapList;
    }

    public ArrayList<Comobox> getListaInfo() {
        return listaInfo;
    }

    public Agencia getAgencia() {
        return (agencia == null)? agencia = new Agencia() : agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    

    public void setListaInfo(ArrayList<Comobox> listaInfo) {
        this.listaInfo = listaInfo;
    }

    public ArrayList<Pagamento> getListaPagamento() {
        return (listaPagamento == null) ? new ArrayList<>() : listaPagamento;
    }

    public ArrayList<Comobox> getMapList(String id) {
        return mapList.get(id);
    }
   
    public void setListaPagamento(ArrayList<Pagamento> listaPagamento) {
        this.listaPagamento = listaPagamento;
    }

    public List<Comobox> getListaLocalidade() {
        return listaLocalidade;
    }
    
    
    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Comobox getLocalidade() {
        return ( (localidade == null) ? localidade = new Comobox() : localidade) ;
    }

    public void setLocalidade(Comobox localidade) {
        this.localidade = localidade;
    }

    public List<Agencia> getListaAGENCIA() {
        return ( (listaAGENCIA == null ) ? listaAGENCIA = new ArrayList<>() : listaAGENCIA ) ;
    }

    public void setListaAGENCIA(List<Agencia> listaAGENCIA) {
        this.listaAGENCIA = listaAGENCIA;
    }
    
    /**
     * Registra uma nova modalidade de pagamento
     */
    
    /**
     * Registra uma nova modalidade de pagamento
     * @param param
     */
    public void RegModalidadePagamento(int param)
    {
        System.err.println(param+"   gjj jjt");
        if(pagamento.getDesignacao() != null && pagamento.getDesignacao().length()>0)
        {
            pagamentoDao.RegObecto(pagamento.getDesignacao(),param);          
            this.mapList.replace(param+"", pagamentoDao.listaInfo(param));  
            info(param);
        } 
    }
    
    /**
     * 
     * @param id identificação do componente
     */
    public void info(int id)
    {
        System.err.println("tjjjtj hhhh mesagesant");
        pagamento.setDesignacao("");
        switch(id)
        {
            case 3:
                Mensagem.msgInfo("Nova fonte de rendimento registrado");
                Validation.AtualizarCompoente("AddItem1_Super3", "AddItem1_CaixaTexto3");
                Validation.AtualizarCompoente("AddItem1_Super3", "growlAdm3");
                break;
             case 4:
                Mensagem.msgInfo("Novo tipo de documento registrado");
                 Validation.AtualizarCompoente("AddItem1_Super4", "AddItem1_CaixaTexto4");
               Validation.AtualizarCompoente("AddItem1_Super4", "growlAdm4");
                break;
              case 5:
                Mensagem.msgInfo("Nova modalidade pagamento registrado");
                Validation.AtualizarCompoente("AddItem1_Super5", "AddItem1_CaixaTexto5");
                Validation.AtualizarCompoente("AddItem1_Super5", "growlAdm5");
                break;
              case 6:
                Mensagem.msgInfo("Nova garantia registrado");
                Validation.AtualizarCompoente("AddItem1_Super6", "AddItem1_CaixaTexto6");
                Validation.AtualizarCompoente("AddItem1_Super6", "growlAdm6");
                break;
              case 8:
                Mensagem.msgInfo("Novo tipo de crédito registrado");
                Validation.AtualizarCompoente("AddItem1_Super8", "AddItem1_CaixaTexto8");
                Validation.AtualizarCompoente("AddItem1_Super8", "growlAdm8");
                break;
              case 2:
                Mensagem.msgInfo("Novo local de trabalho registrado");
                Validation.AtualizarCompoente("AddItem1_Super2", "AddItem1_CaixaTexto2");
                Validation.AtualizarCompoente("AddItem1_Super2", "growlAdm2");
                break;
          
              default:
                Mensagem.msgInfo("Nova profissão registrado");
                Validation.AtualizarCompoente("AddItem1_Super1", "AddItem1_CaixaTexto1");
                Validation.AtualizarCompoente("AddItem1_Super1", "growlAdm1");
                
        }
    }
    public void infoErro(int id)
    {
        System.err.println("tjjjtj hhhh mesagesant");
        pagamento.setDesignacao("");
        switch(id)
        {
            case 3:
                Mensagem.msgInfo("Fonte de rendimento desativado com êxito");
                Validation.AtualizarCompoente("AddItem1_Super3", "AddItem1_CaixaTexto3");
                Validation.AtualizarCompoente("AddItem1_Super3", "growlAdm3");
                break;
             case 4:
                Mensagem.msgInfo("Tipo de documento desativado com êxito");
                 Validation.AtualizarCompoente("AddItem1_Super4", "AddItem1_CaixaTexto4");
               Validation.AtualizarCompoente("AddItem1_Super4", "growlAdm4");
                break;
              case 5:
                Mensagem.msgInfo("Modalidade pagamento desativado com êxito");
                Validation.AtualizarCompoente("AddItem1_Super5", "AddItem1_CaixaTexto5");
                Validation.AtualizarCompoente("AddItem1_Super5", "growlAdm5");
                break;
              case 6:
                Mensagem.msgInfo("Garantia desativada com êxito");
                Validation.AtualizarCompoente("AddItem1_Super6", "AddItem1_CaixaTexto6");
                Validation.AtualizarCompoente("AddItem1_Super6", "growlAdm6");
                break;
              case 8:
                Mensagem.msgInfo("Tipo de crédito desativado com êxito");
                Validation.AtualizarCompoente("AddItem1_Super8", "AddItem1_CaixaTexto8");
                Validation.AtualizarCompoente("AddItem1_Super8", "growlAdm8");
                break;
              case 2:
                Mensagem.msgInfo("Local de trabalho desativado com êxito");
                Validation.AtualizarCompoente("AddItem1_Super2", "AddItem1_CaixaTexto2");
                Validation.AtualizarCompoente("AddItem1_Super2", "growlAdm2");
                break;
          
              default:
                Mensagem.msgInfo("Profissão desativada com êxito");
                Validation.AtualizarCompoente("AddItem1_Super1", "AddItem1_CaixaTexto1");
                Validation.AtualizarCompoente("AddItem1_Super1", "growlAdm1");
                
        }
    }
    
    public void desativeObjClicked(Object o,int param)
    {
        System.err.println(o+" clicked");
        pagamentoDao.objectoDesativar(o.toString());
        this.mapList.replace(param+"", pagamentoDao.listaInfo(param));  
            infoErro(param);
    }
    
    public void adicionarAgencia()
    {
        AdministracaoDao administracaoDao = new AdministracaoDao();
        if((agencia.getNome() != null && !agencia.getNome().equals("")) && (agencia.getLocalidade() != null && !agencia.getLocalidade().equals("")))
        {
            String result = administracaoDao.regAgencia(agencia);
            if(result.equals("true"))
            {
                listaAGENCIA = LocalidadeDao.listAgencias();
                Mensagem.msgInfo("Nova agência registrada!");
                Validation.AtualizarCompoente("formAgencia", "growlAgencia");
                Validation.AtualizarCompoente("formAgencia", "listsAgentia");
                RequestContext.getCurrentInstance().execute("newAgency()");
            }
            else
            {
                Mensagem.msgError(result);
                Validation.AtualizarCompoente("formAgencia", "growlAgencia");
            }
        }
    }
    
    public void addLocalidade()
    {
        
        if(localidade.getValue() != null && !localidade.getValue().isEmpty())
        {
            String result[] = LocalidadeDao.reg(localidade);
            if ("true".equals(result[0])) {
                Mensagem.msgInfo("Nova Localidade registrada!");
                Validation.AtualizarCompoente("formAgencia", "growlAgencia");
                Validation.AtualizarCompoente("formAgencia", "localidadeAgencia");

                listaLocalidade = Comobox.loadCombo("VER_LOCALIDADE", "ID", "LOCALIDADE");

                localidade.setValue("");
                Validation.AtualizarCompoente("AddItem1_SuperLocal", "AddItem1_CaixaTextoLocal");
            } else {
                Mensagem.msgInfo(result[1]);
                Validation.AtualizarCompoente("formAgencia", "growlAgencia");
            }
        }
        
    }
    
    public void desatieLoCalidade(Comobox c)
    {
        String result[] = LocalidadeDao.desative(c);
        if ("true".equals(result[0])) {
            listaLocalidade = Comobox.loadCombo("VER_LOCALIDADE", "ID", "LOCALIDADE");
            Mensagem.msgInfo("Localidade '" + c.getValue() + "' desativada com êxito!");
            Validation.AtualizarCompoente("formAgencia", "growlAgencia");
            Validation.AtualizarCompoente("formAgencia", "localidadeAgencia");
        } else {
            Mensagem.msgInfo(result[1]);
            Validation.AtualizarCompoente("formAgencia", "growlAgencia");
        }
    }
    
    
    public static class LocalidadeDao {

        public static String[] reg(Comobox c) {
            Object o = Call.callSampleFunction("FUNC_REG_LOCALIDADE", Types.VARCHAR,
                    SessionUtil.getUserlogado().getNif(),
                    SessionUtil.getUserlogado().getIdAgencia(),
                    c.getValue(),
                    null);
            return ((o == null) ? new String[]{"false","Erro a registar Localidade"} : o.toString().split(";") );
        }

        public static String[] desative(Comobox c) {
            Object o = Call.callSampleFunction("FUNC_DISABLE_LOCALIDADE", Types.VARCHAR,
                    SessionUtil.getUserlogado().getNif(),
                    SessionUtil.getUserlogado().getIdAgencia(),
                    c.getId());
            return ((o == null) ? new String[]{"false","Erro a destivar Localidade"} : o.toString().split(";") );
        }
        
        public static ArrayList<Agencia>listAgencias()
        {
            ArrayList<Agencia> as = new ArrayList<>();
            ResultSet rs = Call.selectFrom("VER_AGENCIA","*");
            Consumer<HashMap<String, Object>> act = (map) -> {
                Agencia a = new Agencia();
                a.setData(map.get("DATA")+"");
                a.setLocalidade(map.get("LOCALIDADE")+"");
                a.setNumFuncionario(map.get("COLABORADORES")+"");
                a.setNome(map.get("AGENCIA")+"");
                as.add(a);
            };
            Call.forEchaResultSet(act, rs);
            return as;
        }
    }
}



