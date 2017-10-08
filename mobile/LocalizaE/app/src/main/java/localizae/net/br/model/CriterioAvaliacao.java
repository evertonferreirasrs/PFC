package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by marca on 29/09/2017.
 */

public class CriterioAvaliacao extends BaseEntity implements Serializable {
    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("peso")
    @Expose
    private Long peso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }
}
