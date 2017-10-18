package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.AvaliacaoJurado;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by marca on 10/10/2017.
 */

public interface AvaliacaoJuradoInterface {
    @GET("avaliacaoJurado")
    Call<List<AvaliacaoJurado>> getAll();

    @GET("avaliacaoJurado")
    Call<List<AvaliacaoJurado>> getByParameter(@Query("usuario") Long usuario, @Query("criterio") Long criterio, @Query("estande") Long estande);

    @PUT("avaliacaoJurado")
    Call<AvaliacaoJurado> put(@Body AvaliacaoJurado avaliacao);

    @POST("avaliacaoJurado")
    Call<AvaliacaoJurado> post(@Body AvaliacaoJurado avaliacao);
}
