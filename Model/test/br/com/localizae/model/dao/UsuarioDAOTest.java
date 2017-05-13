/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.TipoUsuario;
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
public class UsuarioDAOTest {
    
    Connection conn = null;
    UsuarioDAO dao = null;
    
    public UsuarioDAOTest() {
        
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
        dao = new UsuarioDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class UsuarioDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Usuario entity = new Usuario();
        entity.setSituacao(false);
        entity.setEmail("marcaosi2014@gmail.com");
        entity.setNome("Marcos Antônio");
        entity.setSenha("123456789");
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(1L);
        entity.setTipoUsuario(tipoUsuario);

        dao.create(conn, entity);
        
        Usuario usuario = dao.readById(conn, entity.getId());
        
        assertNotNull(usuario);
    }

    /**
     * Test of delete method, of class UsuarioDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long id = 1L;
        dao.delete(conn, id);
        
        Usuario usuario = dao.readById(conn, id);
        
        assertNull(usuario);
    }

    /**
     * Test of update method, of class UsuarioDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_EQ, "Marcos Antônio");
        List<Usuario> usuarioList = dao.readByCriteria(conn, criteria, 1L, 0L);
        Usuario entity = usuarioList.get(0);
        entity.setEmail("marcos@localizae.net.br");
        
        dao.update(conn, entity);
        
        Usuario newEntity = dao.readById(conn, entity.getId());
        
        assertEquals(entity, newEntity);
    }

    /**
     * Test of readById method, of class UsuarioDAO.
     */
    @Test
    public void testReadById() throws Exception {
        System.out.println("readById");
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_EQ, "Marcos Antônio");
        List<Usuario> usuarioList = dao.readByCriteria(conn, criteria, 1L, 0L);
        Usuario entity = usuarioList.get(0);
        Long id = entity.getId();
        
        Usuario result = dao.readById(conn, id);
        assertEquals(entity, result);
        
    }

    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadAll() throws Exception {
        System.out.println("readALL");
        
        Map<Enum, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        
        
        List<Usuario> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(4, result.size());
        
    }
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByEmail() throws Exception {
        System.out.println("readALL");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, "marcosantonio@localizae.net.br");
        Long limit = null;
        Long offset = null;
        
        
        List<Usuario> result = dao.readByCriteria(conn, criteria, limit, offset);
        
        assertEquals(1, result.size());
        
    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        System.out.println("readALL");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_EQ, "Marcos Antônio");
        Long limit = null;
        Long offset = null;
        
        
        List<Usuario> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(1, result.size());
        
    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadBySenha() throws Exception {
        System.out.println("readALL");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.SENHA_EQ, "123456789");
        Long limit = null;
        Long offset = null;
        
        
        List<Usuario> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(4, result.size());
        
    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByEmailESenha() throws Exception {
        System.out.println("readALL");
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, "marcosantonio@localizae.net.br");
        criteria.put(UsuarioCriteria.SENHA_EQ, "123456789");
        Long limit = null;
        Long offset = null;
        
        
        List<Usuario> result = dao.readByCriteria(conn, criteria, limit, offset);
        assertEquals(1, result.size());
        
    }  
}
