class EstandeInfoView{

    template(model){

        return `
            <div class="row text-left">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="row">
                            <div class="col-lg-7">
                                <h4>Nome do Evento: <small>${model.nome}</small></h4>
                                <h4>Endereço do Evento: <small>${model.endereco}</small></h4>
                                <h4>Data/Hora Início: <small>${DateHelper.getStringFromDate(model.dataHoraEventoInicio)}</small></h4>
                                <h4>Data/Hora Término: <small>${DateHelper.getStringFromDate(model.dataHoraEventoFim)}</small></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `
    }
}