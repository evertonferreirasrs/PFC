class EstandeService {
    delete(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('DELETE', Configuration.getUrl() + "estande/" + id)

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve()
                    } else {
                        // console.log(xhr.responseText)
                        reject("Impossível excluir estande. Tente novamente mais tarde")
                    }
                }
            }
            xhr.send();
        })
    }

    readById(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('GET', Configuration.getUrl() + "estande/" + id)

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
                                e.id,
                                e.posicaoX,
                                e.posicaoY
                            )
                        )
                    } else {
                        console.log(xhr.responseText)
                        reject("Impossível obter estande. Tente novamente mais tarde")
                    }
                }
            }

            xhr.send()
        })
    }

    readAll() {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('GET', Configuration.getUrl() + "estande")

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
                                    e.id,
                                    e.posicaoX,
                                    e.posicaoY
                                ))
                        )
                    } else {
                        // console.log(xhr.responseText);
                        reject("Impossível obter lista de estandes. Tente novamente mais tarde")
                    }
                }
            }

            xhr.send()
        })
    }

    add(estande) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('POST', Configuration.getUrl() + "estande")
            xhr.setRequestHeader("Content-type", "application/json")

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText))
                    } else {
                        reject(xhr.responseText)
                    }
                }
            }

            estande = JSON.stringify(estande)
            // console.log(estande);
            xhr.send(estande)
        })
    }

    update(estande) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('PUT', Configuration.getUrl() + "estande")
            xhr.setRequestHeader("Content-type", "application/json")
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText))
                    } else {
                        reject(xhr.responseText)
                    }
                }
            }
            estande = JSON.stringify(estande)
            xhr.send(estande)
        })
    }

    validate(estande) {
        if (estande.titulo == "") {
            throw "Campo título obrigatório."
        }

        if(estande.descricao == ""){
            throw "Campo descrição obrigatório."
        }

        if(estande.numero == "" || isNaN(estande.numero) || estande.numero < 0){
            throw "Campo número obrigatório e deve ser um número positivo."
        }

        if(estande.evento.id <= 0){
            throw "Campo evento obrigatório."
        }

        if(estande.posicaoX < 0 || estande.posicaoX == "" || isNaN(estande.posicaoX)){
            throw "Campo posição X obrigatório e deve ser um número positivo."
        }

        if(estande.posicaoY < 0 || estande.posicaoY == "" || isNaN(estande.posicaoY)){
            throw "Campo posição Y obrigatório e deve ser um número positivo."
        }
    }
}