package localizae.net.br.services.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import localizae.net.br.helper.WebRequest;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.endpoints.UserEndpointInterface;
import localizae.net.br.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    // Pega uma instancia do retrofit para fazer a comunicacao com o web service
    private UserEndpointInterface userServiceEndpoint = new WebRequest().getRetrofitInstance().create(UserEndpointInterface.class);

    /**
     * Metodo para criar usuario
     * @param usuario objeto com os dados de usuario para serem usados na criacao
     * @param context contexto da chamada do metodo de criacao
     */
    public void CreateUser(Usuario usuario, final Context context) {
        // Cria uma chamada para para o endpoint
        Call<Usuario> call = userServiceEndpoint.createUser(usuario);
        // Coloca na fila do retrofit para realizar a comunicacao
        call.enqueue(new Callback<Usuario>() {
            //Implementacao da callback de resposta em caso de qualquer resposta
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.d(Constants.USER_SERVICE_TAG, response.toString());

                Intent intent = new Intent(Constants.CREATE_USER_ACTIVITY_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, response.code());

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            // Implementacao em caso de erro, o erro deve ser tratado aqui
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d(Constants.USER_SERVICE_TAG, t.toString());

                Intent intent = new Intent(Constants.CREATE_USER_ACTIVITY_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, Constants.RETROFIT_FAILURE);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }
        });

    }

}
