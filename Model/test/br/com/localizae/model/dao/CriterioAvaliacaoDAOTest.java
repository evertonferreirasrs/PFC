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
     * Teste para criar um registro no banco de dados
     */
    @Test
    public void testCreate() throws Exception {
        CriterioAvaliacao entity = new CriterioAvaliacao();
        
        entity.setNome("Explanação");
        entity.setPeso(2L);
        
        dao.create(conn, entity);
        
        CriterioAvaliacao readById = dao.readById(conn, entity.getId());
        
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class CriterioAvaliacaoDAO.
     * Teste para deletar um registro inserido no banco de dados
     */
    @Test
    public void testDelete() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(2L);
        
        dao.create(conn, criterio);
        /*Criando cenário de teste*/
        
        dao.delete(conn, criterio.getId());
        
        CriterioAvaliacao readById = dao.readById(conn, criterio.getId());
        
        assertNull(readById);
    }

    /**
     * Test of update method, of class CriterioAvaliacaoDAO.
     * Teste para atualizar um resgistro inserido no banco de dados.
     */
    @Test
    public void testUpdate() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao entity = new CriterioAvaliacao();
        entity.setNome("Explanação");
        entity.setPeso(2L);
        
        dao.create(conn, entity);
        /*Criando cenário de teste*/
        
        entity.setPeso(3L);
        
        dao.update(conn, entity);
        
        CriterioAvaliacao readById = dao.readById(conn, entity.getId());
        
        assertEquals(entity, readById);
    }

    /**
     * Test of readById method, of class CriterioAvaliacaoDAO.
     * Teste para buscar um criterio pelo seu id no banco de dados.
     */
    @Test
    public void testReadById() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao expResult = new CriterioAvaliacao();
        
        expResult.setNome("Explanação");
        expResult.setPeso(2L);
        
        dao.create(conn, expResult);
        /*Criando cenário de teste*/
        
        CriterioAvaliacao result = dao.readById(conn, expResult.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     * Teste para buscar todos os criterios inseridos no banco de dados.
     */
    @Test
    public void testReadAll() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(2L);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(4L);
        
        dao.create(conn, criterio);
        dao.create(conn, criterio2);
        /*Criando cenário de teste*/
        
        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        
        int qtdeExpResult = 2;
        
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(qtdeExpResult, result.size());
        assertEquals(criterio, result.get(0));
        assertEquals(criterio2, result.get(1));
    }
    
    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     * Teste para buscar criterios no banco de dados filtrando por nome.
     */
    @Test
    public void testReadByNome() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(2L);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(4L);
        
        dao.create(conn, criterio);
        dao.create(conn, criterio2);
        /*Criando cenário de teste*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.NOME_EQ, "Documentação");
        Long limit = null;
        Long offset = null;
        
        int qtdeExpResult = 1;
        
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(qtdeExpResult, result.size());
        assertEquals(criterio2, result.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     * Teste para buscar criterios filtrando por pesos iguais aos passados.
     */
    @Test
    public void testReadByPesoEq() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(2L);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(4L);
        
        dao.create(conn, criterio);
        dao.create(conn, criterio2);
        /*Criando cenário de teste*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.PESO_EQ, 2L);
        Long limit = null;
        Long offset = null;
        
        int qtdeExpResult = 1;
        
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(qtdeExpResult, result.size());
        assertEquals(criterio, result.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     * Teste para buscar criterios filtrando pelo peso menor que o passado por parametro
     */
    @Test
    public void testReadByPesoLt() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(2L);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(4L);
        
        dao.create(conn, criterio);
        dao.create(conn, criterio2);
        /*Criando cenário de teste*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.PESO_LT, 3L);
        Long limit = null;
        Long offset = null;
        
        int qtdeExpResult = 1;
        
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(qtdeExpResult, result.size());
        assertEquals(criterio, result.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class CriterioAvaliacaoDAO.
     * Teste para buscar criterios filtrando por peso maior que o passado por parametro.
     */
    @Test
    public void testReadByPesoGt() throws Exception {
        /*Criando cenário de teste*/
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(2L);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(4L);
        
        dao.create(conn, criterio);
        dao.create(conn, criterio2);
        /*Criando cenário de teste*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(CriterioAvaliacaoCriteria.PESO_GT, 2L);
        Long limit = null;
        Long offset = null;
        
        int qtdeExpResult = 1;
        
        List<CriterioAvaliacao> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(qtdeExpResult, result.size());
        assertEquals(criterio2, result.get(0));
    }
}
