/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import java.security.MessageDigest;
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
    private List<CriterioAvaliacao> criterioAvaliacaoList;
    private IntegranteEquipe integranteEquipe;

    public List<CriterioAvaliacao> getCriterioAvaliacaoList() {
        return criterioAvaliacaoList;
    }

    public void setCriterioAvaliacaoList(List<CriterioAvaliacao> criterioAvaliacaoList) {
        this.criterioAvaliacaoList = criterioAvaliacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.nome);
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.senha);
        hash = 11 * hash + Objects.hashCode(this.tipoUsuario);
        hash = 11 * hash + Objects.hashCode(this.situacao);
        hash = 11 * hash + Objects.hashCode(this.motivo);
        hash = 11 * hash + Objects.hashCode(this.tokenRedeSocial);
        hash = 11 * hash + Objects.hashCode(this.tokenAutenticacao);
        hash = 11 * hash + Objects.hashCode(this.dataHoraExpiracaoToken);
        hash = 11 * hash + Objects.hashCode(this.criterioAvaliacaoList);
        hash = 11 * hash + Objects.hashCode(this.integranteEquipe);
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
        this.encryptPasswd(senha);
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

    public void encryptPasswd(String sign) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sign.getBytes());

            byte[] hash = md.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }

            sign = hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.senha = sign;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }
}
