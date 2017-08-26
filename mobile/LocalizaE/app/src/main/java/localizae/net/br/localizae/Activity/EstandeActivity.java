package localizae.net.br.localizae.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import localizae.net.br.localizae.R;

public class EstandeActivity extends AppCompatActivity {

    private TextView comentar_qualificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estande);

        comentar_qualificar = (TextView) findViewById(R.id.qualificar_comentar_id);

        comentar_qualificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EstandeActivity.this,ComentarQualificarActivity.class));

            }
        });


    }
}
