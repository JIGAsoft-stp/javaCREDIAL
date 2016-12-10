package dao;

import bean.LoginBean;
import conexao.Call;
import conexao.Conexao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Comobox;
import modelo.Movimento;
import modelo.Utilizador;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;
import validacao.Validation;

public class UtilizadorDao implements Serializable {

    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;

    public ResultSet carregarUtilizador() {
        List<Utilizador> lista;
        Utilizador utilizador;
        sql = "SELECT *FROM TABLE(FUNC_LOAD_USERS)";
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ResultSet rs = null;
        @SuppressWarnings({"LocalVariableHidesMemberVariable", "UnusedAssignment"})
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            rs = ps.getResultSet();

        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean logar(Utilizador u)
    {
        boolean entrou=false;
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {        
            Call.SHOWPARAM = false;
            Object dd = Call.callSampleFunction("FUNC_LOGAR", Types.ARRAY, u.getNif(),u.getSenha());
            if(dd!=null)
            {
                if(!((ArrayList<String>)dd).isEmpty())
                {
                    entrou=true;
                    HashMap<String, String> map = new HashMap<>();
                    String campos[];
                    for (String list :((ArrayList<String>)dd))
                    {
                        campos = list.split(";");
                        map.put(campos[0], campos[1]);
                    }
                    u.setNome(map.get("NOME"));
                    u.setNomeUtilizador(u.getNome());
                    u.setNif(map.get("NIF"));
                    u.setEstado(map.get("ESTADO"));
                    u.setIdAgencia(Integer.valueOf(map.get("AGENCIA")));
                    u.setAgenciaNome(map.get("AGENCIA NOME"));
                    u.setPerfil(map.get("PERFIL"));
                    u.setPerfilNome(map.get("PERFIL NOME"));
                    u.setLogin(map.get("LOGIN"));
                }
            }
            if(entrou == true)
            {
                SessionUtil.removerParametro("user");
                SessionUtil.definirParametro("user", u);
                SessionUtil.removerParametro("senha");
                SessionUtil.definirParametro("senha", u.getSenha());
                if(u.getEstado().equals("2"))
                {
                   Validation.AtualizarCompoente("alterarSenhaForm", "utilizador");
                   Validation.AtualizarCompoente("alterarSenhaForm", "alterarSenhaNif");
                   RequestContext.getCurrentInstance().execute("mostrarModal()");
                }
                else
                {
                    if(u.getPerfil().equals("2"))
                        Validation.redirecionar("Relatorio.xhtml"); 
                    else
                         Validation.redirecionar("R.ClienteTest.xhtml");        
                }
            }
        }
        return entrou;
        
    }

    
    /**
     * Método que oculta os menus disponiveis consoante o perfil do utilizador
     * @param perfil operário tem acesso e outros total
     */

    /**
     * Método que oculta os menus disponiveis consoante o perfil do utilizador
     * @param arquivo
     * @return 
     */
    public ArrayList<Comobox> carregarAllUser(String arquivo)
    {
        ArrayList<Comobox> al = new ArrayList<>();
        ResultSet rd = Call.selectFrom("VER_USER","*");
        Call.forEchaResultSet((HashMap<String, Object> map) -> {
            Blob blob =((Blob)map.get("PHOTO"));
            if (blob != null&&map.get("NIF")!=null) {
                try {
                    byte[] fileByte = blob.getBytes(1, (int) blob.length());
                    try (FileOutputStream fos = new FileOutputStream(arquivo + "/" + map.get("NIF") + ".png")) {
                        fos.write(fileByte);
                    } catch (IOException ex) {
                        Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                al.add(new Comobox((String) map.get("NIF"), "/Imagem/" + map.get("NIF") + ".png"));
            } else {
                al.add(new Comobox(map.get("NIF") + "", "/Images/user2.png"));
            }
        }, rd);
        return al;
    }

    public String getImagemUser(LoginBean lb) {
        String imagem = "/Images/user2.png";
        for (Comobox object : lb.getListaUser()) {
            if (object.getId().equals(lb.getUtilizador().getNif())) {
                return object.getValue();
            }
        }
        return imagem;
    }
    
    /**
     * Registra um novo utilizador para usar o sistema
     * @param utilizador
     * @return 
     */
    public Object registrarUtilizador(Utilizador utilizador)
    {
        String functionName = "FUNC_REG_USER";
        Object resp = null;
        try {
            resp = Call.callSampleFunction(functionName, Types.VARCHAR, 
                    utilizador.getNome(),
                  SessionUtil.getUserlogado().getNif(),
                    utilizador.getNif(),
                    utilizador.getPerfil(),
                    utilizador.getFoto().getInputStream(),
                    null,
                     SessionUtil.getUserlogado().getIdAgencia(),
                     utilizador.getIdAgencia(),
                    null,
                    null
            );
        } catch (IOException ex) {
            Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  resp;
    }
    /**
     * Define o acesso do menu ao utilizador
     * @param nifUtilizador nif da pessoa que receberá privilégio
     * @param numeroMenu número do menu
     * @param dataInicio data que terá acesso ao menu
     * @param dataFim data que terminará o acesso ao menu
     * @return 
     */
    public String definirAcessoUtilizador(String nifUtilizador,int numeroMenu,String dataInicio,String dataFim)
    {
        sql="{?=call FUNC_USER_ACCESS(?,?,?,?,?)}";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1,Types.VARCHAR);
                cs.setString(2, SessionUtil.getUserlogado().getNif());
                cs.setString(3, nifUtilizador);
                cs.setInt(4, numeroMenu);
                cs.setString(5, dataInicio);
                cs.setString(6, dataFim);
                cs.execute();
                resultado = cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a definir aceso ao utilizador "+ex.getMessage());
            }
        }
        return resultado;
    }
    
    public List<Utilizador> listaUtilizadores(String parametro, String estadoUtilizador)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        String arquivo = scontext.getRealPath("/Imagem/");
        List<Utilizador> users = new ArrayList<>();
        Conexao conexao = new Conexao();
        if(parametro != null)
        {
            if(estadoUtilizador.equals("1"))
            {
                if(parametro.length()==9)
                    rs = Call.selectFrom("VER_USER WHERE UPPER(NIF) LIKE UPPER('%"+parametro+"%') AND (ESTADO=1 OR ESTADO=2)","*");
                else
                    rs = Call.selectFrom("VER_USER WHERE UPPER(NOME) LIKE UPPER('%"+parametro+"%') AND (ESTADO=1 OR ESTADO=2)","*");
            }
            else
            {
                 if(parametro.length()==9)
                    rs = Call.selectFrom("VER_USER WHERE UPPER(NIF) LIKE UPPER('%"+parametro+"%') AND (ESTADO=0)","*");
                else
                    rs = Call.selectFrom("VER_USER WHERE UPPER(NOME) LIKE UPPER('%"+parametro+"%') AND (ESTADO=0)","*");
            }
            if(rs != null)
            {
                try 
                {
                    while(rs.next())
                    {
                          carregarListaUser(rs, users, arquivo,"NIF", "PHOTO", "NOME");
                          System.out.println("Nome "+rs.getString("NOME"));
                    }
                    rs.close();
                    conexao.desCon();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Erro a listar utilizadores");
                }
            }
               
        }
        else
        {
            if(estadoUtilizador.equals("1"))
            {
                rs = Call.selectFrom("VER_USER WHERE ESTADO=1 OR ESTADO=2","*");
            }
            else
            {
                 rs = Call.selectFrom("VER_USER WHERE ESTADO=0","*");
            }
            if(rs != null)
            {
                try 
                {
                    while(rs.next())
                    {
                          carregarListaUser(rs, users, arquivo,"NIF", "PHOTO", "NOME");
                          System.out.println("Nome "+rs.getString("NOME"));

                    }
                    rs.close();
                    conexao.desCon();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Erro a listar utilizadores");
                }
            }
        }
        return users;
    }
    /**
     * 
     * @param nif
     * @return -1 se não existe 
     */
    public int verificarNif(String nif)
    {
        int existe = -1;
        Conexao c = new Conexao();
        if(c.getCon() != null)
        {
            rs = Call.selectFrom("VER_CLIENTE_SIMPLE", "*");
            if(rs != null)
            {
                try
                {
                    while(rs.next())
                    {
                        if(rs.getString("NIF").equals(nif))
                        {
                            existe = 1;
                            break;
                        }
                    }
                    rs.close();
                    c.closeConect();
                }
                catch (SQLException ex) {
                    Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return existe;
    }
    
    public String alterarSenha(String idUser, String senhaAtual, String novSenha)
    {
        String functionName = "FUNC_USER_ALTERPWD";
        Object resp;
        resp = Call.callSampleFunction(functionName, Types.VARCHAR, idUser,senhaAtual,novSenha);
        return resp.toString();
    }

    private void carregarListaUser(ResultSet rs, List<Utilizador> users, String arquivo, String nif, String foto,String nome) {
        try {
            Utilizador utilizador = new Utilizador();
            Blob blob = rs.getBlob(foto);
            if(blob!=null&&rs.getString(nif)!=null)
            {
                try
                {
                    byte[] fileByte = blob.getBytes(1, (int) blob.length());
                    try (FileOutputStream fos = new FileOutputStream(arquivo + "/" + rs.getString(nif) + ".png")) 
                    {fos.write(fileByte);} 
                    catch (IOException ex) 
                    {Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);}
                } 
                catch (SQLException ex) 
                {Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);}
                
                utilizador.setNome(rs.getString(nome));
                utilizador.setFotoLocal("/Imagem/"+rs.getString(nif)+".png");
                utilizador.setNif(rs.getString(nif));
                utilizador.setEstado("ESTADO");
                utilizador.setPerfil(rs.getString("PREFIL"));
                utilizador.setIdAgencia(rs.getInt("AGENCIA"));
                users.add(utilizador);
            }
            else{
                utilizador.setNome(rs.getString(nome));
                utilizador.setNif(rs.getString(nif));
                utilizador.setFotoLocal("/Images/user2.png");
                utilizador.setEstado("ESTADO");
                utilizador.setPerfil(rs.getString("PREFIL"));
                utilizador.setIdAgencia(rs.getInt("AGENCIA"));
                users.add(utilizador);
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ativarUtilizador(String nif, String novaSenha)
    {
        String procedureName = "PRC_USER_ACTVIE";
        Call.callProcedure(procedureName, nif, novaSenha);
    }
    
    public void logOut(int idLogin)
    {
        String procedureName = "PRC_LOGOUT";
        Call.callProcedure(procedureName, idLogin);
    }
    
    public void desativeUser(String nifUserDesative, boolean type)
    {
        String procedureName = "PRC_USER_DESATIVE";
        Call.callProcedure(procedureName, SessionUtil.getUserlogado().getNif(), nifUserDesative ,((!type)?"D":"F"));
    }
    
    public void redifiniraSenhaUsr(String nifUserRedifinir)
    {
        String procedureName = "FUNC_USER_FORCEPWD";
        Call.callSampleFunction(procedureName,Types.VARCHAR ,SessionUtil.getUserlogado().getNif(), nifUserRedifinir);
    }
    public void alterPerfil(Utilizador u)
    {
        String procedureName = "PRC_USER_ALTERPERFIL";
        Call.callProcedure(procedureName,
                SessionUtil.getUserlogado().getNif(), 
                u.getNif(),
                u.getPerfil()
        );
    }
    
    public void alterAgencia(Utilizador u)
    {
        String procedureName = "PRC_USER_ALTERAGENCIA";
        Call.callProcedure(procedureName,
                SessionUtil.getUserlogado().getNif(), 
                u.getNif(),
                SessionUtil.getUserlogado().getIdAgencia(),
                u.getIdAgencia(),
                null,
                null,
                null
        );
    }
    
    
    public List<Comobox> listaAgencia() {
        List<Comobox> al = new ArrayList<>();
        rs = Call.selectFrom("VER_AGENCIA", "*");
        if (rs != null) {
            try {
                while (rs.next()) {
                    al.add(new Comobox(rs.getString("ID"), rs.getString("AGENCIA")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(UtilizadorDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return al;
    }
}
