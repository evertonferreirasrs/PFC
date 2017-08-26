/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.EstandeCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        /*Criando cenário para testes*/
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande readById = dao.readById(conn, estande.getId());
        
        assertNotNull(readById);
    }

    /**
     * Test of delete method, of class EstandeDAO.
     */
    @Test
    public void testDelete() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        /*Criando cenário para testes*/
        
        dao.delete(conn, estande.getId());
        
        Estande readById = dao.readById(conn, estande.getId());
        
        assertNull(readById);
    }

    /**
     * Test of update method, of class EstandeDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        estande.setEquipe(new ArrayList<>());
        
        dao.create(conn, estande);
        /*Criando cenário para testes*/
        
        estande.setTitulo("LocalizaÊ - Sistema de Posicionamento para FAITEC");
        
        dao.update(conn, estande);
        
        Estande readById = dao.readById(conn, estande.getId());
        
        assertEquals(estande, readById);
    }

    /**
     * Test of readById method, of class EstandeDAO.
     */
    @Test
    public void testReadById() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        /*Criando cenário para testes*/
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande readById = dao.readById(conn, estande.getId());
        
        assertEquals(estande, readById);
    }

    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localização Master");
        estande2.setCurso("Sistemas de Informação");
        estande2.setDescricao("Descrição qualquer Master Pop");
        estande2.setEvento(evento);
        estande2.setNumero(47l);
        estande2.setPeriodo(8l);
        estande2.setTitulo("LocalizaMaster");
        
        dao.create(conn, estande2);
        /*Criando cenário para testes*/
        List<Estande> readByCriteria = dao.readByCriteria(conn, null, 0l, 0l);
        
        assertEquals(2, readByCriteria.size());
        assertEquals(estande, readByCriteria.get(0));
        assertEquals(estande2, readByCriteria.get(1));
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localização Master");
        estande2.setCurso("Sistemas de Informação");
        estande2.setDescricao("Descrição qualquer Master Pop");
        estande2.setEvento(evento);
        estande2.setNumero(47l);
        estande2.setPeriodo(8l);
        estande2.setTitulo("LocalizaMaster");
        
        dao.create(conn, estande2);
        /*Criando cenário para testes*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.TITULO_EQ, estande.getTitulo());
        List<Estande> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, readByCriteria.size());
        assertEquals(estande, readByCriteria.get(0));
        
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByCurso() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localização Master");
        estande2.setCurso("Sistemas de Informação");
        estande2.setDescricao("Descrição qualquer Master Pop");
        estande2.setEvento(evento);
        estande2.setNumero(47l);
        estande2.setPeriodo(8l);
        estande2.setTitulo("LocalizaMaster");
        
        dao.create(conn, estande2);
        /*Criando cenário para testes*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.CURSO_EQ, estande.getCurso());
        List<Estande> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(2, readByCriteria.size());
        assertEquals(estande, readByCriteria.get(0));
        assertEquals(estande2, readByCriteria.get(1));
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByPeriodo() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localização Master");
        estande2.setCurso("Sistemas de Informação");
        estande2.setDescricao("Descrição qualquer Master Pop");
        estande2.setEvento(evento);
        estande2.setNumero(47l);
        estande2.setPeriodo(8l);
        estande2.setTitulo("LocalizaMaster");
        
        dao.create(conn, estande2);
        /*Criando cenário para testes*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.PERIODO_EQ, estande.getPeriodo());
        List<Estande> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, readByCriteria.size());
        assertEquals(estande, readByCriteria.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByNumero() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localização Master");
        estande2.setCurso("Sistemas de Informação");
        estande2.setDescricao("Descrição qualquer Master Pop");
        estande2.setEvento(evento);
        estande2.setNumero(47l);
        estande2.setPeriodo(8l);
        estande2.setTitulo("LocalizaMaster");
        
        dao.create(conn, estande2);
        /*Criando cenário para testes*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.NUMERO_EQ, estande.getNumero());
        List<Estande> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, readByCriteria.size());
        assertEquals(estande, readByCriteria.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class EstandeDAO.
     */
    @Test
    public void testReadByAreaTematica() throws Exception {
        /*Criando cenário para testes*/
        Evento evento = new Evento();
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 23, 23, 20, 0, 0).getTime());
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 19, 19, 20, 0, 0).getTime());
        evento.setEndereco("Alcidão");
        evento.setNome("FAITEC - 2017");
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        
        Estande estande = new Estande();
        estande.setAreaTematica("Localização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Descrição qualquer");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(7l);
        estande.setTitulo("LocalizaÊ");
        
        dao.create(conn, estande);
        
        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localização Master");
        estande2.setCurso("Sistemas de Informação");
        estande2.setDescricao("Descrição qualquer Master Pop");
        estande2.setEvento(evento);
        estande2.setNumero(47l);
        estande2.setPeriodo(8l);
        estande2.setTitulo("LocalizaMaster");
        
        dao.create(conn, estande2);
        /*Criando cenário para testes*/
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(EstandeCriteria.AREATEMATICA_EQ, estande.getAreaTematica());
        List<Estande> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(2, readByCriteria.size());
        assertEquals(estande, readByCriteria.get(0));
        assertEquals(estande2, readByCriteria.get(1));
    }
    
    @Test
    public void testUpdatePartial()throws Exception{
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidao SRS");
        evento.setDataHoraEventoInicio(new Timestamp(2015, 10, 18, 19, 0, 0, 0).getTime());
        evento.setDataHoraEventoFim(new Timestamp(2015, 10, 20, 23, 0, 0, 0).getTime());
        
        EventoDAO daoEvento = new EventoDAO();
        
        daoEvento.create(conn, evento);
        
        Estande estande = new Estande();
        estande.setAreaTematica("area tematica");
        estande.setCurso("sistemas de informacao");
        estande.setDescricao("descricao");
        estande.setEvento(evento);
        estande.setNumero(48l);
        estande.setPeriodo(4l);
        estande.setTitulo("um projeto qualquer");
        
        dao.create(conn, estande);
        
        Estande newEstande = new Estande();
        newEstande.setId(estande.getId());
        newEstande.setTitulo("LocalizaE");
        
        dao.updatePartial(conn, newEstande);
        
        Estande readById = dao.readById(conn, estande.getId());
        
        assertEquals(newEstande.getTitulo(), readById.getTitulo());
    }
}
