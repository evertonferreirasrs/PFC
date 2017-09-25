package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estande extends BaseEntity{

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("curso")
    @Expose
    private String curso;

    @SerializedName("periodo")
    @Expose
    private Long periodo;

    @SerializedName("numero")
    @Expose
    private Long numero;

    @SerializedName("areaTematica")
    @Expose
    private String areaTematica;

    public Estande(Long id){
        this.setId(id);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

}

