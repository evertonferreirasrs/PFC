package localizae.net.br.controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import localizae.net.br.Adapter.IntegranteEquipeAdapter;
import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.Estande;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeuEstandeFragment extends Fragment {


    public MeuEstandeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meu_estande, container, false);
        final TextView campoNumero = (TextView) view.findViewById(R.id.fragment_meuEstande_vNumero);
        final TextView campoProjeto = (TextView) view.findViewById(R.id.fragment_meuEstande_vProjeto);
        final TextView campoAreaTematica = (TextView) view.findViewById(R.id.fragment_meuEstande_vAreaTematica);
        final TextView campoCurso = (TextView) view.findViewById(R.id.fragment_meuEstande_Vcurso);
        final TextView campoDescricao = (TextView) view.findViewById(R.id.fragment_meuEstande_vDescricao);
        final ListView campoIntegrantes = (ListView) view.findViewById(R.id.fragment_integrantesequipe_listView);

        final Call<Estande> estandeCall = new RetrofitInicializador().getEstandeService().getEstande(19L);

        estandeCall.enqueue(new Callback<Estande>() {
            @Override
            public void onResponse(Call<Estande> call, Response<Estande> response) {
                Estande estande = response.body();
                getActivity().setTitle(estande.getTitulo());
                campoNumero.setText(estande.getNumero().toString());
                campoProjeto.setText(estande.getTitulo());
                campoAreaTematica.setText(estande.getAreaTematica());
                campoCurso.setText(estande.getCurso());
                campoDescricao.setText(estande.getDescricao());
                IntegranteEquipeAdapter adapter = new IntegranteEquipeAdapter(estande.getEquipe(), getContext());
                campoIntegrantes.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Estande> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
