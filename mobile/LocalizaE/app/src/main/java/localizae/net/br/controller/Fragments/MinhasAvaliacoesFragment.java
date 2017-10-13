package localizae.net.br.controller.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import localizae.net.br.Adapter.AvaliacaoVisitanteAdapter;
import localizae.net.br.Retrofit.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.AvaliacaoVisitante;
import localizae.net.br.model.Usuario;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhasAvaliacoesFragment extends Fragment {

    private List<AvaliacaoVisitante> avaliacaoVisitantes = new ArrayList<>();

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.COMENTS_TAG)) {
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.MY_AVALIATIONS_FRAGMENT_TAG, "Recebeu resposta " + responseCode);
                switch (responseCode) {
                    case 200:
                        Usuario usuario = (Usuario) intent.getSerializableExtra(Constants.DATA_KEY);
                        List<AvaliacaoVisitante> avaliacaoVisitantes = (List<AvaliacaoVisitante>) intent.getSerializableExtra(Constants.DATA_KEY);
                        break;
                    default:
                        String text = ResponseCodeValidator.validateResponseCode(responseCode);
                        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    public MinhasAvaliacoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minhas_avaliacoes, container, false);
        final ListView avaliacaoVisitanteListView = (ListView) view.findViewById(R.id.fragment_avaliacaoVisitante_listView);

        Call<List<AvaliacaoVisitante>> avaliacoesVisitanteByUserCall = new RetrofitInicializador().getAvaliacaoVisitanteService().getAvaliacoesVisitanteByUser(21L);

        avaliacoesVisitanteByUserCall.enqueue(new Callback<List<AvaliacaoVisitante>>() {
            @Override
            public void onResponse(Call<List<AvaliacaoVisitante>> call, Response<List<AvaliacaoVisitante>> response) {
                List<AvaliacaoVisitante> listaAvaliacao = response.body();
                if(listaAvaliacao.isEmpty()){
                    Toast.makeText(getContext(),"Não possui avaliação",Toast.LENGTH_SHORT).show();
                }
                AvaliacaoVisitanteAdapter adapterEstandes = new AvaliacaoVisitanteAdapter(getContext(), listaAvaliacao);
                avaliacaoVisitanteListView.setAdapter(adapterEstandes);
            }

            @Override
            public void onFailure(Call<List<AvaliacaoVisitante>> call, Throwable t) {
                Toast.makeText(getContext(),"Serviço indisponivel no momento",Toast.LENGTH_SHORT).show();
            }
        });








        return view;
    }

}
