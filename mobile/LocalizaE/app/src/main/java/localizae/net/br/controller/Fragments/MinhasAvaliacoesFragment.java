package localizae.net.br.controller.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import localizae.net.br.Adapter.AvaliacaoVisitanteAdapter;
import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.AvaliacaoVisitante;

import localizae.net.br.model.Usuario;
import localizae.net.br.utils.ControladorDadosUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhasAvaliacoesFragment extends Fragment {

    private List<AvaliacaoVisitante> avaliacaoVisitantes = new ArrayList<>();

    public MinhasAvaliacoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minhas_avaliacoes, container, false);
        final ListView avaliacaoVisitanteListView = (ListView) view.findViewById(R.id.fragment_avaliacaoVisitante_listView);
        final Usuario usuarioLogado = ControladorDadosUsuario.lerDados(getContext());
        Call<List<AvaliacaoVisitante>> avaliacoesVisitanteByUserCall = new RetrofitInicializador().getAvaliacaoVisitanteService().getAvaliacoesVisitanteByUser(usuarioLogado.getId());

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Carregando");
        progress.show();

        avaliacoesVisitanteByUserCall.enqueue(new Callback<List<AvaliacaoVisitante>>() {
            @Override
            public void onResponse(Call<List<AvaliacaoVisitante>> call, Response<List<AvaliacaoVisitante>> response) {
                progress.dismiss();
                List<AvaliacaoVisitante> listaAvaliacao = response.body();
                getActivity().setTitle("Minhas Avaliações");
                if (listaAvaliacao.isEmpty()) {
                    Toast.makeText(getContext(), "Não possui avaliação", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getContext(), usuarioLogado.getId().toString(), Toast.LENGTH_SHORT).show();
                }
                AvaliacaoVisitanteAdapter adapterEstandes = new AvaliacaoVisitanteAdapter(getContext(), listaAvaliacao);
                avaliacaoVisitanteListView.setAdapter(adapterEstandes);
            }

            @Override
            public void onFailure(Call<List<AvaliacaoVisitante>> call, Throwable t) {
                progress.cancel();
                progress.dismiss();
                Toast.makeText(getContext(), "Serviço indisponível no momento", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
