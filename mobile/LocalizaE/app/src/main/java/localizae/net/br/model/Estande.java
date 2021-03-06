package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;

public class Estande extends BaseEntity implements Serializable {

    @SerializedName("equipe")
    @Expose
    private List<IntegranteEquipe> equipe;

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
    private Integer numero;

    @SerializedName("areaTematica")
    @Expose
    private String areaTematica;

    @SerializedName("posicaoX")
    @Expose
    private Integer posicaoX;

    @SerializedName("posicaoY")
    @Expose
    private Integer posicaoY;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    public List<IntegranteEquipe> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<IntegranteEquipe> equipe) {
        this.equipe = equipe;
    }

    public Estande(Long id) {
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Integer getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(Integer posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Integer getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(Integer posicaoY) {
        this.posicaoY = posicaoY;
    }

    @Override
    public String toString() {
        return this.getTitulo();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}