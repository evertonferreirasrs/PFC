/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import java.util.Objects;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class IntegranteEquipe extends Usuario{
    private Boolean responsavel;
    private Estande estande;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.responsavel);
        hash = 37 * hash + Objects.hashCode(this.estande);
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
        final IntegranteEquipe other = (IntegranteEquipe) obj;
        if (!Objects.equals(this.responsavel, other.responsavel)) {
            return false;
        }
        if (!Objects.equals(this.estande, other.estande)) {
            return false;
        }
        return true;
    }

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
}
