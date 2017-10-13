package localizae.net.br.services.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import localizae.net.br.helper.WebRequest;
import localizae.net.br.model.AvaliacaoVisitante;
import localizae.net.br.services.endpoints.BoothEndpointInterface;
import localizae.net.br.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoothService {
    private BoothEndpointInterface boothEndpointInterface = WebRequest.getRetrofitInstance().create(BoothEndpointInterface.class);

    public void avaliacao(AvaliacaoVisitante avaliacaoVisitante, final Context context){
        Call<AvaliacaoVisitante> call = boothEndpointInterface.avaliacao(avaliacaoVisitante);
        call.enqueue(new Callback<AvaliacaoVisitante>() {
            @Override
            public void onResponse(Call<AvaliacaoVisitante> call, Response<AvaliacaoVisitante> response) {
                Log.d(Constants.USER_SERVICE_TAG, response.toString());

                Intent intent = new Intent(Constants.COMENTS_TAG    );
                intent.putExtra(Constants.RESPONSE_CODE_KEY, response.code());

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<AvaliacaoVisitante> call, Throwable t) {
                Log.d(Constants.USER_SERVICE_TAG, t.toString());

                Intent intent = new Intent(Constants.COMENTS_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, Constants.RETROFIT_FAILURE);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }
        });
    }

    public void getAvaliacoesUsuario(Long userId, final Context context){
        Call<List<AvaliacaoVisitante>> call = boothEndpointInterface.getAvaliacoesVisitanteByUser(userId);
        call.enqueue(new Callback<List<AvaliacaoVisitante>>() {
            @Override
            public void onResponse(Call<List<AvaliacaoVisitante>> call, Response<List<AvaliacaoVisitante>> response) {
                Log.d(Constants.USER_SERVICE_TAG, response.toString());

                Intent intent = new Intent(Constants.MY_AVALIATIONS_FRAGMENT_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, response.code());
                intent.putExtra(Constants.DATA_KEY, (Serializable) response.body());

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<List<AvaliacaoVisitante>> call, Throwable t) {
                Log.d(Constants.USER_SERVICE_TAG, t.toString());

                Intent intent = new Intent(Constants.MY_AVALIATIONS_FRAGMENT_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, Constants.RETROFIT_FAILURE);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }
        });
    }
}
