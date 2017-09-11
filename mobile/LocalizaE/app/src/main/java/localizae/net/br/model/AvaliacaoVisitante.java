package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvaliacaoVisitante {
//TODO: PEDIR PRO MARCAO MUDAR O SERVICO DE AVALIACAO DE VISITANTE

    @SerializedName("nomeUsuario")
    @Expose
    private String nomeUsuario;

    @SerializedName("nota")
    @Expose
    private Long nota;

    public String getUsuario() {
        return nomeUsuario;
    }

    public void setUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }
}
