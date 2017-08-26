class EventoView extends View {
    constructor(elemento) {
        super(elemento)
    }

    template(model) {
        return `
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Endereço</th>
                        <th>Data/Hora Início</th>
                        <th>Data/Hora Término</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    ${
                        model.eventoList.map(
                            estande => `
                                <tr class="estande" data-id="${estande.id}">
                                    <td class="numero-estande">${estande.nome}</td>
                                    <td class="numero-estande">${estande.endereco}</td>
                                    <td class="titulo-estande">${DateHelper.getStringFromDate(estande.dataHoraEventoInicio)}</td>
                                    <td class="areaTematica-estande">${DateHelper.getStringFromDate(estande.dataHoraEventoFim)}</td>
                                    <td class="actions-estande">
                                        <center>
                                            <a class="btn btn-info" onclick="eventoController.openEventoInfo(this)" href="#"><i class="fa fa-lg fa-eye"></i></a>&nbsp;
                                            <a class="btn btn-warning" href="${Configuration.getUrlWebApp()}evento/alterar/${estande.id}"><i class="fa fa-edit"></i></a>&nbsp;
                                            <a class="btn btn-danger" onclick="eventoController.delete(this)" href="#"><i class="fa fa-lg fa-trash"></i></a>
                                        </center>
                                    </td>
                                </tr>
                            `
                        ).join('')
                    }
                </tbody>
            </table>
        `
    }
}