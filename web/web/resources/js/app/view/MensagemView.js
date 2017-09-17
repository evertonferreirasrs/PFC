class MensagemView{
    exibirMensagemDeErro(mensagem){
        swal('Erro!', mensagem, 'error')
    }

    exibirMensagemDeSucesso(titulo, mensagem){
        swal(titulo, mensagem, 'success')
    }
}