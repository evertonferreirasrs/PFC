/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model;

import br.com.localizae.model.dao.AvaliacaoVisitanteDAO;
import java.sql.Connection;

/**
 *
 * @author marca
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        AvaliacaoVisitanteDAO dao = new AvaliacaoVisitanteDAO();



        conn.commit();
        conn.close();
    }
}
