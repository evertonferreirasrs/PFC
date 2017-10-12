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
import localizae.net.br.retrofit.RetrofitInicializador;
import localizae.net.br.utils.Cryptographer;
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

        getActivity().setTitle(" Avaliar Estandes");
        View view = inflater.inflate(R.layout.fragment_avaliar_estande, container, false);

        usuario = new Usuario("Jurado", "jurado@localizae.net.br", Cryptographer.md5("123"), new TipoUsuario(3l));

        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();



        CriterioAvaliacao criterioAvaliacao = new CriterioAvaliacao();
        criterioAvaliacao.setNome("Explanação");
        criterioAvaliacao.setPeso(4l);



        CriterioAvaliacao criterioAvaliacao2 = new CriterioAvaliacao();
        criterioAvaliacao2.setNome("Documentação");
        criterioAvaliacao2.setPeso(5l);



        Estande estande = new Estande(18l);
        estande.setTitulo("LocalizaE");
        estande.setCurso("Sistemas de Informação");

        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterioAvaliacao);
        criterioJurado.setEstande(estande);

        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setEstande(estande);
        criterioJurado2.setCriterioAvaliacao(criterioAvaliacao2);

        criterioAvaliacaoList.add(criterioJurado);
        criterioAvaliacaoList.add(criterioJurado2);

        usuario.setCriterioAvaliacaoList(criterioAvaliacaoList);

        final ListView listaEstandes = (ListView) view.findViewById(R.id.fragment_avaliar_estande_listaEstandes);

        Call buscarCriteriosJuradoCall = new RetrofitInicializador().getCriterioJuradoService().getByParameters(71L);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //progressDialog.setTitle("Carregando");
        progressDialog.setMessage("Carregando... por favor aguarde.");
        progressDialog.show();

        buscarCriteriosJuradoCall.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                List<CriterioJurado> criterioJuradoList = (List<CriterioJurado>)response.body();
                Toast.makeText(getContext(), criterioJuradoList.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
                progressDialog.dismiss();
//                criterioJuradoList = usuario.getCriterioAvaliacaoList();
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
