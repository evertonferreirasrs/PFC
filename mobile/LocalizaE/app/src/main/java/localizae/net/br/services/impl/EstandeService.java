package localizae.net.br.services.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import localizae.net.br.helper.WebRequest;
import localizae.net.br.model.Estande;
import localizae.net.br.services.endpoints.EstandeEndpointInterface;
import localizae.net.br.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstandeService {

    private EstandeEndpointInterface boothServiceEndpoint = WebRequest.getRetrofitInstance().create(EstandeEndpointInterface.class);

    public void getAllBooth(final Context context) {
        Call<List<Estande>> call = boothServiceEndpoint.getAllBooth();
        call.enqueue(new Callback<List<Estande>>() {

            @Override
            public void onResponse(Call<List<Estande>> call, Response<List<Estande>> response) {
                Log.d(Constants.BOOTH_SERVICE_TAG, response.toString());

                Intent intent = new Intent(Constants.MAP_ACTIVITY_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, response.code());
                intent.putExtra(Constants.DATA_KEY, (Serializable) response.body());

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<List<Estande>> call, Throwable t) {
                Log.d(Constants.USER_SERVICE_TAG, t.toString());

                Intent intent = new Intent(Constants.CREATE_USER_ACTIVITY_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, Constants.RETROFIT_FAILURE);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }
        });
    }

}
