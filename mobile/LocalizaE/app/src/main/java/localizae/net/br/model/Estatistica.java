package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class Estatistica {

    @SerializedName("posicaox")
    @Expose
    private double posicaox;

    @SerializedName("posicaoy")
    @Expose
    private double posicaoy;

    @SerializedName("usuario")
    @Expose
    private Usuario user;

    @SerializedName("dataHora")
    @Expose
    private Long dataHora;

    public Estatistica(double posicaox, double posicaoy, Usuario user) {
        this.posicaox = posicaox;
        this.posicaoy = posicaoy;
        this.user = user;
        this.dataHora = Calendar.getInstance().getTimeInMillis();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public double getPosicaox() {
        return posicaox;
    }

    public void setPosicaox(double posicaox) {
        this.posicaox = posicaox;
    }

    public double getPosicaoy() {
        return posicaoy;
    }

    public void setPosicaoy(double posicaoy) {
        this.posicaoy = posicaoy;
    }
}
