class ListaAvaliacaoJurado{
    constructor(){
        this._avaliacaoJuradoList = [];
    }

    add(avaliacaoJurado){
        this._avaliacaoJuradoList.push(avaliacaoJurado);
    }

    get avaliacaoJuradoList(){
        return [].concat(this._avaliacaoJuradoList);
    }

    esvazia(){
        this._avaliacaoJuradoList = [];
    }

    delete(id){
        let newArray = []
        this._avaliacaoJuradoList.forEach(avaliacao => {
            if(id != avaliacao.id){
                newArray.push(avaliacao)
            }
        })

        this._avaliacaoJuradoList = newArray
    }
}