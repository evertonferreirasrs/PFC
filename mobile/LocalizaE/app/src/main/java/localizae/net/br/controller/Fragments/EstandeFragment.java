package localizae.net.br.controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import localizae.net.br.controller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstandeFragment extends Fragment {

    private TextView numeroTextView;
    private TextView areaTematicaTextView;
    private TextView periodoTextView;
    private TextView integrantesTextView;
    private TextView descricaoTextView;
    private Button avaliarButton;
    private Button voltarButton;


    public EstandeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estande, container, false);

        numeroTextView = (TextView) view.findViewById(R.id.fragment_estande_numero);
        areaTematicaTextView = (TextView) view.findViewById(R.id.fragment_estande_areaTematica);
        periodoTextView = (TextView) view.findViewById(R.id.fragment_estande_periodo);
        integrantesTextView = (TextView) view.findViewById(R.id.fragment_estande_integrantes);
        descricaoTextView = (TextView) view.findViewById(R.id.fragment_estande_descricao);


        avaliarButton = (Button) view.findViewById(R.id.comentar_id);

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
                
            }
        });

        return view;
    }

}
