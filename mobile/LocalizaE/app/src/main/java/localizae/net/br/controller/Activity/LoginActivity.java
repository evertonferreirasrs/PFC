package localizae.net.br.controller.Activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.impl.UserService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ControladorDadosUsuario;
import localizae.net.br.utils.Cryptographer;
import localizae.net.br.utils.ResponseCodeValidator;

public class LoginActivity extends AppCompatActivity {

    private TextView texto_esqueceu_senha;
    private TextView texto_criar_conta;
    private Button botao_entrar;
    private AlertDialog alerta;
    private Context context;
    private ProgressDialog progressDialog;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.LOGIN_ACTIVITY_TAG)) {
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.LOGIN_ACTIVITY_TAG, "Recebeu resposta " + responseCode);
                progressDialog.dismiss();
                switch (responseCode) {
                    case 200:
                        Toast.makeText(context, getString(R.string.successful_login), Toast.LENGTH_LONG).show();
                        Usuario usuario = (Usuario) intent.getSerializableExtra(Constants.DATA_KEY);
                        ControladorDadosUsuario armazenadorDadosUsuario = new ControladorDadosUsuario();
                        armazenadorDadosUsuario.aramazenarDados(usuario,context);
                        startActivity(new Intent(LoginActivity.this,MenuActivity.class));
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
        context = this;
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();


        botao_entrar = (Button) findViewById(R.id.botao_entrar_id);



        texto_esqueceu_senha = (TextView) findViewById(R.id.texto_esqueceu_senha_id);
        texto_criar_conta = (TextView) findViewById(R.id.texto_criar_conta_id);
        botao_entrar = (Button) findViewById(R.id.botao_entrar_id);

        botao_entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.campo_email_id)).getText().toString();
                String senha = ((EditText) findViewById(R.id.campo_senha_id)).getText().toString();
                if(email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(context, getString(R.string.fill_entries), Toast.LENGTH_LONG).show();
                }else {
                    // LOADER
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    //progressDialog.setTitle("Carregando");
                    progressDialog.setMessage("Carregando... por favor aguarde.");
                    progressDialog.show();

                    registerBroadcast();

                    String hash = Cryptographer.md5(email + Cryptographer.md5(senha));
                    Usuario usuario = new Usuario(email,hash);

                    UserService userService = new UserService();
                    userService.login(usuario,context);
                }
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
        lbm.registerReceiver(broadcastReceiver, new IntentFilter(Constants.LOGIN_ACTIVITY_TAG));
    }

}
