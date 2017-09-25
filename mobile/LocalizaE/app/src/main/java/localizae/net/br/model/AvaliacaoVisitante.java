package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvaliacaoVisitante {
//TODO: PEDIR PRO MARCAO MUDAR O SERVICO DE AVALIACAO DE VISITANTE

    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    @SerializedName("nota")
    @Expose
    private Long nota;

    @SerializedName("opiniao")
    @Expose
    private String opiniao;

    @SerializedName("estande")
    @Expose
    private Estande estande;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario nomeUsuario) {
        this.usuario = nomeUsuario;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) { this.nota = nota; }

    public String getOpiniao() { return opiniao; }

    public void setOpiniao(String opiniao) { this.opiniao = opiniao; }

    public Estande getEstande() { return estande; }

    public void setEstande(Estande estande) { this.estande = estande; }

    public AvaliacaoVisitante (Long nota, String opiniao, Usuario usuario, Estande estande){
        this.nota = nota;
        this.opiniao = opiniao;
        this.usuario = usuario;
        this.estande = estande;
    }
}
