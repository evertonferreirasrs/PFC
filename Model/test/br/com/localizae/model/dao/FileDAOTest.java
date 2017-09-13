/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.dao;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.criteria.FileCriteria;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
import br.com.localizae.model.entity.File;
import br.com.localizae.model.entity.Promocao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marca
 */
public class FileDAOTest {
    private Connection conn;
    private FileDAO dao;
    
    @Before
    public void setUp() throws Exception{
        conn = ConnectionManager.getInstance().getConnection();
        dao = new FileDAO();
    }
    
    @After
    public void tearDown() throws SQLException{
        conn.rollback();
        conn.close();
    }
    
    /*
     * Teste of create method
     *
    */
    @Test
    public void testCreateForEstande() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setEstande(estande);
        
        dao.create(conn, file);
        
        File readById = dao.readById(conn, file.getId());

        assertNotNull(readById);
    }
    
    /*
     * Test of create method
     *
    */
    @Test
    public void testCreateForPromocao() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        dao.create(conn, file);
        
        File readById = dao.readById(conn, file.getId());

        assertNotNull(readById);
    }
    
    /*
    * Test of delete method
    */
    @Test
    public void testDelete() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        dao.create(conn, file);
        
        dao.delete(conn, file.getId());
        
        File readById = dao.readById(conn, file.getId());
        
        assertNull(readById);
    }
    
    /*
    * Test of update method
    */
    @Test
    public void testUpdate() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        dao.create(conn, file);
        
        file.setUri("nomeDoArquivoModificado");
        
        dao.update(conn, file);
        
        File readById = dao.readById(conn, file.getId());
        
        assertEquals(file, readById);
    }
    
    /*
    * Test of readByCriteria method
    */
    @Test
    public void testReadByCriteria() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        File file2 = new File();
        file2.setUri("nomeDoArquivo2");
        file2.setEstande(estande);
        
        dao.create(conn, file);
        dao.create(conn, file2);
        
        List<File> readByCriteria = dao.readByCriteria(conn, null, 0l, 0l); 
        
        assertEquals(2, readByCriteria.size());
        assertEquals(file, readByCriteria.get(0));
        assertEquals(file2, readByCriteria.get(1));
    }
    
    /*
    * Test of readByCriteria method
    */
    @Test
    public void testReadByURI() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        File file2 = new File();
        file2.setUri("nomeDoasdfadArquivo2");
        file2.setEstande(estande);
        
        dao.create(conn, file);
        dao.create(conn, file2);
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(FileCriteria.URI_ILIKE, file.getUri());
        
        List<File> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l); 
        
        assertEquals(1, readByCriteria.size());
        assertEquals(file, readByCriteria.get(0));
//        assertEquals(file2, readByCriteria.get(1));
    }
    
    /*
    * Test of readByCriteria method
    */
    @Test
    public void testReadByEstande() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        File file2 = new File();
        file2.setUri("nomeDoasdfadArquivo2");
        file2.setEstande(estande);
        
        dao.create(conn, file);
        dao.create(conn, file2);
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(FileCriteria.ESTANDE_EQ, file2.getEstande().getId());
        
        List<File> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l); 
        
        assertEquals(1, readByCriteria.size());
        assertEquals(file2, readByCriteria.get(0));
//        assertEquals(file2, readByCriteria.get(1));
    }
    
    /*
    * Test of readByCriteria method
    */
    @Test
    public void testReadByPromocao() throws Exception{
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
        
        EstandeDAO estandeDAO = new EstandeDAO();
        estandeDAO.create(conn, estande);
        
        Promocao promocao = new Promocao();
        promocao.setDataHora(new Timestamp(2017, 10, 19, 19, 20,0,0));
        promocao.setDescricao("Description");
        promocao.setEstande(estande);
        promocao.setNome("Uma promocao");
        
        PromocaoDAO pdao = new PromocaoDAO();
        pdao.create(conn, promocao);
        
        File file = new File();
        file.setUri("nomeDoArquivo");
        file.setPromocao(promocao);
        
        File file2 = new File();
        file2.setUri("nomeDoasdfadArquivo2");
        file2.setEstande(estande);
        
        dao.create(conn, file);
        dao.create(conn, file2);
        
        Map<Enum, Object> criteria = new HashMap<>();
        criteria.put(FileCriteria.PROMO_EQ, file.getPromocao().getId());
        
        List<File> readByCriteria = dao.readByCriteria(conn, criteria, 0l, 0l); 
        
        assertEquals(1, readByCriteria.size());
        assertEquals(file, readByCriteria.get(0));
//        assertEquals(file2, readByCriteria.get(1));
    }
}
