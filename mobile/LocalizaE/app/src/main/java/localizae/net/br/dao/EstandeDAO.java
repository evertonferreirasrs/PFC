package localizae.net.br.dao;

import java.util.ArrayList;
import java.util.List;

import localizae.net.br.model.Estande;

/**
 * Created by marca on 29/09/2017.
 */

public class EstandeDAO {

    public List<Estande> getEstandes() {
        List<Estande> estandeList = new ArrayList<>();

        Estande estande = new Estande(18l);
        estande.setTitulo("LocalizaÊ");
        estandeList.add(estande);

        Estande estande2 = new Estande(25l);
        estande2.setTitulo("PareFácil");
        estandeList.add(estande2);

        return estandeList;
    }
}
