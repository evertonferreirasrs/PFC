class ListaUsuario{
    constructor(){
        this._usuarioList = [];
    }

    add(usuario){
        this._usuarioList.push(usuario);
    }

    get usuarioList(){
        return [].concat(this._usuarioList);
    }

    esvazia(){
        this._usuarioList = [];
    }
}