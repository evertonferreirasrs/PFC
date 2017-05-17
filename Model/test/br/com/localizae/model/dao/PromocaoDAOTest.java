/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.PromocaoCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Promocao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
        System.out.println("create");
        Promocao entity = new Promocao();
        entity.setDataHora(new Timestamp(new Date().getTime()));
        entity.setDescricao("Sorteio de brinde.");
        entity.setNome("Brinde para todos.");
        Estande estande = new Estande();
        estande.setId(1L);
        entity.setEstande(estande);
        dao.create(conn, entity);
        
        Promocao readById = dao.readById(conn, entity.getId());
        
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class PromocaoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        List<Promocao> promocaoList = dao.readByCriteria(conn, null, 1l, null);
        
        Promocao entityGetList = promocaoList.get(0);
        
        Long id = entityGetList.getId();
        
        dao.delete(conn, id);
        
        Promocao readById = dao.readById(conn, id);
        assertNull(readById);
    }

    /**
     * Test of update method, of class PromocaoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        
        Promocao entity = dao.readByCriteria(conn, null, 1L, null).get(0);
        
        entity.setDescricao("Mudei a descrição");
        
        dao.update(conn, entity);
        
        Promocao readById = dao.readById(conn, entity.getId());
        System.out.println(entity);
        System.out.println(readById);
        assertEquals(entity, readById);
        
    }

    /**
     * Test of readById method, of class PromocaoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        List<Promocao> promocaoList = dao.readByCriteria(conn, null, 1L, null);
        Long id = 0L;
        
        Promocao expResult = promocaoList.get(0);
        if(expResult != null){
            id = expResult.getId();
        }
        Promocao result = dao.readById(conn, id);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByCriteriaAll() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<Promocao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(expResult, result.size());
        
    }
    
    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(PromocaoCriteria.ESTANDE_EQ, 1L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 1;
        List<Promocao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(expResult, result.size());
        
    }
    
    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(PromocaoCriteria.NOME_EQ, "Minha promo");
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<Promocao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of readByCriteria method, of class PromocaoDAO.
     */
    @Test
    public void testReadByAreaTematica() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(PromocaoCriteria.AREA_TEMATICA_EQ, "Educação");
        Long limit = null;
        Long offset = null;
        
        int expResult = 2;
        List<Promocao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(expResult, result.size());
        
    }
}
