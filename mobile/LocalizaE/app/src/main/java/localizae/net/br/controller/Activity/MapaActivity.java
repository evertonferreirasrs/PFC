package localizae.net.br.controller.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import localizae.net.br.controller.Fragments.EstandeFragment;
import localizae.net.br.controller.R;

public class MapaActivity extends AppCompatActivity {

    private ImageView botaoMapa;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

//        getSupportActionBar().setTitle(" Mapa");
        getSupportActionBar().hide();
        final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        botaoMapa = (ImageView) findViewById(R.id.mapa_id);


        botaoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MapaActivity.this);
                builder.setTitle("Estande: LocalizaÊ");
                builder.setMessage("Área Temática: Tecnologia");
                builder.setCancelable(false);
                builder.setPositiveButton("+ Informações", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        EstandeFragment estandeFragment = new EstandeFragment();
                        botaoMapa.setVisibility(View.GONE);
                        fragmentManager.beginTransaction().replace(R.id.mapa_fragment_id, estandeFragment).commit();

                    }
                });

                builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                alerta = builder.create();
                alerta.show();
            }
        });
    }
}
