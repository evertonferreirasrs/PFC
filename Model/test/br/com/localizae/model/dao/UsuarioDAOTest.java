/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author marca
 */
public class UsuarioDAOTest {
    
    Connection conn = null;
    UsuarioDAO dao = null;
    
    public UsuarioDAOTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        conn = ConnectionManager.getInstance().getConnection();
        dao = new UsuarioDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo administrador
     */
    @Test
    public void testCreateAdministrador() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo visitante
     */
    @Test
    public void testCreateVisitante() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo jurado
     */
    @Test
    public void testCreateJurado() throws Exception {

    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo expositor
     */
    @Test
    public void testCreateExpositor() throws Exception {

    }

    /**
     * Test of delete method, of class UsuarioDAO.
     * Teste para deletar Usuario
     */
    @Test
    public void testDelete() throws Exception {
        /*Criando cenário para testes*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        dao.delete(conn, usuario.getId());
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertNull(usuarioLido);
    }

    /**
     * Test of update method, of class UsuarioDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        
    }

    /**
     * Test of readById method, of class UsuarioDAO.
     */
    @Test
    public void testReadById() throws Exception {

    }

    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadAll() throws Exception {

    }
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByEmail() throws Exception {

    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByNome() throws Exception {

    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadBySenha() throws Exception {

    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByEmailESenha() throws Exception {

    }  
}
