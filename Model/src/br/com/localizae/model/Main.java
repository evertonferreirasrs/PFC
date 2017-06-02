/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model;

import br.com.localizae.model.dao.UsuarioDAO;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;

/**
 *
 * @author marca
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        System.out.println(dao.readById(conn, 5L));
        
        
    }
}
