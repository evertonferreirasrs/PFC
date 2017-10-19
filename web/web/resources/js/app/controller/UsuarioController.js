class UsuarioController {
    constructor() {
        let $ = document.querySelector.bind(document);
        this._nome = $("#inputNome")
        this._email = $("#inputEmail")
        this._senha = $("#inputPassword")
        this._tipoUsuario = $("#inputTipoUsuario")
        this._id = $("#inputId")
        this._inputEstande = $("#inputEstande")
        this._responsavel = $("#inputResponsavel")
        this._criterioListDiv = $("#criterio")
        this._criterioJuradoView = new CriterioJuradoView($("#criterio"))
        this._mensagemView = new MensagemView()

        this._usuarioList = new Bind(
            new ListaUsuario(),
            new UsuarioView($("#table-users")),
            'delete', 'update', 'add', 'esvazia'
        )
    }

    async alteraAdm(event) {
        event.preventDefault()

        try {
            let service = new UsuarioService()
            let user = await service.readUser(this._id.value)
            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)

            user.nome = this._nome.value
            user.email = this._email.value
            user.tipoUsuario = tipoUsuario
            user.id = this._id.value
            service.validate(user)

            service.update(user).then(result => {
                // swal("Confirmado!", "Foi alterado um usuário!", "success")
                this._mensagemView.exibirMensagemDeSucesso('Confirmado!', 'Usuário Alterado Com Sucesso.')
            }).catch(error => {
                this._mensagemView.exibirMensagemDeErro(error)
            })
        } catch (error) {
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async alteraExpositor(event) {
        event.preventDefault()

        try {
            let service = new UsuarioService()
            let user = await service.readUser(this._id.value)
            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)

            user.nome = this._nome.value
            user.email = this._email.value
            user.tipoUsuario = tipoUsuario
            user.id = this._id.value
            service.validate(user)

            let estande = new Estande(this._inputEstande.text)
            estande.id = this._inputEstande.value
            let integranteEquipe = new IntegranteEquipe(null, estande, this._responsavel.checked)

            user.integranteEquipe = integranteEquipe

            service.update(user)
            .then(result => {
                // swal("Confirmado!", "Foi alterado um usuário!", "success")
                this._mensagemView.exibirMensagemDeSucesso('Confirmado!', 'Usuário Alterado Com Sucesso.')
            }).catch(error => {
                this._mensagemView.exibirMensagemDeErro(error)
            })
        } catch (error) {
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async alteraJurado(event) {
        event.preventDefault()

        try {
            let service = new UsuarioService()
            let user = await service.readUser(this._id.value)
            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)

            user.nome = this._nome.value
            user.email = this._email.value
            user.tipoUsuario = tipoUsuario
            user.id = this._id.value
            service.validate(user)

            let criterioList = this._criterioListDiv.children

            // console.log(this._criterioList.getElementById("id"))
            user.criterioAvaliacaoList = []

            for (let i = 0; i < criterioList.length; i++) {
                let inputCriterio = criterioList[i].getElementsByClassName('inputCriterio')[0]
                let inputEstandeCriterio = criterioList[i].getElementsByClassName('inputEstandeCriterio')[0]

                let criterioAvaliacao = new CriterioAvaliacao()
                criterioAvaliacao.id = inputCriterio.value

                let estande = new Estande()
                estande.id = inputEstandeCriterio.value

                let criterioJurado = new CriterioJurado(criterioAvaliacao, null, estande)
                user.criterioAvaliacaoList.push(criterioJurado)

                // console.log(user.criterioAvaliacaoList)
            }

            service.update(user).then(result => {
                // swal("Confirmado!", "Foi alterado um usuário!", "success")
                this._mensagemView.exibirMensagemDeSucesso('Confirmado!', 'Usuário Alterado Com Sucesso.')
            }).catch(error => {
                this._mensagemView.exibirMensagemDeErro(error)
            })
        } catch (error) {
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async readAdm() {
        let id = this._id.value
        let service = new UsuarioService()

        let user = await service.readUser(id)

        this._nome.value = user.nome
        this._email.value = user.email
    }

    async readExpositor() {
        let id = this._id.value
        let service = new UsuarioService()
        let estandeService = new EstandeService()

        let user = await service.readUser(id)

        this._nome.value = user.nome
        this._email.value = user.email
        this._responsavel.checked = user.integranteEquipe.responsavel

        let estandeList = await estandeService.readAll()

        estandeList.forEach(estande => {
            let option = new Option(estande.titulo, estande.id)

            if (estande.id == user.integranteEquipe.estande.id) {
                option.selected = true
            }

            this._inputEstande.add(option)
        })
    }

    async readJurado() {
        let id = this._id.value
        let service = new UsuarioService()
        let estandeService = new EstandeService()
        let criterioService = new CriterioAvaliacaoService()

        let user = await service.readUser(id)
        let criterioList = await criterioService.readAll()
        let estandeList = await estandeService.readAll()

        this._nome.value = user.nome
        this._email.value = user.email
        user.criterioAvaliacaoList.forEach(criterio => {
            this._criterioJuradoView.add(
                criterioList,
                estandeList,
                criterio.estande.id,
                criterio.criterioAvaliacao.id
            )
        })
    }

    async viewData(element) {
        let id = $(element).closest('tr').data('id')
        let service = new UsuarioService()

        let user = await service.readUser(id)

        swal({
            title: "<small>Dados do Usuário!</small>",
            text: `
                <div class="text-left">
                    <br>
                    Nome: ${user.nome}<br>
                    Email: ${user.email}<br>
                    Situação: ${user.situacao}<br>
                    Motivo: ${user.motivo || '---'}
                </div>
            `,
            html: true
        })
    }

    async addCriterio(event) {
        if (event != null) {
            event.preventDefault()
        }

        let criterioService = new CriterioAvaliacaoService()
        let estandeService = new EstandeService()

        let criterioList = await criterioService.readAll()
        let estandeList = await estandeService.readAll()

        this._criterioJuradoView.add(criterioList, estandeList)
    }

    addAdm(event) {
        event.preventDefault()

        try {
            let service = new UsuarioService()

            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)
            let user = new Usuario(this._nome.value, this._email.value, this._senha.value, tipoUsuario)
            service.validate(user)

            this.add(user, service)
        } catch (error) {
            // swal("Erro!", error, "error")
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    addExpositor(event) {
        event.preventDefault()

        try {
            let service = new UsuarioService()

            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)
            let user = new Usuario(this._nome.value, this._email.value, this._senha.value, tipoUsuario, 'ativo')
            service.validate(user)

            let estande = new Estande(this._inputEstande.text)
            estande.id = this._inputEstande.value
            let integranteEquipe = new IntegranteEquipe(null, estande, this._responsavel.checked)

            user.integranteEquipe = integranteEquipe

            this.add(user, service)
        } catch (error) {
            // swal("Erro!", error, "error")
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    addJurado(event) {
        event.preventDefault()

        try {
            let service = new UsuarioService()

            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)
            let user = new Usuario(this._nome.value, this._email.value, this._senha.value, tipoUsuario, 'ativo')
            service.validate(user)

            let criterioList = this._criterioListDiv.children

            // console.log(this._criterioList.getElementById("id"))
            user.criterioAvaliacaoList = []

            for (let i = 0; i < criterioList.length; i++) {
                let inputCriterio = criterioList[i].getElementsByClassName('inputCriterio')[0]
                let inputEstandeCriterio = criterioList[i].getElementsByClassName('inputEstandeCriterio')[0]
                if(!isNaN(inputCriterio.value) && !isNaN(inputEstandeCriterio.value)){
                    let criterioAvaliacao = new CriterioAvaliacao()
                    criterioAvaliacao.id = inputCriterio.value

                    let estande = new Estande()
                    estande.id = inputEstandeCriterio.value

                    let criterioJurado = new CriterioJurado(criterioAvaliacao, null, estande)
                    user.criterioAvaliacaoList.push(criterioJurado)
                }
                // console.log(user.criterioAvaliacaoList)
            }

            this.add(user, service)
        } catch (error) {
            // swal("Erro!", error, "error")
            // this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async add(data, service) {
        try {
            let result = await service.add(data)

            this._mensagemView.exibirMensagemDeSucesso('Adicionado!', 'Usuário Adicionado Com Sucesso.')
        } catch (error) {
            // swal("Error", error, "error")
            this._mensagemView.exibirMensagemDeErro(error)
        }
    }

    async loadEstandes() {
        let service = new EstandeService()

        let estandeList = await service.readAll()

        estandeList.forEach(estande => {
            this._inputEstande.add(new Option(estande.titulo, estande.id))
        })
    }

    async delete(element) {
        let id = $(element).closest('tr').data('id')
        let service = new UsuarioService()
        swal(
            {
                title: "Você tem certeza disso?",
                text: "Esta operação não poderá ser desfeita!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                cancelButtonText: 'Cancelar',
                confirmButtonText: "Sim, apagar!",
                cancelButtonText: "Cancelar",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            (isConfirm) => {
                if (isConfirm) {
                    try {
                        service.delete(id)
                        this._usuarioList.delete(id)
                        // swal({
                        //     title: "Excluído!",
                        //     text: "O usuário foi excluída.",
                        //     showConfirmButton: true
                        // })
                        this._mensagemView.exibirMensagemDeSucesso('Excluído!', 'Usuário Excluído Com Sucesso.')
                    } catch (error) {
                        // swal("Erro!", error, "error")
                        this._mensagemView.exibirMensagemDeErro(error)
                    }
                } else {
                    // swal("Cancelado!", "Usuário não foi apagado.", "error")
                    this._mensagemView.exibirMensagemDeSucesso('Cancelado!', 'Usuário Não Foi Apagado.')
                }
            }
        )
    }

    async readAll() {
        let service = new UsuarioService()

        let usuarioList = await service.readAll()

        this._usuarioList.esvazia()

        usuarioList.forEach(user => {
            this._usuarioList.add(user)
        })
    }

    async blockUser(element, unblock) {
        let id = $(element).closest('tr').data('id')
        let service = new UsuarioService();

        swal(
            {
                title: "Bloquear/Desbloquer usuário!",
                text: "Escreva o motivo da operação:",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: "slide-from-top",
                inputPlaceholder: "Motivo"
            },
            async (inputValue) => {
                if (inputValue === false) return false;

                if (inputValue === "") {
                    swal.showInputError("Motivo de operação obrigatório!")
                    return false
                }

                let user = await service.readUser(id)

                if (unblock == true) {
                    user.situacao = "Ativo"
                } else {
                    user.situacao = "Bloqueado"
                }

                user.motivo = inputValue

                service.update(user).then(async result => {
                    // swal("OK!", `Usuário bloqueado/desbloqueado com sucesso.`, "success")
                    this._mensagemView.exibirMensagemDeSucesso('Sucesso!', 'Usuário Bloqueado/Desbloqueado Com Sucesso.')
                    let userList = await service.readAll()
                    this._usuarioList.update(userList)
                }).catch(error => {
                    // swal("Erro!", result, "error")
                    this._mensagemView.exibirMensagemDeErro(error)
                })
            }
        )
    }
}