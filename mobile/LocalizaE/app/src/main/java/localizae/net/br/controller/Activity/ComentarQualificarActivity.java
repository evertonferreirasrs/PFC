package localizae.net.br.controller.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import localizae.net.br.controller.R;

public class ComentarQualificarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar_qualificar);

        getSupportActionBar().setIcon(R.drawable.icone_logo);
    }
}
