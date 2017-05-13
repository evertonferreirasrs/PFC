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
public class IntegranteEquipe extends BaseEntity{
    private Boolean responsavel;
    private Usuario usuario;
    private Estande estande;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.responsavel);
        hash = 31 * hash + Objects.hashCode(this.usuario);
        hash = 31 * hash + Objects.hashCode(this.estande);
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
        if (!Objects.equals(this.usuario.getId(), other.usuario.getId())) {
            return false;
        }
        if (!Objects.equals(this.estande.getId(), other.estande.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IntegranteEquipe{" + "responsavel=" + responsavel + ", usuario=" + usuario + ", estande=" + estande + '}';
    }
    
    

    public Boolean getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        this.responsavel = responsavel;
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
