class AvaliacaoVisitanteView extends View{
    constructor(elemento){
        super(elemento)
    }

    template(model){
        return `
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <th>Projeto</th>
                        <th>Visitante</th>
                        <th>Avaliação</th>
                        <th>Comentário</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    ${
                        model.avaliacaoVisitanteList.map(
                            avaliacaoVisitante => `
                                <tr data-id="${avaliacaoVisitante.id}">
                                    <td>${avaliacaoVisitante.estande.titulo}</td>
                                    <td>${avaliacaoVisitante.usuario.nome}</td>
                                    <td><center>
                                        ${
                                            this.getStars(avaliacaoVisitante.nota)
                                        }
                                        </center>
                                    </td>
                                    <td>${avaliacaoVisitante.opiniao}</td>
                                    <td>
                                        <center>
                                            <a class="btn btn-danger" onclick="avaliacaoVisitanteController.delete(this)" href="#"><i class="fa fa-lg fa-trash"></i></a>
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

    getStars(nota){
        let stars = ''

        for(let i = 0; i < nota; i++){
            stars += `<i class="fa fa-star"></i>`
        }

        return stars
    }
}