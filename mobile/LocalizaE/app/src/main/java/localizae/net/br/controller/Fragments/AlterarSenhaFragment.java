package localizae.net.br.controller.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.support.v4.internal.view.SupportMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import localizae.net.br.Retrofit.RetrofitInicializador;
import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.utils.Cryptographer;
import localizae.net.br.utils.LerDadosUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlterarSenhaFragment extends Fragment {

    public AlterarSenhaFragment() {
        // Required empty public constructor
    }

    private Button botaoAlterarSenha;
    private EditText campoNovaSenha1;
    private EditText campoNovaSenha2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alterar_senha, container, false);

        final Usuario usuarioLogado = LerDadosUsuario.lerDados(getContext());


        TextView campoNome = (TextView) view.findViewById(R.id.fragment_alterar_senha_vNome);
        final TextView campoEmail = (TextView) view.findViewById(R.id.fragment_alterar_senha_vEmail);
        campoNome.setText(usuarioLogado.getNome());
        campoEmail.setText(usuarioLogado.getEmail());

        botaoAlterarSenha = (Button) view.findViewById(R.id.botao_alterar_senha_botaoAlterar);
        campoNovaSenha1 = (EditText) view.findViewById(R.id.fragment_alterar_senha_novaSenha1);
        campoNovaSenha2 = (EditText) view.findViewById(R.id.fragment_alterar_senha_novaSenha2);


        botaoAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView campoSenha1 = campoNovaSenha1;
                TextView campoSenha2 = campoNovaSenha2;
                String senha1 = "";
                String senha2 = "";
                senha1 = campoSenha1.getText().toString();
                senha2 = campoSenha2.getText().toString();


                if(senha1.isEmpty() || senha2.isEmpty()){
                    Toast.makeText(getContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                }else if(!senha1.equals(senha2)){
                    Toast.makeText(getContext(), "Senhas não conferem", Toast.LENGTH_SHORT).show();
                } else{
                    usuarioLogado.setSenha(senha1);
                    Call<Usuario> user = new RetrofitInicializador().getUsuarioService().alterarUser(usuarioLogado);

                    user.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            Toast.makeText(getContext(), "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(getContext(), "Serviço indisponível no momento", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        //Call<Usuario> alterarSenhaCall = new RetrofitInicializador().getUsuarioService().alterarUser(usuarioLogado);


        return view;
    }

}
