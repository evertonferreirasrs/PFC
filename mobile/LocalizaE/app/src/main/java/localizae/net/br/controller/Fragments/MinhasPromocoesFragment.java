package localizae.net.br.controller.Fragments;

import android.app.ProgressDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.Promocao;
import localizae.net.br.services.endpoints.PromocaoInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Spinner minhasPromocoesSpinner;
    private ProgressDialog progressDialog;
    private PromocaoInterface promocaoService;

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
        minhasPromocoesSpinner = (Spinner) view.findViewById(R.id.fragment_minhas_promocoes_spinnerPromocoes);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Carregando");
        promocaoService = new RetrofitInicializador().getPromocaoService();


        loadPromocoesSpinner();

        novaPromocaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaFormNovaPromocao(null);
            }
        });

        alterarPromocaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Promocao promocaoSelecionada = (Promocao)minhasPromocoesSpinner.getSelectedItem();
                if(promocaoSelecionada != null){
                    irParaFormNovaPromocao(promocaoSelecionada.getId());
                }
            }
        });

        minhasPromocoesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Promocao promocao = (Promocao)minhasPromocoesSpinner.getItemAtPosition(position);
                tituloPromocaoTextView.setText(promocao.getNome());
                dataHoraPromocaoTextView.setText(new Timestamp(promocao.getDataHora()).toString());
                descricaoPromocaoTextView.setText(promocao.getDescricao());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        excluirPromocaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Promocao promocaoSelecionada = (Promocao) minhasPromocoesSpinner.getSelectedItem();

                Call<Void> deletarPromocaoCall = promocaoService.delete(promocaoSelecionada.getId());

                deletarPromocaoCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getContext(), "Promoção Excluída", Toast.LENGTH_SHORT).show();
                        loadPromocoesSpinner();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Impossível conectar ao servidor", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    private void loadPromocoesSpinner() {
        progressDialog.show();
        Call<List<Promocao>> promocaoCall = promocaoService.getAll();
        promocaoCall.enqueue(new Callback<List<Promocao>>() {
            @Override
            public void onResponse(Call<List<Promocao>> call, Response<List<Promocao>> response) {
                progressDialog.cancel();
                progressDialog.dismiss();
                List<Promocao> promocaoList = response.body();

                if(promocaoList != null){
                    ArrayAdapter<Promocao> adapterPromocao = new ArrayAdapter<Promocao>(getContext(), android.R.layout.simple_list_item_1, promocaoList);
                    minhasPromocoesSpinner.setAdapter(adapterPromocao);
                }
            }

            @Override
            public void onFailure(Call<List<Promocao>> call, Throwable t) {
                Toast.makeText(getContext(), "Falha ao carregar dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irParaFormNovaPromocao(final Long idPromocao) {
        final FormPromocoesFragment formPromocoesFragment = new FormPromocoesFragment ();


        if(idPromocao != null){
            progressDialog.show();
            Call<Promocao> buscarPromocaoCall = promocaoService.getById(idPromocao);

            buscarPromocaoCall.enqueue(new Callback<Promocao>() {
                @Override
                public void onResponse(Call<Promocao> call, Response<Promocao> response) {
                    Promocao promocao = response.body();
                    progressDialog.cancel();
                    progressDialog.dismiss();

                    Bundle args = new Bundle();
                    args.putSerializable("promocao", promocao);
                    formPromocoesFragment.setArguments(args);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_id, formPromocoesFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                @Override
                public void onFailure(Call<Promocao> call, Throwable t) {
                    Toast.makeText(getContext(), "Falha ao carregar dados", Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                    progressDialog.dismiss();
                }
            });
        }else{
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_id, formPromocoesFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}