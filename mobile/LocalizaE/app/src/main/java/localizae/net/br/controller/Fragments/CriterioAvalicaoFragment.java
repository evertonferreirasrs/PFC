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

                CriterioJurado criterioSelecionado = (CriterioJurado)adapterView.getItemAtPosition(position);

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
                            Toast.makeText(getContext(), ((CriterioJurado)adapterView.getItemAtPosition(position)).getCriterioAvaliacao().getNome(), Toast.LENGTH_SHORT).show();
                            if(avaliacaoBuscadaNoBanco.getStatus().toLowerCase() == "fechada"){
                                Button botaoConfirmar = (Button)fragment.findViewById(R.id.criterio_avaliacao_fragment_button_confirm);
                                botaoConfirmar.setEnabled(false);
                                botaoConfirmar.setText("Avaliação Fechada");

                                Button botaoFecharAvaliacao = (Button) fragment.findViewById(R.id.criterio_avaliacao_fragment_button_close);
                                botaoFecharAvaliacao.setVisibility(View.INVISIBLE);
                            }

                            //preencher dados
                            opiniaoTextView.setText(avaliacaoBuscadaNoBanco.getOpiniao());
                            seekBar.setProgress(avaliacaoBuscadaNoBanco.getNota().intValue());
                        }else{
                            Toast.makeText(getContext(), "Não existe avaliação", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        opiniaoTextView.setText(call.request().url().toString());

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
