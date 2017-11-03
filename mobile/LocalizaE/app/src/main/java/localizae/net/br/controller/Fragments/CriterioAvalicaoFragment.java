package localizae.net.br.controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import localizae.net.br.controller.R;
import localizae.net.br.model.AvaliacaoJurado;
import localizae.net.br.model.CriterioAvaliacao;
import localizae.net.br.model.CriterioJurado;
import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.model.Estande;
import localizae.net.br.services.endpoints.AvaliacaoJuradoInterface;
import localizae.net.br.utils.ControladorDadosUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriterioAvalicaoFragment extends Fragment {

    private Button botao_voltar;
    private SeekBar seekBar;
    private ImageView imagem_id;
    private CriterioJurado criterio;
    private FragmentManager manager;

    public CriterioAvalicaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragment = inflater.inflate(R.layout.fragment_criterio_avalicao, container, false);
        final Spinner spinnerCriterio = (Spinner) fragment.findViewById(R.id.criterio_avaliacao_fragment_spinner);
        final SeekBar seekBar = (SeekBar) fragment.findViewById(R.id.criterio_avaliacao_fragment_seekbar);
        final TextView notaValueTextView = (TextView) fragment.findViewById(R.id.criterio_avaliacao_fragment_nota_textView);
        final TextView opiniaoTextView = (TextView) fragment.findViewById(R.id.criterio_avaliacao_fragment_opiniao_textView);
        final Button botaoConfirmar = (Button) fragment.findViewById(R.id.criterio_avaliacao_fragment_button_confirm);
        final AvaliacaoJuradoInterface avaliacaoJuradoService = new RetrofitInicializador().getAvaliacaoJuradoService();
        manager = getActivity().getSupportFragmentManager();

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

            }
        });

        Bundle args = getArguments();
        Estande estandeSelecionado = null;
        if (args != null) {
            List<CriterioJurado> criterioList = (List<CriterioJurado>) args.getSerializable("criterioList");
            estandeSelecionado = criterioList.get(0).getEstande();
            getActivity().setTitle(estandeSelecionado.getTitulo());

            ArrayAdapter<CriterioJurado> adapterCriterioSpinner = new ArrayAdapter<CriterioJurado>(getContext(), android.R.layout.simple_list_item_1, criterioList);
            spinnerCriterio.setAdapter(adapterCriterioSpinner);
        }

        final Estande finalEstandeSelecionado = estandeSelecionado;
        spinnerCriterio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, final int position, long l) {
                notaValueTextView.setText("00");
                opiniaoTextView.setText("");
                botaoConfirmar.setText("Salvar");
                botaoConfirmar.setEnabled(true);
                seekBar.setProgress(0);

                final CriterioJurado criterioSelecionado = (CriterioJurado) adapterView.getItemAtPosition(position);

                //Busca se existe avaliação para este criterio e seu status.

                Call buscaDeAvaliacaoCall = avaliacaoJuradoService.getByParameter(criterioSelecionado.getUsuario().getId(), criterioSelecionado.getCriterioAvaliacao().getId(), criterioSelecionado.getEstande().getId());

                buscaDeAvaliacaoCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        List<AvaliacaoJurado> avaliacaoJuradoList = (List<AvaliacaoJurado>) response.body();
                        AvaliacaoJurado avaliacaoBuscadaNoBanco = null;

                        if (!avaliacaoJuradoList.isEmpty()) {
                            avaliacaoBuscadaNoBanco = avaliacaoJuradoList.get(0);
                        }

                        final AvaliacaoJurado finalAvaliacaoBuscadaNoBanco = avaliacaoBuscadaNoBanco;

                        if (avaliacaoBuscadaNoBanco != null) {
                            Toast.makeText(getContext(), avaliacaoBuscadaNoBanco.getStatus(), Toast.LENGTH_SHORT).show();
                            if (avaliacaoBuscadaNoBanco.getStatus().equals("fechada")) {
                                botaoConfirmar.setEnabled(false);
                                botaoConfirmar.setText("Avaliação Fechada");
                            }

                            //preencher dados
                            opiniaoTextView.setText(avaliacaoBuscadaNoBanco.getOpiniao());
                            seekBar.setProgress(avaliacaoBuscadaNoBanco.getNota().intValue());


                            botaoConfirmar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Salvar atualizando

                                    finalAvaliacaoBuscadaNoBanco.setOpiniao(opiniaoTextView.getText().toString());
                                    finalAvaliacaoBuscadaNoBanco.setNota(new Long(seekBar.getProgress()));
                                    finalAvaliacaoBuscadaNoBanco.setStatus("fechada");

                                    Call<AvaliacaoJurado> atualizarAvaliacaoCall = avaliacaoJuradoService.put(finalAvaliacaoBuscadaNoBanco);
                                    atualizarAvaliacaoCall.enqueue(new Callback<AvaliacaoJurado>() {
                                        @Override
                                        public void onResponse(Call<AvaliacaoJurado> call, Response<AvaliacaoJurado> response) {
                                            Toast.makeText(getContext(), "Atualizado com sucesso.", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<AvaliacaoJurado> call, Throwable t) {
                                            Toast.makeText(getContext(), "Impossível atualizar no momento.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        } else {


                            botaoConfirmar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AvaliacaoJurado avaliacaoParaSalvar = new AvaliacaoJurado();
                                    avaliacaoParaSalvar.setOpiniao(opiniaoTextView.getText().toString());
                                    avaliacaoParaSalvar.setNota(new Long(seekBar.getProgress()));
                                    avaliacaoParaSalvar.setStatus("fechada");
                                    avaliacaoParaSalvar.setUsuario(ControladorDadosUsuario.lerDados(getContext()));
                                    avaliacaoParaSalvar.setEstande(finalEstandeSelecionado);
                                    avaliacaoParaSalvar.setCriterio(((CriterioJurado) spinnerCriterio.getSelectedItem()).getCriterioAvaliacao());

                                    Call<AvaliacaoJurado> atualizarAvaliacaoCall = avaliacaoJuradoService.post(avaliacaoParaSalvar);
                                    atualizarAvaliacaoCall.enqueue(new Callback<AvaliacaoJurado>() {
                                        @Override
                                        public void onResponse(Call<AvaliacaoJurado> call, Response<AvaliacaoJurado> response) {
                                            Toast.makeText(getContext(), "Atualizado com sucesso.", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<AvaliacaoJurado> call, Throwable t) {
                                            Toast.makeText(getContext(), "Impossível atualizar no momento.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
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
                //Buscar avaliacao
                CriterioJurado selectedCriterio = (CriterioJurado) spinnerCriterio.getSelectedItem();

                Call buscaDeAvaliacaoCall = avaliacaoJuradoService.getByParameter(selectedCriterio.getUsuario().getId(), selectedCriterio.getCriterioAvaliacao().getId(), selectedCriterio.getEstande().getId());
                buscaDeAvaliacaoCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        List<AvaliacaoJurado> avaliacaoJuradoList = (List<AvaliacaoJurado>) response.body();
                        AvaliacaoJurado avaliacaoBuscadaNoBanco = null;

                        if (!avaliacaoJuradoList.isEmpty()) {
                            avaliacaoBuscadaNoBanco = avaliacaoJuradoList.get(0);
                        }
                        //Se existir salvar atualizando
                        if (avaliacaoBuscadaNoBanco != null) {
                            if (!avaliacaoBuscadaNoBanco.getStatus().equals("fechada")) {
                                avaliacaoBuscadaNoBanco.setNota(new Long(seekBar.getProgress()));
                                avaliacaoBuscadaNoBanco.setOpiniao(opiniaoTextView.getText().toString());
                                avaliacaoBuscadaNoBanco.setStatus("aberta");
                                Call<AvaliacaoJurado> putCall = avaliacaoJuradoService.put(avaliacaoBuscadaNoBanco);
                                putCall.enqueue(new Callback<AvaliacaoJurado>() {
                                    @Override
                                    public void onResponse(Call<AvaliacaoJurado> call, Response<AvaliacaoJurado> response) {
                                        finishFragment();
                                    }

                                    @Override
                                    public void onFailure(Call<AvaliacaoJurado> call, Throwable t) {

                                    }
                                });
                            } else {
                                finishFragment();
                            }

                        }
                        //Senão salvar criando
                        else {
                            avaliacaoBuscadaNoBanco = new AvaliacaoJurado();
//                            Toast.makeText(getContext(), String.valueOf(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
                            avaliacaoBuscadaNoBanco.setNota(new Long(seekBar.getProgress()));
                            avaliacaoBuscadaNoBanco.setOpiniao(opiniaoTextView.getText().toString());
                            avaliacaoBuscadaNoBanco.setStatus("aberta");
                            avaliacaoBuscadaNoBanco.setUsuario(ControladorDadosUsuario.lerDados(getContext()));
                            avaliacaoBuscadaNoBanco.setEstande(finalEstandeSelecionado);
                            avaliacaoBuscadaNoBanco.setCriterio(((CriterioJurado) spinnerCriterio.getSelectedItem()).getCriterioAvaliacao());

                            Call<AvaliacaoJurado> postCall = avaliacaoJuradoService.post(avaliacaoBuscadaNoBanco);
                            postCall.enqueue(new Callback<AvaliacaoJurado>() {
                                @Override
                                public void onResponse(Call<AvaliacaoJurado> call, Response<AvaliacaoJurado> response) {
                                    finishFragment();
                                }

                                @Override
                                public void onFailure(Call<AvaliacaoJurado> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });

        return fragment;
    }

    private void finishFragment() {
       if(manager.getBackStackEntryCount() > 0){
           manager.popBackStack();
       }
    }

}
