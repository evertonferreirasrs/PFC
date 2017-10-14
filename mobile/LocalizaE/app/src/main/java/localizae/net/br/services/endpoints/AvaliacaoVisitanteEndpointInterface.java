package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.AvaliacaoVisitante;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lyan on 14/10/2017.
 */

public interface AvaliacaoVisitanteEndpointInterface {

    @POST("avaliacaoVisitante")
    Call<AvaliacaoVisitante> avaliacao (@Body AvaliacaoVisitante avaliacaoVisitante);

    @GET("avaliacaoVisitante")
    Call<List<AvaliacaoVisitante>> getAvaliacoesVisitanteByUser (@Query("usuario") Long userId);
}
