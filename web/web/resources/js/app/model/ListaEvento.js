class ListaEvento{
    constructor(){
        this._eventoList = []
    }

    get eventoList(){
        return [].concat(this._eventoList)
    }

    add(evento){
        this._eventoList.push(evento)
    }

    esvazia(){
        this._eventoList = []
    }
}