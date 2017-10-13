class AvaliacaoJuradoView extends View {
    constructor(elemento) {
        super(elemento)
    }

    template(model) {
        return `
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <th>Projeto</th>
                        <th>Jurado</th>
                        <th>Avaliação</th>
                        <th>Status</th>
                        <th>Comentário</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    ${
            model.avaliacaoJuradoList.map(
                avaliacaoJurado => `
                                <tr data-id="${avaliacaoJurado.id}">
                                    <td>${avaliacaoJurado.estande.titulo}</td>
                                    <td>${avaliacaoJurado.usuario.nome}</td>
                                    <td><center>
                                        ${avaliacaoJurado.nota}
                                        </center>
                                    </td>
                                    <td class="text-capitalize ${this.getClassStatus(avaliacaoJurado.status)}">
                                        ${avaliacaoJurado.status}
                                    </td>
                                    <td>${avaliacaoJurado.opiniao}</td>
                                    <td>
                                        <center>
                                            <a class="btn btn-danger" onclick="avaliacaoJuradoController.delete(this)" href="#"><i class="fa fa-lg fa-trash"></i></a>
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
    getClassStatus(status) {
        if (status == 'fechada') {
            return 'background-success'
        } else {
            return 'background-danger'
        }
    }
}

