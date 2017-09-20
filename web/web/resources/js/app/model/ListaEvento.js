class ListaEvento {
    constructor() {
        this._eventoList = []
    }

    get eventoList() {
        return [].concat(this._eventoList)
    }

    add(evento) {
        this._eventoList.push(evento)
    }

    esvazia() {
        this._eventoList = []
    }

    delete(id) {
        let newArray = []
        console.log('dslknsdkgvnkps')
        this._eventoList.forEach(evento => {
            if (id != evento.id) {
                newArray.push(evento)
            }
        })

        this._eventoList = newArray
    }
}