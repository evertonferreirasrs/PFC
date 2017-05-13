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
public class AvaliacaoJurado extends BaseEntity {
    private Long nota;
    private String opiniao;
    private InformacoesParaAvaliacao informacoesParaAvaliacao;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nota);
        hash = 89 * hash + Objects.hashCode(this.opiniao);
        hash = 89 * hash + Objects.hashCode(this.informacoesParaAvaliacao);
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
        final AvaliacaoJurado other = (AvaliacaoJurado) obj;
        if (!Objects.equals(this.opiniao, other.opiniao)) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
            return false;
        }
        if (!Objects.equals(this.informacoesParaAvaliacao.getId(), other.informacoesParaAvaliacao.getId())) {
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

    public InformacoesParaAvaliacao getInformacoesParaAvaliacao() {
        return informacoesParaAvaliacao;
    }

    public void setInformacoesParaAvaliacao(InformacoesParaAvaliacao informacoesParaAvaliacao) {
        this.informacoesParaAvaliacao = informacoesParaAvaliacao;
    }
}
