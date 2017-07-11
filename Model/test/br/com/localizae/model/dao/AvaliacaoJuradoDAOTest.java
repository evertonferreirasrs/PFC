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
import br.com.localizae.model.entity.CriterioJurado;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        
        /*Criando cenário para testes.*/
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        
        AvaliacaoJurado readById = dao.readById(conn, avaliacao.getId());
        
        assertEquals(avaliacao, readById);
    }

    /**
     * Test of delete method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        /*Criando cenário para testes.*/
        
        dao.delete(conn, avaliacao.getId());
        
        AvaliacaoJurado readById = dao.readById(conn, avaliacao.getId());
        
        assertNull(readById);
    }

    /**
     * Test of update method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        /*Criando cenário para testes.*/
        
        avaliacao.setStatus("fechada");
        avaliacao.setDataHoraFechamento(new Timestamp(2017, 10, 27, 22, 0, 0, 0));
        
        dao.update(conn, avaliacao);
        
        AvaliacaoJurado readById = dao.readById(conn, avaliacao.getId());
        
        assertEquals(avaliacao, readById);
    }

    /**
     * Test of readById method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadById() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        /*Criando cenário para testes.*/
        AvaliacaoJurado readById = dao.readById(conn, avaliacao.getId());
        
        assertEquals(avaliacao, readById);
    }

    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(4l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        List<AvaliacaoJurado> list = dao.readByCriteria(conn, null, 0l, 0l);
        
        assertEquals(2, list.size());
        assertEquals(avaliacao, list.get(0));
        assertEquals(avaliacao2, list.get(1));
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByUsuario() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(4l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.USUARIO_EQ, jurado.getId());
        
        List<AvaliacaoJurado> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(avaliacao, list.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByNota() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(3l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.NOTA_EQ, avaliacao.getNota());
        
        List<AvaliacaoJurado> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(avaliacao, list.get(0));
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByEstande() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(3l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.ESTANDE_EQ, estande.getId());
        
        List<AvaliacaoJurado> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(2, list.size());
        assertEquals(avaliacao, list.get(0));
        assertEquals(avaliacao2, list.get(1));
    }
    
    /**
     * Test of readByCriteria method, of class AvaliacaoJuradoDAO.
     */
    @Test
    public void testReadByCriterio() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(3l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.CRITERIO_EQ, criterio.getId());
        
        List<AvaliacaoJurado> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(avaliacao, list.get(0));
    }
    
        /**
     * Test of calcularMediaDeNotas method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotas() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(3l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        Map<Long, Double> calcularMediaDeNotas = dao.calcularMediaDeNotas(conn, null);
        
        assertEquals(3.5, calcularMediaDeNotas.get(estande.getId()), 0.00000001);
    }
    
    /**
     * Test of calcularMediaDeNotasByCriterio method, of class AvaliacaoVisitanteDAO.
     */
    @Test
    public void testCalcularMediaDeNotasByCriterio() throws Exception {
        /*Criando cenário para testes.*/
        //Criterio Avaliacao
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Explanação");
        criterio.setPeso(4l);
        
        CriterioAvaliacao criterio2 = new CriterioAvaliacao();
        criterio2.setNome("Documentação");
        criterio2.setPeso(5l);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        cdao.create(conn, criterio2);
        
        //Evento
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        Timestamp dataHoraEventoFim = new Timestamp(2017, 10, 23, 19, 0, 0, 0);
        evento.setDataHoraEventoFim(dataHoraEventoFim.getTime());
        Timestamp dataHoraEventoInicio = new Timestamp(2017, 10, 27, 23, 0, 0, 0);
        evento.setDataHoraEventoInicio(dataHoraEventoInicio.getTime());
        
        EventoDAO edao = new EventoDAO();
        edao.create(conn, evento);
        
        //Estande
        Estande estande = new Estande();
        estande.setAreaTematica("Localizacao");
        estande.setCurso("Sistemas de Informacao");
        estande.setDescricao("Descricao do estande.");
        estande.setEvento(evento);
        estande.setNumero(45l);
        estande.setPeriodo(4l);
        estande.setTitulo("LocalizaE");
        
        EstandeDAO esdao = new EstandeDAO();
        esdao.create(conn, estande);
        
        //Tipo Usuario
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Jurado");
        tipo.setId(4l);
        
        //Usuario jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Marcos");
        jurado.setEmail("marcos@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipo);
        
        Usuario jurado2 = new Usuario();
        jurado2.setNome("Roberto");
        jurado2.setEmail("roberto@localizae.br");
        jurado2.setSenha("123456");
        jurado2.setSituacao("ativo");
        jurado2.setTipoUsuario(tipo);
        
        List<CriterioJurado> criterioJuradoList = new ArrayList<>();
        jurado.setCriterioAvaliacaoList(criterioJuradoList);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        criterioJurado.setUsuario(jurado);
        
        List<CriterioJurado> criterioJuradoList2 = new ArrayList<>();
        jurado2.setCriterioAvaliacaoList(criterioJuradoList2);
        
        CriterioJurado criterioJurado2 = new CriterioJurado();
        criterioJurado2.setCriterioAvaliacao(criterio2);
        criterioJurado2.setEstande(estande);
        criterioJurado2.setUsuario(jurado2);
        
        UsuarioDAO udao = new UsuarioDAO();
        udao.create(conn, jurado);
        udao.create(conn, jurado2);
        
        AvaliacaoJurado avaliacao = new AvaliacaoJurado();
        avaliacao.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao.setEstande(estande);
        avaliacao.setCriterio(criterio);
        avaliacao.setUsuario(jurado);
        avaliacao.setNota(4l);
        avaliacao.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao.setStatus("aberta");
        
        AvaliacaoJurado avaliacao2 = new AvaliacaoJurado();
        avaliacao2.setDataHoraAbertura(new Timestamp(2017,10,23,20,0,0,0));
        avaliacao2.setEstande(estande);
        avaliacao2.setCriterio(criterio2);
        avaliacao2.setUsuario(jurado2);
        avaliacao2.setNota(3l);
        avaliacao2.setOpiniao("Explanacao um pouco a desejar.");
        avaliacao2.setStatus("aberta");
        
        dao.create(conn, avaliacao);
        dao.create(conn, avaliacao2);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(AvaliacaoJuradoCriteria.CRITERIO_EQ, criterio.getId());
        
        Map<Long, Double> calcularMediaDeNotas = dao.calcularMediaDeNotas(conn, criteria);
        
        assertEquals(4.0, calcularMediaDeNotas.get(estande.getId()), 0.00000001);
    }
}
