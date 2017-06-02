/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
     */
    @Test
    public void testCreate() throws Exception {

    }

    /**
     * Test of delete method, of class UsuarioDAO.
     */
    @Test
    public void testDelete() throws Exception {

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
