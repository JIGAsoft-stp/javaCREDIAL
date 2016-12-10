package modelo;

import dao.UtilizadorDao;
import java.io.Serializable;
import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;

public class Utilizador implements Serializable
{
    private String nome;
    private String utilizadorAdministradorNif;
    private String senha;
    private String perfil="1"; 
    private String estado;
    private String nif;
    private int idAgencia;
    private String agenciaNome;
    private String perfilNome;
    private String login;
    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;
    private Part foto;
    private String fotoLocal;
    private Boolean redifinirSenha;
    private String imageLogar="/Images/user2.png";

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

  

    public Utilizador() 
    {
    }

    public String getUtilizadorAdministrador() {
        return utilizadorAdministradorNif;
    }

    public void setUtilizadorAdministrador(String utilizadorAdministrador) {
        this.utilizadorAdministradorNif = utilizadorAdministradorNif;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNif() 
    {
       return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }



    public void setNomeUtilizador(String nomeUtilizador) {
        this.nome = nomeUtilizador;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEstado() {
        return estado;
    }

    public Boolean getRedifinirSenha() {
        return redifinirSenha;
    }

    public void setRedifinirSenha(Boolean redifinirSenha) {
        this.redifinirSenha = redifinirSenha;
    }
    
    public String getNomeUtilizador() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getImageLogar() {
        return imageLogar;
    }

    public void setImageLogar(String imageLogar) {
        this.imageLogar = imageLogar;
    }

    public String getUtilizadorAdministradorNif() {
        return utilizadorAdministradorNif;
    }

    public void setUtilizadorAdministradorNif(String utilizadorAdministradorNif) {
        this.utilizadorAdministradorNif = utilizadorAdministradorNif;
    }

    public String getAgenciaNome() {
        return agenciaNome;
    }

    public void setAgenciaNome(String agenciaNome) {
        this.agenciaNome = agenciaNome;
    }

    public String getPerfilNome() {
        return perfilNome;
    }

    public void setPerfilNome(String perfilNome) {
        this.perfilNome = perfilNome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFotoLocal() {
        return fotoLocal;
    }

    public void setFotoLocal(String fotoLocal) {
        this.fotoLocal = fotoLocal;
    }

    @Override
    public String toString() {
        return "Utilizador{" + "nome=" + nome + ", utilizadorAdministradorNif=" + utilizadorAdministradorNif + ", senha=" + senha + ", perfil=" + perfil + ", estado=" + estado + ", nif=" + nif + ", idAgencia=" + idAgencia + ", agenciaNome=" + agenciaNome + ", perfilNome=" + perfilNome + ", login=" + login + ", senhaAtual=" + senhaAtual + ", novaSenha=" + novaSenha + ", confirmarSenha=" + confirmarSenha + ", foto=" + foto + ", fotoLocal=" + fotoLocal + ", redifinirSenha=" + redifinirSenha + ", imageLogar=" + imageLogar + '}';
    }

 
       
}