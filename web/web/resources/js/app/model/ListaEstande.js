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

    delete(id){
        let newArray = []
        console.log()
        this._estandeList.forEach(estande => {
            
            if(id != estande.id){
                newArray.push(estande)
            }
        })

        this._estandeList = newArray
    }
}