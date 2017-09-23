package localizae.net.br.controller.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import localizae.net.br.controller.R;

public class LoginActivity extends AppCompatActivity {

    private TextView texto_esqueceu_senha;
    private TextView texto_criar_conta;
    private Button botao_entrar;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        texto_esqueceu_senha = (TextView) findViewById(R.id.texto_esqueceu_senha_id);
        texto_criar_conta = (TextView) findViewById(R.id.texto_criar_conta_id);
        botao_entrar = (Button) findViewById(R.id.botao_entrar_id);

        botao_entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MenuActivity.class));
            }
        });

        texto_esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,RecuperarSenhaActivity.class));

//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setTitle("Recuperar Senha");
//                builder.setMessage("Entre com seu email cadastrado no sistema:");
//
//                EditText editText = new EditText(LoginActivity.this);
//                builder.setView(editText);
//
//                builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Toast.makeText(LoginActivity.this, "Senha enviada para seu email!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                    }
//                });
//
//                alerta = builder.create();
//                alerta.show();
            }
        });

        texto_criar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,CadastrarUsuarioActivity.class));
            }
        });
    }

}
