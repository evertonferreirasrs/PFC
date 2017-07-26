class UsuarioController {
    constructor() {
        let $ = document.querySelector.bind(document);
        this._nome = $("#inputNome")
        this._email = $("#inputEmail")
        this._senha = $("#inputPassword")
        this._tipoUsuario = $("#inputTipoUsuario")
        this._inputEstande = $("#inputEstande")
        this._responsavel = $("#inputResponsavel")
        this._criterioListDiv = $("#criterio")
        this._inputCriterio = $("#inputCriterio")
        this._inputEstandeCriterio = $("#inputEstandeCriterio")
        this._estandeList = []
        this._criterioList = []

        this._usuarioList = new Bind(
            new ListaUsuario(),
            new UsuarioView($("#table-users")),
            'delete', 'update', 'add'
        );
    }

    _loadEstandeList() {
        return new Promise((resolve, reject) => {
            let service = new EstandeService();

            service.buscarTodosEstandes((err, estandeList) => {
                if (err) {
                    reject(err)
                } else {
                    resolve(estandeList)
                }
            });
        })
    }

    _loadCriterioList() {
        return new Promise((resolve, reject) => {
            let service = new CriterioAvaliacaoService();

            service.buscarTodosEstandes((err, criterioList) => {
                if (err) {
                    reject(err)
                } else {
                    resolve(criterioList)
                }
            });
        })
    }

    loadEstandes() {
        this._loadEstandeList().then((estandeList) => {
            estandeList.forEach(estande => {
                let option = new Option(estande.titulo, estande.id)

                this._inputEstande.add(option)
            })
        })
    }

    async addCriterio(event) {
        if (event != null) {
            event.preventDefault()
        }

        if (this._estandeList.length == 0) {
            let estandeList = await this._loadEstandeList()

            estandeList.forEach((e) => {
                let estande = new Estande(
                    e.titulo,
                    e.curso,
                    e.periodo,
                    e.descricao,
                    e.areaTematica,
                    e.numero,
                    e.evento,
                    e.equipe,
                    e.id
                )

                this._estandeList.push(estande)
            })
        }

        if (this._criterioList.length == 0) {
            let criterioList = await this._loadCriterioList()

            criterioList.forEach((c) => {
                let criterio = new CriterioAvaliacao(
                    c.nome,
                    c.peso,
                    c.id
                )

                this._criterioList.push(criterio)
            })

            // console.log(this._criterioList)
        }

        let divCriterio = document.createElement('div')
        divCriterio.classList.add('row', 'criterio')


        let divInputCriterio = document.createElement('div')
        divInputCriterio.classList.add('col-md-6', 'form-group')

        let divInputEstande = document.createElement('div')
        divInputEstande.classList.add('col-md-6', 'form-group')

        let labelCriterio = `<label class="control-label" for="inputCriterio">Critério:<small> (será avaliado)</small></label>`

        let labelEstande = `<label class="control-label" for="inputEstande">Estande:<small> (será avaliado)</small></label>`

        let selectCriterio = document.createElement('select')
        selectCriterio.classList.add('form-control')
        selectCriterio.classList.add('inputCriterio')

        let option = new Option('Selecione...')
        option.disabled = true
        option.selected = true

        selectCriterio.add(option)
        this._criterioList.forEach((criterio) => {
            let option = new Option(criterio.nome, criterio.id)

            selectCriterio.add(option)
        })

        let selectEstande = document.createElement('select')
        selectEstande.classList.add('form-control')
        selectEstande.classList.add('inputEstandeCriterio')

        option = new Option('Selecione...')
        option.disabled = true
        option.selected = true

        selectEstande.add(option)

        this._estandeList.forEach((estande) => {
            let option = new Option(estande.titulo, estande.id)

            selectEstande.add(option)
        })

        divInputCriterio.innerHTML = labelCriterio
        divInputCriterio.appendChild(selectCriterio)

        divInputEstande.innerHTML = labelEstande
        divInputEstande.appendChild(selectEstande)

        divCriterio.appendChild(divInputCriterio)
        divCriterio.appendChild(divInputEstande)

        let criterioList = document.getElementById("criterio")

        criterioList.appendChild(divCriterio)

        // console.log(criterioList)
    }

    adiciona(event) {
        event.preventDefault()

        let service = new UsuarioService()

        try {
            let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value)
            let user = new Usuario(this._nome.value, this._email.value, this._senha.value, tipoUsuario, 'ativo')
            service.validate(user)
            if (tipoUsuario.id == 3) {
                let estande = new Estande(this._inputEstande.text)
                estande.id = this._inputEstande.value
                let integranteEquipe = new IntegranteEquipe(null, estande, this._responsavel.checked)

                user.integranteEquipe = integranteEquipe
            }


            if (tipoUsuario.id == 4) {
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
            }

            // console.log(user)

            // console.log(user)


            service.add(user, (err, usuario) => {
                if (err) {
                    swal("Falha!", err + "\n Tenha certeza de preencher todos os campos.", "error")
                    return
                } else {
                    swal("Confirmado!", "Foi adicionado um Jurado!", "success")
                }
            });
        } catch (e) {
            swal("Falha!", e.message, "error")
        }
    }

    readAll() {
        let service = new UsuarioService()

        service.buscarTodosUsuarios((err, usuarioList) => {
            if (err) {
                console.log(err)
                return;
            }

            this._usuarioList.esvazia()

            usuarioList.forEach(user => {
                this._usuarioList.add(user)
            });
        });
    }

    delete(element) {
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
            function (isConfirm) {
                if (isConfirm) {
                    let id = $(element).closest('tr').data('id')
                    console.log(id)
                    let service = new UsuarioService()

                    service.delete(id, (err) => {
                        if (err) {
                            swal("Erro!", err, "error")
                            return
                        }
                    });
                    swal("Apagado!", "Usuário apagado com sucesso.", "success")
                } else {
                    swal("Cancelado!", "Usuário não foi apagado.", "error")
                }
            }
        )
        this.readAll()
    }

    viewData(element) {
        let id = $(element).closest('tr').data('id')
        let service = new UsuarioService();

        service.buscarUsuario(id, (err, user) => {
            if (err) {
                swal('Erro!', err, 'error')
                return
            } else {
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
        })
    }

    blockUser(element, unblock) {
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
            function (inputValue) {
                if (inputValue === false) return false;

                if (inputValue === "") {
                    swal.showInputError("Motivo de operação obrigatório!")
                    return false
                }

                service.buscarUsuario(id, (err, user) => {
                    if (err) {
                        swal('Erro!', err, 'error')
                        return
                    } else {
                        if(unblock == true){
                            user.situacao = "Ativo"
                        }else{
                            user.situacao = "Bloqueado"
                        }
                        user.motivo = inputValue
                        service.update(user, (error, user) => {
                            swal("OK!", `Usuário ${user.nome} bloqueado com sucesso.`, "success");
                        })
                    }
                })
            }
        )





    }
}