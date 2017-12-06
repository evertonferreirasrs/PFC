package localizae.net.br.services.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import localizae.net.br.helper.WebRequest;
import localizae.net.br.model.Estatistica;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.endpoints.StatisticEndpointInterface;
import localizae.net.br.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstatisticaService {

    private StatisticEndpointInterface statisticServiceEndpoint = WebRequest.getRetrofitInstance().create(StatisticEndpointInterface.class);


    public void storeUserPosition(Estatistica estatistica, final Context context) {
        // Cria uma chamada para para o endpoint
        Call<Estatistica> call = statisticServiceEndpoint.storeUserPosition(estatistica);
        // Coloca na fila do retrofit para realizar a comunicacao
        call.enqueue(new Callback<Estatistica>() {
            //Implementacao da callback de resposta em caso de qualquer resposta
            @Override
            public void onResponse(Call<Estatistica> call, Response<Estatistica> response) {
                Log.d(Constants.STATISTIC_SERVICE_TAG, response.toString());
            }

            // Implementacao em caso de erro, o erro deve ser tratado aqui
            @Override
            public void onFailure(Call<Estatistica> call, Throwable t) {
                Log.d(Constants.STATISTIC_SERVICE_TAG, t.toString());
            }
        });
    }
}
