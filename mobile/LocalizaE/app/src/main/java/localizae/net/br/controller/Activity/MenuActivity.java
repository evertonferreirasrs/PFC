package localizae.net.br.controller.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import localizae.net.br.controller.Fragments.AlterarSenhaFragment;
import localizae.net.br.controller.Fragments.AvaliarEstandeFragment;
import localizae.net.br.controller.Fragments.InicioFragment;
import localizae.net.br.controller.Fragments.MeuEstandeFragment;
import localizae.net.br.controller.Fragments.MinhasAvaliacoesFragment;
import localizae.net.br.controller.Fragments.MinhasPromocoesFragment;
import localizae.net.br.controller.Fragments.QualificacaoComentariosFragment;
import localizae.net.br.controller.Fragments.SobreFragment;
import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.utils.LerDadosUsuario;
import localizae.net.br.utils.VerificadorUsuario;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AlertDialog alerta;

    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mostrarMenuDoUsuario(navigationView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //getActionBar().setIcon(getResources().getDrawable(R.drawable.icone_logo));
        //getSupportActionBar().setIcon(R.drawable.icone_logo);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        // NOME E EMAIL do usuário logado ==========================================================
        Usuario usuarioLogado = LerDadosUsuario.lerDados(this);
        View header = navigationView.getHeaderView(0);
        TextView nome = (TextView)header.findViewById(R.id.textView_nome);
        TextView email = (TextView)header.findViewById(R.id.textView_email);
        nome.setText(usuarioLogado.getNome());
        email.setText(usuarioLogado.getEmail());
        // =========================================================================================

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


       // ABRIR MENSAGEM DE BOAS-VINDAS
        //getSupportActionBar().setIcon(R.drawable.icone_logo);
        setTitle(" LocalizaÊ");
        InicioFragment inicioFragment = new InicioFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_id, inicioFragment).commit();

    }

    private void mostrarMenuDoUsuario(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();

        MenuItem mapa = menu.findItem(R.id.menu_botao_mapa);
        mapa.setVisible(true);

        MenuItem alterarSenha = menu.findItem(R.id.menu_botao_alterar_senha);
        alterarSenha.setVisible(true);

        MenuItem enviarFeedback = menu.findItem(R.id.menu_botao_enviar_feedback);
        enviarFeedback.setVisible(true);

        MenuItem sobre = menu.findItem(R.id.menu_botao_sobre);
        sobre.setVisible(true);

        MenuItem sair = menu.findItem(R.id.menu_botao_sair);
        sair.setVisible(true);

        // Restrições de Visibilidade Particulares =================================================
        MenuItem avaliacoes = menu.findItem(R.id.menu_botao_avaliacoes);
        avaliacoes.setVisible(false);

        MenuItem meuEstande = menu.findItem(R.id.menu_botao_meu_estande);
        meuEstande.setVisible(false);

        MenuItem qualificacaoComentarios = menu.findItem(R.id.menu_botao_qualificacao_comentarios);
        qualificacaoComentarios.setVisible(false);

        MenuItem minhasPromocoes = menu.findItem(R.id.menu_botao_minhas_promocoes);
        minhasPromocoes.setVisible(false);

        MenuItem avaliarEstande = menu.findItem(R.id.menu_botao_avaliar_estande);
        avaliarEstande.setVisible(false);

        // Fim Restrições  =========================================================================

        // PERMISSÕES DE VISIBILIDADE PARA USUÁRIOS ================================================
        if(VerificadorUsuario.getInstances(this).isExpositor()){

            meuEstande.setVisible(true);
            qualificacaoComentarios.setVisible(true);
            minhasPromocoes.setVisible(true);

        }

        if(VerificadorUsuario.getInstances(this).isJurado()){

            avaliarEstande.setVisible(true);

        }

        if(VerificadorUsuario.getInstances(this).isVisitante()){

            avaliacoes.setVisible(true);

        }
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
        if (id == R.id.menu_botao_mapa) {
            Intent intentAbrirMapa = new Intent(getBaseContext(),MapaActivity.class);
            startActivity(intentAbrirMapa);


            // ALTERAR SENHA
        }else if (id == R.id.menu_botao_alterar_senha) {
            setTitle(" Alterar Senha");
//            getSupportActionBar().setIcon(R.drawable.ic_vpn_key_black_24dp);
            AlterarSenhaFragment alterarSenhaFragment = new AlterarSenhaFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, alterarSenhaFragment).commit();

            // MINHAS AVALIAÇÕES
        }else if (id == R.id.menu_botao_avaliacoes) {
            setTitle(" Alterar Senha");
            MinhasAvaliacoesFragment minhasAvaliacoesFragment = new MinhasAvaliacoesFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, minhasAvaliacoesFragment).commit();


        // MEU ESTANDE
        } else if (id == R.id.menu_botao_meu_estande) {
            setTitle(" (Nome do Estande)");
//            getSupportActionBar().setIcon(R.drawable.ic_pin_drop_black_24dp);
            MeuEstandeFragment meuEstandeFragment = new MeuEstandeFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, meuEstandeFragment).commit();


            // COMENTÁRIOS E QUALIFICAÇÕES DO MEU ESTANDE
        } else if (id == R.id.menu_botao_qualificacao_comentarios) {
            setTitle(" Avaliações do Estande");
//            getSupportActionBar().setIcon(R.drawable.ic_star_black_24dp);
            QualificacaoComentariosFragment qualificacaoComentariosFragment = new QualificacaoComentariosFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, qualificacaoComentariosFragment).commit();


            // CADASTRAR PROMOÇÃO
        } else if (id == R.id.menu_botao_minhas_promocoes) {
            setTitle(" Minhas Promoções");
            MinhasPromocoesFragment minhasPromocoesFragment = new MinhasPromocoesFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, minhasPromocoesFragment).commit();


        // AVALIAR ESTANDES
        }else if (id == R.id.menu_botao_avaliar_estande) {
            setTitle(" Avaliar Estandes");
//            getSupportActionBar().setIcon(R.drawable.ic_speaker_notes_black_24dp);
            AvaliarEstandeFragment avaliarEstandeFragment = new AvaliarEstandeFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, avaliarEstandeFragment).commit();


        // ENVIAR FEEDBACK
        } else if (id == R.id.menu_botao_enviar_feedback) {
            setTitle(" Enviar Feedback");
//            getSupportActionBar().setIcon(R.drawable.ic_feedback_black_24dp);
//            EnviarFeedbackFragment enviarFeedbackFragment = new EnviarFeedbackFragment();
//            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.fragment_id, enviarFeedbackFragment).commit();

            startActivity(new Intent(MenuActivity.this,FeedbackActivity.class));




        // SOBRE
        }else if (id == R.id.menu_botao_sobre) {
            setTitle(" Sobre");
//            getSupportActionBar().setIcon(R.drawable.ic_school_black_24dp);
            SobreFragment sobre = new SobreFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, sobre).commit();

        // SAIR
        } else if (id == R.id.menu_botao_sair) {
            setTitle(" Sair");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sair");
            builder.setMessage("Deseja realmente sair do aplicativo?");
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();

                    android.os.Process.killProcess(android.os.Process.myPid());
//                    finishAffinity();
                }
            });

            builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alerta = builder.create();
            alerta.show();

            // SE CONTINUAR, VOLTA PARA O INICIO
            setTitle("LozalizaÊ");
            InicioFragment inicioFragment = new InicioFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_id, inicioFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
