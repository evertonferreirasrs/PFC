class UsuarioController{
    constructor(){
        let $ = document.querySelector.bind(document);
        this._nome = $("#inputNome");
        this._email = $("#inputEmail");
        this._senha = $("#inputPassword");
        this._tipoUsuario = $("#inputTipoUsuario");

        this._usuarioList = new Bind(
            new ListaUsuario(),
            new UsuarioView($("#table-users")),
            'delete', 'update', 'add'
        );
    }

    adiciona(event){
        event.preventDefault();
        console.log(this._tipoUsuario.value);
        let tipoUsuario = new TipoUsuario(null, this._tipoUsuario.value);
        let user = new Usuario(this._nome.value, this._email.value, this._senha.value, tipoUsuario, 'ativo');

        let service = new UsuarioService();

        service.add(user, (err, usuario) => {
            if(err){
                console.log(err);
                return;
            }
        });
    }

    readAll(){
        let service = new UsuarioService();
        
        service.buscarTodosUsuarios((err, usuarioList) => {
            if(err){
                console.log(err);
                return;
            }

            this._usuarioList.esvazia();

            usuarioList.forEach(user => {
                this._usuarioList.add(user);
            });
        });
    }

    delete(element){
        let id = $(element).closest('tr').data('id');
        console.log(id);
        let service = new UsuarioService();

        service.delete(id, (err) => {
            if(err){
                console.log(err);
                return;
            }
            console.log("Usuario excluido com sucesso.");
            this.readAll();
        });
    }
}