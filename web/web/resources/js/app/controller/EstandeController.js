class EstandeController {
    constructor() {
        let $ = document.querySelector.bind(document)
        this._titulo = $("#inputTitulo")
        this._numero = $("#inputNumero")
        this._areaTematica = $("#inputAreaTematica")
        this._curso = $("#inputCurso")
        this._periodo = $("#inputPeriodo")
        this._descricao = $("#inputDescricao")
        this._evento = $("#inputEvento")
        this._posicaoX = $("#inputPosicaoX")
        this._posicaoY = $("#inputPosicaoY")
        this._estandeInfoView = new EstandeInfoView();
        this._id = $("#inputId")

        this._estandeList = new Bind(
            new ListaEstande(),
            new EstandeView($("#table-estandes")),
            'delete', 'update', 'add', 'esvazia'
        )
    }

    async altera(event){
        event.preventDefault()

        let evento = new Evento(this._evento.text)
        evento.id = this._evento.value

        let estande = new Estande(this._titulo.value, this._curso.value, this._periodo.value, 
            this._descricao.value, this._areaTematica.value, this._numero.value, evento, null, this._id.value, this._posicaoX.value, this._posicaoY.value)

        let service = new EstandeService()

        service.update(estande).then(result => {
            swal('Atualizado!', 'Estande atualizado com sucesso.', 'success')
        }).catch(error => {
            swal('Erro!', error, 'error')
        })
    }

    async loadEstande(){
        let id = this._id.value
        let service = new EstandeService();

        let estande = await service.readById(id)
        await this.loadSelectEvent()

        this._titulo.value = estande.titulo
        this._numero.value = estande.numero
        this._descricao.value = estande.descricao
        this._posicaoX.value = estande.posicaoX
        this._posicaoY.value = estande.posicaoY

        let areaTematicaList = this._areaTematica.getElementsByTagName('option')
        
        for(let i = 0; i < areaTematicaList.length; i++){
            let element = areaTematicaList[i]
            if(element.value == estande.areaTematica){
                element.selected = true   
            }
        }

        let cursoList = this._curso.getElementsByTagName('option')

        for(let i = 0; i < cursoList.length; i++){
            let element = cursoList[i]
            if(element.value == estande.curso){
                element.selected = true   
            }
        }

        let periodoList = this._periodo.getElementsByTagName('option')

        for(let i = 0; i < periodoList.length; i++){
            let element = periodoList[i]
            if(element.value == estande.periodo){
                element.selected = true   
            }
        }

        let eventoList = this._evento.getElementsByTagName('option')

        for(let i = 0; i < eventoList.length; i++){
            let element = eventoList[i]
            if(element.value == estande.evento.id){
                element.selected = true
            }
        }

        // console.log(estande)
    }

    async delete(element) {
        let id = $(element).closest('tr').data('id')
        let service = new EstandeService()
        swal(
            {
                title: "Você tem certeza disso?",
                text: "Esta operação não poderá ser desfeita!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Sim, apagar!",
                cancelButtonText: "Cancelar",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            (isConfirm) => {
                if (isConfirm) {
                    try {
                        service.delete(id)
                        this._estandeList.delete(id)
                        swal({
                            title: "Excluído!",
                            text: "O estande foi excluída.",
                            showConfirmButton: true
                        })
                    } catch (error) {
                        swal("Erro!", error, "error")
                    }
                } else {
                    swal("Cancelado!", "Usuário não foi apagado.", "error")
                }
            }
        )

    }

    async openEstandeInfo(element) {
        let id = $(element).closest('tr').data('id')

        let service = new EstandeService()
        let estande = await service.readById(id)

        swal({
            title: estande.titulo,
            text: this._estandeInfoView.template(estande),
            html: true
        })
    }

    async readAll() {
        let service = new EstandeService()

        let estandeList = await service.readAll();

        this._estandeList.esvazia()

        estandeList.forEach(estande => {
            this._estandeList.add(estande)
        })
    }

    async loadSelectEvent() {
        let service = new EventoService()
        let eventoList = await service.readAll()

        let option = new Option('Selecione....')
        option.disabled = true
        option.selected = true

        this._evento.add(option)

        eventoList.forEach(evento => {
            option = new Option(evento.nome, evento.id)
            // console.log(evento)

            this._evento.add(option)
        })
    }

    async adiciona(event) {
        event.preventDefault()

        let service = new EstandeService();

        let evento = new Evento(this._evento.text)
        evento.id = this._evento.value

        let estande = new Estande(this._titulo.value, this._curso.value, this._periodo.value,
            this._descricao.value, this._areaTematica.value, this._numero.value, evento, null, null, this._posicaoX.value, this._posicaoY.value)

        // console.log(estande)

        service.add(estande)
            .then(result => {
                swal('Cadastrado!', 'Estande Cadastrado com sucesso.', 'success')
            }).catch(error => {
                swal('Erro!', error, 'error')
            })
    }
}