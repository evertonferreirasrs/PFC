package localizae.net.br.controller.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Properties;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.utils.ControladorDadosUsuario;
import localizae.net.br.utils.LerDadosUsuario;


public class FeedbackActivity extends Activity implements OnClickListener{

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText sub, msg;
    String rec, subject, textMessage, infUsuario;

    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        context = this;

        Button login = (Button) findViewById(R.id.btn_submit);
        sub = (EditText) findViewById(R.id.et_sub);
        msg = (EditText) findViewById(R.id.et_text);

        login.setOnClickListener(this);

        voltar = (Button) findViewById(R.id.botao_voltar_id);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        rec = "localizae2017@gmail.com";
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();

        Usuario usuarioLogado = ControladorDadosUsuario.lerDados(this);
        String tipo = "";
        if(usuarioLogado.getTipoUsuario().getId() == 1){
            tipo = "ADMINISTRADOR";
        }
        if (usuarioLogado.getTipoUsuario().getId() == 2){
            tipo = "VISITANTE";
        }
        if (usuarioLogado.getTipoUsuario().getId() == 3){
            tipo = "EXPOSITOR";
        }
        if(usuarioLogado.getTipoUsuario().getId() == 4){
            tipo = "JURADO";
        }

        infUsuario =  "<b>Feedback do Usuário:</b> " + usuarioLogado.getNome() + "<br><br>" +
                "<b>Perfil do usuário no sistema: </b>" + tipo + "<br><br>" +
                "<b>Email:</b> " + usuarioLogado.getEmail() + "<br><br>" + "<b>Mensagem:</b> ";

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

        if(subject.isEmpty() || textMessage.isEmpty()){
            Toast.makeText(context, "Preencha os campos.", Toast.LENGTH_LONG).show();
        }else{
            pdialog = ProgressDialog.show(context, "", "Enviando...", true);

            RetreiveFeedTask task = new RetreiveFeedTask();
            task.execute();
        }

    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(infUsuario + textMessage, "text/html; charset=utf-8");
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
            msg.setText("");
            sub.setText("");

            if(subject.isEmpty() || textMessage.isEmpty()){
                Toast.makeText(context, "Preencha os campos.", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Enviado com Sucesso, obrigado!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
