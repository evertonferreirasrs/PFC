package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.CriterioJurado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marca on 11/10/2017.
 */

public interface CriterioJuradoInterface {
    @GET("criterioJurado")
    Call<List<CriterioJurado>> getAll();

    @GET("criterioJurado")
    Call<List<CriterioJurado>> getByParameters(@Query("usuario") Long usuario);
}
