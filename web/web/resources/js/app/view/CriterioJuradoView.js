class CriterioJuradoView extends View {
    template(criterioList, estandeList, estandeSelected, criterioSelected) {

        return `
            <div class="row criterio">
                <div class="col-md-6 form-group">
                    <label class="control-label" for="inputCriterio">Critério:<small> (será avaliado)</small></label>
                    <select class="form-control inputCriterio">
                        <option disabled selected>Selecione...</option>
                        
                        ${
                            criterioList.map(criterio => {
                                return `<option value="${criterio.id}" ${this.ifSelected(criterioSelected, criterio.id)}>${criterio.nome}</option>`
                            }).join('')
                        }
                    </select>
                </div>
                <div class="col-md-6 form-group">
                    <label class="control-label" for="inputEstandeCriterio">Estande:<small> (será avaliado)</small></label>
                    <select class="form-control inputEstandeCriterio">
                        <option disabled selected>Selecione...</option>
                        ${
                            estandeList.map(estande => {
                                return `<option value="${estande.id}" ${this.ifSelected(estandeSelected, estande.id)}>${estande.titulo}</option>`
                            }).join('')
                        }
                    </select>
                </div>
            </div>
        `
    }

    add(criterioList, estandeList, estadeSelected, criterioSelected) {
        this._elemento.insertAdjacentHTML('beforeEnd', this.template(criterioList, estandeList, estadeSelected, criterioSelected))
    }

    ifSelected(selected, possibleSelected){
        if(possibleSelected == selected) return "selected"
        return ""
    }
}