class EventoService {
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
                                e.dataHoraEventoInicio,
                                e.dataHoraEventoFim,
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
}