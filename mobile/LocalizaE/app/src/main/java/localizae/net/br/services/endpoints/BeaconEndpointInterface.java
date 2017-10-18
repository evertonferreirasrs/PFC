package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.Beacon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeaconEndpointInterface {

    @GET("beacon")
    Call<List<Beacon>> getBeacons();

}
