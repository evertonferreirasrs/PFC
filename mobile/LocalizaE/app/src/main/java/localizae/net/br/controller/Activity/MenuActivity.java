package localizae.net.br.controller.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import localizae.net.br.controller.Fragments.AlterarSenhaFragment;
import localizae.net.br.controller.Fragments.AvaliarEstandeFragment;
import localizae.net.br.controller.Fragments.EnviarFeedbackFragment;
import localizae.net.br.controller.Fragments.InicioFragment;
import localizae.net.br.controller.Fragments.MeuEstandeFragment;
import localizae.net.br.controller.Fragments.MinhasAvaliacoesFragment;
import localizae.net.br.controller.Fragments.MinhasAvaliacoesJuradoFragment;
import localizae.net.br.controller.Fragments.MinhasPromocoesFragment;
import localizae.net.br.controller.Fragments.QualificacaoComentariosFragment;
import localizae.net.br.controller.Fragments.SobreFragment;
import localizae.net.br.controller.R;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //getActionBar().setIcon(getResources().getDrawable(R.drawable.icone_logo));
        //getSupportActionBar().setIcon(R.drawable.icone_logo);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // ABRIR MENSAGEM DE BOAS-VINDAS
        //getSupportActionBar().setIcon(R.drawable.icone_logo);
        setTitle(" LocalizaÊ");
        InicioFragment inicioFragment = new InicioFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_id, inicioFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // MAPA DA FAITEC
        if (id == R.id.mapa_id) {
            Intent intentAbrirMapa = new Intent(getBaseContext(),MapaActivity.class);
            startActivity(intentAbrirMapa);


            // ALTERAR SENHA
        }else if (id == R.id.alterar_senha_id) {
            setTitle(" Alterar Senha");
//            getSupportActionBar().setIcon(R.drawable.ic_vpn_key_black_24dp);
            AlterarSenhaFragment alterarSenhaFragment = new AlterarSenhaFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, alterarSenhaFragment).commit();


            // MINHAS AVALIAÇÕES
        }else if (id == R.id.minhas_avaliacoes_id) {
            setTitle(" Minhas Avaliações");
//            getSupportActionBar().setIcon(R.drawable.ic_chat_black_24dp);
            MinhasAvaliacoesFragment minhasAvaliacoesFragment = new MinhasAvaliacoesFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, minhasAvaliacoesFragment).commit();


        // MEU ESTANDE
        } else if (id == R.id.meu_estande_id) {
            setTitle(" (Nome do Estande)");
//            getSupportActionBar().setIcon(R.drawable.ic_pin_drop_black_24dp);
            MeuEstandeFragment meuEstandeFragment = new MeuEstandeFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, meuEstandeFragment).commit();


            // COMENTÁRIOS E QUALIFICAÇÕES DO MEU ESTANDE
        } else if (id == R.id.qualificacao_comentarios_id) {
            setTitle(" Avaliações do Estande");
//            getSupportActionBar().setIcon(R.drawable.ic_star_black_24dp);
            QualificacaoComentariosFragment qualificacaoComentariosFragment = new QualificacaoComentariosFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, qualificacaoComentariosFragment).commit();


            // CADASTRAR PROMOÇÃO
        } else if (id == R.id.minhas_promocoes_id) {
            setTitle(" Minhas Promoções");
            MinhasPromocoesFragment minhasPromocoesFragment = new MinhasPromocoesFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, minhasPromocoesFragment).commit();


        // AVALIAR ESTANDES
        }else if (id == R.id.avaliar_estande_id) {
            setTitle(" Avaliar Estandes");
//            getSupportActionBar().setIcon(R.drawable.ic_speaker_notes_black_24dp);
            AvaliarEstandeFragment avaliarEstandeFragment = new AvaliarEstandeFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, avaliarEstandeFragment).commit();


        // MINHAS AVALIAÇÕES
        } else if (id == R.id.minhas_avaliacoes_jurado_id) {
            setTitle(" Minhas Avaliações");
//            getSupportActionBar().setIcon(R.drawable.ic_chat_black_24dp);
            MinhasAvaliacoesJuradoFragment minhasAvaliacoesJuradoFragment = new MinhasAvaliacoesJuradoFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, minhasAvaliacoesJuradoFragment).commit();


        // ENVIAR FEEDBACK
        } else if (id == R.id.enviar_feedback_id) {
            setTitle(" Enviar Feedback");
//            getSupportActionBar().setIcon(R.drawable.ic_feedback_black_24dp);
            EnviarFeedbackFragment enviarFeedbackFragment = new EnviarFeedbackFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, enviarFeedbackFragment).commit();


        // SOBRE
        }else if (id == R.id.sobre_id) {
            setTitle(" Sobre");
//            getSupportActionBar().setIcon(R.drawable.ic_school_black_24dp);
            SobreFragment sobre = new SobreFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, sobre).commit();

        // SAIR
        } else if (id == R.id.sair_id) {
            setTitle(" Sair");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sair");
            builder.setMessage("Deseja realmente sair do aplicativo?");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }
            });

            builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alerta = builder.create();
            alerta.show();

            // SE CONTINUAR, VOLTA PARA O MAPA
            setTitle("LozalizaÊ");
//            getSupportActionBar().setIcon(R.drawable.ic_location_on_black_24dp);
            InicioFragment inicioFragment = new InicioFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, inicioFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
