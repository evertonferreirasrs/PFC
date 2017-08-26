class EstandeService {
    buscarTodosEstandes(cb) {
        let xhr = new XMLHttpRequest();

        xhr.open('GET', Configuration.getUrl() + "estande");

        xhr.onreadystatechange = () => {
            //Se a requisicao estiver concluida e a resposta estivar pronta
            if (xhr.readyState == 4) {
                //Se a requisico foi executada com sucesso
                if (xhr.status == 200) {
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
                } else {
                    console.log(xhr.responseText);
                    cb("Impossível obter lista de estandes. Tente novamente mais tarde");
                }
            }
        }

        xhr.send();
    }

    delete(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('DELETE', Configuration.getUrl() + "estande/" + id);

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve()
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível excluir estande. Tente novamente mais tarde");
                    }
                }
            }
            xhr.send();
        })
    }

    readById(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('GET', Configuration.getUrl() + "estande/" + id);

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        let e = JSON.parse(xhr.responseText)
                        resolve(
                            new Estande(
                                e.titulo,
                                e.curso,
                                e.periodo,
                                e.descricao,
                                e.areaTematica,
                                e.numero,
                                e.evento,
                                e.equipe,
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

            xhr.open('GET', Configuration.getUrl() + "estande");

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve(
                            JSON.parse(xhr.responseText)
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

    add(estande) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('POST', Configuration.getUrl() + "estande");
            xhr.setRequestHeader("Content-type", "application/json");

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText));
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível cadastrar estande.");
                    }
                }
            };

            estande = JSON.stringify(estande);
            console.log(estande);
            xhr.send(estande);
        })
    }

    update(estande) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();

            xhr.open('PUT', Configuration.getUrl() + "estande");
            xhr.setRequestHeader("Content-type", "application/json");

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText));
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível atualizar estande.");
                    }
                }
            };

            estande = JSON.stringify(estande);
            xhr.send(estande);
        })
    }
}