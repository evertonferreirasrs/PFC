/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

/**
 *
 * @author marca
 */
public class CriterioJurado {
    private CriterioAvaliacao criterioAvaliacao;
    private Usuario usuario;

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
}
