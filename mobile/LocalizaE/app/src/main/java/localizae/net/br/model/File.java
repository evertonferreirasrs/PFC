package localizae.net.br.model;

/**
 * Created by marca on 14/10/2017.
 */

class File {
    private String uri;
    private byte[] file;
    private String base64;
    private Estande estande;
    private Promocao promocao;

    @Override
    public String toString() {
        return "File{" + "uri=" + uri + ", File=" + file + ", estande=" + estande + ", promocao=" + promocao + '}';
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
        return file;
    }

    public void setFile(byte[] File) {
        this.file = File;
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
