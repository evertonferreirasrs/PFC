package localizae.net.br.services.impl;

import android.util.Log;

import localizae.net.br.helper.WebRequest;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.endpoints.UserEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    private static final String TAG = "USER_SERVICE";
    private UserEndpointInterface userServiceEndpoint = new WebRequest().retrofit.create(UserEndpointInterface.class);

    public void CreateUser(Usuario usuario) {

        Call<Usuario> call = userServiceEndpoint.createUser(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.d(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });

    }

}
