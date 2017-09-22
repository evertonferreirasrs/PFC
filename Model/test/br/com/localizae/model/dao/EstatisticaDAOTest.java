/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.EstatisticaCriteria;
import br.com.localizae.model.entity.Estatistica;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class EstatisticaDAOTest {
    private Connection conn;
    private EstatisticaDAO dao;
    
    public EstatisticaDAOTest() {
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
        dao = new EstatisticaDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class EstatisticaDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        dao.create(conn, entity);
        Estatistica readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of delete method, of class EstatisticaDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        dao.create(conn, entity);
        
        dao.delete(conn, entity.getId());
        Estatistica readById = dao.readById(conn, entity.getId());
        assertNull(readById);
        
    }

    

    /**
     * Test of readById method, of class EstatisticaDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        dao.create(conn, entity);
        Estatistica readById = dao.readById(conn, entity.getId());
        assertEquals(entity, readById);
    }

    /**
     * Test of readByCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testReadByCriteriaAll() throws Exception {
        System.out.println("readByCriteria");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        Estatistica entity2 = new Estatistica();
        entity2.setPosicaoX(15L);
        entity2.setPosicaoY(50L);
        entity2.setDataHora(new Timestamp(2017, 10, 25, 21, 35, 0, 0).getTime());
        entity2.setUsuario(usuario);
        
        dao.create(conn, entity);
        dao.create(conn, entity2);        
        
        Map<Enum, Object> criteria = new HashMap<>();
        Long limit = 0l;
        Long offset = 0l;
        
        List<Estatistica> expResult = new ArrayList<>();
        expResult.add(entity);
        expResult.add(entity2);
        
        List<Estatistica> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of readByCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testReadByCriteriaByParameter() throws Exception {
        System.out.println("readByCriteria");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        Estatistica entity2 = new Estatistica();
        entity2.setPosicaoX(15L);
        entity2.setPosicaoY(50L);
        entity2.setDataHora(new Timestamp(2017, 10, 25, 21, 35, 0, 0).getTime());
        entity2.setUsuario(usuario);
        
        dao.create(conn, entity);
        dao.create(conn, entity2);        
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstatisticaCriteria.POSX_EQ, 15L);
        criteria.put(EstatisticaCriteria.POSY_EQ, 15L);
        
        Long limit = 0l;
        Long offset = 0l;
        
        List<Estatistica> expResult = new ArrayList<>();
        expResult.add(entity);
        
        
        List<Estatistica> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of readByCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testReadByCriteriaWithLimit() throws Exception {
        System.out.println("readByCriteria");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        Estatistica entity2 = new Estatistica();
        entity2.setPosicaoX(15L);
        entity2.setPosicaoY(50L);
        entity2.setDataHora(new Timestamp(2017, 10, 25, 21, 35, 0, 0).getTime());
        entity2.setUsuario(usuario);
        
        dao.create(conn, entity);
        dao.create(conn, entity2);        
        
        Map<Enum, Object> criteria = new HashMap<>();
        
        Long limit = 1L;
        Long offset = 0l;
        
        List<Estatistica> expResult = new ArrayList<>();
        expResult.add(entity);
        
        List<Estatistica> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of readByCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testReadByCriteriaWithLimitAndOffset() throws Exception {
        System.out.println("readByCriteria");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Visitante");
        tipo.setId(2L);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(tipo);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, usuario);
        
        Estatistica entity = new Estatistica();
        entity.setPosicaoX(15L);
        entity.setPosicaoY(15L);
        entity.setDataHora(new Timestamp(2017, 10, 25, 21, 30, 0, 0).getTime());
        entity.setUsuario(usuario);
        
        Estatistica entity2 = new Estatistica();
        entity2.setPosicaoX(15L);
        entity2.setPosicaoY(50L);
        entity2.setDataHora(new Timestamp(2017, 10, 25, 21, 35, 0, 0).getTime());
        entity2.setUsuario(usuario);
        
        dao.create(conn, entity);
        dao.create(conn, entity2);        
        
        Map<Enum, Object> criteria = new HashMap<>();
        
        Long limit = 1L;
        Long offset = 1L;
        
        List<Estatistica> expResult = new ArrayList<>();
        expResult.add(entity2);
        
        List<Estatistica> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(expResult, result);
    }

    /**
     * Test of applyCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testApplyCriteriaNoCriteria() throws Exception {
        System.out.println("applyCriteria");
        Map<Enum, Object> criteria = null;
        List<Object> args = new ArrayList<>();

        String expResult = "";
        String result = dao.applyCriteria(criteria, args);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of applyCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testApplyCriteriaOneCriteria() throws Exception {
        System.out.println("applyCriteria");
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstatisticaCriteria.POSX_EQ, 15L);
        
        List<Object> args = new ArrayList<>();

        String expResult = " AND posicaoX = ?";
        String result = dao.applyCriteria(criteria, args);
        assertEquals(expResult, result);
        assertEquals(1, args.size());
    }
    
    /**
     * Test of applyCriteria method, of class EstatisticaDAO.
     */
    @Test
    public void testApplyCriteriaTwoCriteria() throws Exception {
        System.out.println("applyCriteria");
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstatisticaCriteria.POSX_EQ, 15L);
        criteria.put(EstatisticaCriteria.POSY_EQ, 15L);

        
        List<Object> args = new ArrayList<>();

        String expResult = " AND posicaoX = ? AND posicaoY = ?";
        String result = dao.applyCriteria(criteria, args);
        assertEquals(expResult, result);
        assertEquals(2, args.size());
    }
}
