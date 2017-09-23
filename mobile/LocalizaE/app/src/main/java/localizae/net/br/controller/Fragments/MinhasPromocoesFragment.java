package localizae.net.br.controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import localizae.net.br.controller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhasPromocoesFragment extends Fragment {

    private Button botao_cadastrar_id;
    private Button botao_alterar_id;


    public MinhasPromocoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(" Minhas Promoções");

        View view = inflater.inflate(R.layout.fragment_minhas_promocoes, container, false);

        botao_cadastrar_id = (Button) view.findViewById(R.id.novo_id);

        botao_cadastrar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FormPromocoesFragment formPromocoesFragment = new FormPromocoesFragment ();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, formPromocoesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        botao_alterar_id = (Button) view.findViewById(R.id.alterar_id);

        botao_alterar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FormPromocoesFragment formPromocoesFragment  = new FormPromocoesFragment ();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, formPromocoesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return view;
    }

}
