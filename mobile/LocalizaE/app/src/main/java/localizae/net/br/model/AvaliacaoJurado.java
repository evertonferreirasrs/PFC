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
