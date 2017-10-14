package localizae.net.br.controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import localizae.net.br.controller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhasPromocoesFragment extends Fragment {

    private Button novaPromocaoButton;
    private Button alterarPromocaoButton;
    private Button excluirPromocaoButton;
    private ImageView imagemPromocaoImageView;
    private TextView tituloPromocaoTextView;
    private TextView dataHoraPromocaoTextView;
    private TextView descricaoPromocaoTextView;

    public MinhasPromocoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(" Minhas Promoções");

        View view = inflater.inflate(R.layout.fragment_minhas_promocoes, container, false);
        novaPromocaoButton = (Button) view.findViewById(R.id.fragment_minhas_promocoes_buttonNovaPromocao);
        alterarPromocaoButton = (Button) view.findViewById(R.id.fragment_minhas_promocoes_buttonAlterar);
        excluirPromocaoButton = (Button) view.findViewById(R.id.fragment_minhas_promocoes_buttonExcluir);
        imagemPromocaoImageView = (ImageView) view.findViewById(R.id.fragment_minhas_promocoes_imagePromocao);
        tituloPromocaoTextView = (TextView) view.findViewById(R.id.fragment_minhas_promocoes_textViewTitulo);
        dataHoraPromocaoTextView = (TextView) view.findViewById(R.id.fragment_minhas_promocoes_textViewDataHora);
        descricaoPromocaoTextView = (TextView) view.findViewById(R.id.fragment_minhas_promocoes_textViewDescricao);


        return view;
    }

    private void irParaFormNovaPromocao() {
        FormPromocoesFragment formPromocoesFragment = new FormPromocoesFragment ();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_id, formPromocoesFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}