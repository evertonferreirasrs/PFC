/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model;

import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.dao.UsuarioDAO;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marca
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_EQ, "Marcos Antônio");
        List<Usuario> usuarioList = dao.readByCriteria(conn, criteria, 1L, 0L);
        Usuario entity = usuarioList.get(0);
        entity.setEmail("marcos@localizae.net.br");
        
        dao.update(conn, entity);
        
        
        
    }
}
