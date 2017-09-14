package localizae.net.br.controller.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

public class EstandeActivity extends AppCompatActivity {

    private TextView comentar_qualificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estande);

        getSupportActionBar().setIcon(R.drawable.icone_logo);

        comentar_qualificar = (TextView) findViewById(R.id.qualificar_comentar_id);

        comentar_qualificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EstandeActivity.this,ComentarQualificarActivity.class));

            }
        });
    }

}
