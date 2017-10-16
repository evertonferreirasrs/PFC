package localizae.net.br.helper.Location;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.litesuits.bluetooth.LiteBluetooth;
import com.litesuits.bluetooth.scan.PeriodScanCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import localizae.net.br.controller.Activity.MapaActivity;
import localizae.net.br.controller.R;
import localizae.net.br.model.Beacon;
import localizae.net.br.model.Estande;
import localizae.net.br.services.impl.BeaconService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

public class BeaconScanner {

    public static void scanBeacon(final Context context, List<Beacon> beaconList) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context receoverContext, Intent intent) {
                if (intent.getAction().equals(Constants.BEACON_SCANNER_TAG)) {
                    int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                    Log.d(Constants.BEACON_SCANNER_TAG, "Recebeu resposta " + responseCode);
                    switch (responseCode) {
                        case 200:
                            List<Beacon> returnedBeaconList = (List<Beacon>) intent.getSerializableExtra(Constants.DATA_KEY);
                            if (returnedBeaconList == null && returnedBeaconList.isEmpty()) {
                                Toast.makeText(context, context.getString(R.string.impossible_location_estimation), Toast.LENGTH_LONG).show();
                            } else {
                                updateBeaconData(returnedBeaconList, context);
                            }
                            break;
                        default:
                            String text = ResponseCodeValidator.validateResponseCode(responseCode);
                            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };

        if(beaconList == null || beaconList.isEmpty()) {
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
            lbm.registerReceiver(broadcastReceiver, new IntentFilter(Constants.BEACON_SCANNER_TAG));

            BeaconService bs = new BeaconService();
            bs.getBeacons(context);
        } else {
            updateBeaconData(beaconList, context);
        }
    }

    private static void updateBeaconData(final List<Beacon> beaconList, final Context context) {
        LiteBluetooth liteBluetooth = new LiteBluetooth(context);
        liteBluetooth.enableBluetoothIfDisabled((Activity) context, 1);
        liteBluetooth.startLeScan(new PeriodScanCallback(Constants.BLE_SCAN_TIMEOUT) {
            @Override
            public void onScanTimeout() {
                Intent intent = new Intent(Constants.BEACON_SCAN_ACTIVITY_TAG);
                intent.putExtra(Constants.DATA_KEY, (Serializable) beaconList);

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
                lbm.sendBroadcast(intent);
            }

            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Log.d("BATATA", "device: " + device.getName() + "  mac: " + device.getAddress()
                        + "  rssi: " + rssi + "  scanRecord: " + Arrays.toString(scanRecord));
                // Based on  iBeacon standard
                // 0  - 8  9 bytes  - iBeacon prefix
                // 9  - 24 16 bytes - Proximity UUID
                // 25 - 26 2 bytes  - Major
                // 27 - 28 2 bytes  - Minor
                // 29      1 byte   - TxPower ?????
                Integer txPower = new Byte(scanRecord[31]).intValue();
                for (Beacon b : beaconList) {
                    if (b.getMAC().equals(device.getAddress())) {
                        b.setRSSI(rssi);
                        b.setTxPower(txPower);
                        b.setScanRecord(scanRecord);
                    }
                }
            }
        });
    }
}
