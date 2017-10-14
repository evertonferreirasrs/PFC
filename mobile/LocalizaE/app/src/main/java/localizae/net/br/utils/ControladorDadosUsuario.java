package localizae.net.br.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.TipoUsuario;
import localizae.net.br.model.Usuario;

public class ControladorDadosUsuario {

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
