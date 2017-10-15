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

import localizae.net.br.controller.R;
import localizae.net.br.model.Usuario;
import localizae.net.br.utils.Cryptographer;
import localizae.net.br.utils.LerDadosUsuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlterarSenhaFragment extends Fragment {

    public AlterarSenhaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alterar_senha, container, false);
        final Usuario usuarioLogado = LerDadosUsuario.lerDados(getContext());

        TextView campoNome = (TextView) view.findViewById(R.id.fragment_alterar_senha_vNome);
        TextView campoEmail = (TextView) view.findViewById(R.id.fragment_alterar_senha_vEmail);
        Button botaoAlterarSenha = (Button) view.findViewById(R.id.botao_alterar_senha_botaoAlterar);

        campoNome.setText(usuarioLogado.getNome());
        campoEmail.setText(usuarioLogado.getEmail());

        botaoAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campoNovaSenha1 = ((EditText) view.findViewById(R.id.fragment_alterar_senha_novaSenha1)).getText().toString();
                String campoNovaSenha2 = ((EditText) view.findViewById(R.id.fragment_alterar_senha_novaSenha2)).getText().toString();

                if (campoNovaSenha1 == null || campoNovaSenha2 == null) {
                    Toast.makeText(getContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                } else if (campoNovaSenha1 != campoNovaSenha2) {
                    Toast.makeText(getContext(), "Senhas nao conferem", Toast.LENGTH_SHORT).show();
                } else {

                    ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Alterando dados... por favor aguarde.");
                    progressDialog.show();

                    String hash = Cryptographer.md5(campoNovaSenha1);

                    Usuario usuario = new Usuario();
                }
            }
        });
        return view;
    }

}
