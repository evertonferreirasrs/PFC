package localizae.net.br.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class Usuario extends BaseEntity{

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
    private tipoUsuario tipoUsuario = new tipoUsuario();

    private class tipoUsuario {
        @SerializedName("id")
        @Expose
        private Long id = 1L;
    }

    @SerializedName("situacao")
    @Expose
    private String situacao;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public tipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(tipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
