package localizae.net.br.Retrofit;

import localizae.net.br.services.endpoints.BoothEndpointInterface;
import localizae.net.br.services.endpoints.StandEndpointInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Everton on 13/10/2017.
 */

public class RetrofitInicializador {
    private final Retrofit retrofit;

    public RetrofitInicializador (){
        retrofit = new Retrofit.Builder().baseUrl("http://18.220.190.201:8080/localizae/").addConverterFactory(GsonConverterFactory.create()).build();

    }

    public BoothEndpointInterface getAvaliacaoVisitanteService(){
        return retrofit.create(BoothEndpointInterface.class);
    }

    public StandEndpointInterface getEstandeService(){
        return retrofit.create(StandEndpointInterface.class);
    }
}
