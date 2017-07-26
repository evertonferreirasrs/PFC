class CriterioAvaliacaoService{
    buscarTodosEstandes(cb){
        let xhr = new XMLHttpRequest();

        xhr.open('GET', Configuration.getUrl()+"criterio");

        xhr.onreadystatechange = () => {
            //Se a requisicao estiver concluida e a resposta estivar pronta
            if(xhr.readyState == 4){
                //Se a requisico foi executada com sucesso
                if(xhr.status == 200){
                    cb(null, JSON.parse(xhr.responseText)
                    .map(c => new CriterioAvaliacao(
                        c.nome,
                        c.peso,
                        c.id
                    )));
                }else{
                    console.log(xhr.responseText);
                    cb("Impossível obter lista de Critérios. Tente novamente mais tarde");
                }
            }
        }

        xhr.send();
    }
}