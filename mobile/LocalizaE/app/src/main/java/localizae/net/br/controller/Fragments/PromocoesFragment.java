package localizae.net.br.controller.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import localizae.net.br.controller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromocoesFragment extends Fragment {


    public PromocoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_promocoes, container, false);



        return view;
    }

}
