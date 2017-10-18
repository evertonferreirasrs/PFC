package localizae.net.br.services.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import localizae.net.br.helper.WebRequest;
import localizae.net.br.model.Beacon;
import localizae.net.br.services.endpoints.BeaconEndpointInterface;
import localizae.net.br.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeaconService {

    private BeaconEndpointInterface beaconServiceEndpoint = WebRequest.getRetrofitInstance().create(BeaconEndpointInterface.class);

    public void getBeacons(final Context context) {
        Call<List<Beacon>> call = beaconServiceEndpoint.getBeacons();
        call.enqueue(new Callback<List<Beacon>>() {

            @Override
            public void onResponse(Call<List<Beacon>> call, Response<List<Beacon>> response) {
                Log.d(Constants.BEACON_SERVICE_TAG, response.toString());

                Intent intent = new Intent(Constants.BEACON_SCANNER_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, response.code());
                intent.putExtra(Constants.DATA_KEY, (Serializable) response.body());

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<List<Beacon>> call, Throwable t) {
                Log.d(Constants.BEACON_SERVICE_TAG, t.toString());

                Intent intent = new Intent(Constants.BEACON_SCANNER_TAG);
                intent.putExtra(Constants.RESPONSE_CODE_KEY, Constants.RETROFIT_FAILURE);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }
        });
    }

}
