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
 * @author Equipe LocalizaÊ
 */
public class PromocaoDAOTest {
    
    private Connection conn = null;
    private PromocaoDAO dao = null;
    
    public PromocaoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        conn = ConnectionManager.getInstance().getConnection();
        dao = new PromocaoDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class PromocaoDAO.
     */
    @Test
    public void testCreate() throws Exception {
        
    }

    /**
     * Test of delete method, of class PromocaoDAO.
     */
    @Test
    public void testDelete() throws Exception {

    }

    /**
     * Test of update method, of class PromocaoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        
    }

    /**
     * Test of readById method, of class PromocaoDAO.
     */
    @Test
    public void testReadById() throws Exception {
    }

    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByCriteriaAll() throws Exception {
        
    }
    
    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        
    }
    
    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        
    }

    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByAreaTematica() throws Exception {
        
    }
}
