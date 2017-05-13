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
public class Usuario extends BaseEntity{
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private boolean bloqueado;
    private String motivo;
    private String tokenRedeSocial;
    private String tokenAutenticacao;
    private Timestamp dataHoraExpiracaoToken;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.senha);
        hash = 41 * hash + Objects.hashCode(this.tipoUsuario);
        hash = 41 * hash + (this.bloqueado ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.motivo);
        hash = 41 * hash + Objects.hashCode(this.tokenRedeSocial);
        hash = 41 * hash + Objects.hashCode(this.tokenAutenticacao);
        hash = 41 * hash + Objects.hashCode(this.dataHoraExpiracaoToken);
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
        final Usuario other = (Usuario) obj;
        if (this.bloqueado != other.bloqueado) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        if (!Objects.equals(this.tokenRedeSocial, other.tokenRedeSocial)) {
            return false;
        }
        if (!Objects.equals(this.tokenAutenticacao, other.tokenAutenticacao)) {
            return false;
        }
        if (!Objects.equals(this.tipoUsuario, other.tipoUsuario)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraExpiracaoToken, other.dataHoraExpiracaoToken)) {
            return false;
        }
        return true;
    }
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTokenRedeSocial() {
        return tokenRedeSocial;
    }

    public void setTokenRedeSocial(String tokenRedeSocial) {
        this.tokenRedeSocial = tokenRedeSocial;
    }

    public String getTokenAutenticacao() {
        return tokenAutenticacao;
    }

    public void setTokenAutenticacao(String tokenAutenticacao) {
        this.tokenAutenticacao = tokenAutenticacao;
    }

    public Timestamp getDataHoraExpiracaoToken() {
        return dataHoraExpiracaoToken;
    }

    public void setDataHoraExpiracaoToken(Timestamp dataHoraExpiracaoToken) {
        this.dataHoraExpiracaoToken = dataHoraExpiracaoToken;
    }
}
