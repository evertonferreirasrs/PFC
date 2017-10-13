package localizae.net.br.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import localizae.net.br.controller.R;
import localizae.net.br.model.TipoUsuario;
import localizae.net.br.model.Usuario;


public class ArmazenadorDadosUsuario {

    public static void aramazenarDados(Usuario usuario, Context context) {

        SharedPreferences sharedPref =  context.getSharedPreferences("UsuarioLogado", 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(context.getString(R.string.chave_logado_id), String.valueOf(usuario.getId()));
        editor.putString(context.getString(R.string.chave_logado_nome), usuario.getNome());
        editor.putString(context.getString(R.string.chave_logado_email), usuario.getEmail());
        editor.putString(context.getString(R.string.chave_logado_hash), usuario.getHash());
        editor.putString(context.getString(R.string.chave_logado_senha), usuario.getSenha());
        editor.putString(context.getString(R.string.chave_logado_tipo), String.valueOf(usuario.getTipoUsuario().getId()));
        editor.putString(context.getString(R.string.chave_logado_situacao), usuario.getSituacao());

        editor.commit();

    }
}
