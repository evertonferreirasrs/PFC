class ListaEstande{
    constructor(){
        this._estandeList = [];
    }

    add(estande){
        this._estandeList.push(estande);
    }

    get estandeList(){
        return [].concat(this._estandeList);
    }

    esvazia(){
        this._estandeList = [];
    }
}