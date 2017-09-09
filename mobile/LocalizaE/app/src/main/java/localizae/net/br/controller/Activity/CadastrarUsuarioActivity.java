package localizae.net.br.controller.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.impl.UserService;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        getSupportActionBar().setTitle("Cadastrar");

        context = this;

        Button cadastrar_id = (Button) findViewById(R.id.botao_cadastar_id);
        cadastrar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(CadastrarUsuarioActivity.this,LoginActivity.class));
                //Realizar o cadastro
                String nome = ((EditText) findViewById(R.id.campo_nome_id)).getText().toString();
                String email = ((EditText) findViewById(R.id.campo_email_id)).getText().toString();
                String senha = ((EditText) findViewById(R.id.campo_senha_id)).getText().toString();
                String confirmaSenha = ((EditText) findViewById(R.id.campo_confirmar_senha_id)).getText().toString();

                if (senha.equals(confirmaSenha)) {
                    UserService userService = new UserService();
                    userService.CreateUser(new Usuario(nome, email, senha));
                } else {
                    Toast.makeText(context, getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
