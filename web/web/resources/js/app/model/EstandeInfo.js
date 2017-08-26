class EstandeInfo{
    constructor(){
        this._estande = new Estande()
        this._estande.equipe = []
    }

    get estande(){
        return this._estande
    }

    update(estande){
        this._estande = estande
    }
}