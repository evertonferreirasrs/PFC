package localizae.net.br.controller.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import localizae.net.br.controller.R;
import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.model.AvaliacaoVisitante;
import localizae.net.br.model.Estande;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.endpoints.AvaliacaoVisitanteEndpointInterface;
import localizae.net.br.services.impl.AvaliacaoVisitanteService;
import localizae.net.br.services.impl.EstandeService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ControladorDadosUsuario;
import localizae.net.br.utils.LerDadosUsuario;
import localizae.net.br.utils.ResponseCodeValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentarQualificarFragment extends Fragment {

    private Button botao_enviar;
    private Button botao_voltar;
    private EditText comentarioEditText;
    private RatingBar nota_ratingbar;

    public ComentarQualificarFragment() {
        // Required empty public constructor
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.COMENTS_TAG)) {
                int responseCode = intent.getIntExtra(Constants.RESPONSE_CODE_KEY, 500);
                Log.d(Constants.COMENTS_TAG, "Recebeu resposta " + responseCode);
                switch (responseCode) {
                    case 200:
                        Toast.makeText(getActivity(), getString(R.string.successful_comment), Toast.LENGTH_LONG).show();
                        Usuario usuario = (Usuario) intent.getSerializableExtra(Constants.DATA_KEY);
                        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putLong(Constants.USER_ID_KEY, usuario.getId());
                        editor.commit();
                        break;
                    default:
                        String text = ResponseCodeValidator.validateResponseCode(responseCode);
                        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comentar_qualificar, container, false);

        botao_enviar = (Button) view.findViewById(R.id.botao_entrar_id);
        botao_voltar = (Button) view.findViewById(R.id.botao_voltar_id);
        comentarioEditText = (EditText) view.findViewById(R.id.comentario_editText_comentario) ;
        nota_ratingbar = (RatingBar) view.findViewById(R.id.comentarQualificar_nota);
        final FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

        botao_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView viewById = comentarioEditText;
                String comentario = "";
                if(viewById != null){
                    comentario = viewById.getText().toString();
                }
                if(comentario.isEmpty()){
                    Toast.makeText(getActivity(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }else {
                    registerBroadcast();
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    long userId = sharedPref.getLong(Constants.USER_ID_KEY, 0);
                    final long nota = (long) nota_ratingbar.getProgress();
                    Bundle arguments = getArguments();
                    Estande estande = null;
                    if(arguments != null){
                        estande = (Estande)arguments.getSerializable("estande");
                        if(estande == null){
                            Toast.makeText(getContext(), "Impossível realizar requisição", Toast.LENGTH_SHORT).show();

                            if(supportFragmentManager.getBackStackEntryCount() > 0){
                                supportFragmentManager.popBackStack();
                            }
                        }
                    }

                    Usuario usuarioLogado = ControladorDadosUsuario.lerDados(getContext());

                    AvaliacaoVisitante avaliacaoVisitante = new AvaliacaoVisitante(nota, comentario, usuarioLogado, estande);
                    AvaliacaoVisitanteEndpointInterface service = new RetrofitInicializador().getAvaliacaoVisitanteService();
                    Call<AvaliacaoVisitante> avaliacaoVisitanteCall = service.avaliacao(avaliacaoVisitante);

                    avaliacaoVisitanteCall.enqueue(new Callback<AvaliacaoVisitante>() {
                        @Override
                        public void onResponse(Call<AvaliacaoVisitante> call, Response<AvaliacaoVisitante> response) {
                            Toast.makeText(getContext(), "Avaliado Com Sucesso.", Toast.LENGTH_LONG).show();
                            if(supportFragmentManager.getBackStackEntryCount() > 0){
                                supportFragmentManager.popBackStack();
                            }
                        }

                        @Override
                        public void onFailure(Call<AvaliacaoVisitante> call, Throwable t) {
                            Toast.makeText(getContext(), "Impossível enviar avaliação no momento", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(supportFragmentManager.getBackStackEntryCount() > 0){
                    supportFragmentManager.popBackStack();
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
        lbm.unregisterReceiver(broadcastReceiver);
    }

    private void registerBroadcast() {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
        lbm.registerReceiver(broadcastReceiver, new IntentFilter(Constants.COMENTAR_QUALIFICAR_FRAGMENT_TAG));
    }

}
