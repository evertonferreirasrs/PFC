package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("usuario")
    Call<List<Usuario>> getByEmail(@Query("email") String email);

    @PATCH("usuario")
    Call<Usuario> alterarUser(@Body Usuario usuario);

}
