/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.UsuarioCriteria;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.CriterioJurado;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
import br.com.localizae.model.entity.IntegranteEquipe;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
     * Teste para criar usuario do tipo administrador
     */
    @Test
    public void testCreateAdministrador() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo visitante
     */
    @Test
    public void testCreateVisitante() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo jurado
     */
    @Test
    public void testCreateJurado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        /*Criando cenário para testes*/
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Jurado");
        tipoUsuario.setId(4L);
        usuario.setTipoUsuario(tipoUsuario);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        /*Criando cenário para testes*/
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        usuario.setCriterioAvaliacaoList(criterioAvaliacaoList);
        
        dao.create(conn, usuario);
        Usuario userRead = dao.readById(conn, usuario.getId());
        
        assertNotNull(userRead);
        assertEquals(usuario, userRead);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para criar usuario do tipo expositor
     */
    @Test
    public void testCreateExpositor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        /*Criando cenário para testes*/
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Expositor");
        tipoUsuario.setId(3L);
        usuario.setTipoUsuario(tipoUsuario);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        /*Criando cenário para testes*/
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(usuario);
        
        usuario.setIntegranteEquipe(integrante);
        
        dao.create(conn, usuario);
        Usuario userRead = dao.readById(conn, usuario.getId());
        
        assertNotNull(userRead);
        assertEquals(usuario, userRead);
    }

    /**
     * Test of delete method, of class UsuarioDAO.
     * Teste para deletar Usuario
     */
    @Test
    public void testDelete() throws Exception {
        /*Criando cenário para testes*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        dao.delete(conn, usuario.getId());
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertNull(usuarioLido);
    }

    /**
     * Test of update method, of class UsuarioDAO.
     * Teste para atualizar usuário administrador
     */
    @Test
    public void testUpdateAdministrador() throws Exception {
        /*Criando cenário para testes*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        usuario.setNome("Marcos Antônio");
        dao.update(conn, usuario);
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of update method, of class UsuarioDAO.
     * teste para atualizar usuário visitante
     */
    @Test
    public void testUpdateVisitante() throws Exception {
        /*Criando cenário para testes*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        usuario.setNome("Marcos Antônio");
        dao.update(conn, usuario);
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para atualizar usuario do tipo jurado
     */
    @Test
    public void testUpdateJurado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        /*Criando cenário para testes*/
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Jurado");
        tipoUsuario.setId(4L);
        usuario.setTipoUsuario(tipoUsuario);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        usuario.setCriterioAvaliacaoList(criterioAvaliacaoList);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        usuario.setNome("Marcos Antônio");
        usuario.getCriterioAvaliacaoList().remove(0);
        dao.update(conn, usuario);
        
        Usuario userRead = dao.readById(conn, usuario.getId());
        
        assertNotNull(userRead);
        assertEquals(usuario, userRead);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para atualizar usuario do tipo expositor
     */
    @Test
    public void testUpdateExpositor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        /*Criando cenário para testes*/
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Expositor");
        tipoUsuario.setId(3L);
        usuario.setTipoUsuario(tipoUsuario);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(usuario);
        
        usuario.setIntegranteEquipe(integrante);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        usuario.setNome("Marcos Antônio");
        integrante.setResponsavel(false);
        dao.update(conn, usuario);
        
        Usuario userRead = dao.readById(conn, usuario.getId());
        
        assertNotNull(userRead);
        assertEquals(usuario, userRead);
    }

    /**
     * Test of readById method, of class UsuarioDAO.
     * Teste de ler usuário do tipo administrador pelo id
     */
    @Test
    public void testReadAdministradorById() throws Exception {
        /*Criando cenário de testes.*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Administrador");
        tipoUsuario.setId(1L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        /*Criando cenário de testes.*/
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para ler usuario do tipo visitante pelo Id
     */
    @Test
    public void testReadByVisitanteId() throws Exception {
        /*Cirando cenário para testes.*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Visitante");
        tipoUsuario.setId(2L);
        
        usuario.setTipoUsuario(tipoUsuario);
        
        dao.create(conn, usuario);
        /*Cirando cenário para testes.*/
        
        Usuario usuarioLido = dao.readById(conn, usuario.getId());
        
        assertEquals(usuario, usuarioLido);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para ler usuario do tipo jurado pelo Id
     */
    @Test
    public void testJuradoById() throws Exception {
        /*Criando cenário para testes*/
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Jurado");
        tipoUsuario.setId(4L);
        usuario.setTipoUsuario(tipoUsuario);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        usuario.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, usuario);
        Usuario userRead = dao.readById(conn, usuario.getId());
        
        assertNotNull(userRead);
        assertEquals(usuario, userRead);
    }
    
    /**
     * Test of create method, of class UsuarioDAO.
     * Teste para ler usuario do tipo expositor pelo Id
     */
    @Test
    public void testReadExpositorById() throws Exception {
        /*Criando cenário para testes*/        
        Usuario usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcos@localizae.net.br");
        usuario.setSenha("123456");
        usuario.setSituacao("ativo");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome("Expositor");
        tipoUsuario.setId(3L);
        usuario.setTipoUsuario(tipoUsuario);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(usuario);
        
        usuario.setIntegranteEquipe(integrante);
        
        dao.create(conn, usuario);
        /*Criando cenário para testes*/
        
        Usuario userRead = dao.readById(conn, usuario.getId());
        
        assertNotNull(userRead);
        assertEquals(usuario, userRead);
    }

    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadAll() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("123456");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("123456");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        
        /*Criando cenário para testes.*/
        
        List<Usuario> list = dao.readByCriteria(conn, null, 0l, 0l);
        
        assertEquals(4, list.size());
        assertEquals(administrador, list.get(0));
        assertEquals(visitante, list.get(1));
        assertEquals(expositor, list.get(2));
        assertEquals(jurado, list.get(3));
    }
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByEmail() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("123456");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("123456");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, "everton@localizae.br");
        
        List<Usuario> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(jurado, list.get(0));
    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByNome() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("123456");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("123456");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.NOME_EQ, "Marcos");
        
        List<Usuario> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(administrador, list.get(0));
    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadBySenha() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("1234568");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456789");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("12345678");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("1234567");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.SENHA_EQ, visitante.getSenha());
        
        List<Usuario> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(visitante, list.get(0));
    }  
    
    /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByEmailESenha() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("12345678");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456789");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("1234567");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.EMAIL_EQ, "everton@localizae.br");
        criteria.put(UsuarioCriteria.SENHA_EQ, jurado.getSenha());
        
        List<Usuario> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(1, list.size());
        assertEquals(jurado, list.get(0));
    }
    
        /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadBySituacao() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("12345678");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456789");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("1234567");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.SITUACAO_ILIKE, "ativo");
        
        List<Usuario> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(4, list.size());
        assertEquals(administrador, list.get(0));
        assertEquals(visitante, list.get(1));
        assertEquals(expositor, list.get(2));
        assertEquals(jurado, list.get(3));
    }
    
     /**
     * Test of readByCriteria method, of class UsuarioDAO.
     */
    @Test
    public void testReadByTipoUsuario_FK() throws Exception {
        /*Criando cenário para testes.*/
        //Criar tipos de usuário
        TipoUsuario tipoAdm = new TipoUsuario();
        TipoUsuario tipoVisitante = new TipoUsuario();
        TipoUsuario tipoExpositor = new TipoUsuario();
        TipoUsuario tipoJurado = new TipoUsuario();
        
        tipoAdm.setNome("Administrador");
        tipoAdm.setId(1l);
        
        tipoVisitante.setNome("Visitante");
        tipoVisitante.setId(2l);
        
        tipoExpositor.setNome("Expositor");
        tipoExpositor.setId(3l);
        
        tipoJurado.setNome("Jurado");
        tipoJurado.setId(4l);
        
        //Criar usuário administrador
        Usuario administrador = new Usuario();
        administrador.setNome("Marcos");
        administrador.setEmail("marcaosi2014@localizae.br");
        administrador.setSenha("12345678");
        administrador.setSituacao("ativo");
        administrador.setTipoUsuario(tipoAdm);
        dao.create(conn, administrador);
        
        //Criar usuário visitante
        Usuario visitante = new Usuario();
        visitante.setNome("Lyan");
        visitante.setEmail("lyan@localizae.br");
        visitante.setSenha("123456789");
        visitante.setSituacao("ativo");
        visitante.setTipoUsuario(tipoVisitante);
        dao.create(conn, visitante);
        
        //Criar usuário expositor
        Usuario expositor = new Usuario();
        expositor.setNome("Roberto");
        expositor.setEmail("roberto@localizae.br");
        expositor.setSenha("1234567");
        expositor.setSituacao("ativo");
        expositor.setTipoUsuario(tipoExpositor);
        
        Estande estande = new Estande();
        estande.setAreaTematica("Geolocalização");
        estande.setCurso("Sistemas de Informação");
        estande.setDescricao("Trabalho sobre como se localizar através de aplicativos desenvolvido para a FAITEC");
        estande.setNumero(40L);
        estande.setPeriodo(7L);
        estande.setTitulo("LocalizaÊ");
        
        Evento evento = new Evento();
        evento.setNome("FAITEC 2017");
        evento.setEndereco("Alcidão");
        evento.setDataHoraEventoInicio(new Timestamp(2017, 10, 18, 19, 0, 0, 0));
        evento.setDataHoraEventoFim(new Timestamp(2017, 10, 20, 23, 0, 0, 0));
        
        estande.setEvento(evento);
        
        EventoDAO evdao = new EventoDAO();
        evdao.create(conn, evento);
        
        EstandeDAO edao = new EstandeDAO();
        edao.create(conn, estande);
        
        
        IntegranteEquipe integrante = new IntegranteEquipe();
        integrante.setEstande(estande);
        integrante.setResponsavel(true);
        integrante.setUsuario(expositor);
        
        expositor.setIntegranteEquipe(integrante);
        
        dao.create(conn, expositor);
        
        //Criar usuário jurado
        Usuario jurado = new Usuario();
        jurado.setNome("Everton");
        jurado.setEmail("everton@localizae.br");
        jurado.setSenha("123456");
        jurado.setSituacao("ativo");
        jurado.setTipoUsuario(tipoJurado);
        
        CriterioAvaliacao criterio = new CriterioAvaliacao();
        criterio.setNome("Documentação");
        criterio.setPeso(4L);
        
        CriterioAvaliacaoDAO cdao = new CriterioAvaliacaoDAO();
        cdao.create(conn, criterio);
        
        CriterioJurado criterioJurado = new CriterioJurado();
        criterioJurado.setCriterioAvaliacao(criterio);
        criterioJurado.setEstande(estande);
        
        List<CriterioJurado> criterioAvaliacaoList = new ArrayList<>();
        criterioAvaliacaoList.add(criterioJurado);
        
        jurado.setCriterioAvaliacaoList(criterioAvaliacaoList);
        /*Criando cenário para testes*/
        
        dao.create(conn, jurado);
        /*Criando cenário para testes.*/
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(UsuarioCriteria.TIPO_USUARIO_EQ, 1l);
        
        List<Usuario> list = dao.readByCriteria(conn, criteria, 0l, 0l);
        
        assertEquals(4, list.size());
        assertEquals(administrador, list.get(0));
    }
}
