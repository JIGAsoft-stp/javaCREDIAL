
package dao;

import conexao.Call;
import conexao.Conexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.Comobox;
import modelo.HistoricoCredito;
import modelo.Prestacoes;
import modelo.Utilizador;
import sessao.SessionUtil;
import validacao.OperacaoData;


public class ClienteDao
{
    private CallableStatement cs;
    private String sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String resultado;
    
  
   public List<Cliente> totalClientes()
   {
         List<Cliente> lista = new ArrayList<>();
        rs = Call.selectFrom("VER_CLIENTE_SIMPLE", "*");
          if(rs != null)
          {
              try
              {
                  while(rs.next())
                  {
                      Cliente cliente = new Cliente();
                      cliente.setNif(rs.getString("NIF"));
                      cliente.setNome(rs.getString("NOME"));
                      cliente.setApelido(rs.getString("APELIDO"));
                      cliente.setNumeroDossier(rs.getString("DOSSIER"));
                      lista.add(cliente);
                  }
                  rs.close();
              }
              catch (SQLException ex) 
              {
                  Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        return lista;
   }
   public List<Cliente> listaClientes(String campoPesquisa, String valor)
   {
       List<Cliente> clientes = new ArrayList<>();
       if(valor == null || valor.equals(""))
       {
           rs = Call.selectFrom("VER_CLIENTE_SIMPLE WHERE ROWNUM<=60", "*");
            if(rs != null)
             {
                 try
                 {
                     while(rs.next())
                     {
                         Cliente cliente = new Cliente();
                         cliente.setNif(rs.getString("NIF"));
                         cliente.setNome(rs.getString("NOME"));
                         cliente.setApelido(rs.getString("APELIDO"));
                         cliente.setNumeroDossier(rs.getString("DOSSIER"));
                         clientes.add(cliente);
                     }
                     rs.close();
                 }
                 catch (SQLException ex) 
                 {
                     Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
       }
       else {
           rs = Call.callTableFunction("FUNCT_LOAD_CLIENT", "*", campoPesquisa, valor);
           if (rs != null) {
               try {
                   while (rs.next()) {
                       Cliente cliente = new Cliente();
                       cliente.setNif(rs.getString("NIF"));
                       cliente.setNome(rs.getString("NOME"));
                       cliente.setApelido(rs.getString("APELIDO"));
                       cliente.setNumeroDossier(rs.getString("DOSSIER"));
                       clientes.add(cliente);
                   }
                   rs.close();
               } catch (SQLException ex) {
                   Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return clientes;
   }
   
   public String adicionarCliente(Cliente cliente)
   {
       System.out.println(cliente.toString());
       sql="{?=call FUNC_REG_CLIENTE_COMPLETO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
       Conexao conexao = new Conexao();
       if(conexao.getCon()!=null)
       {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.setObject(2, SessionUtil.getUserlogado().getNif());
                cs.setObject(3, cliente.getNif());
                cs.setObject(4, cliente.getNome());
                cs.setObject(5, cliente.getApelido());
                cs.setObject(6, cliente.getGenero());
                cs.setObject(7, OperacaoData.toSQLDate(cliente.getDataNasc()));
                cs.setObject(8, cliente.getMorada());
                cs.setObject(9, ((cliente.getTelefone() != null && cliente.getTelefone().length()>0) ? cliente.getTelefone() : null));
                cs.setObject(10, cliente.getTelemovel());
                cs.setObject(11, ((cliente.getServico() != null && cliente.getServico().length()>0) ? cliente.getServico() : null)) ;
                cs.setObject(12, cliente.getEstadoCivil());
                cs.setObject(13, cliente.getLocalidade());
                cs.setObject(14, cliente.getProfissao());
                cs.setObject(15, cliente.getLocalTrabalho());
                cs.setObject(16, cliente.getSalario());
                cs.setObject(17, cliente.getNumeroCapa() );
                cs.setObject(18, cliente.getMes());
                cs.setObject(19, cliente.getAno());
                cs.setObject(20, cliente.getLetra());
                cs.setObject(21,null);
                cs.setInt(22, SessionUtil.getUserlogado().getIdAgencia());
                cs.setString(23, ((cliente.getEmail() == null || cliente.getEmail().isEmpty()) ?  null : cliente.getEmail()) );
                cs.execute();
                resultado = cs.getString(1);
                conexao.desCon();
            }
            catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a registrar cliente "+ex.getMessage());
            }
       }
        return resultado;
   }
   
   public List<Comobox> listaSexos()
   {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        List<Comobox> al= new ArrayList<>();
        sql="select * from GENDER";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    while (rs.next())
                    {  

                        al.add(new Comobox(rs.getString("GEN_ID"), rs.getString("GEN_DESC")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter sexos "+ex.getMessage());
            }
        }
        return al;
   }
              
   public List<Comobox> listaEstados()
   {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        List<Comobox> al= new ArrayList<>();
        sql="select * from ESTADOCIVIL";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.execute();
                rs=cs.executeQuery(); 
                if (rs!=null) 
                {  
                    while (rs.next())
                    {  
                        al.add(new Comobox(rs.getString("CIVIL_ID"), rs.getString("CIVIL_DESIG")));
                    }  
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(CreditoDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro a obter sexos "+ex.getMessage());
            }
        }
        return al;
   }
   // Lista de historico de créditos dum determinado cliente
   public List<HistoricoCredito> historicoCredito(String nif)
   {
       List<HistoricoCredito> historico = new ArrayList<>();
       HistoricoCredito historicoCredito = null;
        sql = "SELECT * FROM TABLE(FUNCT_HISTORICO_CREDITO(?,?))";
        Conexao conexao = new Conexao();
        if(conexao.getCon()!=null)
        {
            try
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, nif);
                cs.setObject(2, null);
                cs.execute();
                rs = cs.getResultSet();
                if(rs!=null)
                {
                   while(rs.next())
                   {

                       historicoCredito = new HistoricoCredito();
                       historicoCredito.setNumeroDossier(rs.getString("DOSSIER"));
                       historicoCredito.setEstado(rs.getString("ESTADO"));
                       historicoCredito.setCapitalInicial(rs.getString("CAPITAL INICIAL"));
                       historicoCredito.setTotalCredito(rs.getString("TOTAL CREDITO"));
                       historicoCredito.setTeag(rs.getString("TAEG"));
                       historicoCredito.setValorPago(rs.getString("VALOR PAGO"));
                       historicoCredito.setNumeroCredito(rs.getString("DOSSIER"));
                       historicoCredito.setNumeroCheque(rs.getString("NUMERO CHEQUE"));
                       historicoCredito.setDataFimCredito(rs.getString("DATA"));
                       historicoCredito.setIdCredito(rs.getString("ESCONDE_ID"));
                       System.out.println("Esconde_id "+historicoCredito.getIdCredito());
                       historico.add(historicoCredito);
                   }
                   rs.close();

               }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Erro a obter histórico de creditos do cliente "+ex.getMessage());
            }
        }
        return historico;
   }
   
   public List<Comobox> LocalTrabalho()
   {
       Conexao conexao = new Conexao();
       List<Comobox> local = new ArrayList<>();
       sql ="SELECT *FROM VER_LOCALTRABALHO";
       if(conexao.getCon()!=null)
       {
            try
            {
                ps = conexao.getCon().prepareStatement(sql);
                ps.execute();
                rs = ps.getResultSet();
                if(rs != null)
                {
                    while(rs.next())
                    {
                        if(rs.getString("LOCAL") != null)
                        {
                           local.add(new Comobox(rs.getString("REALID"), rs.getString("LOCAL"))); 
                        }
                        
                    }
                    rs.close();
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       return local;
   }
  /**
   * 
   * @param nif
   * @return 1 se existe
   */
  public int verificarNif(String nif)
  {
       rs = Call.selectFrom("VER_CLIENTE_SIMPLE", "*");
       int i = 0;
       if(rs != null)
       {
           try 
           {
               while(rs.next())
               {
                   if(nif.equals(rs.getString("NIF")))
                   {
                       i=1;
                       break;
                   }
               }
               rs.close();
           }
           catch (SQLException ex) {
               Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return i;
  }
   public List<Comobox> ListaLocalidade()
   {
       Conexao conexao = new Conexao();
       List<Comobox> lista = new ArrayList<>();
       sql ="SELECT *FROM VER_LOCALIDADE";
       if(conexao.getCon()!=null)
       {
            try
            {
                ps = conexao.getCon().prepareStatement(sql);
                ps.execute();
                rs = ps.getResultSet();
                if(rs != null)
                {
                    while(rs.next())
                    {
                        if(rs.getString("LOCALIDADE") != null)
                        {
                            lista.add(new Comobox(rs.getString("ID"),rs.getString("LOCALIDADE")));
                        }  
                    }
                    rs.close();
                    conexao.desCon();
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       return lista;
   }
    

    public List<Comobox> ListaProfissao()
    {
         Conexao conexao = new Conexao();
         List<Comobox> lista = new ArrayList<>();
       sql ="SELECT *FROM VER_PROFISSAO";
       if(conexao.getCon()!=null)
       {
            try
            {
                ps = conexao.getCon().prepareStatement(sql);
                ps.execute();
                rs = ps.getResultSet();
                if(rs != null)
                {
                    while(rs.next())
                    {
                        lista.add(new Comobox(rs.getString("REALID"), rs.getString("PROFISSAO")));
                    }
                    rs.close();
                    conexao.desCon();
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       return lista;
    }
  
   public List<Prestacoes> listaPrestacoes(String idCredito)
   {
       List<Prestacoes> listaPrestacoeses = new ArrayList<>();
       Conexao conexao = new Conexao();
       sql ="SELECT * FROM TABLE(FUNCT_HISTORICO_PAGAMENTO(?,?))";
       Prestacoes prestacoes = null;
       if(conexao.getCon()!=null)
       {
            try 
            {
                cs = conexao.getCon().prepareCall(sql);
                cs.setString(1, idCredito);
                cs.setObject(2, null);
                cs.execute();
                rs = cs.getResultSet();
                if(rs != null)
                {
                    while(rs.next())
                    {
                        prestacoes = new Prestacoes();
                        prestacoes.setDataEmissao(rs.getString("DATA EMISSAO"));
                        prestacoes.setDataEndose(rs.getString("DATA ENDOSSADO"));
                        prestacoes.setNumeroDossier(rs.getString("DOSSIER"));
                        prestacoes.setReembolso(rs.getString("REEMBOSO"));
                        prestacoes.setPrestacaoPaga(rs.getString("PRESTAÇÃO PAGA"));
                        prestacoes.setIdPagamento(rs.getString("ID"));
                        prestacoes.setEstadoP(rs.getString("ESTADO"));
                        listaPrestacoeses.add(prestacoes);
                    }
                    rs.close();
                    conexao.desCon();
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        return listaPrestacoeses;
   }
   
   public List<Cliente> listaClientesTotal()
   {
        List<Cliente> lista = new ArrayList<>();
         rs = Call.selectFrom("VER_CLIENTE_SIMPLE", "*");
          if(rs != null)
          {
              try
              {
                  while(rs.next())
                  {
                      Cliente cliente = new Cliente();
                      cliente.setNif(rs.getString("NIF"));
                      cliente.setNome(rs.getString("NOME"));
                      cliente.setApelido(rs.getString("APELIDO"));
                      cliente.setNumeroDossier(rs.getString("DOSSIER"));
                      lista.add(cliente);
                  }
                  rs.close();
              }
              catch (SQLException ex) 
              {
                  Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          return lista;
       }
 
   public void addInfo(ResultSet rs)
   {
       List<Cliente> info = new ArrayList<>();
       if(rs != null)
       {
           try 
           {
               while(rs.next())
               {
                    Cliente cliente = new Cliente();
                    cliente.setNif(rs.getString("NIF"));
                    cliente.setNome(rs.getString("NOME"));
                    cliente.setApelido(rs.getString("APELIDO"));
                    cliente.setNumeroDossier(rs.getString("DOCIER"));
                    info.add(cliente);
               }
           }
           catch (SQLException ex) {
               Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   public String devolverDossier(String nif)
   {
       String dossier = null;
       rs = Call.selectFrom("VER_CLIENTE_SIMPLE ","*");
       if(rs != null)
       {
           try 
           {
               while(rs.next())
               {
                   if(nif.equals(rs.getString("NIF")))
                   {
                      dossier = rs.getString("DOSSIER");
                   }
               }
               rs.close();
           }
           catch (SQLException ex) {
               Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return dossier;
   }
   
    public Cliente showAllInfomationClient(String nif) {
        Cliente cliente = null;
        rs = Call.selectFrom("VER_CLIENTE_COMPLET where NIF = ?", "*", nif);
        if (rs != null) {
            try {
//                NIF	NUEMRO DOSSIERCLIENTE	NOME	APELIDO	SEXO	DATA NASCIMENTO	DATA REGISTRO	ESTADO CIVIL	TELEFONE FIXO	MAIL
//                        MORADA	LOCAL	TELEFONE MOVEL	SERVICO	PROFISAO	LOCAL TRABALHO	SALARIO	COLABORADOR	DATA
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                while (rs.next()) {
                    cliente = new Cliente();
                    cliente.setNif(rs.getString("NIF"));
                    cliente.setNome(rs.getString("NOME"));
                    cliente.setApelido(rs.getString("APELIDO"));
                    cliente.setDataNasc(format.parse(rs.getString("DATA NASCIMENTO")));
                    cliente.setEmail(rs.getString("MAIL"));
                    cliente.setEstadoCivil(rs.getString("ESTADO CIVIL"));
                    cliente.setGenero(rs.getString("SEXO"));
//                    cliente.setLetra(rs.getString("DOSSIER"));
                    cliente.setLocalTrabalho(rs.getString("LOCAL TRABALHO"));
                    cliente.setLocalidade(rs.getString("LOCAL"));
//                    cliente.setAno(rs.getString("DOSSIER"));
//                    cliente.setMes(rs.getString("DOSSIER"));
                    cliente.setMorada(rs.getString("MORADA"));
//                    cliente.setNumeroCapa(rs.getString("DOSSIER"));
                    cliente.setNumeroDossier(rs.getString("NUEMRO DOSSIERCLIENTE"));
                    cliente.setProfissao(rs.getString("PROFISAO"));
                    cliente.setSalario(rs.getString("SALARIO"));
                    cliente.setServico(rs.getString("SERVICO"));
                    cliente.setTelefone(rs.getString("TELEFONE MOVEL"));
                    cliente.setTelemovel(rs.getString("TELEFONE FIXO"));
                }
                rs.close();
            } catch (Exception ex) { Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex); }
        }
        return cliente;
    }   
}
