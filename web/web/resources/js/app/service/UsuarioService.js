class UsuarioService{

    //Nos métodos desta classe cb significa CallBack, onde recebe uma funcao de callback do chamador.
    
    add(usuario, cb){
        let xhr = new XMLHttpRequest();

        xhr.open('POST', Configuration.getUrl()+"usuario");
        xhr.setRequestHeader("Content-type", "application/json");

        xhr.onreadystatechange = () => {
            if(xhr.readyState == 4){
                if(xhr.status == 200){
                    cb(null, JSON.parse(xhr.responseText));
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível cadastrar usuário.", null);
                }
            }
        };
        console.log(usuario);
        usuario = JSON.stringify(usuario);
        xhr.send(usuario);
    }

    update(usuario, cb){
        let xhr = new XMLHttpRequest();

        xhr.open('PUT', Configuration.getUrl()+"usuario");
        xhr.setRequestHeader("Content-type", "application/json");

        xhr.onreadystatechange = () => {
            if(xhr.readyState == 4){
                if(xhr.status == 200){
                    cb(null, JSON.parse(xhr.responseText));
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível atualizar usuário.", null);
                }
            }
        };
        console.log(usuario);
        usuario = JSON.stringify(usuario);
        xhr.send(usuario);
    }

    buscarTodosUsuarios(cb){
        let xhr = new XMLHttpRequest();

        xhr.open('GET', Configuration.getUrl()+"usuario");

        xhr.onreadystatechange = () => {
            //Se a requisicao estiver concluida e a resposta estivar pronta
            if(xhr.readyState == 4){
                //Se a requisico foi executada com sucesso
                if(xhr.status == 200){
                    cb(null, JSON.parse(xhr.responseText)
                    .map(u => new Usuario(
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
                    )));
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível obter lista de usuários. Tente novamente mais tarde");
                }
            }
        }

        xhr.send();
    }

    buscarUsuario(id, cb){
        let xhr = new XMLHttpRequest();

        xhr.open('GET', Configuration.getUrl()+"usuario/"+id);

        xhr.onreadystatechange = () => {
            //Se a requisicao estiver concluida e a resposta estivar pronta
            if(xhr.readyState == 4){
                //Se a requisico foi executada com sucesso
                if(xhr.status == 200){
                    let u = JSON.parse(xhr.responseText)
                    cb(null, new Usuario(
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
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível obter usuário. Tente novamente mais tarde");
                }
            }
        }

        xhr.send();
    }

    delete(id, cb){
        let xhr = new XMLHttpRequest();

        xhr.open('DELETE', Configuration.getUrl()+"usuario/"+id);

        xhr.onreadystatechange = () => {
            if(xhr.readyState == 4){
                if(xhr.status == 200){
                    cb(null);
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível excluir usuário selecionado.");
                }
            }
        };

        xhr.send();
    }

    validate(user){
        if(!user.nome){
            throw new Error("Preencha o campo Nome")
        }

        if(!user.email){
            throw new Error("Preencha o campo Email")
        }

        if(!user.senha){
            throw new Error("Preencha o campo Senha")
        }
    }
}