package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by marca on 29/09/2017.
 */

public class CriterioJurado extends BaseEntity implements Serializable {

    @SerializedName("criterioAvaliacao")
    @Expose
    private CriterioAvaliacao criterioAvaliacao;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;
    @SerializedName("estande")
    @Expose
    private Estande estande;

    @Override
    public String toString() {
        return this.criterioAvaliacao.getNome();
    }

    public CriterioAvaliacao getCriterioAvaliacao() {
        return criterioAvaliacao;
    }

    public void setCriterioAvaliacao(CriterioAvaliacao criterioAvaliacao) {
        this.criterioAvaliacao = criterioAvaliacao;
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
}
