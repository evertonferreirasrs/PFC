class LoginController {
    constructor() {
        this._username = document.getElementById("inputUsername")
        this._password = document.getElementById("inputPassword")


    }

    async login(event) {
        event.preventDefault()

        let senhaCripto = md5(this._password.value)
        let usuario = {
            email: this._username.value,
            senha: senhaCripto,
            hash: md5(this._username.value + senhaCripto)
        }

        let service = new LoginService()

        let user = await service.login(usuario)

        if (user.tipoUsuario.id != 1) {
            localStorage['msg-localizae'] = JSON.stringify({
                'msg': "É necessário ser Administrador para entrar no sistema",
                'type': 'danger'
            })
            window.location.href = Configuration.getUrlWebApp() + "entrar"
        } else {
            ControladorDeDados.saveUser(user)
            window.location.href = Configuration.getUrlWebApp() + "gerenciador"
        }
    }

    logout() {
        ControladorDeDados.saveUser(null)
        localStorage['msg-localizae'] = JSON.stringify({
            'msg': "Saída efetuada com sucesso.",
            'type': 'success'
        })
        window.location.href = Configuration.getUrlWebApp() + "entrar"
    }
}