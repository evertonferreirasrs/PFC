package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.Estande;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Everton on 13/10/2017.
 */

public interface StandEndpointInterface {

    @GET("estande/{estandeId}")
    Call<Estande> getEstande (@Path("estandeId") Long estandeId);

}
