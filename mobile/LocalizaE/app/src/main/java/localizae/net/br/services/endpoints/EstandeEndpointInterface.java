package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.AvaliacaoVisitante;
import localizae.net.br.model.Estande;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EstandeEndpointInterface {

    @GET("estande")
    Call<List<Estande>> getAllBooth();

    @GET("estande/{estandeId}")
    Call<Estande> getEstande (@Path("estandeId") Long estandeId);
}
