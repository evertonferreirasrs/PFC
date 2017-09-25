package localizae.net.br.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Usuario extends BaseEntity implements Serializable{

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("senha")
    @Expose
    private String senha;

    @SerializedName("tipoUsuario")
    @Expose
    private TipoUsuario tipoUsuario;

    @SerializedName("situacao")
    @Expose
    private String situacao;

    @SerializedName("hash")
    @Expose
    private String hash;

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String email, String hash){
        this.email = email;
        this.hash = hash;
    }

    public Usuario(Long id){
        this.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
