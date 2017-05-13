/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.InformacoesParaAvaliacaoCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.InformacoesParaAvaliacao;
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
public class InformacoesParaAvaliacaoDAOTest {
    Connection conn = null;
    InformacoesParaAvaliacaoDAO dao = null;
    
    public InformacoesParaAvaliacaoDAOTest() {
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
        dao = new InformacoesParaAvaliacaoDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        InformacoesParaAvaliacao entity = new InformacoesParaAvaliacao();
        Usuario usuario = new Usuario();
        usuario.setId(4L);
        
        Estande estande = new Estande();
        estande.setId(2L);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setId(2L);
        
        entity.setCriterioAvaliacao(criterio);
        entity.setEstande(estande);
        entity.setUsuario(usuario);
        
        dao.create(conn, entity);
        
        InformacoesParaAvaliacao readById = dao.readById(conn, entity.getId());
        System.out.println(readById);
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        InformacoesParaAvaliacao get = dao.readByCriteria(conn, null, 1L, null).get(0);
        
        Long id = get.getId();
        
        
        dao.delete(conn, id);
        
        InformacoesParaAvaliacao readById = dao.readById(conn, id);
        assertNull(readById);
    }

    /**
     * Test of update method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        
        InformacoesParaAvaliacao entity = dao.readByCriteria(conn, null, 1L, null).get(0);
        Estande estande = new Estande();
        estande.setId(2L);
        entity.setEstande(estande);
        
        dao.update(conn, entity);
        
        InformacoesParaAvaliacao readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of readById method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        InformacoesParaAvaliacao expResult = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = expResult.getId();
        
        InformacoesParaAvaliacao result = dao.readById(conn, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of readByCriteria method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<InformacoesParaAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testReadByUsuario() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(InformacoesParaAvaliacaoCriteria.USUARIO_EQ, 4L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<InformacoesParaAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(InformacoesParaAvaliacaoCriteria.ESTANDE_EQ, 4L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 1;
        List<InformacoesParaAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class InformacoesParaAvaliacaoDAO.
     */
    @Test
    public void testReadByCriterio() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(InformacoesParaAvaliacaoCriteria.CRITERIO_EQ, 3L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 1;
        List<InformacoesParaAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
}
