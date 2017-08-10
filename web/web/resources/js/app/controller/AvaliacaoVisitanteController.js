class AvaliacaoVisitanteController{
    constructor(estande){
        let $ = document.querySelector.bind(document)
        this._estande = estande
        this._avaliacaoVisitanteView = new Bind(
            new ListaAvaliacaoVisitante(),
            new AvaliacaoVisitanteView($("#table-avaliacoes")),
            'delete', 'add'
        )
        console.log(this._estande)
    }

    async loadAvaliacoes(){
        let service = new AvaliacaoVisitanteService()

        let list = null
        if(this._estande != null){
            list = await service.readByEstande(this._estande)
        }else{
            list = await service.readAll()
        }

        list.forEach(avaliacao => {
            this._avaliacaoVisitanteView.add(avaliacao)
        });
    }

    async delete(element){
        let id = $(element).closest('tr').data('id')
        let service = new AvaliacaoVisitanteService()

        swal(
            {
                title: "Tem certeza que deseja excluir esta avaliação?",
                text: "A avaliação excluída não poderá ser recuperada!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sim, excluir!",
                closeOnConfirm: false
            },
            () => {
                service.delete(id).then(result => {
                    this._avaliacaoVisitanteView.delete(id)
                    swal({
                        title: "Excluído!",
                        text: "A avaliação foi excluída.",
                        showConfirmButton: true
                    })
                }).catch(error => {
                    swal("Erro!", error, "error")
                })
            }
        )
    }
}