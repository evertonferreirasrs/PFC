/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class Promocao extends BaseEntity{
    private String nome;
    private String descricao;
    private Timestamp dataHora;
    private Estande estande;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.descricao);
        hash = 67 * hash + Objects.hashCode(this.dataHora);
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
        final Promocao other = (Promocao) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.dataHora, other.dataHora)) {
            return false;
        }
        if (!Objects.equals(this.estande.getId(), other.estande.getId())) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        String toString = "Nome: "+this.nome;
        toString += "\nDescrição: "+this.descricao;
        toString += "\nDataHora: "+this.dataHora.toString();
        toString += "\nEstande: "+this.estande.getId();
        
        return toString;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande usuario) {
        this.estande = usuario;
    }
}
