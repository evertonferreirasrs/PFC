class EstandeView extends View {
    constructor(elemento) {
        super(elemento)
    }

    template(model) {
        return `
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <th>Número</th>
                        <th>Projeto</th>
                        <th>Área Temática</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    ${
                        model.estandeList.map(
                            estande => `
                                <tr class="estande" data-id="${estande.id}">
                                    <td class="numero-estande">${estande.numero}</td>
                                    <td class="titulo-estande">${estande.titulo}</td>
                                    <td class="areaTematica-estande">${estande.areaTematica}</td>
                                    <td class="actions-estande">
                                        <center>
                                            <a class="btn btn-info" href="${Configuration.getUrlWebApp()}estande/${estande.id}/avaliacao"><i class="fa fa-lg  fa-commenting-o"></i></a>&nbsp;
                                            <a class="btn btn-info" onclick="estandeController.openEstandeInfo(this)" href="#"><i class="fa fa-lg fa-eye"></i></a>&nbsp;
                                            <a class="btn btn-warning" href="${Configuration.getUrlWebApp()}estande/alterar/${estande.id}"><i class="fa fa-edit"></i></a>&nbsp;
                                            <a class="btn btn-danger" onclick="estandeController.delete(this)" href="#"><i class="fa fa-lg fa-trash"></i></a>
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