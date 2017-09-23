/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.entity;

import br.com.localizae.model.base.BaseEntity;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author marca
 */
public class File extends BaseEntity{
    private String uri;
    private byte[] File;
    private String base64;
    private Estande estande;
    private Promocao promocao;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.uri);
        hash = 53 * hash + Arrays.hashCode(this.File);
        hash = 53 * hash + Objects.hashCode(this.base64);
        hash = 53 * hash + Objects.hashCode(this.estande);
        hash = 53 * hash + Objects.hashCode(this.promocao);
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
        final File other = (File) obj;
        if (!Objects.equals(this.uri, other.uri)) {
            return false;
        }
        if (!Objects.equals(this.base64, other.base64)) {
            return false;
        }
        if (!Arrays.equals(this.File, other.File)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "File{" + "uri=" + uri + ", File=" + File + ", estande=" + estande + ", promocao=" + promocao + '}';
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if(!uri.contains(".jpg")){
            uri += ".jpg";
        }
        this.uri = uri;
    }

    public byte[] getFile() {
        return File;
    }

    public void setFile(byte[] File) {
        this.File = File;
    }

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande estande) {
        this.estande = estande;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
