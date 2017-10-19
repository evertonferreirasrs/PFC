class ControladorDeDados{
    static saveUser(user){
        localStorage['user-localizae'] = JSON.stringify(user)
        console.log(JSON.stringify(user))
    }

    static getUserLogged(){
        return JSON.parse(localStorage['user-localizae'])
    }
}