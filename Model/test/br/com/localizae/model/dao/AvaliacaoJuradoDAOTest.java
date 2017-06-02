/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.AvaliacaoJuradoCriteria;
import br.com.localizae.model.entity.AvaliacaoJurado;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.Estande;
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
public class AvaliacaoJuradoDAOTest {

    Connection conn = null;
    AvaliacaoJuradoDAO dao = null;

    public AvaliacaoJuradoDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        dao = new AvaliacaoJuradoDAO();
        conn = ConnectionManager.getInstance().getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testCreate() throws Exception {

    }

    /**
     * Test of delete method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        
    }

    /**
     * Test of update method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        
    }

    /**
     * Test of readById method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByUsuario() throws Exception {
        
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByNota() throws Exception {
        
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByCriterio() throws Exception {
        
    }
    
        /**
     * Test of calcularMediaDeNotas method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotas() throws Exception {
        
    }
    
    /**
     * Test of calcularMediaDeNotasByCriterio method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotasByCriterio() throws Exception {
        
    }
}
