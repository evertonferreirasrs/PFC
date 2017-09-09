package localizae.net.br.services.endpoints;

import localizae.net.br.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserEndpointInterface {

    @GET("usuario/{userId}")
    Call<Usuario> getUser(@Path("userId") Long userId);

    @POST("usuario")
    Call<Usuario> createUser(@Body Usuario usuario);

}
