class LoginService{
    async login(data){
        let httpService = new HttpService()

        let result = await httpService.post(Configuration.getUrl()+"usuario/login", data)

        return result
    }
}