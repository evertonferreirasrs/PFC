class UsuarioService {
    async readAll() {
        let httpService = new HttpService()

        let uList = await httpService.get(Configuration.getUrl() + "usuario")

        return uList.map(u => new Usuario(
            u.nome,
            u.email,
            u.senha,
            u.tipoUsuario,
            u.situacao,
            u.motivo,
            u.tokenRedeSocial,
            u.tokenAutenticacao,
            u.dataHoraExpiracaoToken,
            u.criterioAvaliacaoList,
            u.integranteEquipe,
            u.id
        ))
    }

    delete(id) {
        let httpService = new HttpService()

        httpService.delete(Configuration.getUrl() + `usuario/${id}`)
            .then(result => {
                return result
            })
            .catch(result => {
                throw new Error("Impossível deletar Usuário.")
                console.log(result)
            })
    }

    add(usuario) {
        let httpService = new HttpService()

        httpService.post(Configuration.getUrl() + "usuario", usuario)
            .then(u => {
                return new Usuario(
                    u.nome,
                    u.email,
                    u.senha,
                    u.tipoUsuario,
                    u.situacao,
                    u.motivo,
                    u.tokenRedeSocial,
                    u.tokenAutenticacao,
                    u.dataHoraExpiracaoToken,
                    u.criterioAvaliacaoList,
                    u.integranteEquipe,
                    u.id
                )
            }).catch(result => {
                console.log(result)
                throw new Error("Impossível cadastrar usuário.")
            })
    }

    async readUser(id) {
        let httpService = new HttpService()

        let u = await httpService.get(Configuration.getUrl() + `usuario/${id}`)

        return new Usuario(
            u.nome,
            u.email,
            u.senha,
            u.tipoUsuario,
            u.situacao,
            u.motivo,
            u.tokenRedeSocial,
            u.tokenAutenticacao,
            u.dataHoraExpiracaoToken,
            u.criterioAvaliacaoList,
            u.integranteEquipe,
            u.id
        )
    }

    update(usuario) {
        let httpService = new HttpService()

        httpService.put(Configuration.getUrl() + "usuario", usuario)
            .then(result => {
                return result
            })
            .catch(result => {
                console.log(result)
                throw new Error("Impossível salvar usuário")
            })
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('PUT', Configuration.getUrl() + "usuario")
            xhr.setRequestHeader("Content-type", "application/json")

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText))
                    } else {
                        console.log(xhr.responseText)
                        reject("Impossível atualizar usuário.")
                    }
                }
            }
            usuario = JSON.stringify(usuario)
            xhr.send(usuario)
        })
    }
}