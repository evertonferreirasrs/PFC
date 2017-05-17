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
        System.out.println("create");

        AvaliacaoJurado entity = new AvaliacaoJurado();
        entity.setNota(5L);
        entity.setOpiniao("Boa comunicação com os visitantes");

        InformacoesParaAvaliacao informacoesParaAvaliacao = new InformacoesParaAvaliacao();
        informacoesParaAvaliacao.setId(1L);

        entity.setInformacoesParaAvaliacao(informacoesParaAvaliacao);

        dao.create(conn, entity);
        AvaliacaoJurado readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        AvaliacaoJurado get = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = get.getId();

        dao.delete(conn, id);
        AvaliacaoJurado readById = dao.readById(conn, id);
        assertNull(readById);
    }

    /**
     * Test of update method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");

        AvaliacaoJurado entity = dao.readByCriteria(conn, null, 1L, null).get(0);
        entity.setOpiniao("Primeira opinião alterada no Banco");

        dao.update(conn, entity);
        AvaliacaoJurado readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of readById method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        AvaliacaoJurado expResult = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = expResult.getId();

        AvaliacaoJurado result = dao.readById(conn, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;

        int expResult = 3;
        List<AvaliacaoJurado> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByUsuario() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.USUARIO_EQ, 4L);
        Long limit = null;
        Long offset = null;

        int expResult = 3;
        List<AvaliacaoJurado> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByNota() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.NOTA_EQ, 2L);
        Long limit = null;
        Long offset = null;

        int expResult = 1;
        List<AvaliacaoJurado> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.ESTANDE_EQ, 2L);
        Long limit = null;
        Long offset = null;

        int expResult = 1;
        List<AvaliacaoJurado> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByCriterio() throws Exception {
        System.out.println("readByCriteria");

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.CRITERIO_EQ, 3L);
        Long limit = null;
        Long offset = null;

        int expResult = 1;
        List<AvaliacaoJurado> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
        /**
     * Test of calcularMediaDeNotas method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotas() throws Exception {
        System.out.println("calcularMediaDeNotas");
        
        Map<Long, Long> mediaDeNotas = dao.calcularMediaDeNotas(conn);
        //Verifica a quantidade de respostas
        assertEquals(mediaDeNotas.size(), 3);
        
        //Verifica todos os valores contidos no mapa        
        Long media = mediaDeNotas.get(2L);
        Long expeted = 2L;
        assertEquals(media, expeted);
        
        media = mediaDeNotas.get(3L);
        expeted = 5L;
        assertEquals(media, expeted);
        
        media = mediaDeNotas.get(4L);
        expeted = 3L;
        assertEquals(media, expeted);
    }
    
    /**
     * Test of calcularMediaDeNotasByCriterio method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotasByCriterio() throws Exception {
        System.out.println("calcularMediaDeNotasByCriterio");
        
        Map<InformacoesParaAvaliacao, Long> mediaDeNotas = dao.calcularMediaDeNotasByCriterio(conn);

        //Verifica a quantidade de respostas
        assertEquals(mediaDeNotas.size(), 3);
        
        //Verifica todos os valores contidos no mapa
        InformacoesParaAvaliacao info = new InformacoesParaAvaliacao();
        
        CriterioAvaliacao criterioAvaliacao = new CriterioAvaliacao();
        criterioAvaliacao.setId(2L);
        info.setCriterioAvaliacao(criterioAvaliacao);
        
        Estande estande = new Estande();
        estande.setId(2L);
        info.setEstande(estande);
        info.setUsuario(new Usuario());
        
        Long media = mediaDeNotas.get(info);
        Long expeted = 2L;
        assertEquals(media, expeted);
        
        
        info = new InformacoesParaAvaliacao();
        criterioAvaliacao = new CriterioAvaliacao();
        criterioAvaliacao.setId(3L);
        info.setCriterioAvaliacao(criterioAvaliacao);
        
        estande = new Estande();
        estande.setId(3L);
        info.setEstande(estande);
        info.setUsuario(new Usuario());
        
        media = mediaDeNotas.get(info);
        expeted = 5L;
        assertEquals(media, expeted);
        
        
        info = new InformacoesParaAvaliacao();
        criterioAvaliacao = new CriterioAvaliacao();
        criterioAvaliacao.setId(2L);
        info.setCriterioAvaliacao(criterioAvaliacao);
        
        estande = new Estande();
        estande.setId(4L);
        info.setEstande(estande);
        info.setUsuario(new Usuario());
        
        media = mediaDeNotas.get(info);
        expeted = 3L;
        assertEquals(media, expeted);
    }
}
