package localizae.net.br.localizae.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import localizae.net.br.localizae.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnviarFeedbackFragment extends Fragment {


    public EnviarFeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enviar_feedback, container, false);
    }

}
