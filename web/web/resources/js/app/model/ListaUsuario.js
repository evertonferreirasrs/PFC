class ListaUsuario{
    constructor(){
        this._usuarioList = [];
    }

    add(usuario){
        this._usuarioList.push(usuario);
        console.log('saf')
    }

    get usuarioList(){
        return [].concat(this._usuarioList);
    }

    esvazia(){
        this._usuarioList = [];
    }
}