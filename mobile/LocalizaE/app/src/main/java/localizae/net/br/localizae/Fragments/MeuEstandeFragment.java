package localizae.net.br.localizae.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import localizae.net.br.localizae.R;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meu_estande, container, false);

        return view;
    }

}
