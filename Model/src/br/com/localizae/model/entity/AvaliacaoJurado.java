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
public class AvaliacaoJurado extends BaseEntity {
    private Long nota;
    private String opiniao;
    private String status;
    private Timestamp dataHoraAbertura;
    private Timestamp dataHoraFechamento;
    private Usuario usuario;
    private CriterioAvaliacao criterio;
    private Estande estande;

    @Override
    public String toString() {
        return "AvaliacaoJurado{" + "nota=" + nota + ", opiniao=" + opiniao + ", status=" + status + ", dataHoraAbertura=" + dataHoraAbertura + ", dataHoraFechamento=" + dataHoraFechamento + ", usuario=" + usuario.getNome() + ", criterio=" + criterio.getNome() + ", estande=" + estande.getTitulo() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nota);
        hash = 41 * hash + Objects.hashCode(this.opiniao);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.dataHoraAbertura);
        hash = 41 * hash + Objects.hashCode(this.dataHoraFechamento);
        hash = 41 * hash + Objects.hashCode(this.usuario);
        hash = 41 * hash + Objects.hashCode(this.criterio);
        hash = 41 * hash + Objects.hashCode(this.estande);
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
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraAbertura, other.dataHoraAbertura)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraFechamento, other.dataHoraFechamento)) {
            return false;
        }
        if (!Objects.equals(this.usuario.getId(), other.usuario.getId())) {
            return false;
        }
        if (!Objects.equals(this.criterio.getId(), other.criterio.getId())) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(Timestamp dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    public Timestamp getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    public void setDataHoraFechamento(Timestamp dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CriterioAvaliacao getCriterio() {
        return criterio;
    }

    public void setCriterio(CriterioAvaliacao criterio) {
        this.criterio = criterio;
    }

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande estande) {
        this.estande = estande;
    }
}