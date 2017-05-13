/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.AvaliacaoVisitanteCriteria;
import br.com.localizae.model.entity.AvaliacaoVisitante;
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
public class AvaliacaoVisitanteDAOTest {
    
    Connection conn = null;
    AvaliacaoVisitanteDAO dao = null;
    
    public AvaliacaoVisitanteDAOTest() {
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
        dao = new AvaliacaoVisitanteDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");

        AvaliacaoVisitante entity = new AvaliacaoVisitante();
        entity.setComentario("Meu primeiro comentário de TESTE.");
        entity.setNota(5L);
        
        Estande estande = new Estande();
        estande.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        
        entity.setEstande(estande);
        entity.setUsuario(usuario);
        
        dao.create(conn, entity);
        
        AvaliacaoVisitante readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        AvaliacaoVisitante get = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = get.getId();
        
        dao.delete(conn, id);
        AvaliacaoVisitante readById = dao.readById(conn, id);
        assertNull(readById);
    }

    /**
     * Test of update method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");

        AvaliacaoVisitante entity = dao.readByCriteria(conn, null, 1L, null).get(0);
        entity.setComentario("Novo Comentário Atualizado");
        
        dao.update(conn, entity);
        AvaliacaoVisitante readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of readById method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        AvaliacaoVisitante expResult = dao.readByCriteria(conn, null, 1L, null).get(0);
        Long id = expResult.getId();

        AvaliacaoVisitante result = dao.readById(conn, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<AvaliacaoVisitante> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByUsuario() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.USUARIO_EQ, 2L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 4;
        List<AvaliacaoVisitante> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.ESTANDE_EQ, 2L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 1;
        List<AvaliacaoVisitante> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result.size());
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByNota() throws Exception {
        System.out.println("readByCriteria");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.NOTA_EQ, 2L);
        Long limit = null;
        Long offset = null;
        
        int expResult = 1;
        List<AvaliacaoVisitante> result = dao.readByCriteria(conn, criteria, limit, offset);
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
        assertEquals(mediaDeNotas.size(), 4);
        
        //Verifica todos os valores contidos no mapa
        Long media = mediaDeNotas.get(1L);
        Long expeted = 5L;
        assertEquals(expeted, media);
        
        media = mediaDeNotas.get(2L);
        expeted = 2L;
        assertEquals(media, expeted);
        
        media = mediaDeNotas.get(3L);
        expeted = 3L;
        assertEquals(media, expeted);
        
        media = mediaDeNotas.get(4L);
        expeted = 1L;
        assertEquals(media, expeted);
    }
}
