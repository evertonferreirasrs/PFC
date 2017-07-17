class View{
    constructor(elemento){
        this._elemento = elemento;
    }

    template(model){
        throw new Error("Método template deve ser implementado.");
    }

    update(model){
        if(this._elemento != null)
        this._elemento.innerHTML = this.template(model);
    }
}