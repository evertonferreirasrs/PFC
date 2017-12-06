package localizae.net.br.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import localizae.net.br.controller.R;
import localizae.net.br.model.AvaliacaoVisitante;

/**
 * Created by Everton on 13/10/2017.
 */

public class AvaliacaoEstandeAdapter extends BaseAdapter {
    private final List<AvaliacaoVisitante> avaliacaoVisitantes;
    private final Context context;

    public AvaliacaoEstandeAdapter(Context context, List<AvaliacaoVisitante> avaliacaoVisitantes) {
        this.avaliacaoVisitantes = avaliacaoVisitantes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return avaliacaoVisitantes.size();
    }

    @Override
    public Object getItem(int position) {
        return avaliacaoVisitantes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return avaliacaoVisitantes.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AvaliacaoVisitante avaliacaoVisitante = avaliacaoVisitantes.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_listview_avaliacaovisitante, parent, false);
        }

        TextView tituloUsuario = (TextView) view.findViewById(R.id.fragment_avaliacaoVisitante_Estande);
        tituloUsuario.setText("Usuário: ");

        TextView campoUsuario = (TextView) view.findViewById(R.id.fragment_avaliacaoVisitante_vEstande);
        campoUsuario.setText(avaliacaoVisitante.getUsuario().getNome());

        RatingBar campoNota = (RatingBar) view.findViewById(R.id.fragment_avaliacaoVisitante_vNota);
        campoNota.setRating(avaliacaoVisitante.getNota());

        TextView campoOpiniao = (TextView) view.findViewById(R.id.fragment_avaliacaoVisitante_vComentario);
        campoOpiniao.setText(avaliacaoVisitante.getOpiniao());

        return view;
    }
}
