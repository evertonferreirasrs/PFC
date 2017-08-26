class AvaliacaoVisitanteService{
    readByEstande(estande) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('GET', Configuration.getUrl() + "avaliacaoVisitante?estande="+estande);

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve(
                            JSON.parse(xhr.responseText)
                            .map(a => new AvaliacaoVisitante(
                                a.nota,
                                a.opiniao,
                                new Usuario(a.usuario.nome, null, null, null, null, null, null, null, null, null, null, a.usuario.id),
                                new Estande(a.estande.titulo, null, null, null, null, null, null, null, a.estande.id),
                                a.id
                            ))
                        )
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível obter lista de estandes. Tente novamente mais tarde");
                    }
                }
            }

            xhr.send();
        })
    }

    readAll() {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('GET', Configuration.getUrl() + "avaliacaoVisitante");

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve(
                            JSON.parse(xhr.responseText)
                            .map(a => new AvaliacaoVisitante(
                                a.nota,
                                a.opiniao,
                                new Usuario(a.usuario.nome, null, null, null, null, null, null, null, null, null, null, a.usuario.id),
                                new Estande(a.estande.titulo, null, null, null, null, null, null, null, a.estande.id),
                                a.id
                            ))
                        )
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível obter lista de estandes. Tente novamente mais tarde");
                    }
                }
            }

            xhr.send();
        })
    }

    delete(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('DELETE', Configuration.getUrl() + "avaliacaoVisitante/"+id);

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve("Avaliação Excluída.")
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível excluir avaliações. Tente novamente mais tarde");
                    }
                }
            }

            xhr.send();
        })
    }
}