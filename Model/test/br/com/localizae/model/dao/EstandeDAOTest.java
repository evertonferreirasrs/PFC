/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author marca
 */
public class EstandeDAOTest {
    private EstandeDAO dao = null;
    private Connection conn = null;
    
    public EstandeDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        this.dao = new EstandeDAO();
        this.conn = ConnectionManager.getInstance().getConnection();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class EstandeDAO.
     */
    @Test
    public void testCreate() throws Exception {

    }

    /**
     * Test of delete method, of class EstandeDAO.
     */
    @Test
    public void testDelete() throws Exception {

    }

    /**
     * Test of update method, of class EstandeDAO.
     */
    @Test
    public void testUpdate() throws Exception {

    }

    /**
     * Test of readById method, of class EstandeDAO.
     */
    @Test
    public void testReadById() throws Exception {

    }

    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {

    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByNome() throws Exception {

    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByCurso() throws Exception {

    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByPeriodo() throws Exception {

    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByNumero() throws Exception {

    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByAreaTematica() throws Exception {

    }
}
