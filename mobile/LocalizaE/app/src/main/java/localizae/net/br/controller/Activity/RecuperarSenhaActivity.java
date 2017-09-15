package localizae.net.br.controller.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import localizae.net.br.controller.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private Button enviar_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        getSupportActionBar().setIcon(R.drawable.icone_logo);

        enviar_id = (Button) findViewById(R.id.botao_enviar_id);

        enviar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RecuperarSenhaActivity.this,LoginActivity.class));
            }
        });
    }
}
