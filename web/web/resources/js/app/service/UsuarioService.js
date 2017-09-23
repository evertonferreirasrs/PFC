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
                throw new Error(result)
                // console.log(result)
            })
    }

    async add(usuario) {
        let httpService = new HttpService()

        try {
            let u = await httpService.post(Configuration.getUrl() + "usuario", usuario)
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
        } catch (error) {
            throw error
        }
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
        return httpService.put(Configuration.getUrl() + "usuario", usuario)
    }

    validate(user) {
        if (user.nome == null) {
            throw new Error('Campo Nome obrigatório')
        }

        if (user.email == null) {
            throw new Error('Campo Email obrigatório')
        }

        if (user.senha == null) {
            throw new Error('Campo Senha obrigatório')
        }
    }
}