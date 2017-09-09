package localizae.net.br.controller.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import localizae.net.br.controller.R;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private Button cadastrar_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        getSupportActionBar().setTitle("Cadastrar");

        cadastrar_id = (Button) findViewById(R.id.botao_cadastar_id);

        cadastrar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CadastrarUsuarioActivity.this,LoginActivity.class));
            }
        });

    }
}
