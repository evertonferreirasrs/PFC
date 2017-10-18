package localizae.net.br.controller.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public ImageView mapaImageView;
    private View userPositionCircleView;
    private ProgressDialog progressDialog;
    private List<Estande> estandeList;
    private List<Beacon> beaconList;
    private Integer[][] mapColisionMatrix;
    private Timer timer;
    private Map<String, Integer> beacons = new HashMap();

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.MAP_ACTIVITY_TAG)) {
                progressDialog.dismiss();

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
        final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        registerBroadcast();

        progressDialog = new ProgressDialog(MapaActivity.this);
        progressDialog.setMessage(getString(R.string.loading_booths));
        progressDialog.show();

        EstandeService es = new EstandeService();
        es.getAllBooth(this);

        userPositionCircleView = (View) findViewById(R.id.user_position_circle);
        userPositionCircleView.setVisibility(View.INVISIBLE);

        mapaImageView = (ImageView) findViewById(R.id.mapa_id);
        mapaImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) motionEvent.getAxisValue(MotionEvent.AXIS_X);
                    int y = (int) motionEvent.getAxisValue(MotionEvent.AXIS_Y);
                    Estande estande = null;
                    for (Estande e : estandeList) {
                        if (e.getNumero() == mapColisionMatrix[x][y]) {
                            estande = e;
                            break;
                        }
                    }

                    if (estande != null) {
                        //Toast.makeText(MapaActivity.this, estande.getTitulo(), Toast.LENGTH_SHORT).show();
                        timer.cancel();
                        timer.purge();
                        timer = null;

                        mapaImageView.setVisibility(View.INVISIBLE);
                        userPositionCircleView.setVisibility(View.INVISIBLE);

                        Bundle args = new Bundle();
                        args.putSerializable("estandeId", estande.getId());

                        EstandeFragment estandeFragment = new EstandeFragment();
                        estandeFragment.setArguments(args);

                        FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
                            @Override
                            public void onBackStackChanged() {
                                restoreActivity();
                            }
                        };

                        FragmentManager supportFragmentManager = getSupportFragmentManager();
                        supportFragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);
                        supportFragmentManager.beginTransaction().replace(R.id.mapa_fragment_id, estandeFragment).addToBackStack(null).commit();
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

        mapColisionMatrix = null;

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
        final boolean allowScan[] = new boolean[1];
        allowScan[0] = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Permission granted start beacon finding
                final BroadcastReceiver beaconBr = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals(Constants.BEACON_SCAN_ACTIVITY_TAG)) {
                            allowScan[0] = true;
                            List<Beacon> returnedBeaconList = (List<Beacon>) intent.getSerializableExtra(Constants.DATA_KEY);
                            if (returnedBeaconList == null && returnedBeaconList.isEmpty()) {
                                Toast.makeText(context, context.getString(R.string.impossible_location_estimation), Toast.LENGTH_LONG).show();
                            } else {
                                beaconList = new ArrayList<Beacon>(returnedBeaconList);
                                //Collections.copy(beaconList, returnedBeaconList);
                                double[][] positions = new double[returnedBeaconList.size()][2];
                                double[] distances = new double[returnedBeaconList.size()];
                                int i = 0;
                                for (Beacon b : returnedBeaconList) {
                                    positions[i][0] = b.getxCoordinate();
                                    positions[i][1] = b.getyCoordinate();
                                    distances[i] = LocationService.getDistance(b.getRSSI(), b.getTxPower());
                                    Log.d("BATATA", "MAC:" + b.getMAC() + " RSSI: " + b.getRSSI() + " TxPower: " + b.getTxPower() + "SCAN RECORD" + Arrays.toString(b.getScanRecord()));
                                    i++;
                                }
                                // X - 0
                                // Y - 1
                                double[] position = LocationService.detectUserPosition(positions, distances);
                                Log.d("BATATA", "positions: " + Arrays.toString(positions));
                                Log.d("BATATA", "distances: " + Arrays.toString(distances));
                                Log.d("BATATA", "user coordniate: " + Arrays.toString(position));
                                RelativeLayout.LayoutParams mapParams = (RelativeLayout.LayoutParams) mapaImageView.getLayoutParams();

                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(userPositionCircleView.getWidth(), userPositionCircleView.getHeight());
                                params.leftMargin = (int) ((mapParams.leftMargin + position[0] + 0.5));
                                params.topMargin = (int) ((mapParams.topMargin + position[1] + 0.5));
                                userPositionCircleView.setLayoutParams(params);

                                if (userPositionCircleView.getVisibility() == View.INVISIBLE) {
                                    userPositionCircleView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                };

                if (allowScan[0]) {
                    allowScan[0] = false;
                    LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MapaActivity.this);
                    lbm.registerReceiver(beaconBr, new IntentFilter(Constants.BEACON_SCAN_ACTIVITY_TAG));

                    BeaconScanner.scanBeacon(MapaActivity.this, beaconList);
                }
            }
        }, 60, Constants.BEACON_SCAN_PERIOD);
    }

    private void updateMatrix() {
        int width = mapaImageView.getWidth();
        int height = mapaImageView.getHeight();

        mapColisionMatrix = new Integer[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapColisionMatrix[i][j] = 0;
            }
        }

        for (Estande e : estandeList) {
            int minX = e.getPosicaoX();
            int maxX = e.getPosicaoX() + 80;

            int minY = e.getPosicaoY();
            int maxY = e.getPosicaoY() + 110;

            for (int i = minX; i < maxX; i++) {
                for (int j = minY; j < maxY; j++) {
                    mapColisionMatrix[i][j] = e.getNumero();
                }
            }
        }
    }

    private void restoreActivity() {
        mapaImageView.setVisibility(View.VISIBLE);
        startBeaconScan();
    }
}