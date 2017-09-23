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
public class AvaliarEstandeFragment extends Fragment {

    private Button botaoAvaliar;

    public AvaliarEstandeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(" Avaliar Estandes");

        View view = inflater.inflate(R.layout.fragment_avaliar_estande, container, false);

        botaoAvaliar = (Button) view.findViewById(R.id.avaliar_id);

        botaoAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CriterioAvalicaoFragment criterioAvalicaoFragment = new CriterioAvalicaoFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, criterioAvalicaoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
