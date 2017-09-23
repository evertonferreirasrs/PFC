package localizae.net.br.controller.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Optional;

import localizae.net.br.controller.Fragments.EstandeFragment;
import localizae.net.br.controller.R;
import localizae.net.br.model.Estande;
import localizae.net.br.services.impl.EstandeService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

public class MapaActivity extends AppCompatActivity {

    private ImageView mapaImageView;
    private List<Estande> estandeList;
    private Integer[][] mapColisionMatrix;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.MAP_ACTIVITY_TAG)) {
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.MAP_ACTIVITY_TAG, "Recebeu resposta " + responseCode);
                switch (responseCode) {
                    case 200:
                        estandeList = (List<Estande>)intent.getSerializableExtra(Constants.DATA_KEY);
                        updateMatrix();
                        break;
                    default:
                        String text = ResponseCodeValidator.validateResponseCode(responseCode);
                        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    private void registerBroadcast() {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(broadcastReceiver, new IntentFilter(Constants.MAP_ACTIVITY_TAG));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_mapa);

        registerBroadcast();
        EstandeService es = new EstandeService();
        es.getAllBooth(this);

        mapaImageView = (ImageView) findViewById(R.id.mapa_id);
        mapaImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) motionEvent.getAxisValue(MotionEvent.AXIS_X);
                    int y = (int) motionEvent.getAxisValue(MotionEvent.AXIS_Y);
                    //Toast.makeText(MapaActivity.this, "X " + x + " Y " + y, Toast.LENGTH_SHORT).show();

                    final int estandeNumber = mapColisionMatrix[x][y];
                    Optional<Estande> estande = estandeList.stream().filter(e -> e.getNumero() == estandeNumber).findFirst();
                    if (estande.isPresent()) {
                        Toast.makeText(MapaActivity.this, estande.get().getTitulo(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MapaActivity.this, "X " + x + " Y " + y, Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Unregister broadcast receiver
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.unregisterReceiver(broadcastReceiver);
    }

    private void updateMatrix() {
        int width = mapaImageView.getMeasuredWidth();
        int height = mapaImageView.getMeasuredHeight();

        mapColisionMatrix = new Integer[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                mapColisionMatrix[i][j] = 0;
            }
        }

        for (Estande e : estandeList) {
            int minX = e.getPosicaoX();
            int maxX = e.getPosicaoX() + 50;

            int minY = e.getPosicaoY();
            int maxY = e.getPosicaoY() + 50;

            for (int i = minY; i < maxY; i++) {
                for (int j = minX; j < maxX; j++) {
                    mapColisionMatrix[i][j] = e.getNumero();
                }
            }
        }
    }
}
