package localizae.net.br.controller.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import localizae.net.br.controller.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private Button enviar_id;
    private Button voltar_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        getSupportActionBar().hide();

        enviar_id = (Button) findViewById(R.id.botao_enviar_id);

        enviar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RecuperarSenhaActivity.this,LoginActivity.class));
            }
        });

        voltar_id = (Button) findViewById(R.id.botao_voltar_id);

        voltar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RecuperarSenhaActivity.this,LoginActivity.class));
            }
        });
    }
}
