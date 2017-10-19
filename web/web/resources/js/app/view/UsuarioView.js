class UsuarioView extends View{
    constructor(elemento){
        super(elemento)
    }

    template(model){
        
        return `
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Tipo</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    ${model.usuarioList.map(
                        user => `
                            <tr class="user" data-id="${user.id}">
                                <td class="nome-user">${user.nome}</td>
                                <td class="email-user">${user.email}</td>
                                <td class="tipo-user">${user.tipoUsuario.nome}</td>
                                <td class="actions-user">
                                    <center>
                                        <a class="btn btn-danger" onclick="usuarioController.blockUser(this, ${this.isBlock(user.situacao)})"><i class="fa fa-lg fa-${this.isBan(user.situacao)}"></i></a>&nbsp;
                                        <a class="btn btn-info" onclick="usuarioController.viewData(this)"><i class="fa fa-lg fa-eye"></i></a>&nbsp;
                                        ${this.linkUpdate(user.tipoUsuario.nome, user.id)}
                                        <a class="btn btn-danger" onclick="usuarioController.delete(this)" href="#"><i class="fa fa-lg fa-trash"></i></a>
                                    </center>
                                </td>
                            </tr>
                        `
                    ).join('')}
                </tbody>
            </table>
        `;
    }

    linkUpdate(tipo, id){
        if(tipo.toUpperCase() == "ADMINISTRADOR"){
            return `<a class="btn btn-warning" href="${Configuration.getUrlWebApp() + 'usuario/alterar/administrador/'+id}"><i class="fa fa-edit"></i></a>&nbsp;`
        }else if(tipo.toUpperCase() == "EXPOSITOR"){
            return `<a class="btn btn-warning" href="${Configuration.getUrlWebApp() + 'usuario/alterar/expositor/'+id}"><i class="fa fa-edit"></i></a>&nbsp;`
        }else if(tipo.toUpperCase() == "JURADO"){
            return `<a class="btn btn-warning" href="${Configuration.getUrlWebApp() + 'usuario/alterar/jurado/'+id}"><i class="fa fa-edit"></i></a>&nbsp;`
        }else{
            return ''
        }
    }

    isBan(situacao){
        if(situacao.toUpperCase() == "ATIVO"){
            return 'unlock'
        }else{
            return 'lock'
        }
    }

    isBlock(situacao){
        if(situacao.toUpperCase() == "ATIVO"){
            return false
        }else{
            return true
        }
    }
}