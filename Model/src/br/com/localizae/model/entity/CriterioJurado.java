/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import java.util.Objects;

/**
 *
 * @author marca
 */
public class CriterioJurado {
    private CriterioAvaliacao criterioAvaliacao;
    private Usuario usuario;
    private Estande estande;

    @Override
    public String toString() {
        return "CriterioJurado{" + "criterioAvaliacao=" + criterioAvaliacao.getNome() + ", usuario=" + usuario.getNome() + ", estande=" + estande.getTitulo() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.criterioAvaliacao);
        hash = 73 * hash + Objects.hashCode(this.usuario);
        hash = 73 * hash + Objects.hashCode(this.estande);
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
        final CriterioJurado other = (CriterioJurado) obj;
        if (!Objects.equals(this.criterioAvaliacao.getId(), other.criterioAvaliacao.getId())) {
            return false;
        }
        if (!Objects.equals(this.usuario.getId(), other.usuario.getId())) {
            return false;
        }
        if (!Objects.equals(this.estande.getId(), other.estande.getId())) {
            return false;
        }
        return true;
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
