/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.EventoCriteria;
import br.com.localizae.model.entity.Evento;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ascom
 */
public class EventoDAOTest {

    private Connection conn;
    private EventoDAO dao;

    public EventoDAOTest() {
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
        dao = new EventoDAO();
    }

    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of create method, of class EventoDAO.
     */
    @Test
    public void testCreate() throws Exception {
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);

        Evento readById = dao.readById(conn, evento.getId());

        assertEquals(readById, evento);
    }

    /**
     * Test of delete method, of class EventoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);

        dao.delete(conn, evento.getId());

        Evento readById = dao.readById(conn, evento.getId());
        assertNull(readById);
    }

    /**
     * Test of update method, of class EventoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        /*Criando cenário de testes.*/
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);
        /*Criando cenário de testes.*/

        evento.setNome("Feira de Tecnologia da FAI - 2017");

        dao.update(conn, evento);

        Evento readById = dao.readById(conn, evento.getId());

        assertEquals(evento, readById);
    }

    /**
     * Test of readById method, of class EventoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        /*Criando cenário de testes.*/
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);
        /*Criando cenário de testes.*/

        Evento readById = dao.readById(conn, evento.getId());
        
        assertEquals(evento, readById);
    }

    /**
     * Test of readByCriteria method, of class EventoDAO.
     */
    @Test
    public void testReadAll() throws Exception {
        /*Criando cenário de testes.*/
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);
        
        Evento evento2 = new Evento();
        evento2.setNome("FAITEC 2016");
        evento2.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento2.setDataHoraEventoInicio(new Timestamp(2016, 10, 18, 19, 0, 0, 0));
        evento2.setDataHoraEventoFim(new Timestamp(2016, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento2);
        
        Evento evento3 = new Evento();
        evento3.setNome("FAITEC 2015");
        evento3.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento3.setDataHoraEventoInicio(new Timestamp(2015, 10, 18, 19, 0, 0, 0));
        evento3.setDataHoraEventoFim(new Timestamp(2015, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento3);
        /*Criando cenário de testes.*/
        
        List<Evento> list = dao.readByCriteria(conn, null, 0l, 0l);
        
        assertEquals(3, list.size());
        assertEquals(evento, list.get(0));
        assertEquals(evento2, list.get(1));
        assertEquals(evento3, list.get(2));
    }
    
    /**
     * Test of readByCriteria method, of class EventoDAO.
     */
    @Test
    public void testReadbyNome() throws Exception {
        /*Criando cenário de testes.*/
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);
        
        Evento evento2 = new Evento();
        evento2.setNome("FAITEC 2016");
        evento2.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento2.setDataHoraEventoInicio(new Timestamp(2016, 10, 18, 19, 0, 0, 0));
        evento2.setDataHoraEventoFim(new Timestamp(2016, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento2);
        
        Evento evento3 = new Evento();
        evento3.setNome("FAITEC 2015");
        evento3.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento3.setDataHoraEventoInicio(new Timestamp(2015, 10, 18, 19, 0, 0, 0));
        evento3.setDataHoraEventoFim(new Timestamp(2015, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento3);
        /*Criando cenário de testes.*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EventoCriteria.NOME_ILIKE, "FAITEC 2015");
        
        List<Evento> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(evento3, list.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class EventoDAO.
     */
    @Test
    public void testReadbyEndereco() throws Exception {
        /*Criando cenário de testes.*/
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento);
        
        Evento evento2 = new Evento();
        evento2.setNome("FAITEC 2016");
        evento2.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento2.setDataHoraEventoInicio(new Timestamp(2016, 10, 18, 19, 0, 0, 0));
        evento2.setDataHoraEventoFim(new Timestamp(2016, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento2);
        
        Evento evento3 = new Evento();
        evento3.setNome("FAITEC 2015");
        evento3.setEndereco("Alcidão Santa Rita do Sapucaí");
        evento3.setDataHoraEventoInicio(new Timestamp(2015, 10, 18, 19, 0, 0, 0));
        evento3.setDataHoraEventoFim(new Timestamp(2015, 10, 20, 23, 0, 0, 0));

        dao.create(conn, evento3);
        /*Criando cenário de testes.*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EventoCriteria.ENDERECO_ILIKE, "Alcidão Santa Rita do Sapucaí");
        
        List<Evento> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(3, list.size());
        assertEquals(evento, list.get(0));
        assertEquals(evento2, list.get(1));
        assertEquals(evento3, list.get(2));
    }
}
