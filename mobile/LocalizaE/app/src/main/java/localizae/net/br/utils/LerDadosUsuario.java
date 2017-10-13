package localizae.net.br.utils;

import android.content.Context;
import android.content.SharedPreferences;

import localizae.net.br.controller.R;
import localizae.net.br.model.TipoUsuario;
import localizae.net.br.model.Usuario;


public class LerDadosUsuario {

    public static Usuario lerDados(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences("UsuarioLogado", 0);

        String usuarioId = sharedPref.getString(context.getString(R.string.chave_logado_id), "id não encontrado");
        String usuarioNome = sharedPref.getString(context.getString(R.string.chave_logado_nome), "nome não encontrado");
        String usuarioEmail = sharedPref.getString(context.getString(R.string.chave_logado_email), "email não encontrado");
        String usuarioHash = sharedPref.getString(context.getString(R.string.chave_logado_hash), "hash não encontrado");
        String usuarioSenha = sharedPref.getString(context.getString(R.string.chave_logado_senha), "senha não encontrado");
        String usuarioTipo = sharedPref.getString(context.getString(R.string.chave_logado_tipo), "tipo não encontrado");
        String usuarioSituacao = sharedPref.getString(context.getString(R.string.chave_logado_situacao), "situacao não encontrado");

        Usuario usuario = new Usuario();
        usuario.setId(Long.parseLong(usuarioId));
        usuario.setNome(usuarioNome);
        usuario.setEmail(usuarioEmail);
        usuario.setHash(usuarioHash);
        usuario.setSenha(usuarioSenha);
        usuario.setSituacao(usuarioSituacao);
        usuario.setTipoUsuario(new TipoUsuario(Long.parseLong(usuarioTipo)));

        return usuario;
    }
}
