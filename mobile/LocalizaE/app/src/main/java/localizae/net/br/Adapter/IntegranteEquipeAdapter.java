package localizae.net.br.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import localizae.net.br.controller.R;
import localizae.net.br.model.IntegranteEquipe;

/**
 * Created by Everton on 13/10/2017.
 */

public class IntegranteEquipeAdapter extends BaseAdapter{
    private List<IntegranteEquipe> equipe;
    private final Context context;

    public IntegranteEquipeAdapter(List<IntegranteEquipe> equipe, Context context) {
        this.equipe = equipe;
        this.context = context;
    }

    @Override
    public int getCount() {
        return equipe.size();
    }

    @Override
    public Object getItem(int i) {
        return equipe.get(i);
    }

    @Override
    public long getItemId(int i) {
        return equipe.get(i).getUsuario().getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        IntegranteEquipe integranteEquipe = equipe.get(i);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.item_listview_integrantesequipe, viewGroup, false);
        }

        ((TextView)view.findViewById(R.id.fragment_integrantesEquipe_vNome)).setText(integranteEquipe.getUsuario().getNome());
        ((TextView)view.findViewById(R.id.fragment_integrantesEquipe_vEmail)).setText(integranteEquipe.getUsuario().getEmail());

        return view;
    }
}
