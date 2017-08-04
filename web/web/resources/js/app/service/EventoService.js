class EventoService {
    readById(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('GET', Configuration.getUrl() + "evento/"+id);

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        let e = JSON.parse(xhr.responseText)
                        resolve(
                            new Evento(
                                e.nome,
                                e.endereco,
                                new Date(e.dataHoraEventoInicio),
                                new Date(e.dataHoraEventoFim),
                                e.id
                            )
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

            xhr.open('GET', Configuration.getUrl() + "evento");

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve(
                            JSON.parse(xhr.responseText)
                            .map(e => new Evento(
                                e.nome,
                                e.endereco,
                                new Date(e.dataHoraEventoInicio),
                                new Date(e.dataHoraEventoFim),
                                e.id
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

            xhr.open('DELETE', Configuration.getUrl() + "evento/"+id);

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve("Evento Excluído.")
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível obter lista de estandes. Tente novamente mais tarde");
                    }
                }
            }

            xhr.send();
        })
    }

    add(evento) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('POST', Configuration.getUrl() + "evento");
            xhr.setRequestHeader("Content-type", "application/json");

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText));
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível cadastrar evento.");
                    }
                }
            };

            evento = JSON.stringify(evento);
            console.log(evento);
            xhr.send(evento);
        })
    }

    update(evento) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('PUT', Configuration.getUrl() + "evento");
            xhr.setRequestHeader("Content-type", "application/json");

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText));
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível atualizar evento.");
                    }
                }
            };

            evento = JSON.stringify(evento);
            console.log(evento);
            xhr.send(evento);
        })
    }
}