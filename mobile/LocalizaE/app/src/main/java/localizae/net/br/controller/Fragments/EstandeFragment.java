package localizae.net.br.controller.Fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import localizae.net.br.Adapter.IntegranteEquipeAdapter;
import localizae.net.br.controller.Activity.MapaActivity;
import localizae.net.br.controller.R;
import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.model.CriterioJurado;
import localizae.net.br.model.Estande;
import localizae.net.br.model.IntegranteEquipe;
import localizae.net.br.services.endpoints.EstandeEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstandeFragment extends Fragment {

    private TextView numeroTextView;
    private TextView areaTematicaTextView;
    private TextView periodoTextView;
    private TextView descricaoTextView;
    private TextView tituloTextView;
    private TextView cursoTextView;
    private ListView integrantesListView;
    private Button avaliarButton;
    private Button voltarButton;
    private Estande estande;


    public EstandeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estande, container, false);
        view.setBackgroundColor(Color.WHITE);

        numeroTextView = (TextView) view.findViewById(R.id.fragment_estande_numero);
        areaTematicaTextView = (TextView) view.findViewById(R.id.fragment_estande_areaTematica);
        periodoTextView = (TextView) view.findViewById(R.id.fragment_estande_periodo);
        integrantesListView = (ListView) view.findViewById(R.id.fragment_estande_integrantes);
        descricaoTextView = (TextView) view.findViewById(R.id.fragment_estande_descricao);
        tituloTextView = (TextView) view.findViewById(R.id.fragment_estande_titulo);
        cursoTextView = (TextView) view.findViewById(R.id.fragment_estande_curso);
        avaliarButton = (Button) view.findViewById(R.id.fragment_estande_avaliar);
        voltarButton = (Button) view.findViewById(R.id.fragment_estande_voltar);
        EstandeEndpointInterface estandeService = new RetrofitInicializador().getEstandeService();
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        voltarButton.setEnabled(false);
        progressDialog.setMessage("Carregando...");

        progressDialog.show();

        Bundle args = getArguments();
        if (args != null) {
            Long estandeId = (Long) args.getSerializable("estandeId");
            if (estandeId != null) {
                Call<Estande> estandeCall = estandeService.getEstande(estandeId);
                estandeCall.enqueue(new Callback<Estande>() {
                    @Override
                    public void onResponse(Call<Estande> call, Response<Estande> response) {
                        progressDialog.cancel();
                        progressDialog.dismiss();
                        voltarButton.setEnabled(true);
                        estande = response.body();
                        getActivity().setTitle(estande.getTitulo());
                        preencherDados(estande);
                    }

                    @Override
                    public void onFailure(Call<Estande> call, Throwable t) {
                        Toast.makeText(getContext(), "Impossível buscar estande.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        avaliarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ComentarQualificarFragment comentarQualificarFragment = new ComentarQualificarFragment();

                Bundle argsAvaliar = new Bundle();
                argsAvaliar.putSerializable("estande", estande);
                comentarQualificarFragment.setArguments(argsAvaliar);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mapa_fragment_id, comentarQualificarFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

                if (supportFragmentManager.getBackStackEntryCount() > 0) {
                    supportFragmentManager.popBackStack();
                }
            }
        });

        return view;
    }

    private void preencherDados(Estande estande) {
        numeroTextView.setText(estande.getNumero().toString());
        areaTematicaTextView.setText(estande.getAreaTematica());
        periodoTextView.setText(estande.getPeriodo().toString());
        descricaoTextView.setText(estande.getDescricao());
        tituloTextView.setText(estande.getTitulo());
        cursoTextView.setText(estande.getCurso());

        IntegranteEquipeAdapter integranteEquipeAdapter = new IntegranteEquipeAdapter(estande.getEquipe(), getContext());
        integrantesListView.setAdapter(integranteEquipeAdapter);
    }

}
