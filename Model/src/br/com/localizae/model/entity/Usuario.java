/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import br.com.localizae.model.utils.Criptografia;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Equipe LocalizaÊ
 */
public class Usuario extends BaseEntity {

    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private String situacao;
    private String motivo;
    private String tokenRedeSocial;
    private String tokenAutenticacao;
    private Timestamp dataHoraExpiracaoToken;
    private List<CriterioJurado> criterioAvaliacaoList;
    private IntegranteEquipe integranteEquipe;
    private String hash;

    public List<CriterioJurado> getCriterioAvaliacaoList() {
        return criterioAvaliacaoList;
    }

    public void setCriterioAvaliacaoList(List<CriterioJurado> criterioAvaliacaoList) {
        this.criterioAvaliacaoList = criterioAvaliacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.senha);
        hash = 89 * hash + Objects.hashCode(this.tipoUsuario);
        hash = 89 * hash + Objects.hashCode(this.situacao);
        hash = 89 * hash + Objects.hashCode(this.motivo);
        hash = 89 * hash + Objects.hashCode(this.tokenRedeSocial);
        hash = 89 * hash + Objects.hashCode(this.tokenAutenticacao);
        hash = 89 * hash + Objects.hashCode(this.dataHoraExpiracaoToken);
        hash = 89 * hash + Objects.hashCode(this.criterioAvaliacaoList);
        hash = 89 * hash + Objects.hashCode(this.integranteEquipe);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.situacao, other.situacao)) {
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
        if (!Objects.equals(this.tipoUsuario.getId(), other.tipoUsuario.getId())) {
            return false;
        }
        if (!Objects.equals(this.dataHoraExpiracaoToken, other.dataHoraExpiracaoToken)) {
            return false;
        }
        if (this.getId() == 4 && !Objects.equals(this.criterioAvaliacaoList, other.criterioAvaliacaoList)) {
            return false;
        }
        if (!Objects.equals(this.integranteEquipe, other.integranteEquipe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", tipoUsuario=" + tipoUsuario + ", situacao=" + situacao + ", motivo=" + motivo + ", criterioAvaliacaoList=" + criterioAvaliacaoList + ", integranteEquipe=" + integranteEquipe + '}';
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

    public IntegranteEquipe getIntegranteEquipe() {
        return integranteEquipe;
    }

    public void setIntegranteEquipe(IntegranteEquipe integranteEquipe) {
        this.integranteEquipe = integranteEquipe;
    }

    public String encryptPasswd(String sign) {

        sign = Criptografia.criptografarComMD5(sign);

        this.senha = sign;
        return sign;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
