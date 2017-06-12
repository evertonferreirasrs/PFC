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
public class AvaliacaoVisitante extends BaseEntity {
    private Long nota;
    private String opiniao;
    private Usuario usuario;
    private Estande estande;

    @Override
    public String toString() {
        return "AvaliacaoVisitante{" + "nota=" + nota + ", opiniao=" + opiniao + ", usuario=" + usuario.getNome() + ", estande=" + estande.getTitulo() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nota);
        hash = 67 * hash + Objects.hashCode(this.opiniao);
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.estande);
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
        final AvaliacaoVisitante other = (AvaliacaoVisitante) obj;
        if (!Objects.equals(this.opiniao, other.opiniao)) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
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

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public String getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
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
