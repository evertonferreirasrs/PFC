/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class Estande extends BaseEntity{
    private String curso;
    private String descricao;
    private Long periodo;
    private String titulo;
    private String areaTematica;
    private Long numero;
    private List<IntegranteEquipe> equipe = new ArrayList<>();
    private Evento evento;

    @Override
    public String toString() {
        return "Estande{" + "curso=" + curso + ", descricao=" + descricao + ", periodo=" + periodo + ", titulo=" + titulo + ", areaTematica=" + areaTematica + ", numero=" + numero + ", equipe=" + equipe + ", evento=" + evento.getNome() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.curso);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.periodo);
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.areaTematica);
        hash = 97 * hash + Objects.hashCode(this.numero);
        hash = 97 * hash + Objects.hashCode(this.equipe);
        hash = 97 * hash + Objects.hashCode(this.evento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estande other = (Estande) obj;
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.areaTematica, other.areaTematica)) {
            return false;
        }
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.equipe.size(), other.equipe.size())) {
            return false;
        }
        if (!Objects.equals(this.evento.getId(), other.evento.getId())) {
            return false;
        }
        return true;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public List<IntegranteEquipe> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<IntegranteEquipe> equipe) {
        this.equipe = equipe;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
