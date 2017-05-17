/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import java.util.Objects;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class InformacoesParaAvaliacao extends BaseEntity {
    private Estande estande;
    private CriterioAvaliacao criterioAvaliacao;
    private Usuario usuario;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.estande);
        hash = 29 * hash + Objects.hashCode(this.criterioAvaliacao);
        hash = 29 * hash + Objects.hashCode(this.usuario);
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
        final InformacoesParaAvaliacao other = (InformacoesParaAvaliacao) obj;
        if (!Objects.equals(this.estande.getId(), other.estande.getId())) {
            return false;
        }
        if (!Objects.equals(this.criterioAvaliacao.getId(), other.criterioAvaliacao.getId())) {
            return false;
        }
        if (!Objects.equals(this.usuario.getId(), other.usuario.getId())) {
            return false;
        }
        return true;
    }
    
    

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande estande) {
        this.estande = estande;
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
}
