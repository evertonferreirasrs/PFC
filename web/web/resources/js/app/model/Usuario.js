class Usuario{
    constructor(nome, email, senha, tipoUsuario, situacao, motivo, tokenRedeSocial, tokenAutenticacao, 
    dataHoraExpiracaoToken, criterioAvaliacaoList, integranteEquipe, id){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.situacao = situacao;
        this.motivo = motivo;
        this.tokenRedeSocial = tokenRedeSocial;
        this.tokenAutenticacao = tokenAutenticacao;
        this.dataHoraExpiracaoToken = dataHoraExpiracaoToken;
        this.criterioAvaliacaoList = criterioAvaliacaoList;
        this.integranteEquipe = integranteEquipe;
        this.id = id;
    }

}