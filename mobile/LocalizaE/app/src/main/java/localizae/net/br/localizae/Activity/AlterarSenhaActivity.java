package localizae.net.br.localizae.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import localizae.net.br.localizae.R;

public class AlterarSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        getSupportActionBar().setTitle("Alterar Senha");

    }
}