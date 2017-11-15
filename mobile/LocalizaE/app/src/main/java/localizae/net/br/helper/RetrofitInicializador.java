package localizae.net.br.helper;

import localizae.net.br.services.endpoints.AvaliacaoJuradoInterface;
import localizae.net.br.services.endpoints.AvaliacaoVisitanteEndpointInterface;
import localizae.net.br.services.endpoints.CriterioJuradoInterface;
import localizae.net.br.services.endpoints.EstandeEndpointInterface;
import localizae.net.br.services.endpoints.UserEndpointInterface;
import retrofit2.Retrofit;

/**
 * Created by Everton on 13/10/2017.
 */

public class RetrofitInicializador {
    private final Retrofit retrofit;

    public RetrofitInicializador() {
        retrofit = WebRequest.getRetrofitInstance();
    }

    public CriterioJuradoInterface getCriterioJuradoService() {
        return retrofit.create(CriterioJuradoInterface.class);
    }

    public AvaliacaoJuradoInterface getAvaliacaoJuradoService() {
        return retrofit.create(AvaliacaoJuradoInterface.class);
    }

    public EstandeEndpointInterface getEstandeService() {
        return retrofit.create(EstandeEndpointInterface.class);
    }

    public AvaliacaoVisitanteEndpointInterface getAvaliacaoVisitanteService() {
        return retrofit.create(AvaliacaoVisitanteEndpointInterface.class);
    }

    public UserEndpointInterface getUsuarioService() {
        return retrofit.create(UserEndpointInterface.class);
    }
}
