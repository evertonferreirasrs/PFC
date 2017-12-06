package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.Estande;
import localizae.net.br.model.Estatistica;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StatisticEndpointInterface {

    @POST("estatistica")
    Call<Estatistica> storeUserPosition(@Body Estatistica estatistica);

}
