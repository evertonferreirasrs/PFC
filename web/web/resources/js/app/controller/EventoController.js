class EventoController {
    constructor() {
        let $ = document.querySelector.bind(document)
        this._nome = $("#inputNome")
        this._endereco = $("#inputEndereco")
        this._dataHoraEventoInicio = $("#inputDataHoraEventoInicio")
        this._dataHoraEventoFim = $("#inputDataHoraEventoFim")
        this._id = $("#inputId")
        this._mensagemView = new MensagemView()

        this._eventoList = new Bind(
            new ListaEvento(),
            new EventoView($("#table-eventos")),
            'delete', 'update', 'add', 'esvazia'
        )

        this._eventoInfoView = new EstandeInfoView()
    }

    async openEventoInfo(element) {
        let id = $(element).closest('tr').data('id')
        let service = new EventoService()

        let evento = await service.readById(id)

        swal({
            title: evento.nome,
            text: this._eventoInfoView.template(evento),
            html: true
        })
    }

    async altera(event) {
        event.preventDefault()
        // let view = new MensagemView()

        try {
            let service = new EventoService()

            let evento = new Evento(this._nome.value, this._endereco.value,
                DateHelper.getTimeFromString(this._dataHoraEventoInicio.value),
                DateHelper.getTimeFromString(this._dataHoraEventoFim.value),
                this._id.value
            )

            service.update(evento)
                .then(result => {
                    // swal('Atualizado!', 'Evento atualizado com sucesso.', 'success')
                    this._mensagemView.exibirMensagemDeSucesso('Atualizado!', 'Evento Atualizado Com Sucesso.')
                }).catch(error => {
                    // swal('Erro!', error, 'error')
                    this._mensagemView.exibirMensagemDeErro(error)
                })
        }catch(error){
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async loadEvento() {
        let service = new EventoService()
        let id = this._id.value


        let evento = await service.readById(id)

        this._nome.value = evento.nome
        this._endereco.value = evento.endereco
        this._dataHoraEventoInicio.value = DateHelper.getStringFromDate(evento.dataHoraEventoInicio)
        this._dataHoraEventoFim.value = DateHelper.getStringFromDate(evento.dataHoraEventoFim)
    }

    async adiciona(event) {
        event.preventDefault()
        // let view = new MensagemView()

        try {
            let service = new EventoService()

            let evento = new Evento(this._nome.value, this._endereco.value,
                DateHelper.getTimeFromString(this._dataHoraEventoInicio.value),
                DateHelper.getTimeFromString(this._dataHoraEventoFim.value)
            )

            service.add(evento)
                .then(result => {
                    this._mensagemView.exibirMensagemDeSucesso('Cadastrado!', 'Evento Cadastrado Com Sucesso.')
                    // swal('Cadastrado!', 'Evento Cadastrado com sucesso.', 'success')
                }).catch(error => {
                    this._mensagemView.exibirMensagemDeErro(error)
                    // swal('Erro!', error, 'error')
                })
        } catch (error) {
            this._mensagemView.exibirMensagemDeSucesso(error.message)
        }
    }

    async delete(element) {
        let id = $(element).closest('tr').data('id')

        let service = new EventoService()

        swal(
            {
                title: "Tem certeza que deseja excluir este evento?",
                text: "O evento excluído não poderá ser recuperado!",
                type: "warning",
                showCancelButton: true,
                cancelButtonText: 'Cancelar',
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sim, excluir!",
                closeOnConfirm: false
            },
            (isConfirm) => {
                if (isConfirm) {
                    try {
                        service.delete(id)
                        this._eventoList.delete(id)
                        this._mensagemView.exibirMensagemDeSucesso('Excluído!', 'Registro Excluído Com Sucesso!')
                    } catch (error) {
                        this._mensagemView.exibirMensagemDeErro(error)
                    }
                } else {
                    this._mensagemView.exibirMensagemDeErro('Evento não foi apagado.')
                }
            }
        )
    }

    async loadEventos() {
        let service = new EventoService()

        let eventoList = await service.readAll()

        eventoList.forEach(evento => {
            this._eventoList.add(evento)
        })
    }
}