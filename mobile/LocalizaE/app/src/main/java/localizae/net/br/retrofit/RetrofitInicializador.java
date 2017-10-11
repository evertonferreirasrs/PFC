package localizae.net.br.retrofit;

import localizae.net.br.model.AvaliacaoJurado;
import localizae.net.br.services.endpoints.AvaliacaoJuradoInterface;
import localizae.net.br.services.endpoints.CriterioJuradoInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marca on 10/10/2017.
 */

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://18.220.190.201:8080/localizae/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public AvaliacaoJuradoInterface getAvaliacaoJuradoService(){
        return retrofit.create(AvaliacaoJuradoInterface.class);
    }

    public CriterioJuradoInterface getCriterioJuradoService(){
        return retrofit.create(CriterioJuradoInterface.class);
    }
}
