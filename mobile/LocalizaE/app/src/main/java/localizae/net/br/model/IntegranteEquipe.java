package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Everton on 13/10/2017.
 */

public class IntegranteEquipe extends BaseEntity {
    @SerializedName("responsavel")
    @Expose
    private Boolean responsavel;

    @SerializedName("estande")
    @Expose
    private Estande estande;

    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    public Boolean getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        this.responsavel = responsavel;
    }

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande estande) {
        this.estande = estande;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
