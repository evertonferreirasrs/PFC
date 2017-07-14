class Usuario{
    constructor(nome, email, senha, tipoUsuario, situacao, motivo, tokenRedeSocial, tokenAutenticacao, 
    dataHoraExpiracaoToken, criterioAvaliacaoList, integranteEquipe){
        this._nome = nome;
        this._email = email;
        this._senha = senha;
        this._tipoUsuario = tipoUsuario;
        this._situacao = situacao;
        this._motivo = motivo;
        this._tokenRedeSocial = tokenRedeSocial;
        this._tokenAutenticacao = tokenAutenticacao;
        this._dataHoraExpiracaoToken = dataHoraExpiracaoToken;
        this._criterioAvaliacaoList = criterioAvaliacaoList;
        this._integranteEquipe = integranteEquipe;
        Object.freeze(this);
    }

    get nome(){
        return this._nome;
    }

    get email(){
        return this._email;
    }

    get senha(){
        return this._senha;
    }

    get tipoUsuario(){
        return this._tipoUsuario;
    }

    get situacao(){
        return this._situacao;
    }

    get motivo(){
        return this._motivo;
    }

    get tokenRedeSocial(){
        return this._tokenRedeSocial;
    }

    get tokenAutenticacao(){
        return this._tokenAutenticacao;
    }

    get dataHoraExpiracaoToken(){
        return this._dataHoraExpiracaoToken;
    }

    get criterioAvaliacaoList(){
        return this._criterioAvaliacaoList;
    }

    get integranteEquipe(){
        return this._integranteEquipe;
    }
}