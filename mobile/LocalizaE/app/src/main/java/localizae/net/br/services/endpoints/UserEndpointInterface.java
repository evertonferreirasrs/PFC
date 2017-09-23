package localizae.net.br.services.endpoints;

import localizae.net.br.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface para conter os endpoints das chamadas a web service
 */
public interface UserEndpointInterface {

    // Metodo GET do retrofit para buscar usuarios
    @GET("usuario/{userId}")
    Call<Usuario> getUser(@Path("userId") Long userId);

    // Metodo POST retrofit para criar usuario
    @POST("usuario")
    Call<Usuario> createUser(@Body Usuario usuario);

    @POST("usuario/login")
    Call<Usuario> login(@Body Usuario usuario);

}
