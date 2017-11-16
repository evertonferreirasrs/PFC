package localizae.net.br.services.endpoints;

import java.util.List;

import localizae.net.br.model.Promocao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static android.R.attr.path;

/**
 * Created by marca on 14/10/2017.
 */

public interface PromocaoInterface {
    @GET("promocao")
    Call<List<Promocao>> getAll();

    @GET("promocao/{id}")
    Call<Promocao> getById(@Path("id") Long id);

    @DELETE("promocao/{id}")
    Call<Void> delete(@Path("id") Long id);

    @POST("promocao")
    Call<Void> create(@Body Promocao promocao);
}
