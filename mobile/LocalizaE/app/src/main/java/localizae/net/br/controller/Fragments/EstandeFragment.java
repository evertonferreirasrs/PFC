package localizae.net.br.controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import localizae.net.br.Retrofit.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.CriterioJurado;
import localizae.net.br.model.Estande;
import localizae.net.br.model.IntegranteEquipe;
import localizae.net.br.services.endpoints.StandEndpointInterface;
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

        numeroTextView = (TextView) view.findViewById(R.id.fragment_estande_numero);
        areaTematicaTextView = (TextView) view.findViewById(R.id.fragment_estande_areaTematica);
        periodoTextView = (TextView) view.findViewById(R.id.fragment_estande_periodo);
        integrantesListView = (ListView) view.findViewById(R.id.fragment_estande_integrantes);
        descricaoTextView = (TextView) view.findViewById(R.id.fragment_estande_descricao);
        avaliarButton = (Button) view.findViewById(R.id.fragment_estande_avaliar);
        StandEndpointInterface estandeService = new RetrofitInicializador().getEstandeService();

        Bundle args = getArguments();

        if(args != null){
            Long estandeId = (Long)args.getSerializable("estandeId");
            if(estandeId != null){
                Call<Estande> estandeCall = estandeService.getEstande(estandeId);
                estandeCall.enqueue(new Callback<Estande>() {
                    @Override
                    public void onResponse(Call<Estande> call, Response<Estande> response) {
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
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, comentarQualificarFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        voltarButton = (Button) view.findViewById(R.id.botao_voltar_id);

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_estande)).commit();
            }
        });

        return view;
    }

    private void preencherDados(Estande estande) {
        numeroTextView.setText(estande.getNumero().toString());
        areaTematicaTextView.setText(estande.getAreaTematica());
        periodoTextView.setText(estande.getPeriodo().toString());
        descricaoTextView.setText(estande.getDescricao());

        IntegranteEquipeAdapter integranteEquipeAdapter = new IntegranteEquipeAdapter(estande.getEquipe(), getContext());
        integrantesListView.setAdapter(integranteEquipeAdapter);
    }

}
