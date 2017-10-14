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

    private TextView botao_comentar;
    private Button botao_voltar;


    public EstandeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_estande, container, false);

        botao_comentar = (TextView) view.findViewById(R.id.comentar_id);

        botao_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ComentarQualificarFragment comentarQualificarFragment = new ComentarQualificarFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, comentarQualificarFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        botao_voltar = (Button) view.findViewById(R.id.botao_voltar_id);

        botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        return view;
    }

}
