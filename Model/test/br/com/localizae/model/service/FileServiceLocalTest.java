/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model.service;

import br.com.localizae.model.ConnectionManager;
import br.com.localizae.model.dao.EstandeDAO;
import br.com.localizae.model.dao.EventoDAO;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
import br.com.localizae.model.entity.File;
import br.com.localizae.model.utils.Constantes;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class FileServiceLocalTest {
    private FileServiceLocal instance;
    private Connection conn;
    public FileServiceLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        instance = new FileServiceLocal();
        conn = ConnectionManager.getInstance().getConnection();
    }
    
    @After
    public void tearDown() throws SQLException {
        conn.rollback();
        conn.close();
    }

    /**
     * Test of upload method, of class FileServiceLocal.
     */
    @Test
    public void testUpload() throws Exception {
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
        conn.commit();
        
        byte[] expectedRead = new byte[] { (byte) 125, (byte) 126, (byte) 127};
        
        File file = new File();
        file.setUri("NomedoArquivo");
        file.setEstande(estande);
        file.setFile(expectedRead);
        
        instance.upload(file);
        
        FileInputStream fis = new FileInputStream(Constantes.PATH_FILE + file.getUri());
        byte[] fileContent = new byte[] {Byte.parseByte(String.valueOf(fis.read())), Byte.parseByte(String.valueOf(fis.read())), Byte.parseByte(String.valueOf(fis.read()))};
        
        assertEquals(expectedRead[0], fileContent[0]);
        assertEquals(expectedRead[1], fileContent[1]);
        assertEquals(expectedRead[2], fileContent[2]);
        
        
        estandeDAO.delete(conn, estande.getId());
        conn.commit();
    }

    /**
     * Test of download method, of class FileServiceLocal.
     */
    @Test
    public void testDownload_String() throws Exception {
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
        conn.commit();
        
        byte[] expectedRead = new byte[] { (byte) 125, (byte) 126, (byte) 127};
        
        File file = new File();
        file.setUri("NomedoArquivo");
        file.setEstande(estande);
        file.setFile(expectedRead);
        
        instance.upload(file);
        
        byte[] result = instance.download(file.getUri());
        
        assertArrayEquals(expectedRead, result);
        
        estandeDAO.delete(conn, estande.getId());
        conn.commit();
    }

    /**
     * Test of download method, of class FileServiceLocal.
     */
    @Test
    public void testDownload_Long() throws Exception {
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
        conn.commit();
        
        byte[] expectedRead = new byte[] { (byte) 125, (byte) 126, (byte) 127};
        
        File file = new File();
        file.setUri("NomedoArquivo");
        file.setEstande(estande);
        file.setFile(expectedRead);
        
        instance.upload(file);
        
        byte[] result = instance.download(file.getId());
        
        assertArrayEquals(expectedRead, result);
        
        estandeDAO.delete(conn, estande.getId());
        conn.commit();
    }
    
}
