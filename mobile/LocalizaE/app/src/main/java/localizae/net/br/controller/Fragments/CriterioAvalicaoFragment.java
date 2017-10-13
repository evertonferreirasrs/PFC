package localizae.net.br.controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import localizae.net.br.controller.R;
import localizae.net.br.model.AvaliacaoJurado;
import localizae.net.br.model.CriterioJurado;
import localizae.net.br.retrofit.RetrofitInicializador;
import localizae.net.br.utils.LerDadosUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriterioAvalicaoFragment extends Fragment {

    private Button botao_voltar;
    private SeekBar seekBar;
    private ImageView imagem_id;
    private CriterioJurado criterio;

    public CriterioAvalicaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragment = inflater.inflate(R.layout.fragment_criterio_avalicao, container, false);
        Spinner spinnerCriterio = (Spinner) fragment.findViewById(R.id.criterio_avaliacao_fragment_spinner);
        final SeekBar seekBar = (SeekBar) fragment.findViewById(R.id.criterio_avaliacao_fragment_seekbar);
        final TextView notaValueTextView = (TextView) fragment.findViewById(R.id.criterio_avaliacao_fragment_nota_textView);
        final TextView opiniaoTextView = (TextView) fragment.findViewById(R.id.criterio_avaliacao_fragment_opiniao_textView);
        final Button botaoConfirmar = (Button)fragment.findViewById(R.id.criterio_avaliacao_fragment_button_confirm);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                notaValueTextView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getContext(), String.valueOf(seekBar.getProgress()), Toast.LENGTH_SHORT).show();

            }
        });

        Bundle args = getArguments();

        if(args != null){
            List<CriterioJurado> criterioList = (List<CriterioJurado>)args.getSerializable("criterioList");
            getActivity().setTitle(criterioList.get(0).getEstande().getTitulo());

            ArrayAdapter<CriterioJurado> adapterCriterioSpinner = new ArrayAdapter<CriterioJurado>(getContext(), android.R.layout.simple_list_item_1, criterioList);
            spinnerCriterio.setAdapter(adapterCriterioSpinner);
        }

        spinnerCriterio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, final int position, long l) {
                notaValueTextView.setText("00");
                opiniaoTextView.setText(LerDadosUsuario.lerDados(getContext()).getNome());
                botaoConfirmar.setText("Salvar");

                final CriterioJurado criterioSelecionado = (CriterioJurado)adapterView.getItemAtPosition(position);

                //Busca se existe avaliação para este criterio e seu status.
                Call buscaDeAvaliacaoCall = new RetrofitInicializador().getAvaliacaoJuradoService().getByParameter(criterioSelecionado.getUsuario().getId(), criterioSelecionado.getCriterioAvaliacao().getId(), criterioSelecionado.getEstande().getId());

                buscaDeAvaliacaoCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        List<AvaliacaoJurado> avaliacaoJuradoList =(List<AvaliacaoJurado>) response.body();
                        AvaliacaoJurado avaliacaoBuscadaNoBanco = null;

                        if(!avaliacaoJuradoList.isEmpty()){
                            avaliacaoBuscadaNoBanco = avaliacaoJuradoList.get(0);
                        }

                        if(avaliacaoBuscadaNoBanco != null){
                            if(avaliacaoBuscadaNoBanco.getStatus().toLowerCase() == "fechada"){
                                botaoConfirmar.setEnabled(false);
                                botaoConfirmar.setText("Avaliação Fechada");
                            }

                            //preencher dados
//                            opiniaoTextView.setText(criterioSelecionado.getCriterioAvaliacao().getId().toString());
                            seekBar.setProgress(avaliacaoBuscadaNoBanco.getNota().intValue());
                            //testar
                            Toast.makeText(getContext(), avaliacaoBuscadaNoBanco.getOpiniao(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        botao_voltar = (Button) fragment.findViewById(R.id.botao_voltar_id);

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

        return fragment;
    }

}
