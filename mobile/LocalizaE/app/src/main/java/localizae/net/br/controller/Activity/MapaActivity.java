package localizae.net.br.controller.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

import localizae.net.br.controller.R;

public class MapaActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    private ImageView botaoMapa;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        getSupportActionBar().setTitle("Mapa");

        ListView listView = (ListView)findViewById(R.id.listView_id);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.array_list)));

        adapter = new ArrayAdapter<String>(MapaActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);


        botaoMapa = (ImageView) findViewById(R.id.mapa_id);

        botaoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MapaActivity.this);
                builder.setTitle("Estande: LocalizaÊ");
                builder.setMessage("Área Temática: Tecnologia");
                builder.setCancelable(false);
                builder.setPositiveButton("+ Informações", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(new Intent(MapaActivity.this,EstandeActivity.class));
                    }
                });

                builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                alerta = builder.create();
                alerta.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pesquisar, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
