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
import br.com.localizae.model.entity.Evento;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);

        /*Criando cenario para testes.*/
        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        dao.create(conn, avaliacao);

        AvaliacaoVisitante readById = dao.readById(conn, avaliacao.getId());

        assertEquals(avaliacao, readById);
    }

    /**
     * Test of delete method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testDelete() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        dao.create(conn, avaliacao);
        /*Criando cenario para testes.*/

        dao.delete(conn, avaliacao.getId());

        AvaliacaoVisitante readById = dao.readById(conn, avaliacao.getId());

        assertNull(readById);
    }

    /**
     * Test of update method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        dao.create(conn, avaliacao);
        /*Criando cenario para testes.*/

        avaliacao.setNota(3l);
        dao.update(conn, avaliacao);

        AvaliacaoVisitante readById = dao.readById(conn, avaliacao.getId());

        assertEquals(avaliacao, readById);
    }

    /**
     * Test of readById method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadById() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        dao.create(conn, avaliacao);
        /*Criando cenario para testes.*/
        AvaliacaoVisitante readById = dao.readById(conn, avaliacao.getId());

        assertEquals(avaliacao, readById);
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadAll() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estandes
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localizacao");
        estande2.setCurso("Sistemas de Informacão");
        estande2.setDescricao("Descricao do estande2");
        estande2.setEvento(evento);
        estande2.setNumero(46l);
        estande2.setPeriodo(6l);
        estande2.setTitulo("PareFacil");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        esdao.create(conn, estande2);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        AvaliacaoVisitante avaliacao2 = new AvaliacaoVisitante();
        avaliacao2.setEstande(estande2);
        avaliacao2.setNota(2l);
        avaliacao2.setOpiniao("Trabalho mais ou menos.");
        avaliacao2.setUsuario(visitante);

        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenario para testes.*/
        List<AvaliacaoVisitante> list = dao.readByCriteria(conn, null, 0l, 0l);

        assertEquals(2, list.size());
        assertEquals(avaliacao, list.get(0));
        assertEquals(avaliacao2, list.get(1));
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByUsuario() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        Usuario visitante2 = new Usuario();
        visitante2.setNome("Everton");
        visitante2.setEmail("everton@localizae.br");
        visitante2.setSenha("123456");
        visitante2.setSituacao("ativo");
        visitante2.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);
        udao.create(conn, visitante2);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estandes
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localizacao");
        estande2.setCurso("Sistemas de Informacão");
        estande2.setDescricao("Descricao do estande2");
        estande2.setEvento(evento);
        estande2.setNumero(46l);
        estande2.setPeriodo(6l);
        estande2.setTitulo("PareFacil");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        esdao.create(conn, estande2);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        AvaliacaoVisitante avaliacao2 = new AvaliacaoVisitante();
        avaliacao2.setEstande(estande2);
        avaliacao2.setNota(2l);
        avaliacao2.setOpiniao("Trabalho mais ou menos.");
        avaliacao2.setUsuario(visitante2);

        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenario para testes.*/

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.USUARIO_EQ, visitante2.getId());

        List<AvaliacaoVisitante> list = dao.readByCriteria(conn, criteria, 0l, 0l);

        assertEquals(1, list.size());
        assertEquals(avaliacao2, list.get(0));
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        Usuario visitante2 = new Usuario();
        visitante2.setNome("Everton");
        visitante2.setEmail("everton@localizae.br");
        visitante2.setSenha("123456");
        visitante2.setSituacao("ativo");
        visitante2.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);
        udao.create(conn, visitante2);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estandes
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localizacao");
        estande2.setCurso("Sistemas de Informacão");
        estande2.setDescricao("Descricao do estande2");
        estande2.setEvento(evento);
        estande2.setNumero(46l);
        estande2.setPeriodo(6l);
        estande2.setTitulo("PareFacil");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        esdao.create(conn, estande2);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        AvaliacaoVisitante avaliacao2 = new AvaliacaoVisitante();
        avaliacao2.setEstande(estande2);
        avaliacao2.setNota(2l);
        avaliacao2.setOpiniao("Trabalho mais ou menos.");
        avaliacao2.setUsuario(visitante2);

        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenario para testes.*/

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.ESTANDE_EQ, estande.getId());

        List<AvaliacaoVisitante> list = dao.readByCriteria(conn, criteria, 0l, 0l);

        assertEquals(1, list.size());
        assertEquals(avaliacao, list.get(0));
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testReadByNota() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        Usuario visitante2 = new Usuario();
        visitante2.setNome("Everton");
        visitante2.setEmail("everton@localizae.br");
        visitante2.setSenha("123456");
        visitante2.setSituacao("ativo");
        visitante2.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);
        udao.create(conn, visitante2);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estandes
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localizacao");
        estande2.setCurso("Sistemas de Informacão");
        estande2.setDescricao("Descricao do estande2");
        estande2.setEvento(evento);
        estande2.setNumero(46l);
        estande2.setPeriodo(6l);
        estande2.setTitulo("PareFacil");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        esdao.create(conn, estande2);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        AvaliacaoVisitante avaliacao2 = new AvaliacaoVisitante();
        avaliacao2.setEstande(estande2);
        avaliacao2.setNota(2l);
        avaliacao2.setOpiniao("Trabalho mais ou menos.");
        avaliacao2.setUsuario(visitante2);

        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenario para testes.*/

        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoVisitanteCriteria.NOTA_EQ, 2l);

        List<AvaliacaoVisitante> list = dao.readByCriteria(conn, criteria, 0l, 0l);

        assertEquals(1, list.size());
        assertEquals(avaliacao2, list.get(0));
    }

    /**
     * Test of calcularMediaDeNotas method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotas() throws Exception {
        /*Criando cenario para testes.*/
        //Criando tipo Usuario
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2l);

        //Criando Usuario
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoUsuario);

        Usuario visitante2 = new Usuario();
        visitante2.setNome("Everton");
        visitante2.setEmail("everton@localizae.br");
        visitante2.setSenha("123456");
        visitante2.setSituacao("ativo");
        visitante2.setTipoUsuario(tipoUsuario);

        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, visitante);
        udao.create(conn, visitante2);

        //Criando evento
        Evento evento = new Evento();
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim);
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio);
        evento.setEndereco("Alcidao");
        evento.setNome("FAITEC 2017");

        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);

        //Criando estandes
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacão");
        estande.setDescricao("Descricao do estande");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");

        Estande estande2 = new Estande();
        estande2.setAreaTematica("Localizacao");
        estande2.setCurso("Sistemas de Informacão");
        estande2.setDescricao("Descricao do estande2");
        estande2.setEvento(evento);
        estande2.setNumero(46l);
        estande2.setPeriodo(6l);
        estande2.setTitulo("PareFacil");

        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        esdao.create(conn, estande2);

        AvaliacaoVisitante avaliacao = new AvaliacaoVisitante();
        avaliacao.setEstande(estande);
        avaliacao.setNota(5l);
        avaliacao.setOpiniao("Muito bom, gostei.");
        avaliacao.setUsuario(visitante);

        AvaliacaoVisitante avaliacao2 = new AvaliacaoVisitante();
        avaliacao2.setEstande(estande2);
        avaliacao2.setNota(2l);
        avaliacao2.setOpiniao("Trabalho mais ou menos.");
        avaliacao2.setUsuario(visitante2);

        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenario para testes.*/
        Map<Long, Long> medias = dao.calcularMediaDeNotas(conn);

        assertEquals(2, medias.size());
        assertEquals(5l, (long) medias.get(estande.getId()));
        assertEquals(2l, (long) medias.get(estande2.getId()));
    }
}
