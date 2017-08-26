class EstandeInfoView{

    template(model){
        let responsavel = this.getExpositorResponsavel(model.equipe)

        return `
            <div class="row text-left">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="row">
                            <div class="col-lg-7">
                                <h4>Número do Estande: <small>${model.numero}</small></h4>
                                <h4>Área Temática: <small>${model.areaTematica}</small></h4>
                                <h4>Expositor responsável: <small>${responsavel.nome || '---'}</small></h4>
                                <h4>Email do responsável: <small>${responsavel.email || '---'}</small></h4>
                            </div>
                            <div class="col-lg-5">
                                <h4>Curso: <small>${model.curso}</small></h4> 
                                <h4>Período: <small>${model.periodo}º período</small></h4>
                                <h4>Integrantes:</h4>
                                ${
                                    model.equipe.map(value => {
                                        return `<h4 class="text-left"><small>${value.usuario.nome}</small></h4>`
                                    }).join('')
                                    // model.equipe.forEach(integrante => `<h4 class="text-center"><small>${integrante}</small></h4>`)
                                }
                            </div>
                        </div>
                        <hr>
                        <h4 align="justify">Descrição: <small>${model.descricao}</small></h4>
                    </div>
                </div>
            </div>
        `
    }

    getExpositorResponsavel(equipe){
        let responsavel = {nome:'', email:''}
        equipe.map(value => {
            if(value.responsavel == true){
                responsavel = value.usuario
            }
        })

        return responsavel
    }
}