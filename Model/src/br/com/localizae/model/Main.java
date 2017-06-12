/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model;

import br.com.localizae.model.dao.CriterioAvaliacaoDAO;
import br.com.localizae.model.dao.EstandeDAO;
import br.com.localizae.model.dao.EventoDAO;
import br.com.localizae.model.dao.UsuarioDAO;
import br.com.localizae.model.entity.CriterioAvaliacao;
import br.com.localizae.model.entity.CriterioJurado;
import br.com.localizae.model.entity.Estande;
import br.com.localizae.model.entity.Evento;
import br.com.localizae.model.entity.IntegranteEquipe;
import br.com.localizae.model.entity.TipoUsuario;
import br.com.localizae.model.entity.Usuario;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marca
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Connection conn = ConnectionManager.getInstance().getConnection();
        UsuarioDAO dao = new UsuarioDAO();
        
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
        
        System.out.println(list);
        
        conn.commit();
        conn.close();
    }
}
