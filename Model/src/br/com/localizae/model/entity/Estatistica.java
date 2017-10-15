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
 * @author marca
 */
public class Estatistica extends BaseEntity{
    private Long posicaoX;
    private Long posicaoY;
    private Long dataHora;
    private Usuario usuario;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.posicaoX);
        hash = 61 * hash + Objects.hashCode(this.posicaoY);
        hash = 61 * hash + Objects.hashCode(this.dataHora);
        hash = 61 * hash + Objects.hashCode(this.usuario);
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
        final Estatistica other = (Estatistica) obj;
        if (!Objects.equals(this.posicaoX, other.posicaoX)) {
            return false;
        }
        if (!Objects.equals(this.posicaoY, other.posicaoY)) {
            return false;
        }
        if (!Objects.equals(this.dataHora, other.dataHora)) {
            return false;
        }
        return true;
    }

    public Long getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(Long posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Long getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(Long posicaoY) {
        this.posicaoY = posicaoY;
    }

    public Long getDataHora() {
        return dataHora;
    }

    public void setDataHora(Long dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
