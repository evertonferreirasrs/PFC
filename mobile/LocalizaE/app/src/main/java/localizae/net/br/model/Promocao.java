package localizae.net.br.model;

/**
 * Created by marca on 14/10/2017.
 */

public class Promocao {
    private String nome;
    private String descricao;
    private Long dataHora;
    private Estande estande;
    private File imagem;

    @Override
    public String toString() {
        return this.nome;
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

    public Long getDataHora() {
        return dataHora;
    }

    public void setDataHora(Long dataHora) {
        this.dataHora = dataHora;
    }

    public Estande getEstande() {
        return estande;
    }

    public void setEstande(Estande usuario) {
        this.estande = usuario;
    }

    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }
}
