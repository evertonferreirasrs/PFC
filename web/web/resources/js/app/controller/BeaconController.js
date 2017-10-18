class BeaconController {
    constructor() {
        let $ = document.querySelector.bind(document)
        this._mac = $("#inputMac")
        this._xCoordinate = $("#inputXCoordinate")
        this._yCoordinate = $("#inputYCoordinate")
        this._id = $("#inputId")
        this._mensagemView = new MensagemView()

        this._beaconList = new Bind(
            new ListaBeacon(),
            new BeaconView($("#table-beacons")),
            'delete', 'update', 'add', 'esvazia'
        )
    }

    // async openEventoInfo(element) {
    //     let id = $(element).closest('tr').data('id')
    //     let service = new EventoService()

    //     let evento = await service.readById(id)

    //     swal({
    //         title: evento.nome,
    //         text: this._eventoInfoView.template(evento),
    //         html: true
    //     })
    // }

    async altera(event) {
        event.preventDefault()
        // let view = new MensagemView()

        try {
            let service = new BeaconService()

            let beacon = new Beacon(this._mac.value, this._xCoordinate.value, 
                this._yCoordinate.value, this._id.value)

            service.update(beacon)
                .then(result => {
                    // swal('Atualizado!', 'Evento atualizado com sucesso.', 'success')
                    this._mensagemView.exibirMensagemDeSucesso('Atualizado!', 'Beacon Atualizado Com Sucesso.')
                }).catch(error => {
                    // swal('Erro!', error, 'error')
                    this._mensagemView.exibirMensagemDeErro(error)
                })
        }catch(error){
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async loadBeacon() {
        let service = new BeaconService()
        let id = this._id.value


        let beacon = await service.readById(id)

        this._mac.value = beacon.mac
        this._xCoordinate.value = beacon.xCoordinate
        this._yCoordinate.value = beacon.yCoordinate
    }

    async adiciona(event) {
        event.preventDefault()
        // let view = new MensagemView()

        try {
            let service = new BeaconService()

            let beacon = new Beacon(this._mac.value, this._xCoordinate.value, this._yCoordinate.value)

            service.add(beacon)
                .then(result => {
                    this._mensagemView.exibirMensagemDeSucesso('Cadastrado!', 'Beacon Cadastrado Com Sucesso.')
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

        let service = new BeaconService()

        swal(
            {
                title: "Tem certeza que deseja excluir este beacon?",
                text: "O beacon excluído não poderá ser recuperado!",
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
                        this._beaconList.delete(id)
                        this._mensagemView.exibirMensagemDeSucesso('Excluído!', 'Registro Excluído Com Sucesso!')
                    } catch (error) {
                        this._mensagemView.exibirMensagemDeErro(error)
                    }
                } else {
                    this._mensagemView.exibirMensagemDeErro('Beacon não foi apagado.')
                }
            }
        )
    }

    async loadBeacons() {
        let service = new BeaconService()

        let beaconList = await service.readAll()

        beaconList.forEach(beacon => {
            this._beaconList.add(beacon)
        })
    }
}