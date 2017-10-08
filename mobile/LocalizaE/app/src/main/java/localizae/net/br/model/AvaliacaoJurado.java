package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvaliacaoJurado extends BaseEntity{
//TODO: PEDIR PRO MARCAO MUDAR SERVICO DE RETORNO DA AVALIACAO DO JURADO
    @SerializedName("nomeUsuario")
    @Expose
    private String nomeUsuario;

    @SerializedName("nota")
    @Expose
    private Long nota;

    @SerializedName("criterio")
    @Expose
    private String criterio;

    @SerializedName("opiniao")
    @Expose
    private String opiniao;

    public String getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
    }

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
}
