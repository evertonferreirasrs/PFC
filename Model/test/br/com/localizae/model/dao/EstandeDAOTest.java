/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.EstandeCriteria;
import br.com.localizae.model.entity.Estande;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
        System.out.println("create");
        
        Estande entity = new Estande();
        entity.setAreaTematica("Educação");
        entity.setCurso("Pedagogia");
        entity.setDescricao("Descrição do estande em questão");
        entity.setTitulo("Um nome para o estande");
        entity.setNumero(45L);
        entity.setPeriodo(4L);
        entity.setEquipe(new ArrayList<>());
        System.out.println(entity);

        dao.create(conn, entity);
        
        Estande readById = dao.readById(conn, entity.getId());
        System.out.println(readById);
        assertEquals(entity, readById);
        
    }

    /**
     * Test of delete method, of class EstandeDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        Long id = 0L;
        List<Estande> estandeList = dao.readByCriteria(conn, null, 1L, null);
        Estande entity = estandeList.get(0);
        if(entity != null){
            id = entity.getId();
        }

        dao.delete(conn, id);
        
        Estande readById = dao.readById(conn, id);
        assertNull(readById);
    }

    /**
     * Test of update method, of class EstandeDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");

        Estande entity = dao.readByCriteria(conn, null, 1L, null).get(0);
        if(entity != null){
            entity.setCurso("Sistemas de Informação");
        }

        dao.update(conn, entity);
        Estande readById = dao.readById(conn, entity.getId());
        
        assertEquals(entity, readById);
    }

    /**
     * Test of readById method, of class EstandeDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        
        Long id = null;
        List<Estande> estandeList = dao.readByCriteria(conn, null, 1L, 0L);
        Estande entity = estandeList.get(0);

        Estande result = dao.readById(conn, entity.getId());
        assertEquals(entity, result);

    }

    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;

        int expResult = 4;
        List<Estande> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.NOME_EQ, "Old Game");
        Long limit = null;
        Long offset = null;

        int expResult = 1;
        List<Estande> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByCurso() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.CURSO_EQ, "Sistemas de Informação");
        Long limit = null;
        Long offset = null;

        int expResult = 2;
        List<Estande> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByPeriodo() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.PERIODO_EQ, 2L);
        Long limit = null;
        Long offset = null;

        int expResult = 4;
        List<Estande> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByNumero() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.NUMERO_EQ, 43L);
        Long limit = null;
        Long offset = null;

        int expResult = 1;
        List<Estande> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByAreaTematica() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.AREATEMATICA_EQ, "Educação");
        Long limit = null;
        Long offset = null;

        int expResult = 2;
        List<Estande> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
}
