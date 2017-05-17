/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
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
    private String nome;
    private String areaTematica;
    private Long numero;
    private List<IntegranteEquipe> equipe;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.curso);
        hash = 61 * hash + Objects.hashCode(this.descricao);
        hash = 61 * hash + Objects.hashCode(this.periodo);
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.areaTematica);
        hash = 61 * hash + Objects.hashCode(this.numero);
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
        if (!Objects.equals(this.nome, other.nome)) {
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
        return true;
    }

    @Override
    public String toString() {
        String toString = "Curso: "+this.curso;
        toString += "\nDescricao: "+this.descricao;
        toString += "\nPeriodo: "+this.periodo;
        toString += "\nNome: "+this.nome;
        toString += "\nArea Tematica: "+this.areaTematica;
        toString += "\nNumero: "+this.numero;
        toString += "\nEquipe: "+this.equipe.size();
        
        return toString;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
