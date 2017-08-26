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

    delete(id){
        let newArray = []
        this._usuarioList.forEach(avaliacao => {
            if(id != avaliacao.id){
                newArray.push(avaliacao)
            }
        })

        this._usuarioList = newArray
    }

    update(userList){
        this._usuarioList = userList
    }
}