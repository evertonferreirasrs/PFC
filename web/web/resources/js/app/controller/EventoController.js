class EventoController{
    constructor(){
        let $ = document.querySelector.bind(document)
        this._nome = $("#inputNome")
        this._endereco = $("#inputEndereco")
        this._dataHoraEventoInicio = $("#inputDataHoraEventoInicio")
        this._dataHoraEventoFim = $("#inputDataHoraEventoFim")
        this._id = $("#inputId")

        this._eventoList = new Bind(
            new ListaEvento(),
            new EventoView($("#table-eventos")),
            'delete', 'update', 'add'
        )

        this._eventoInfoView = new EstandeInfoView()
    }

    async openEventoInfo(element){
        let id = $(element).closest('tr').data('id')
        let service = new EventoService()

        let evento = await service.readById(id)
        
        swal({
            title: evento.nome,
            text: this._eventoInfoView.template(evento),
            html: true
        })
    }

    async altera(event){
        event.preventDefault()

        let service = new EventoService()
        console.log(this._dataHoraEventoInicio.value)

        let evento = new Evento(this._nome.value, this._endereco.value, 
                                DateHelper.getTimeFromString(this._dataHoraEventoInicio.value), 
                                DateHelper.getTimeFromString(this._dataHoraEventoFim.value),
                                this._id.value
                            )

        service.update(evento)
            .then(result => {
                swal('Atualizado!', 'Evento atualizado com sucesso.', 'success')
            }).catch(error => {
                swal('Erro!', error, 'error')
            })
    }

    async loadEvento(){
        let service = new EventoService()
        let id = this._id.value
        

        let evento = await service.readById(id)

        this._nome.value = evento.nome
        this._endereco.value = evento.endereco
        this._dataHoraEventoInicio.value = DateHelper.getStringFromDate(evento.dataHoraEventoInicio)
        this._dataHoraEventoFim.value = DateHelper.getStringFromDate(evento.dataHoraEventoFim)
    }

    async adiciona(event){
        event.preventDefault()

        let service = new EventoService()

        let evento = new Evento(this._nome.value, this._endereco.value, 
                                DateHelper.getTimeFromString(this._dataHoraEventoInicio.value), 
                                DateHelper.getTimeFromString(this._dataHoraEventoFim.value)
                            )

        service.add(evento)
            .then(result => {
                swal('Cadastrado!', 'Evento Cadastrado com sucesso.', 'success')
            }).catch(error => {
                swal('Erro!', error, 'error')
            })
    }

    async delete(element){
        let id = $(element).closest('tr').data('id')

        let service = new EventoService()

        swal(
            {
                title: "Tem certeza que deseja excluir este evento?",
                text: "O evento excluído não poderá ser recuperado!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sim, excluir!",
                closeOnConfirm: false
            },
            function () {
                service.delete(id).then(result => {
                    swal({
                        title: "Excluído!",
                        text: "O evento foi excluído. A página será recarregada em 2 segundos.",
                        timer: 2000,
                        showConfirmButton: false
                    })
                    setTimeout(function () {
                        location.reload()
                    }, 2000)
                }).catch(error => {
                    swal("Erro!", error, "error")
                })
            }
        )
    }

    async loadEventos(){
        let service = new EventoService()

        let eventoList = await service.readAll()

        eventoList.forEach(evento => {
            this._eventoList.add(evento)
        })
    }
}