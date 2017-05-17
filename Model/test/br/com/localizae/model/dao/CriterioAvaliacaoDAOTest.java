/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.CriterioAvaliacaoCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
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
public class CriterioAvaliacaoDAOTest {
    Connection conn = null;
    CriterioAvaliacaoDAO dao = null;
    
    public CriterioAvaliacaoDAOTest() {
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
        dao = new CriterioAvaliacaoDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class CriterioAvaliacaoDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        CriterioAvaliacao entity = new CriterioAvaliacao();
        entity.setNome("Explanação");
        
        dao.create(conn, entity);
        
        CriterioAvaliacao readById = dao.readById(conn, entity.getId());
        
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class CriterioAvaliacaoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        CriterioAvaliacao readByCriteria = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = readByCriteria.getId();
        
        dao.delete(conn, id);
        
        CriterioAvaliacao readById = dao.readById(conn, id);
        
        assertNull(readById);
    }

    /**
     * Test of update method, of class CriterioAvaliacaoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        
        CriterioAvaliacao entity = dao.readByCriteria(conn, null, 1L, null).get(0);
        entity.setNome("Critério 1");
        
        dao.update(conn, entity);
        
        CriterioAvaliacao readById = dao.readById(conn, entity.getId());
        
        assertEquals(entity, readById);
    }

    /**
     * Test of readById method, of class CriterioAvaliacaoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        
        CriterioAvaliacao expResult = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = expResult.getId();
        
        CriterioAvaliacao result = dao.readById(conn, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
        
    }
    
    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.NOME_EQ, "Organização do Estande");
        Long limit = null;
        Long offset = null;
        
        int expResult = 1;
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
        
    }
}
