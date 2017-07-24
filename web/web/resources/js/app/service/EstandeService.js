class EstandeService{
    buscarTodosEstandes(cb){
        let xhr = new XMLHttpRequest();

        xhr.open('GET', Configuration.getUrl()+"estande");

        xhr.onreadystatechange = () => {
            //Se a requisicao estiver concluida e a resposta estivar pronta
            if(xhr.readyState == 4){
                //Se a requisico foi executada com sucesso
                if(xhr.status == 200){
                    cb(null, JSON.parse(xhr.responseText)
                    .map(e => new Estande(
                        e.titulo,
                        e.curso,
                        e.periodo,
                        e.descricao,
                        e.areaTematica,
                        e.numero,
                        e.evento,
                        e.equipe,
                        e.id
                    )));
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível obter lista de estandes. Tente novamente mais tarde");
                }
            }
        }

        xhr.send();
    }
}