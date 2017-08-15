class HttpService {
    get(url) {
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('get', url)

            xhr.onreadystatechange = () => {
                //Se a requisicao estiver concluida e a resposta estivar pronta
                if (xhr.readyState == 4) {
                    //Se a requisico foi executada com sucesso
                    if (xhr.status == 200) {
                        resolve(JSON.parse(xhr.responseText))
                    } else {
                        console.log(xhr.responseText);
                        reject("Impossível obter lista de usuários. Tente novamente mais tarde")
                    }
                }
            }

            xhr.send()
        })
    }

    delete(url){
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('DELETE', url)

            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        resolve(true)
                    } else {
                        reject(xhr.responseText)
                    }
                }
            }

            xhr.send()
        })
    }

    post(url, data){
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('POST', url)
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
            data = JSON.stringify(data)
            xhr.send(data)
        })
    }

    put(url, data){
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest()

            xhr.open('PUT', url)
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
            data = JSON.stringify(data)
            xhr.send(data)
        })
    }
}