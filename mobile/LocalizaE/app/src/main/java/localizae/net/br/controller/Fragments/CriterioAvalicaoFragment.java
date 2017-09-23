package localizae.net.br.controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import localizae.net.br.controller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriterioAvalicaoFragment extends Fragment {

    private Button botao_voltar;
    private SeekBar seekBar;
    private ImageView imagem_id;


    public CriterioAvalicaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_criterio_avalicao, container, false);

        getActivity().setTitle(" (Nome do Estande)");

        seekBar = (SeekBar) view.findViewById(R.id.seekBar_id);
        imagem_id = (ImageView) view.findViewById(R.id.imagem_nota_id);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int valorProgresso = i;

                if(valorProgresso == 1){
                    imagem_id.setImageResource(R.drawable.n0);
                }else if(valorProgresso == 2){
                    imagem_id.setImageResource(R.drawable.n10);
                }else if(valorProgresso == 3){
                    imagem_id.setImageResource(R.drawable.n20);
                }else if(valorProgresso == 4){
                    imagem_id.setImageResource(R.drawable.n30);
                }else if(valorProgresso == 5){
                    imagem_id.setImageResource(R.drawable.n40);
                }else if(valorProgresso == 6){
                    imagem_id.setImageResource(R.drawable.n50);
                }else if(valorProgresso == 7){
                    imagem_id.setImageResource(R.drawable.n60);
                }else if(valorProgresso == 8){
                    imagem_id.setImageResource(R.drawable.n70);
                }else if(valorProgresso == 9){
                    imagem_id.setImageResource(R.drawable.n80);
                }else if(valorProgresso == 10){
                    imagem_id.setImageResource(R.drawable.n90);
                }else if(valorProgresso == 11){
                    imagem_id.setImageResource(R.drawable.n100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        botao_voltar = (Button) view.findViewById(R.id.botao_voltar_id);

        botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AvaliarEstandeFragment avaliarEstandeFragment = new AvaliarEstandeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_id, avaliarEstandeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
