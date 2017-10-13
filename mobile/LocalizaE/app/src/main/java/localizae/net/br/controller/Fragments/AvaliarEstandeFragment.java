package localizae.net.br.controller.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import localizae.net.br.controller.Activity.LoginActivity;
import localizae.net.br.controller.R;
import localizae.net.br.dao.EstandeDAO;
import localizae.net.br.model.CriterioAvaliacao;
import localizae.net.br.model.CriterioJurado;
import localizae.net.br.model.Estande;
import localizae.net.br.model.TipoUsuario;
import localizae.net.br.model.Usuario;
import localizae.net.br.Retrofit.RetrofitInicializador;
import localizae.net.br.utils.Cryptographer;
import localizae.net.br.utils.LerDadosUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliarEstandeFragment extends Fragment {

    private Usuario usuario;

    private Button botaoAvaliar;

    public AvaliarEstandeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Avaliar Estandes");
        View view = inflater.inflate(R.layout.fragment_avaliar_estande, container, false);

        Usuario usuarioLogado = LerDadosUsuario.lerDados(getContext());

        final ListView listaEstandes = (ListView) view.findViewById(R.id.fragment_avaliar_estande_listaEstandes);

        Call buscarCriteriosJuradoCall = new RetrofitInicializador().getCriterioJuradoService().getByParameters(usuarioLogado.getId());
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //progressDialog.setTitle("Carregando");
        progressDialog.setMessage("Carregando... por favor aguarde.");
        progressDialog.show();

        buscarCriteriosJuradoCall.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                progressDialog.cancel();
                progressDialog.dismiss();

                List<CriterioJurado> criterioJuradoList = (List<CriterioJurado>)response.body();

                //Montar um mapa com chave sendo titulo do estande e valor a lista de criterios daquele estande.
                final Map<String, List<CriterioJurado>> mapaDeCriterios = getMapEstandeFromListCriterio(criterioJuradoList);

                //Preencher o listView com um array de strings sendo os titulos dos estandes.
                List<String> strings = new ArrayList<>();
                Object[] array = mapaDeCriterios.keySet().toArray();
                for(int i = 0; i < array.length; i++){
                    strings.add((String)array[i]);
                }

                ArrayAdapter<String> adapterEstandes = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, strings);
                listaEstandes.setAdapter(adapterEstandes);

                listaEstandes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        //Quando o usuario clicar no estande, deve enviar para o fragment a lista de criteriosJurado para o fragment
                        List<CriterioJurado> listaDeCriterios = mapaDeCriterios.get((String)adapterView.getItemAtPosition(position));
                        CriterioAvalicaoFragment criterioAvalicaoFragment = new CriterioAvalicaoFragment();

                        Bundle args = new Bundle();
                        args.putSerializable("criterioList", (Serializable) listaDeCriterios);
                        criterioAvalicaoFragment.setArguments(args);

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_id, criterioAvalicaoFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "ERRO", Toast.LENGTH_SHORT).show();
            }
        });





//        botaoAvaliar = (Button) view.findViewById(R.id.avaliar_id);
//
//        botaoAvaliar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                CriterioAvalicaoFragment criterioAvalicaoFragment = new CriterioAvalicaoFragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_id, criterioAvalicaoFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });

        return view;
    }

    @NonNull
    private Map<String, List<CriterioJurado>> getMapEstandeFromListCriterio(List<CriterioJurado> criterioJuradoList) {
        final Map<String, List<CriterioJurado>> mapaDeCriterios = new HashMap<>();
        for(CriterioJurado criterio : criterioJuradoList){
            String tituloEstande = criterio.getEstande().getTitulo();
            if(mapaDeCriterios.get(tituloEstande) == null){
                mapaDeCriterios.put(tituloEstande, new ArrayList<CriterioJurado>());
            }

            mapaDeCriterios.get(tituloEstande).add(criterio);
        }
        return mapaDeCriterios;
    }

}
