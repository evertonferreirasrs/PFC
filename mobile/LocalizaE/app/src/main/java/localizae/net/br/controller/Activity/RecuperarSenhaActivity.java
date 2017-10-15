package localizae.net.br.controller.Activity;

import android.os.Bundle;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import localizae.net.br.Retrofit.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.endpoints.AvaliacaoJuradoInterface;
import localizae.net.br.services.endpoints.UserEndpointInterface;
import localizae.net.br.services.impl.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RecuperarSenhaActivity extends AppCompatActivity implements OnClickListener{

    private Button enviar_id;
    private Button voltar_id;

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText sub, msg;
    String rec, subject, textMessage;
    String senhaGerada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        getSupportActionBar().hide();

        context = this;

        Button login = (Button) findViewById(R.id.botao_enviar_id);
        sub = (EditText) findViewById(R.id.et_sub);
        msg = (EditText) findViewById(R.id.et_text);

        login.setOnClickListener(this);

        voltar_id = (Button) findViewById(R.id.botao_voltar_id);

        voltar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        String letras = "abcdefghijklmnopqrstuvwxyz123456789";

        Random random = new Random();

        int index = -1;
        for( int i = 0; i < 7; i++ ) {
            index = random.nextInt( letras.length() );
            senhaGerada += letras.substring( index, index + 1 );
        }

        //Gerar senha
//        String[] carct = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
//        for (int x = 0; x < 7; x++) {
//            int j = (int) (Math.random() * carct.length);
//            senhaGerada += carct[j];
//        }
        // Fim gerar senha

        rec = sub.getText().toString();
        subject = "Recuperar Senha";
        textMessage = "Olá, utilize esta senha provisória para acessar o aplicativo LocalizaÊ e criar sua nova senha.<br><br>" +
        "Email: " + rec + "<br>" + "Senha: " + senhaGerada + "<br><br>" + "Atenciosamente, a equipe LocalizaÊ.";

//        String hash = Cryptographer.md5(rec + Cryptographer.md5(senhaGerada));
//        Usuario usuario = new Usuario(rec,hash);


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("localizae2017@gmail.com", "elmrfai2017");
            }
        });

        final Call<List<Usuario>> usuarioList = new RetrofitInicializador().getUsuarioService().getByEmail(rec);

        usuarioList.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> listaUsuario = response.body();
                if (listaUsuario.isEmpty() || rec.isEmpty()) {
                    Toast.makeText(context, "Email não cadastrado no sistema.", Toast.LENGTH_SHORT).show();
                }else{

                    Usuario usuario = listaUsuario.get(0);
                    usuario.setSenha(senhaGerada);

                    Call<Usuario> user = new RetrofitInicializador().getUsuarioService().alterarUser(usuario);

                    user.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            pdialog = ProgressDialog.show(context, "", "Enviando...", true);

                            RetreiveFeedTask task = new RetreiveFeedTask();
                            task.execute();
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(context, "Serviço indisponível no momento", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(context, "Serviço indisponível no momento", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            sub.setText("");

            Toast.makeText(getApplicationContext(), "Enviado com Sucesso, verifique sua caixa de email.", Toast.LENGTH_LONG).show();
        }
    }
}
