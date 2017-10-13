package localizae.net.br.controller.Activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.litesuits.bluetooth.LiteBluetooth;
import com.litesuits.bluetooth.scan.PeriodScanCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import localizae.net.br.controller.Fragments.EstandeFragment;
import localizae.net.br.controller.R;
import localizae.net.br.helper.Location.BeaconScanner;
import localizae.net.br.model.Beacon;
import localizae.net.br.model.Estande;
import localizae.net.br.services.LocationService;
import localizae.net.br.services.impl.EstandeService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

public class MapaActivity extends AppCompatActivity {

    private ImageView mapaImageView;
    private List<Estande> estandeList;
    private Integer[][] mapColisionMatrix;
    private Timer timer;
    private Map<String, Integer> beacons = new HashMap();

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.MAP_ACTIVITY_TAG)) {
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.MAP_ACTIVITY_TAG, "Recebeu resposta " + responseCode);
                switch (responseCode) {
                    case 200:
                        estandeList = (List<Estande>) intent.getSerializableExtra(Constants.DATA_KEY);
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

        if (ActivityCompat.checkSelfPermission(MapaActivity.this, Manifest.permission.BLUETOOTH) == PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapaActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.BLUETOOTH_PRIVILEGED, Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.REQUEST_PERMISSION_CODE);
        }

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

                    Estande estande = null;
                    for (Estande e : estandeList) {
                        if (e.getNumero() == mapColisionMatrix[x][y]) {
                            estande = e;
                            break;
                        }
                    }

                    if (estande != null) {
                        Toast.makeText(MapaActivity.this, estande.getTitulo(), Toast.LENGTH_SHORT).show();
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

        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constants.REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startBeaconScan();
                }
                break;
        }
    }

    private void startBeaconScan() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Permission granted start beacon finding
                final boolean allowScan[] = new boolean[1];
                allowScan[0] = true;
                final BroadcastReceiver beaconBr = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals(Constants.BEACON_SCAN_ACTIVITY_TAG)) {
                            allowScan[0] = true;
                            List<Beacon> returnedBeaconList = (List<Beacon>) intent.getSerializableExtra(Constants.DATA_KEY);
                            if (returnedBeaconList == null && returnedBeaconList.isEmpty()) {
                                Toast.makeText(context, context.getString(R.string.impossible_location_estimation), Toast.LENGTH_LONG).show();
                            } else {
                                double[][] positions = new double[returnedBeaconList.size()][2];
                                double[] distances = new double[returnedBeaconList.size()];
                                int i = 0;
                                for (Beacon b : returnedBeaconList) {
                                    positions[i][0] = b.getxCoordinate();
                                    positions[i][1] = b.getyCoordinate();
                                    distances[i] = LocationService.getDistance(b.getRSSI(), b.getTxPower());
                                    i++;
                                }
                                LocationService.detectUserPosition(positions, distances);
                            }
                        }
                    }
                };

                if (allowScan[0]) {
                    allowScan[0] = false;
                    LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MapaActivity.this);
                    lbm.registerReceiver(beaconBr, new IntentFilter(Constants.BEACON_SCAN_ACTIVITY_TAG));

                    BeaconScanner.scanBeacon(MapaActivity.this);
                }
            }
        }, 60, Constants.BEACON_SCAN_PERIOD);
    }

    private void updateMatrix() {
        int width = mapaImageView.getWidth();
        int height = mapaImageView.getHeight();

        mapColisionMatrix = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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
