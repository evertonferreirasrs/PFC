class ControladorDeDados{
    static saveUser(user){
        localStorage['user-localizae'] = user
    }

    static getUserLogged(){
        return localStorage['user-localizae']
    }
}