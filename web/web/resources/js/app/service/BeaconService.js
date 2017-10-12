class BeaconService {
    readById(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('GET', Configuration.getUrl() + "beacon/"+id)

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        let b = JSON.parse(xhr.responseText)
                        resolve(
                            new Beacon(
                                b.mac,
                                b.xCoordinate,
                                b.yCoordinate,
                                b.id
                            )
                        )
                    } else {
                        reject("Impossível obter beacon. Tente novamente mais tarde")
                    }
                }
            }

            xhr.send()
        })
    }

    readAll() {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('GET', Configuration.getUrl() + "beacon")

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve(
                            JSON.parse(xhr.responseText)
                            .map(b => new Evento(
                                b.mac,
                                b.xCoordinate,
                                b.yCoordinate,
                                b.id
                            ))
                        )
                    } else {
                        reject("Impossível obter lista de beacons. Tente novamente mais tarde")
                    }
                }
            }

            xhr.send()
        })
    }

    delete(id) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('DELETE', Configuration.getUrl() + "beacon/"+id)

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve("Beacon Excluído.")
                    } else {
                        reject("Impossível obter lista de beacons. Tente novamente mais tarde")
                    }
                }
            }

            xhr.send()
        })
    }

    add(beacon) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('POST', Configuration.getUrl() + "beacon")
            xhr.setRequestHeader("Content-type", "application/json")

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText))
                    } else {
                        reject(xhr.responseText)
                    }
                }
            };

            beacon = JSON.stringify(beacon)
            xhr.send(beacon)
        })
    }

    update(beacon) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('PUT', Configuration.getUrl() + "beacon")
            xhr.setRequestHeader("Content-type", "application/json")

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText))
                    } else {
                        reject(xhr.responseText)
                    }
                }
            };

            beacon = JSON.stringify(beacon);
            xhr.send(beacon);
        })
    }
}