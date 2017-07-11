package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import java.sql.Timestamp;
import java.util.Objects;

public class Evento extends BaseEntity{
    private String nome;
    private String endereco;
    private Long dataHoraEventoInicio;
    private Long dataHoraEventoFim;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.endereco);
        hash = 53 * hash + Objects.hashCode(this.dataHoraEventoInicio);
        hash = 53 * hash + Objects.hashCode(this.dataHoraEventoFim);
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraEventoInicio, other.dataHoraEventoInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraEventoFim, other.dataHoraEventoFim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evento{" + "nome=" + nome + ", endereco=" + endereco + ", dataHoraEventoInicio=" + dataHoraEventoInicio + ", dataHoraEventoFim=" + dataHoraEventoFim + '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getDataHoraEventoInicio() {
        return dataHoraEventoInicio;
    }

    public void setDataHoraEventoInicio(Long dataHoraEventoInicio) {
        this.dataHoraEventoInicio = dataHoraEventoInicio;
    }

    public Long getDataHoraEventoFim() {
        return dataHoraEventoFim;
    }

    public void setDataHoraEventoFim(Long dataHoraEventoFim) {
        this.dataHoraEventoFim = dataHoraEventoFim;
    }
}
