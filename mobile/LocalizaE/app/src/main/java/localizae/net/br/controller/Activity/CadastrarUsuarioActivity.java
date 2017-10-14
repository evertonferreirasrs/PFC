package localizae.net.br.controller.Activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.TipoUsuario;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.impl.UserService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private Button voltar_id;
    // Variavel para armazenar o contexto
    private Context context;

    // Variavel broadcastReceiber para receber chamadas de outras telas/classes
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Identifica a origem do intent
            if (intent.getAction().equals(Constants.CREATE_USER_ACTIVITY_TAG)) {
                // Pega o codigo de resposta recebido da service
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.CREATE_USER_ACTIVITY_TAG, "Recebeu resposta " + responseCode);
                // Switch case pelo codigo de resposta
                switch (responseCode) {
                    case 200:
                        // 200 sempre é sucesso enviado pelo servidor
                        Toast.makeText(context, getString(R.string.account_created), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CadastrarUsuarioActivity.this,LoginActivity.class));
                        break;
                    default:
                        // Caso outro erro aconteca
                        String text = ResponseCodeValidator.validateResponseCode(responseCode);
                        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        getSupportActionBar().hide();

        // Salva o contexto
        context = this;

        Button cadastrar_id = (Button) findViewById(R.id.botao_cadastar_id);
        cadastrar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(CadastrarUsuarioActivity.this,LoginActivity.class));

                //Realizar o cadastro recebendo informacoes do usuario
                String nome = ((EditText) findViewById(R.id.campo_nome_id)).getText().toString();
                String email = ((EditText) findViewById(R.id.campo_email_id)).getText().toString();
                String senha = ((EditText) findViewById(R.id.campo_senha_id)).getText().toString();
                String confirmaSenha = ((EditText) findViewById(R.id.campo_confirmar_senha_id)).getText().toString();

                // Validacao basica do campo
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
                    Toast.makeText(context, getString(R.string.fill_entries), Toast.LENGTH_LONG).show();
                } else {
                    // Validacao de senha
                    if (senha.equals(confirmaSenha)) {
                        // Registrar o broadcast receiver para receber chamadas de outros atores
                        registerBroadcast();
                        // Instancia de uma user service para criar o usuario
                        UserService userService = new UserService();
                        userService.CreateUser(new Usuario(nome, email, senha, new TipoUsuario(Constants.USER_VISITANT)), context);
                    } else {
                        Toast.makeText(context, getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        voltar_id = (Button) findViewById(R.id.botao_voltar_id);

        voltar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CadastrarUsuarioActivity.this,LoginActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Unregister broadcast receiver
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.unregisterReceiver(broadcastReceiver);
    }

    private void registerBroadcast() {
        //Resgister broadcast receiver
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(broadcastReceiver, new IntentFilter(Constants.CREATE_USER_ACTIVITY_TAG));
    }
}
