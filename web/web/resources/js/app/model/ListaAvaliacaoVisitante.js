class ListaAvaliacaoVisitante{
    constructor(){
        this._avaliacaoVisitanteList = [];
    }

    add(avaliacaoVisitante){
        this._avaliacaoVisitanteList.push(avaliacaoVisitante);
    }

    get avaliacaoVisitanteList(){
        return [].concat(this._avaliacaoVisitanteList);
    }

    esvazia(){
        this._avaliacaoVisitanteList = [];
    }

    delete(id){
        let newArray = []
        this._avaliacaoVisitanteList.forEach(avaliacao => {
            if(id != avaliacao.id){
                newArray.push(avaliacao)
            }
        })

        this._avaliacaoVisitanteList = newArray
    }
}