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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.impl.UserService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private Context context;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.CREATE_USER_ACTIVITY_TAG)) {
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.CREATE_USER_ACTIVITY_TAG, "Recebeu resposta " + responseCode);
                switch (responseCode) {
                    case 200:
                        Toast.makeText(context, getString(R.string.account_created), Toast.LENGTH_LONG).show();
                        break;
                    default:
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

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
                    Toast.makeText(context, getString(R.string.fill_entries), Toast.LENGTH_LONG).show();
                } else {
                    if (senha.equals(confirmaSenha)) {

                        registerBroadcast();

                        UserService userService = new UserService();
                        userService.CreateUser(new Usuario(nome, email, senha), context);
                    } else {
                        Toast.makeText(context, getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
                    }
                }
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
