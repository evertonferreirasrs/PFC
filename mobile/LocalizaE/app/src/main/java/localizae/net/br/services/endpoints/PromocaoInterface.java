package localizae.net.br.services.endpoints;

import localizae.net.br.model.Promocao;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by marca on 14/10/2017.
 */

public interface PromocaoInterface {
    @GET("promocao")
    Call<Promocao> getAll();
}
