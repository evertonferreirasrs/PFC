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

    private static final String TAG = "USER_SERVICE";
    private UserEndpointInterface userServiceEndpoint = new WebRequest().retrofit.create(UserEndpointInterface.class);

    public void CreateUser(Usuario usuario, final Context context) {
        Call<Usuario> call = userServiceEndpoint.createUser(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.d(TAG, response.toString());

                Intent intent = new Intent(Constants.CREATE_USER_ACTIVITY_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, response.code());

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Log.d(TAG, t.toString());

                Intent intent = new Intent(Constants.CREATE_USER_ACTIVITY_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, Constants.RETROFIT_FAILURE);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }
        });

    }

}
