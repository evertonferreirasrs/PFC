class AvaliacaoJuradoController{
    constructor(estande){
        let $ = document.querySelector.bind(document)
        this._estande = estande
        this._avaliacaoJuradoView = new Bind(
            new ListaAvaliacaoJurado(),
            new AvaliacaoJuradoView($("#table-avaliacoes")),
            'delete', 'add'
        )
        // console.log(this._estande)
    }

    async loadAvaliacoes(){
        let service = new AvaliacaoJuradoService()

        let list = null
        if(this._estande != null){
            list = await service.readByEstande(this._estande)
        }else{
            list = await service.readAll()
        }

        list.forEach(avaliacao => {
            this._avaliacaoJuradoView.add(avaliacao)
        })
        //console.log(list)
    }

    async delete(element){
        let id = $(element).closest('tr').data('id')
        let service = new AvaliacaoJuradoService()

        swal(
            {
                title: "Tem certeza que deseja excluir esta avaliação?",
                text: "A avaliação excluída não poderá ser recuperada!",
                type: "warning",
                showCancelButton: true,
                cancelButtonText: 'Cancelar',
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sim, excluir!",
                closeOnConfirm: false
            },
            () => {
                service.delete(id).then(result => {
                    this._avaliacaoJuradoView.delete(id)
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