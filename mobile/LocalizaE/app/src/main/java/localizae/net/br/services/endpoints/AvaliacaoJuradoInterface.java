package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.AvaliacaoJurado;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by marca on 10/10/2017.
 */

public interface AvaliacaoJuradoInterface {
    @GET("avaliacaoJurado")
    Call<List<AvaliacaoJurado>> getAll();
}
