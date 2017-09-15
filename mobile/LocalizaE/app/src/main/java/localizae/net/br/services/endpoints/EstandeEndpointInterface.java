package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.Estande;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EstandeEndpointInterface {

    @GET("estande")
    Call<List<Estande>> getAllBooth();

}
