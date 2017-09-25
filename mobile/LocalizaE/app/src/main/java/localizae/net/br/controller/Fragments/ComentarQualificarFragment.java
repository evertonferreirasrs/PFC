package localizae.net.br.controller.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import localizae.net.br.controller.R;
import localizae.net.br.model.AvaliacaoVisitante;
import localizae.net.br.model.Estande;
import localizae.net.br.model.Usuario;
import localizae.net.br.services.impl.BoothService;
import localizae.net.br.utils.Constants;
import localizae.net.br.utils.ResponseCodeValidator;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentarQualificarFragment extends Fragment {

    private Button botao_enviar;
    private Button botao_voltar;
    private EditText comentarioEditText;

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
                    AvaliacaoVisitante avaliacaoVisitante = new AvaliacaoVisitante(5L, comentario, new Usuario(21L), new Estande(18L));

                    BoothService boothService = new BoothService();
                    boothService.avaliacao(avaliacaoVisitante, getActivity());
                    Toast.makeText(getActivity(), "preenchido", Toast.LENGTH_LONG).show();
                }
            }
        });

        botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EstandeFragment estandeFragment = new EstandeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, estandeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void registerBroadcast() {
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter(Constants.COMENTAR_QUALIFICAR_FRAGMENT_TAG));
    }

}
