package localizae.net.br.services.endpoints;

import localizae.net.br.model.AvaliacaoVisitante;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BoothEndpointInterface {

    @POST("avaliacaoVisitante")
    Call<AvaliacaoVisitante> avaliacao (@Body AvaliacaoVisitante avaliacaoVisitante);

}
