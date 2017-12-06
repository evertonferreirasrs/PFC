package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvaliacaoJurado extends BaseEntity {
    //TODO: PEDIR PRO MARCAO MUDAR SERVICO DE RETORNO DA AVALIACAO DO JURADO
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    @SerializedName("nota")
    @Expose
    private Long nota;

    @SerializedName("criterio")
    @Expose
    private CriterioAvaliacao criterio;

    @SerializedName("opiniao")
    @Expose
    private String opiniao;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("estande")
    @Expose
    private Estande estande;

    public String getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande estande) {
        this.estande = estande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getNomeUsuario() {
        return usuario;
    }

    public void setNomeUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public CriterioAvaliacao getCriterio() {
        return criterio;
    }

    public void setCriterio(CriterioAvaliacao criterio) {
        this.criterio = criterio;
    }

}
