class LoginController{
    constructor(){
        this._username = document.getElementById("inputUsername")
        this._password = document.getElementById("inputPassword")
        

    }

    async login(event){
        event.preventDefault()

        let senhaCripto = md5(this._password.value)
        let usuario = {
            email: this._username.value,
            senha: senhaCripto,
            hash: md5(this._username.value+senhaCripto)
        }

        let service = new LoginService()

        localStorage['user-localizae'] = await service.login(usuario)
        window.location.href = Configuration.getUrlWebApp()+"gerenciador"
    }

    logout(){
        localStorage['user-localizae'] = null
        localStorage['msg-localizae'] = JSON.stringify({
            'msg': "Saída efetuada com sucesso.",
            'type': 'success'
        })
        window.location.href = Configuration.getUrlWebApp()+"entrar"
    }
}