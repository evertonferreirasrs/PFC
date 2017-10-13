package localizae.net.br.utils;

import android.content.Context;

import localizae.net.br.model.Usuario;

/**
 * Created by Heid on 13/10/2017.
 */

public class VerificadorUsuario {

    private Usuario usuario;
    private static VerificadorUsuario instances;

    private VerificadorUsuario(Context context){
        this.usuario = LerDadosUsuario.lerDados(context);
    }

    public static VerificadorUsuario getInstances(Context context){
        if(instances == null){
            instances = new VerificadorUsuario(context);
        }

        return instances;

    }

    public boolean isJurado(){
        if(this.usuario.getTipoUsuario().getId() == Constants.TIPO_USUARIO_JURADO){
            return true;
        }else{
            return false;
        }
    }

    public boolean isAdministrador(){
        if(this.usuario.getTipoUsuario().getId() == Constants.TIPO_USUARIO_ADMINISTRADOR){
            return true;
        }else{
            return false;
        }
    }

    public boolean isExpositor(){
        if(this.usuario.getTipoUsuario().getId() == Constants.TIPO_USUARIO_EXPOSITOR){
            return true;
        }else{
            return false;
        }
    }

    public boolean isVisitante(){
        if(this.usuario.getTipoUsuario().getId() == Constants.TIPO_USUARIO_VISITANTE){
            return true;
        }else{
            return false;
        }
    }
}
